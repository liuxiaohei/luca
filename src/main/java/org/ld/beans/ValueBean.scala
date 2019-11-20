package org.ld.beans

import scala.beans.BeanProperty

/*
 * @author ld
 * ResponseBody bean 实体
 */
class ValueBean {
  @BeanProperty var id: Integer = _
  @BeanProperty var value: String = _
}
