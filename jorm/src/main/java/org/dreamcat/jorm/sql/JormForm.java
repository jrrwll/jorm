package org.dreamcat.jorm.sql;


import lombok.Getter;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public class JormForm {

    @Getter
    JormSelect select;
    String tableName;
    String alias;

    public <T> JormWhere where(JormPredicate predicate) {
        return null; // todo impl
    }

    public <T> JormInnerJoin innerJoin(Class<T> entityClass, String alias) {
        return null; // todo impl
    }
}
