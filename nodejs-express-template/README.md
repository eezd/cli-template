```sh
pnpm i

pnpm dev
```



- 特点
  -  `utils/mysql.js` And `utils/sqlite.js` 使用了 `new Promise` 方式封装
  - 自带 PUG 模板, 带简易版分页
  - 重新封装了拦截器

`app.js`

```js
// error handler
app.use(function (err, req, res, next) {
  // set locals, only providing error in development
  // res.locals.message = err.message;
  // res.locals.error = req.app.get("env") === "development" ? err : {};

  // 存在 err.code 说明是业务异常, 设置 HTTP 状态码为 200
  if (err.code) {
    res.status(200);
  } else {
    res.status(err.status || 500);
  }

  res.json({ msg: err.message || err, code: err.code || 500 });
  // res.render("error");
});
```



适合快速构建一些小项目, **本项目后续估计不会发生改动**
