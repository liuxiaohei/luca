package org.ld.utils;

import org.ld.enums.SystemErrorCodeEnum;
import org.ld.exception.CodeStackException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import scala.Enumeration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/**
 * 异常相关工具
 */
public class ExceptionUtil {

    private static final Map<Class, Enumeration.Value> EXCEPTIONS = new HashMap<>();

    static {
        EXCEPTIONS.put(NullPointerException.class, SystemErrorCodeEnum.NULL_POINTER_EXCEPTION());
        EXCEPTIONS.put(HttpMessageNotReadableException.class, SystemErrorCodeEnum.PARAMS_INVALID());
        EXCEPTIONS.put(MethodArgumentNotValidException.class, SystemErrorCodeEnum.PARAMS_INVALID());
        EXCEPTIONS.put(OutOfMemoryError.class,SystemErrorCodeEnum.OUT_OF_MEMORY_ERROR());
        EXCEPTIONS.put(IOException.class,SystemErrorCodeEnum.IO_EXCEPTION());
        EXCEPTIONS.put(FileNotFoundException.class,SystemErrorCodeEnum.FILE_NOTFOUND_EXCEPTION());
        EXCEPTIONS.put(ClassNotFoundException.class,SystemErrorCodeEnum.CLASS_NOT_FOUND_EXCEPTION());
        EXCEPTIONS.put(ClassCastException.class,SystemErrorCodeEnum.CLASS_CAST_EXCEPTION());
        EXCEPTIONS.put(IndexOutOfBoundsException.class,SystemErrorCodeEnum.INDEX_OUTOFBOUNDS_EXCEPTION());
        EXCEPTIONS.put(ArithmeticException.class,SystemErrorCodeEnum.ARITHMETIC_EXCEPTION());
        EXCEPTIONS.put(SQLException.class,SystemErrorCodeEnum.SQL_EXCEPTION());
        EXCEPTIONS.put(DataAccessException.class,SystemErrorCodeEnum.DATA_ACCESS_FAILED());
        EXCEPTIONS.put(UnsupportedOperationException.class,SystemErrorCodeEnum.UNSUPPORTED_OPERATION_EXCEPTION());
        EXCEPTIONS.put(TypeNotPresentException.class,SystemErrorCodeEnum.TYPE_NOT_PRESENT_EXCEPTION());
        EXCEPTIONS.put(StringIndexOutOfBoundsException.class,SystemErrorCodeEnum.STRING_INDEX_OUT_OF_BOUNDS_EXCEPTION());
        EXCEPTIONS.put(SecurityException.class,SystemErrorCodeEnum.SECURITY_EXCEPTION());
        EXCEPTIONS.put(NumberFormatException.class,SystemErrorCodeEnum.NUMBER_FORMAT_EXCEPTION());
        EXCEPTIONS.put(NoSuchFieldException.class,SystemErrorCodeEnum.NO_SUCH_FIELD_EXCEPTION());
        EXCEPTIONS.put(NegativeArraySizeException.class,SystemErrorCodeEnum.NEGATIVE_ARRAY_SIZE_EXCEPTION());
        EXCEPTIONS.put(InterruptedException.class,SystemErrorCodeEnum.INTERRUPTED_EXCEPTION());
        EXCEPTIONS.put(InstantiationException.class,SystemErrorCodeEnum.INSTANTIATION_EXCEPTION());
        EXCEPTIONS.put(IllegalThreadStateException.class,SystemErrorCodeEnum.ILLEGAL_THREAD_STATE_EXCEPTION());
        EXCEPTIONS.put(IllegalStateException.class,SystemErrorCodeEnum.ILLEGAL_STATE_EXCEPTION());
        EXCEPTIONS.put(IllegalMonitorStateException.class,SystemErrorCodeEnum.ILLEGAL_MONITOR_STATE_EXCEPTION());
        EXCEPTIONS.put(IllegalAccessException.class,SystemErrorCodeEnum.ILLEGAL_ACCESS_EXCEPTION());
        EXCEPTIONS.put(EnumConstantNotPresentException.class,SystemErrorCodeEnum.ENUM_CONSTANT_NOT_PRESENT_EXCEPTION());
        EXCEPTIONS.put(CloneNotSupportedException.class,SystemErrorCodeEnum.CLONE_NOT_SUPPORTED_EXCEPTION());
        EXCEPTIONS.put(ArrayStoreException.class,SystemErrorCodeEnum.ARRAY_STORE_EXCEPTION());
        EXCEPTIONS.put(VirtualMachineError.class,SystemErrorCodeEnum.VIRTUAL_MACHINE_ERROR());
        EXCEPTIONS.put(VerifyError.class,SystemErrorCodeEnum.VERIFY_ERROR());
        EXCEPTIONS.put(UnsupportedClassVersionError.class,SystemErrorCodeEnum.UNSUPPORTED_CLASS_VERSION_ERROR());
        EXCEPTIONS.put(UnsatisfiedLinkError.class,SystemErrorCodeEnum.UNSATISFIED_LINK_ERROR());
        EXCEPTIONS.put(UnknownError.class,SystemErrorCodeEnum.UNKNOWN_ERROR());
        EXCEPTIONS.put(ThreadDeath.class,SystemErrorCodeEnum.THREAD_DEATH());
        EXCEPTIONS.put(StackOverflowError.class,SystemErrorCodeEnum.STACK_OVERFLOW_ERROR());
        EXCEPTIONS.put(NoSuchFieldError.class,SystemErrorCodeEnum.NO_SUCH_FIELD_ERROR());
        EXCEPTIONS.put(NoClassDefFoundError.class,SystemErrorCodeEnum.NO_CLASS_DEF_FOUND_ERROR());
        EXCEPTIONS.put(LinkageError.class,SystemErrorCodeEnum.LINKAGE_ERROR());
        EXCEPTIONS.put(InternalError.class,SystemErrorCodeEnum.INTERNAL_ERROR());
        EXCEPTIONS.put(InstantiationError.class,SystemErrorCodeEnum.INSTANTIATION_ERROR());
        EXCEPTIONS.put(IncompatibleClassChangeError.class,SystemErrorCodeEnum.INCOMPATIBLE_CLASS_CHANGE_ERROR());
        EXCEPTIONS.put(IllegalAccessError.class,SystemErrorCodeEnum.ILLEGAL_ACCESS_ERROR());
        EXCEPTIONS.put(ExceptionInInitializerError.class,SystemErrorCodeEnum.EXCEPTION_IN_INITIALIZER_ERROR());
        EXCEPTIONS.put(ClassFormatError.class,SystemErrorCodeEnum.CLASS_FORMAT_ERROR());
        EXCEPTIONS.put(ClassCircularityError.class,SystemErrorCodeEnum.CLASS_CIRCULARITY_ERROR());
        EXCEPTIONS.put(AssertionError.class,SystemErrorCodeEnum.ASSERTION_ERROR());
        EXCEPTIONS.put(AbstractMethodError.class,SystemErrorCodeEnum.ABSTRACT_METHOD_ERROR());

    }

    private static AtomicBoolean merged = new AtomicBoolean(false);

    /**
     * 添加自定义Exception判断规则(只有第一次运行时才生效)
     * todo 用Akka实现
     */
    @Deprecated
    public synchronized static void mergeExceptionRule(Consumer<Map<Class, Enumeration.Value>> exceptionsConsumer) {
        if (!merged.get()) {
            exceptionsConsumer.accept(EXCEPTIONS);
            merged.set(true);
        }
    }

    /**
     * 只运行一次 添加自定义Exception判断
     */
    @Deprecated
    public static void mergeExceptionRule(Map<Class, Enumeration.Value> newExceptions) {
        mergeExceptionRule(exceptions -> newExceptions.forEach(exceptions::put));
    }

    public static Optional<Enumeration.Value> getSystemErrorValue(Throwable e) {
        if (e instanceof CodeStackException) {
            return Optional.of((CodeStackException) e).map(CodeStackException::getValue);
        }
        return EXCEPTIONS.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isInstance(e))
                .map(Map.Entry::getValue)
                .findFirst();
    }
}
