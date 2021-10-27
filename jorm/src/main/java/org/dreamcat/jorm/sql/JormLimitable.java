package org.dreamcat.jorm.sql;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public interface JormLimitable extends JormAppendable {

    default JormLimit limit(int size) {
        JormLimit sql = new JormLimit(this);
        sql.append("limit " + size);
        return sql;
    }

    default JormLimit limit(int offset, int size) {
        JormLimit sql = new JormLimit(this);
        sql.append("limit ").append(String.valueOf(size))
                .append(" offset ").append(String.valueOf(offset));
        return sql;
    }
}
