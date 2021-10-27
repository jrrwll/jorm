package org.dreamcat.jorm.sql;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public class JormSql implements JormAppendable {

    final StringBuilder raw = new StringBuilder();

    public JormSql(JormAppendable sql) {
        this.raw.append(sql.toString());
    }

    @Override
    public JormSql append(String part) {
        raw.append(' ').append(part);
        return this;
    }

    @Override
    public String toString() {
        return raw.toString();
    }
}
