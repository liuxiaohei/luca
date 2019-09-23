package org.ld.beans

import scala.beans.BeanProperty

/*
 * @author ld
 * ResponseBody bean 实体
 */
class ResponseBodyBean[T] {
  @BeanProperty var state: Integer = _
  @BeanProperty var message: String = "成功!"
  @BeanProperty var data: T = _
  @BeanProperty var stackTrace: String = _
}
