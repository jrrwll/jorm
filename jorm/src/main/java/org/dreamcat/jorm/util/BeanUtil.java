package org.dreamcat.jorm.util;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public class BeanUtil {

    public static char toLowerCase(char c) {
        // A-Z 65-90, a-z 97-122
        return c >= 65 && c <= 90 ? (char) (c + 32) : c;
    }

    public static String extractFieldNameForGetterSetter(Serializable getterOrSetter) {
        String methodName = extractLambdaMethodName(getterOrSetter);
        int offset = methodName.startsWith("is") ? 2 : 3;
        return toLowerCase(methodName.charAt(offset)) + methodName.substring(offset + 1);
    }

    public static String extractLambdaMethodName(Serializable lambda) {
        return ofSerializedLambda(lambda).getImplMethodName();
    }

    public static SerializedLambda ofSerializedLambda(Serializable lambda) {
        try {
            Method method = lambda.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(true);
            return (SerializedLambda) method.invoke(lambda);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
