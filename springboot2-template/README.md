参考 Ruoyi-Vue, 但是删除了部门这个功能, 权限管理不使用`SS`, 后续文档会说明

# main

如果不需要 mysql_ssl, 可以把 `useSSL=${ssl.config}` 改成 `useSSL=false`

`eezd-main/src/main/resources/application.yml`

```yaml
# 项目相关配置
eezd:
  name: eezd
  version: 1.0.0
  # 文件路径
  profile: E:/Code/Workspace/aOnline/cli-template/springboot2-template/uploadPath
  # 获取ip地址开关
  addressEnabled: false
  # 验证码类型 math 数字计算 char 字符验证
  captchaType: math

# 应用服务 WEB 访问端口
server:
  port: 8081

ssl:
  cert:
    #path: file:E:/Code/ssl
    path: classpath:mysql_ssl/
  config: true&
    verifyServerCertificate=true&
    requireSSL=true&
    clientCertificateKeyStoreUrl=${ssl.cert.path}/keystore.jks&
    clientCertificateKeyStorePassword=123456&
    trustCertificateKeyStoreUrl=${ssl.cert.path}/truststore.jks&
    trustCertificateKeyStorePassword=123456

spring:
  datasource:
    url: jdbc:mysql://127.0.0.1:3306/eezd_sys?
      serverTimezone=Asia/Shanghai&
      useUnicode=true&
      characterEncoding=utf-8&
      zeroDateTimeBehavior=convertToNull&
      allowPublicKeyRetrieval=true&
      tinyInt1isBit=true&
      useSSL=${ssl.config}
    username: root
    password: root
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 初始连接数
    initialSize: 5
    # 最小连接池数量
    minIdle: 10
    # 最大连接池数量
    maxActive: 20
    # 配置获取连接等待超时的时间
    maxWait: 60000
    # 配置连接超时时间
    connectTimeout: 30000
    # 配置网络超时时间
    socketTimeout: 60000
    # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    timeBetweenEvictionRunsMillis: 60000
    # 配置一个连接在池中最小生存的时间，单位是毫秒
    minEvictableIdleTimeMillis: 300000
    # 配置一个连接在池中最大生存的时间，单位是毫秒
    maxEvictableIdleTimeMillis: 900000
    # 配置检测连接是否有效
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
  mvc:
    pathmatch:
      matching-strategy: ANT_PATH_MATCHER   # 修复swagger3.0报错
  # 资源信息
  messages:
    # 国际化资源文件路径
    basename: i18n/messages
  # redis 配置
  redis:
    # 地址
    host: localhost
    # 端口，默认为6379
    port: 6379
    # 数据库索引
    database: 1
    # 密码
    password: catchredis
    # 连接超时时间
    timeout: 10s
    lettuce:
      pool:
        # 连接池中的最小空闲连接
        min-idle: 0
        # 连接池中的最大空闲连接
        max-idle: 8
        # 连接池的最大数据库连接数
        max-active: 8
        # #连接池最大阻塞等待时间（使用负值表示没有限制）
        max-wait: -1ms

# token配置
token:
  # 令牌自定义标识
  header: Authorization
  # 令牌密钥
  secret: S5DVJ759CUZHAAYC97FHN3XA9CR99P3SFR3MMCSTFC49CUR2XMH9
  # 令牌有效期（默认30分钟）
  expireTime: 30

# 用户配置
user:
  password:
    # 密码最大错误次数
    maxRetryCount: 5
    # 密码锁定时间（默认10分钟）
    lockTime: 10

# MyBatis配置
mybatis:
  # 搜索指定包别名
  type-aliases-package: com.eezd.**.domain
  # 配置mapper的扫描，找到所有的mapper.xml映射文件
  mapper-locations: classpath*:mapper/*Mapper.xml
  configuration:
    # 日志
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

mybatis-plus:
  type-aliases-package: com.eezd.**.domain
  mapper-locations: classpath*:mapper/*Mapper.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

# PageHelper分页插件
pagehelper:
  helperDialect: mysql
  supportMethodsArguments: true
  params: count=countSql

swagger:
  enabled: true

knife4j:
  enable: true
  setting:
    language: zh_cn

# 防止XSS攻击
xss:
  # 过滤开关
  enabled: true
  # 排除链接（多个用逗号分隔）
  excludes: /system/notice
  # 匹配链接
  urlPatterns: /system/*,/monitor/*,/tool/*,/xss-test/*
```

生成JDBC用的证书, 请先确保你已经使用 openSSL 生成了证书

```sh
# 包含受信任的 CA 证书的存储库
keytool -importcert -alias Cacert -file ca.pem -keystore truststoremysql -storepass 123456

# 中间件, 用于导出和共享证书和私钥，以便在不同的系统和应用程序之间进行迁移和交换。
openssl pkcs12 -export -in client-cert.pem -inkey client-key.pem -name "mysqlclient" -passout pass:123456 -out client-keystore.p12

# 包含客户端的证书和私钥，用于进行客户端身份验证
keytool -importkeystore -srckeystore client-keystore.p12 -srcstoretype pkcs12 -srcstorepass 123456 -destkeystore keystoremysql -deststoretype JKS -deststorepass 123456
```

## Redis

- 下面是一些基本用法

`common.core.RedisCache.java`

