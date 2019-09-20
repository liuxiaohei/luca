package org.ld.enums

/*
 * @author ld
 * 最标准的错误码枚举 枚举
 */
object ErrorCodeEnum extends Enumeration {
  val SUCCESS = Value(1000,"成功")
  val UNKNOW = Value(-1,"未知异常")
}
