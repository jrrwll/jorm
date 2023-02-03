package org.dreamcat.jorm;

import org.dreamcat.jorm.jooq.tables.Student;
import org.junit.jupiter.api.Test;

/**
 * @author Jerry Will
 * @version 2023-01-23
 */
class FirstDemoTest {

    @Test
    void test() {
        from(Student.class)
        .select()
                .where()

    }

    public static From1<?> from(Class<?> tableClass) {
        return new From1<>();
    }

    public static class From1<T> {

        public void select() {

        }
    }

    public static class OrderFields {

    }
}
