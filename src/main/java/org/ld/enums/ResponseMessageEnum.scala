package org.ld.enums

import java.util
import org.springframework.http.HttpStatus
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ResponseMessage
import scala.collection.JavaConverters._

/*
 * @author ld
 */
object ResponseMessageEnum extends Enumeration {

  /**
   * 隐式转换 HttpStatus => Int
   */
  implicit def getValue(a: HttpStatus): Int = a.value()

  Value(HttpStatus.OK, "操作成功")
  Value(HttpStatus.BAD_REQUEST, "登录参数错误")
  Value(HttpStatus.UNAUTHORIZED, "用户名或密码错误")
  Value(HttpStatus.FORBIDDEN, "用户被禁止")
  Value(HttpStatus.NOT_FOUND, "找不到资源")
  Value(HttpStatus.CONFLICT, "业务逻辑异常")
  Value(HttpStatus.UNPROCESSABLE_ENTITY, "参数校验异常")
  Value(HttpStatus.INTERNAL_SERVER_ERROR, "服务器内部错误")
  Value(HttpStatus.SERVICE_UNAVAILABLE, "Hystrix异常")

  def getMessages: util.List[ResponseMessage] = values
    .map((e: Enumeration#Value) => new ResponseMessageBuilder().code(e.id).message(e.toString).responseModel(new ModelRef("ApiError")).build)
    .toBuffer.asJava
}