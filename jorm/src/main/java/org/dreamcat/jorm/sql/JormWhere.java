package org.dreamcat.jorm.sql;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public class JormWhere extends JormSql implements JormOrderable {


    public JormWhere(JormAppendable sql) {
        super(sql);
    }

    public JormWhere and(JormPredicate predicate) {
        return null; // todo impl
    }
}