```java

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

如果不开启存入的数据就会变成

```java
\xac\xed\x00\x05t\x00 key
```

## Exception

### 抛出异常

`common.exception.ServiceException.java`

- 通过 `MessageUtils.message` 方法可以获取 `i18n` 的值

```java
throw new ServiceException("业务异常",400);

        throw new ServiceException("获取部门ID异常",HttpStatus.UNAUTHORIZED);

        throw new ServiceException(MessageUtils.message("user.password.not.match"));

// 填充{0}的值: "允许的文件最大大小是：{0}MB"
        throw new ServiceException(MessageUtils.message("pload.exceed.maxSize",new Object[]{defaultMaxSize}));
```

- 如果你不喜欢这种方式, 希望像下面这样抛出异常
  -
  请参考: https://github.com/yangzongzhuan/RuoYi/tree/master/ruoyi-common/src/main/java/com/ruoyi/common/exception/user

```java
throw new UserPasswordNotMatchException();
```

- 也可以这样

```java
/**
 * 文件名称超长限制异常类
 */
public class FileSizeLimitExceededException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        throw new ServiceException(MessageUtils.message("pload.exceed.maxSize", new Object[]{defaultMaxSize}));
    }
}
```

### 全局异常拦截器

- 接下来我们需要在 **全局异常拦截器** 上进行异常拦截, 否则系统无法捕获我们抛出的异常
    - **需要注意**: 我们在拦截器上标记了**业务异常**, 否则系统无法拦截到我们自己抛出异常

这里面还有其他异常处理器这里不再一一赘述

`main.core.exception.GlobalExceptionHandler.java`

```java
/**
 * 业务异常
 */
@ExceptionHandler(ServiceException.class)
public AjaxResult handleServiceException(ServiceException e,HttpServletRequest request){
        log.error(e.getMessage(),e);
        Integer code=e.getCode();
        return StringUtils.isNotNull(code)?AjaxResult.error(code,e.getMessage()):AjaxResult.error(e.getMessage());
        }
```

# Spring Security

https://springdoc.cn/spring-security/servlet/getting-started.html

https://juejin.cn/post/7212616585768714299

https://www.codedemo.club/spring-security-create-new-custom-security-expression/

Spring Security 的两个核心功能：

- 身份认证（authentication），即验证用户身份的合法性，以判断用户能否登录。
- 授权（authorization），即验证用户是否有权限访问某些资源或者执行某些操作。

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    <version>3.1.5</version>
</dependency>
```

## 步骤

- Filter: `UsernamePasswordAuthenticationFilter`
    - 拦截Http请求，获取用户名和秘密等认证信息
- AuthenticationManager: `ProviderManager`
    - 从filter中获取认证信息，然后查找合适的AuthenticationProvider来发起认证流程
- AuthenticationProvider: `DaoAuthenticationProvider`
    - 调用UserDetailsService来查询已经保存的用户信息并与从http请求中获取的认证信息比对。如果成功则返回，否则则抛出异常。
- UserDetailsService: `InMemoryUserDetailsManager`
    - 负责获取用户保存的认证信息，例如查询数据库。

## 实现

- `SysLoginServiceImpl`
    - 我们先封装好 `UsernamePasswordAuthenticationToken`
    - 通过 `authenticationManager` 调用 `UserDetailsServiceImpl`

```java
// 封装请求信息为 Authentication
UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);

// 设置联系上下文, 后续方便SysPasswordService获取信息
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        authentication=authenticationManager.authenticate(authenticationToken);
```

- `UserDetailsServiceImpl`
    - 从数据库中获取这个用户的数据, 包括密码
    - 然后通过 `passwordService.validate` 验证密码
    - 最后返回用户数据

```java
@Override
public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        // 获取数据库 user
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",username);
        SysUser user=sysUserMapper.selectOne(queryWrapper);

        if(StringUtils.isNull(user)){
        log.info("登录用户：{} 不存在.",username);
        throw new ServiceException(MessageUtils.message("user.not.exists"));
        }else if(UserStatus.DELETED.getCode().equals(user.getDelFlag())){
        log.info("登录用户：{} 已被删除.",username);
        throw new ServiceException(MessageUtils.message("user.password.delete"));
        }else if(UserStatus.DISABLE.getCode().equals(user.getStatus())){
        log.info("登录用户：{} 已被停用.",username);
        throw new ServiceException(MessageUtils.message("user.blocked"));
        }

        // 传入 user 进行密码验证
        passwordService.validate(user);

        return createLoginUser(user);
        }

public UserDetails createLoginUser(SysUser user){
        return new LoginUser(user.getUserId(),user.getDeptId(),user);
        }
```

- `passwordService`
    - 其他内容跳过了,

> 上面内容中, 我们已经执行完 `authenticationManager` 了

- 最后, 我们根据信息生成token
    - 注意, 这个方法还会将数据存入 redis

```java
LoginUser loginUser=(LoginUser)authentication.getPrincipal();
// recordLoginInfo(loginUser.getUserId());
// 生成token
        return tokenService.createToken(loginUser);
```

# common

## file

## html

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

## http

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

## ip

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

## sign

- `common.utils.sign.Md5Utils.java`
- `common.utils.sign.Base64.java`
    - 一般将图片转化为`Base64`格式进行传输, 比如验证码
    - 具体用法请参考 `main.web.system.controller.SysLoginController.java`

```java
// 获取 MD5
Md5Utils.hash("123")
```

## text

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

## uuid

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



