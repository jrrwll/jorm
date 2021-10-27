package org.dreamcat.jorm.sql;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public interface JormAppendable {

    JormAppendable append(String part);

    @Override
    String toString();
}
