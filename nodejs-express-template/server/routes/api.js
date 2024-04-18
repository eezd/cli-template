var express = require("express");
var router = express.Router();

const mysql = require("../utils/mysql");
const sqlite = require("../utils/sqlite");

router.get("/", async (req, res, next) => {
  res.render("api", { title: "Api" });
});

// http://localhost:3000/api/user?id=1
router.get("/user", async (req, res, next) => {
  const id = req.query.id;
  let rows = await sqlite.all(`SELECT id, name FROM user WHERE id = ?`, [id]);

  res.json({ data: rows });
});

module.exports = router;
