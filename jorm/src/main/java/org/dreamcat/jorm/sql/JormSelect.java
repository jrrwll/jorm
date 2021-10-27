package org.dreamcat.jorm.sql;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.dreamcat.common.util.StringUtil;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
@RequiredArgsConstructor
public class JormSelect {

    final List<JormColumn> columns;

    public JormSelect(JormColumn... columns) {
        this(new ArrayList<>(Arrays.asList(columns)));
    }

    public <T> JormForm from(Class<T> entityClass) {
        String tableName = StringUtil.toSnakeCase(entityClass.getSimpleName());
        return new JormForm();
    }

    public <T> JormForm from(Class<T> entityClass, String alias) {
        return new JormForm();
    }
}
