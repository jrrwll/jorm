package org.dreamcat.jorm.sql;

import java.util.function.Function;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public interface JormOrderable extends JormAppendable {

    default <T, R> JormOrder orderBy(Function<T, R> fieldRef) {
        JormOrder sql = new JormOrder(this);
        sql.append("order by ");
        return sql;
    }

    default <T, R> JormOrder orderByDesc(Function<T, R> fieldRef) {
        return null; // todo impl
    }
}
