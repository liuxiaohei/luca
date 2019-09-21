package org.ld.enums

/*
 * @author ld
 * 错误码枚举
 */
object ErrorCodeEnum extends Enumeration {
  val SUCCESS = Value(200,"成功")
  val UNKNOW = Value(-1,"未知异常")
}
