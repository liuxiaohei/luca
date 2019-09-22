package org.ld.enums

/*
 * @author ld
 * 错误码枚举
 */
object SystemErrorCodeEnum extends Enumeration {
  val UNKNOW = Value(-1,"未知异常")
  val SUCCESS = Value(200,"成功")
  val PARAMS_INVALID = Value(1,"参数错误")
  val DATABASE_ACCESS_FAILED = Value(2,"数据库访问失败")
  val DATA_ACCESS_FAILED = Value(3,"数据访问失败")
}
