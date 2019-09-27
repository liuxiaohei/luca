package org.ld.beans

import scala.beans.BeanProperty

/*
 * @author ld
 * ResponseBody bean 实体
 */
class ResponseBodyBean[T] {
  @BeanProperty var errorCode: Integer = _
  @BeanProperty var message: String = _
  @BeanProperty var data: T = _
  @BeanProperty var stackTrace: Array[String] = _
}
