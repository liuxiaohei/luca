package org.ld.enums

import java.util
import org.springframework.http.HttpStatus
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ResponseMessage

import scala.collection.JavaConverters

/*
 * @author ld
 */
object ResponseMessageEnum extends Enumeration {
  Value(HttpStatus.OK.value(), "操作成功")
  Value(HttpStatus.BAD_REQUEST.value(), "登录参数错误")
  Value(HttpStatus.UNAUTHORIZED.value(), "用户名或密码错误")
  Value(HttpStatus.FORBIDDEN.value(), "用户被禁止")
  Value(HttpStatus.NOT_FOUND.value(), "找不到资源")
  Value(HttpStatus.CONFLICT.value(), "业务逻辑异常")
  Value(HttpStatus.UNPROCESSABLE_ENTITY.value(), "参数校验异常")
  Value(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器内部错误")
  Value(HttpStatus.SERVICE_UNAVAILABLE.value(), "Hystrix异常")

  def getMessagesStream: util.stream.Stream[ResponseMessage] = JavaConverters.asJavaCollection(values).stream().map((e: Enumeration#Value) => new ResponseMessageBuilder().code(e.id).message(e.toString).responseModel(new ModelRef("ApiError")).build)
}