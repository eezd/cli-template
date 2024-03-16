package com.eezd.main;

import com.eezd.common.constant.CacheConstants;
import com.eezd.common.utils.RedisCache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.concurrent.TimeUnit;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MainApplicationTests {
    @Autowired
    private RedisCache redisCache;

    @Test
    void main() {
        // 缓存基本对象
        redisCache.setCacheObject("key", "value");

        // 20秒后过期
        redisCache.setCacheObject("key", "value", 20, TimeUnit.SECONDS);

        // 重新设置"key"的TTL为 60秒
        redisCache.expire("key", 60); // true=设置成功；false=设置失败

        // 重新设置"key"的TTL为 10 分钟
        redisCache.expire("key", 10, TimeUnit.MINUTES); // true=设置成功；false=设置失败

        // 获取 TTL
        redisCache.getExpire("key");

        // 判断 key 是否存在
        redisCache.hasKey("key"); // true 存在 false不存在

        // 获取缓存
        redisCache.getCacheObject("key");

        // 删除单个对象
        redisCache.deleteObject("key");

        // 批量删除key以"my_key:"开头的所有
        Collection<String> keys = redisCache.keys("my_key:" + "*");
        redisCache.deleteObject(keys);

    }

    @Test
    void order() {
        List<String> dataList = Arrays.asList("ListValue1", "ListValue2");
        long count = redisCache.setCacheList("list:key", dataList);
        System.out.println("List缓存数量：" + count);
        List<String> cachedList = redisCache.getCacheList("list:key");
        System.out.println("List缓存内容：" + cachedList);

        Set<String> dataSet = new HashSet<>(Arrays.asList("SetValue1", "SetValue2"));
        BoundSetOperations<String, String> setOperation = redisCache.setCacheSet("set:key", dataSet);
        System.out.println("Set缓存数据：" + setOperation.members());
        Set<String> cachedSet = redisCache.getCacheSet("set:key");
        System.out.println("Set缓存内容：" + cachedSet);

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("map:key1", "MapValue1");
        dataMap.put("map:key2", "MapValue2");
        redisCache.setCacheMap("map:key", dataMap);
        System.out.println("Map已缓存");
        Map<String, String> cachedMap = redisCache.getCacheMap("map:key");
        System.out.println("Map缓存内容：" + cachedMap);


        redisCache.setCacheMapValue("hash:key", "subKey1", "HashValue1");
        redisCache.setCacheMapValue("hash:key", "subKey2", "HashValue2");
        System.out.println("Hash数据已缓存");
        String hashValue = redisCache.getCacheMapValue("hash:key", "subKey1");
        System.out.println("Hash中的值：" + hashValue);
        List<String> hashValues = redisCache.getMultiCacheMapValue("hash:key", Arrays.asList("subKey1", "subKey2"));
        System.out.println("Hash中的多个值：" + hashValues);
        boolean deleted = redisCache.deleteCacheMapValue("hash:key", "subKey1");
        System.out.println("Hash数据已删除：" + deleted);

        Collection<String> cachedKeys = redisCache.keys("hash:*");
        System.out.println("缓存的键列表：" + cachedKeys);
    }
}

