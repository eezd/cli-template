





## Exception

### 抛出异常

- 通过 `MessageUtils.message` 方法可以获取 `i18n` 的值
- 需要注意: 你需要全局注册这个异常, 才可以使用(请看下面)

`common.exception.ServiceException`

```java
throw new ServiceException("业务异常",400);

throw new ServiceException("获取部门ID异常",HttpStatus.UNAUTHORIZED);

throw new ServiceException(MessageUtils.message("user.password.not.match"));

// 填充{0}的值: "允许的文件最大大小是：{0}MB"
throw new ServiceException(MessageUtils.message("pload.exceed.maxSize",new Object[]{defaultMaxSize}));
```

- 如果你不喜欢这种方式, 希望像下面这样抛出异常

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

`main.core.exception.GlobalExceptionHandler`

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



## 登录(Spring Security)

这里简单讲下流程

- 登录的 `controller` 👉`com.eezd.main.web.system.controller.SysLoginRegisterController`

- Spring Security的验证, Token的生成基本都在这目录下👉`com.eezd.main.core.service`

- `Spring Security` 配置文件👉`com.eezd.main.core.config.SecurityConfig`

### Spring Security验证流程

以下文件均在 `com.eezd.main.core.service` 目录
- 第一步: `SysLoginService.login()`
  - 创建 `Authentication` 身份验证数据
  - 准备调用 `authenticationManager` 去验证身份

```java
// 封装请求信息为 Authentication
UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
// 设置联系上下文, 后续方便SysPasswordService获取信息
SecurityContextHolder.getContext().setAuthentication(authenticationToken);
// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
authentication = authenticationManager.authenticate(authenticationToken);
```

- 第二步: `UserDetailsServiceImpl.loadUserByUsername()`
  - 验证用户是否存在
  - 验证用户密码
  - 添加用户权限, 添加用户角色
  - 最后返回用户数据 `return new LoginUser(user.getUserId(), user, permissions)`
  - (整个文件都是, 这里就不给代码了)

- 第三步: `SysLoginService.login()`
  - 获取刚刚返回的用户数据 `loginUser`
  - `recordLoginInfo(loginUser.getUserId());` 是记录登录时间到数据库
  - 最后生成 `createToken`

```java
LoginUser loginUser = (LoginUser) authentication.getPrincipal();
recordLoginInfo(loginUser.getUserId());

// 生成token (token+user数据会一同保存在redis里)
return tokenService.createToken(loginUser);
```



### TokenService

位置👉`com.eezd.main.core.service.TokenService`

- 注意: Token会和 `loginUser` 用户数据一起保存在 `redis` 里, 后续由 `JwtAuthenticationTokenFilter` 来获取用户数据, 验证 `token` 的有效性和其他的数据

- `createToken(LoginUser loginUser)`: 会在redis中创建 `loginUser` 用户信息
- `delLoginUser(String token)`: 而删除用户身份信息则是直接删除 `redis` 里面的对应数据

下面这份数据是创建令牌时保存在 `redis` 中的用户信息

```json
{
    "@type": "com.eezd.common.domain.LoginUser",
    "browser": "Unknown",
    "expireTime": 1715511674466,
    "ipaddr": "127.0.0.1",
    "loginLocation": "内网IP",
    "loginTime": 1715509874466,
    "os": "Unknown",
    "roles": [
        {
            "authority": "ROLE_admin"
        },
        {
            "authority": "system:user:query"
        },
        {
            "authority": "system:user:add"
        },
        {
            "authority": "system:user:edit"
        },
        {
            "authority": "system:user:remove"
        },
        {
            "authority": "system:user:export"
        },
        {
            "authority": "system:user:import"
        },
        {
            "authority": "system:user:resetPwd"
        },
        {
            "authority": "system:role:query"
        },
        {
            "authority": "system:role:add"
        },
        {
            "authority": "system:role:edit"
        },
        {
            "authority": "system:role:remove"
        },
        {
            "authority": "system:role:export"
        }
    ],
    "token": "34b1db63-79da-492c-9aa4-f2484ab08372",
    "user": {
        "avatar": "",
        "createBy": "admin",
        "createTime": "2023-12-17 17:16:16",
        "delFlag": "0",
        "email": "ry@163.com",
        "loginDate": "2024-05-12 15:55:27",
        "loginIp": "127.0.0.1",
        "nickName": "若依",
        "password": "$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2",
        "phonenumber": "15888888888",
        "remark": "管理员",
        "roleId": 1,
        "sex": "1",
        "status": "0",
        "updateBy": "",
        "updateTime": "2024-03-06 14:18:07",
        "userId": 1,
        "userName": "admin"
    },
    "userId": 1,
    "username": "admin"
}
```



