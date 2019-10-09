package org.ld.enums

/*
 * @author ld
 */
object ResponseMessageEnum extends Enumeration {
  Value(200, "操作成功")
  Value(400, "登录参数错误")
  Value(401, "用户名或密码错误")
  Value(403, "用户被禁止")
  Value(404, "找不到资源")
  Value(409, "业务逻辑异常")
  Value(422, "参数校验异常")
  Value(500, "服务器内部错误")
  Value(503, "Hystrix异常")
}