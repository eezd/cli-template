
该文档将会对, `eezd-common` 里面一些模块的用法进行说明

## Redis

- 下面是一些基本用法

`common.core.RedisCache.java`

```java

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, args = "--mpw.key=5b6226a1840474da")
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
```

> List缓存数量：2
> List缓存内容：[ListValue1, ListValue2]
> Set缓存数据：[SetValue1, SetValue2]
> Set缓存内容：[SetValue1, SetValue2]
> Map已缓存
> Map缓存内容：{map:key1=MapValue1, map:key2=MapValue2}
> Hash数据已缓存
> Hash中的值：HashValue1
> Hash中的多个值：[HashValue1, HashValue2]
> Hash数据已删除：true
> 缓存的键列表：[hash:key]

### FastJson序列化

`main.core.config.RedisConfig.java`

`main.core.config.FastJson2JsonRedisSerializer.java`

如果不开启, Redis 存入的数据就会变成, 虽然不影响读取, 但影响我看

```java
\xac\xed\x00\x05t\x00 key
```



## utils

### file

### html

- `common.utils.html.EscapeUtil.java`
    - 转义和反转义工具类

```java
public static void main(String[]args){
        String html="<script>alert(1);</script>";
        String escape=EscapeUtil.escape(html);
        System.out.println("clean: "+EscapeUtil.clean(html)); // alert(1);
        System.out.println("escape: "+escape); // %3c%73%63%72%69%70%74%3e%61%6c%65%72%74%28%31%29%3b%3c%2f%73%63%72%69%70%74%3e
        System.out.println("unescape: "+EscapeUtil.unescape(escape)); // <script>alert(1);</script>
        }
```

### http

- `common.utils.http.HttpUtils.java`
    - 用于发送GET/POST请求, 返回值是`String`类型

```java
// 向指定 URL 发送GET方法的请求
HttpUtils.sendGet("http://www.baidu.com");
// 带参数的GET请求
        HttpUtils.sendGet("http://www.baidu.com/s","wb=g");

// 向指定 URL 发送POST方法的请求
        HttpUtils.sendPost("http://www.baidu.com","wb=g");
// 带参数的SSL POST请求
        HttpUtils.sendSSLPost("https://www.baidu.com/s","wb=g");
```

- `common.utils.http.HttpHelper.java`
    - 从ServletRequest中获取请求体的内容，并以字符串的形式返回

```java
// 假设有一个 ServletRequest 对象
ServletRequest request= /* obtain ServletRequest instance */;

// 调用 HttpHelper 的 getBodyString 方法获取请求体内容
        String requestBody=HttpHelper.getBodyString(request);

// 打印请求体内容
        System.out.println("Request Body: "+requestBody);
```

### ip

- `common.utils.ip.IpUtils.java`
- `common.utils.ip.AddressUtils.java`
    - 常用大概就这些, 其他的看代码

```java
// 获取客户端IP
IpUtils.getIpAddr();

// 检查是否为内部IP地址
        IpUtils.internalIp("127.0.0.1");

// 获取本地主机IP地址
        IpUtils.getHostIp();

// 获取获取主机名
        IpUtils.getHostName();

// 根据IP地址获取地理位置
        AddressUtils.getRealAddressByIP("168.126.63.1");
```

### sign

- `common.utils.sign.Md5Utils.java`
- `common.utils.sign.Base64.java`
    - 一般将图片转化为`Base64`格式进行传输, 比如验证码
    - 具体用法请参考 `main.web.system.controller.SysLoginController.java`

```java
// 获取 MD5
Md5Utils.hash("123")
```

### text

- `common.utils.text.CharsetKit.java`
    - 字符集编码转换

```java
// 源编码: 默认ISO-8859-1, 目标编码: 默认UTF-8
CharsetKit.convert("转换文本","ISO-8859-1","UTF-8");

// 返回 系统字符集编码
        CharsetKit.systemCharset();
```

- `common.utils.text.StrFormatter.java`
    - 字符串格式化, 支持自动填充 `{}`

```java
// this is a for b
StrFormatter.format("this is {} for {}","a","b")

// 转义{}: this is \{} for a
        format("this is \\{} for {}","a","b")

// 转义\: this is \a for b
        format("this is \\\\{} for {}","a","b")
```

- `common.utils.text.Convert.java`

待编写

### uuid

- `common.utils.uuid.IdUtils.java`

```java
// 随机UUID, 7840f4d0-94a1-4f05-bcda-78c2e883e036
IdUtils.randomUUID();
// 去掉了横线的UUID, 7840f4d094a14f05bcda78c2e883e036
        IdUtils.randomUUID();
// 使用性能更好的ThreadLocalRandom生成UUID
        IdUtils.fastUUID();
// 去掉了横线
        IdUtils.fastSimpleUUID();
```

- `common.utils.uuid.Seq.java`
    - 一个序列的构成是由 `yyMMddHHmmss + 一位机器标识 + 3长度循环递增字符串`