### JWT过滤器

位置👉`com.eezd.main.core.security.filter.JwtAuthenticationTokenFilter`

- 该方法会在需要验证身份时, 获取请求的 `token` 并设置 `Authentication` 值, 这样我们就能知道该请求到底是哪个用户发出的
  - 注意, 这取决于你 `SecurityConfig` 设置的在什么情况下需要验证身份



### SecurityConfig

位置👉`com.eezd.main.core.config.SecurityConfig`

关于 Spring Security 配置, 详情看里面的注解, 都有解释



## 异步日志

位置👉`com.eezd.main.core.manager.AsyncManager`

功能是异步的向数据库写入日志, 在本项目中用于写入登录日志(`sys_logininfor`)和操作日志(`sys_oper_log`)

- 主要有两个方法
  - 第一个: `execute(TimerTask task)`
  - 第二个: `shutdown()`

- 下面这是写入操作日志的 TimerTask (`sys_oper_log`)
  - 位置👉`com.eezd.main.core.manager.factory.AsyncFactory`
  - 功能是: 向 `sys_oper_log` 添加日志

```java
/**
    * 操作日志记录
    *
    * @param operLog 操作日志信息
    * @return 任务task
    */
public static TimerTask recordOper(final SysOperLog operLog) {
    return new TimerTask() {
        @Override
        public void run() {
            // 远程查询操作地点
            operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
            SpringUtils.getBean(ISysOperLogService.class).insertOperlog(operLog);
        }
    };
}
```

用法

```java
// com.eezd.main.core.aspectj.LogAspect
AsyncManager.me().execute(AsyncFactory.recordOper(operLog));
```



### LogAspect

位置👉`com.eezd.main.core.aspectj.LogAspect`

位置👉`com.eezd.common.annotation.Log`

- 这个模块的功能是给操作添加日志记录

```java
@Log(title = "log-test", businessType = BusinessType.OTHER)
@PostMapping("/log-test")
@ApiOperation("log-test")
public AjaxResult testLog(@RequestParam(value = "psw") String psw) {
    // 重新设置默认账号的初始密码
    SysConfig sysConfig = new SysConfig();
    sysConfig.setConfigId(2L);
    sysConfig.setConfigValue(psw);

    int result = sysConfigMapper.updateById(sysConfig);

    return AjaxResult.success(result);
}
```

- 然后打开 `sys_oper_log` 表会发现下面有这么一条记录, 这就是 `@Log` 的用法了

```json
[
  {
    "oper_id": 1,
    "title": "log-test",
    "business_type": 0,
    "method": "com.eezd.main.web.HelloController.testLog()",
    "request_method": "POST",
    "operator_type": 1,
    "oper_name": "admin",
    "oper_url": "/log-test",
    "oper_ip": "127.0.0.1",
    "oper_location": "内网IP",
    "oper_param": "{\"psw\":\"123457\"}",
    "json_result": "{\"msg\":\"操作成功\",\"code\":200,\"data\":1}",
    "status": 0,
    "error_msg": "",
    "oper_time": "13/5/2024 13:04:23",
    "cost_time": 388
  }
]
```



### Anonymous匿名访问

位置👉`com.eezd.main.core.config.properties.PermitAllUrlProperties`

位置👉`com.eezd.common.annotation.Anonymous`

给 `Controller` 里面的方法加上, 那么就可以不需要登录就可以访问了

```java
@Anonymous
@GetMapping("/test")
@ApiOperation("test")
public AjaxResult test() {
    return AjaxResult.success("list");
}
```



## 其他

防XSS、、、、、