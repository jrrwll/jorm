package org.dreamcat.jorm;

import java.util.List;
import org.dreamcat.jorm.sql.JormSql;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
public interface Jorm {

    <T> List<T> queryForList(JormSql sql, Class<T> clazz);
}
