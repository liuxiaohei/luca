package org.ld.enums

/*
 * @author ld
 * 系统级ErrorCode
 * https://www.cnblogs.com/qlqwjy/p/7816290.html
 */
@SuppressWarnings(Array("all"))
object SystemErrorCodeEnum extends Enumeration {
  val UNKNOW = Value(-1, "未知异常")
  val NULL_POINTER_EXCEPTION = Value(1, "空指针异常")
  val OUT_OF_MEMORY_ERROR = Value(2, "内存溢出异常")
  val IO_EXCEPTION = Value(3, "IO异常")
  val FILE_NOTFOUND_EXCEPTION = Value(4, "找不到文件异常")
  val CLASS_NOT_FOUND_EXCEPTION = Value(5, "类找不到异常")
  val CLASS_CAST_EXCEPTION = Value(6, "类转换异常，将一个不是该类的实例转换成这个类就会抛出这个异常")
  val NO_SUCH_METHOD_EXCEPTION = Value(7, "没有这个方法异常，一般发生在反射调用方法的时候")
  val INDEX_OUTOFBOUNDS_EXCEPTION = Value(8, "索引越界异常，当操作一个字符串或者数组的时候经常遇到的异常")
  val ARITHMETIC_EXCEPTION = Value(9, "算术异常，发生在数字的算术运算时的异常，如一个数字除以 0 就会报这个错。")
  val SQL_EXCEPTION = Value(10, "数据库访问失败")
  val DATA_ACCESS_FAILED = Value(11, "数据访问失败")
  val UnsupportedOperationException = Value(12, "不支持的方法异常。指明请求的方法不被支持情况的异常")
  val TypeNotPresentException = Value(13, "类型不存在异常。当应用试图以某个类型名称的字符串表达方式访问该类型，但是根据给定的名称又找不到该类型是抛出该异常。该异常与 ClassNotFoundException的区别在于该异常是unchecked（不被检查）异常，而ClassNotFoundException 是checked（被检查）异常。")
  val StringIndexOutOfBoundsException = Value(14, "字符串索引越界异常。当使用索引值访问某个字符串中的字符，而该索引值小于0或大于等于序列大小时，抛出该异常。\n")
  val SecurityException = Value(15, "安全异常。由安全管理器抛出，用于指示违反安全情况的异常。")
  val NumberFormatException = Value(16, "数字格式异常。当试图将一个String转换为指定的数字类型，而该字符串确不满足数字类型要求的格式时，抛出该异常。\n")
  val NoSuchFieldException = Value(17, "属性不存在异常。当访问某个类的不存在的属性时抛出该异常")
  val NegativeArraySizeException = Value(18, "数组大小为负值异常。当使用负数大小值创建数组时抛出该异常。\n")
  val InterruptedException = (19, "被中止异常。当某个线程处于长时间的等待、休眠或其他暂停状态，而此时其他的线程通过Thread的interrupt方法终止该线程时抛出该异常。\n")
  val InstantiationException = (20, "实例化异常。当试图通过newInstance()方法创建某个类的实例，而该类是一个抽象类或接口时，抛出该异常。")
  val IllegalThreadStateException = Value(21, "违法的线程状态异常。当县城尚未处于某个方法的合法调用状态，而调用了该方法时，抛出异常。\n")
  val IllegalStateException = Value(22, "违法的状态异常。当在Java环境和应用尚未处于某个方法的合法调用状态，而调用了该方法时，抛出该异常。\n")
  val IllegalMonitorStateException = Value(23, "违法的监控状态异常。当某个线程试图等待一个自己并不拥有的对象（O）的监控器或者通知其他线程等待该对象（O）的监控器时，抛出该异常。\n")
  val IllegalAccessException = Value(24, "违法的访问异常。当应用试图通过反射方式创建某个类的实例、访问该类属性、调用该类方法，而当时又无法访问类的、属性的、方法的或构造方法的定义时抛出该异常。")
  val EnumConstantNotPresentException = Value(25, "枚举常量不存在异常。当应用试图通过名称和枚举类型访问一个枚举对象，但该枚举对象并不包含常量时，抛出该异常。\n");
  val CloneNotSupportedException = Value(26, "不支持克隆异常。当没有实现Cloneable接口或者不支持克隆方法时,调用其clone()方法则抛出该异常。\n")
  val ArrayStoreException = Value(27, "数组存储异常。当向数组中存放非数组声明类型对象时抛出。\n");
  val VirtualMachineError = Value(28, "虚拟机错误。用于指示虚拟机被破坏或者继续执行操作所需的资源不足的情况。\n")
  val VerifyError = Value(29, "验证错误。当验证器检测到某个类文件中存在内部不兼容或者安全问题时抛出该错误。\n")
  val UnsupportedClassVersionError = Value(30, "不支持的类版本错误。当Java虚拟机试图从读取某个类文件，但是发现该文件的主、次版本号不被当前Java虚拟机支持的时候，抛出该错误。\n")
  val UnsatisfiedLinkError = Value(31, "未满足的链接错误。当Java虚拟机未找到某个类的声明为native方法的本机语言定义时抛出。\n")
  val UnknownError = Value(32, "未知错误。用于指示Java虚拟机发生了未知严重错误的情况。\n")
  val ThreadDeath = Value(33, "线程结束。当调用Thread类的stop方法时抛出该错误，用于指示线程结束。\n")
  val StackOverflowError = Value(34, "堆栈溢出错误。当一个应用递归调用的层次太深而导致堆栈溢出时抛出该错误。\n")
  val NoSuchFieldError = Value(35, "域不存在错误。当应用试图访问或者修改某类的某个域，而该类的定义中没有该域的定义时抛出该错误。\n")
  val NoClassDefFoundError = Value(36, "未找到类定义错误。当Java虚拟机或者类装载器试图实例化某个类，而找不到该类的定义时抛出该错误。\n")
  val LinkageError = Value(37, "链接错误。该错误及其所有子类指示某个类依赖于另外一些类，在该类编译之后，被依赖的类改变了其类定义而没有重新编译所有的类，进而引发错误的情况。\n")
  val InternalError = Value(38, "内部错误。用于指示Java虚拟机发生了内部错误。\n")
  val InstantiationError = Value(39, "实例化错误。当一个应用试图通过Java的new操作符构造一个抽象类或者接口时抛出该异常.\n")
  val IncompatibleClassChangeError = Value(40, "不兼容的类变化错误。当正在执行的方法所依赖的类定义发生了不兼容的改变时，抛出该异常。一般在修改了应用中的某些类的声明定义而没有对整个应用重新编译而直接运行的情况下，容易引发该错误。\n")
  val IllegalAccessError = Value(41, "违法访问错误。当一个应用试图访问、修改某个类的域（Field）或者调用其方法，但是又违反域或方法的可见性声明，则抛出该异常。")
  val ExceptionInInitializerError = Value(42, "初始化程序错误。当执行一个类的静态初始化程序的过程中，发生了异常时抛出。静态初始化程序是指直接包含于类中的static语句段。")
  val ClassFormatError = Value(43, "类格式错误。当Java虚拟机试图从一个文件中读取Java类，而检测到该文件的内容不符合类的有效格式时抛出。")
  val ClassCircularityError = Value(44, "类循环依赖错误。在初始化一个类时，若检测到类之间循环依赖则抛出该异常。\n")
  val AssertionError = Value(45, "断言错。用来指示一个断言失败的情况。\n")
  val AbstractMethodError = Value(46, "抽象方法错误。当应用试图调用抽象方法时抛出。\n")


  val SUCCESS = Value(200, "操作成功")
  val PARAMS_INVALID = Value(10000, "参数错误")

}
