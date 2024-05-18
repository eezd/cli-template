参考 Ruoyi-Vue, 但是删除了部门这个功能, 权限管理不使用`SS`, 后续文档会说明


## 使用

请先创建 `eezd-main/src/main/resources/application.yml` 文件, 按照个人需求修改完毕后, 再运行程序

如果不需要 mysql_ssl, 可以把 `useSSL=${ssl.config}` 改成 `useSSL=false`

```yaml
eezd:
  name: eezd
  version: 1.0.0
  # 文件上传路径
  profile: E:/Code/Workspace/aOnline/cli-template/springboot2-template/uploadPath
  # 获取ip地址开关
  addressEnabled: false
  # 验证码类型 math 数字计算 char 字符验证
  captchaType: math

server:
  port: 8081

ssl:
  cert:
    # path: file:E:/Code/ssl/
    path: classpath:mysql_ssl/
  config: true&
    verifyServerCertificate=true&
    requireSSL=true&
    clientCertificateKeyStoreUrl=${ssl.cert.path}keystore.jks&
    clientCertificateKeyStorePassword=123456&
    trustCertificateKeyStoreUrl=${ssl.cert.path}truststore.jks&
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
      autoReconnect=true&
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
    # 如果数据库my.cnf配置文件中wait_timeout和interactive_timeout的值小于该值
    # , 可能会报错 Communications link failure, 默认是8小时, 如果没改就不用管
    timeBetweenEvictionRunsMillis: 60000
    # 配置一个连接在池中最小生存的时间，单位是毫秒
    minEvictableIdleTimeMillis: 300000
    # 配置一个连接在池中最大生存的时间，单位是毫秒
    maxEvictableIdleTimeMillis: 900000
    # 配置检测连接是否有效
    validationQuery: SELECT 1;
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
  mvc:
    pathmatch:
      # 修复swagger3.0报错
      matching-strategy: ANT_PATH_MATCHER
  messages:
    # 国际化资源文件路径
    basename: i18n/messages
  redis:
    host: localhost
    port: 6379
    database: 1
    password: catchredis
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

token:
  header: Authorization
  secret: S5DVJ759CUZHAAYC97FHN3XA9CR99P3SFR3MMCSTFC49CUR2XMH9
  # 令牌有效期（默认30分钟）
  expireTime: 30

user:
  password:
    # 密码最大错误次数
    maxRetryCount: 5
    # 密码锁定时间（默认10分钟）
    lockTime: 10

mybatis-plus:
  type-aliases-package: com.eezd.**.domain
  mapper-locations: classpath*:mapper/*Mapper.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl

# 接口文档增强
knife4j:
  enabled: true
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


## 开发文档

- 主要分为两个部分
  - [System](md/system.md)
    - 对系统的构成和原理进行说明
  - [Utils](md/utils.md)
    - 对系统的使用进行说明