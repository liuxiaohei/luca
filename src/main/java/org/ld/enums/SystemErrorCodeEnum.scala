package org.ld.enums

/*
 * @author ld
 * 系统级ErrorCode
 * https://tools.ietf.org/html/rfc7231#section-6.5.1
 */
object SystemErrorCodeEnum extends Enumeration {
  val UNKNOW = Value(-1,"未知异常")
  val PARAMS_INVALID = Value(1,"参数错误")
  val DATABASE_ACCESS_FAILED = Value(2,"数据库访问失败")
  val DATA_ACCESS_FAILED = Value(3,"数据访问失败")
  val SUCCESS = Value(200,"操作成功")
}
