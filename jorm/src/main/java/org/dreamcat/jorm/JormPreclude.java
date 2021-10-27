package org.dreamcat.jorm;

import java.io.Serializable;
import java.util.function.Function;
import org.dreamcat.jorm.sql.JormColumn;
import org.dreamcat.jorm.sql.JormPredicate;
import org.dreamcat.jorm.sql.JormSelect;
import org.dreamcat.jorm.util.BeanUtil;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public class JormPreclude {

    private JormPreclude() {
    }

    public static JormSelect selectAll() {
        return new JormSelect(JormColumn.ALL);
    }

    public static JormSelect selectAny(String s) {
        return new JormSelect(JormColumn.ALL);
    }

    public static <T, R> JormPredicate like(Function<T, R> fieldRef, String like) {
        return null; // todo impl
    }

    public static <T1, R1, T2, R2> JormPredicate eq(Function<T1, R1> fieldRef, Function<T2, R2> otherFieldRef) {
        return op(fieldRef, otherFieldRef, "=");
    }

    public static <T, R> JormPredicate eq(Function<T, R> fieldRef, Object value) {
        return op(fieldRef, value, "=");
    }

    public static <T1, R1, T2, R2> JormPredicate gt(Function<T1, R1> fieldRef, Function<T2, R2> otherFieldRef) {
        return op(fieldRef, otherFieldRef, ">");
    }

    public static <T, R> JormPredicate gt(Function<T, R> fieldRef, Object value) {
        return op(fieldRef, value, ">");
    }

    public static <T1, R1, T2, R2> JormPredicate op(Function<T1, R1> fieldRef, Function<T2, R2> otherFieldRef,
            String op) {
        String columnName = BeanUtil.extractFieldNameForGetterSetter((Serializable) fieldRef);

        return null; // todo impl
    }

    private static <T, R> JormPredicate op(Function<T, R> fieldRef, Object value, String op) {
        return null; // todo impl
    }
}
