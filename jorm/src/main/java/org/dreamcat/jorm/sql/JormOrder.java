package org.dreamcat.jorm.sql;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public class JormOrder extends JormSql implements JormOrderable, JormLimitable {

    public JormOrder(JormAppendable sql) {
        super(sql);
    }
}
