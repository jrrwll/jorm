package org.dreamcat.jorm.compare;

import static org.dreamcat.common.util.DateUtil.parseLocalDate;
import static org.dreamcat.common.util.DateUtil.parseLocalDateTime;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.SneakyThrows;
import org.dreamcat.common.util.SystemUtil;
import org.dreamcat.jorm.jooq.tables.Student;
import org.dreamcat.jorm.jooq.tables.StudentScore;
import org.dreamcat.jorm.jooq.tables.records.StudentScoreRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.codegen.GenerationTool;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;

/**
 * @author Jerry Will
 * @version 2023-01-23
 */
class JooqTest {

    @SneakyThrows
    @Test
    void generateCode() {
        String projectDir = new File(".").getCanonicalPath();
        String xml = "<configuration xmlns='http://www.jooq.org/xsd/jooq-codegen-3.7.0.xsd'>\n"
                + "  <jdbc>\n"
                + "    <driver>org.sqlite.JDBC</driver>\n"
                + "    <url>jdbc:sqlite:" + projectDir
                + "/src/test/resources/testdb.sqlite</url>\n"
                + "    <user></user>\n"
                + "    <password></password>\n"
                + "  </jdbc>\n"
                + "  <generator>\n"
                + "    <database />\n"
                + "    <generate />\n"
                + "    <target>\n"
                + "      <packageName>org.dreamcat.jorm.jooq</packageName>\n"
                + "      <directory>" + projectDir
                + "/build/generated/sources/jooq/main</directory>\n"
                + "    </target>\n"
                + "  </generator>\n"
                + "</configuration>";
        System.out.println("xml: " + xml);
        GenerationTool.generate(xml);
    }

    @SneakyThrows
    @Test
    void test() {
        String projectDir = new File(".").getCanonicalPath();
        String jdbcUrl = "jdbc:sqlite:" + projectDir + "/src/test/resources/testdb.sqlite";
        try (Connection connection = DriverManager.getConnection(
                jdbcUrl, SystemUtil.getEnv("USER", ""), "")) {
            DSLContext dslContext = DSL.using(connection, SQLDialect.SQLITE);
            insert(dslContext);
            Result<org.jooq.Record> res = dslContext
                    .select(
                            StudentScore.STUDENT_SCORE.SUBJECT,
                            DSL.avg(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.median(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.max(StudentScore.STUDENT_SCORE.TIME),
                            DSL.min(StudentScore.STUDENT_SCORE.TIME),
                            DSL.count(StudentScore.STUDENT_SCORE.STUDENT_ID),
                            DSL.countDistinct(StudentScore.STUDENT_SCORE.STUDENT_ID),

                            DSL.sin(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.cos(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.tan(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.tanh(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.atan(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.atanh(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.sqrt(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.power(StudentScore.STUDENT_SCORE.SCORE, 2),
                            DSL.exp(StudentScore.STUDENT_SCORE.SCORE),
                            DSL.log(StudentScore.STUDENT_SCORE.SCORE, 2),
                            DSL.log10(StudentScore.STUDENT_SCORE.SCORE),

                            DSL.currentUser(),
                            DSL.currentDate(),
                            DSL.currentTime(),
                            DSL.currentLocalDate(),
                            DSL.currentLocalTime(),
                            DSL.currentLocalDateTime()
                    )
                    .from(Student.STUDENT)
                    .rightJoin(StudentScore.STUDENT_SCORE)
                    .on(Student.STUDENT.ID.eq(StudentScore.STUDENT_SCORE.STUDENT_ID))
                    .where(StudentScore.STUDENT_SCORE.TIME.ge(parseLocalDateTime("2014-06-01 00:00:00")))
                    .groupBy(StudentScore.STUDENT_SCORE.SUBJECT)
                    .orderBy(Student.STUDENT.BIRTHDAY)
                    .fetch();
            res.forEach(row -> {
                System.out.println("fetch Record: " +
                        IntStream.range(0, row.size()).mapToObj(row::get)
                                .map(Objects::toString)
                                .collect(Collectors.joining(", ")));
            });
            delete(dslContext);
        }
    }


    void insert(DSLContext dslContext) {
        dslContext.insertInto(Student.STUDENT, Student.STUDENT.ID, Student.STUDENT.NAME, Student.STUDENT.BIRTHDAY)
                .values(1L, "skyblue", parseLocalDate("1997-09-03"))
                .values(2L, "cyan", parseLocalDate("1997-08-02"))
                .execute();
        dslContext.insertInto(StudentScore.STUDENT_SCORE)
                .values(1L, 1L, "chinese", BigDecimal.valueOf(120), parseLocalDateTime("2016-06-07"))
                .values(2L, 1L, "math", BigDecimal.valueOf(116), parseLocalDateTime("2016-06-07"))
                .values(3L, 1L, "science", BigDecimal.valueOf(233), parseLocalDateTime("2016-06-08"))
                .execute();

        dslContext.insertInto(StudentScore.STUDENT_SCORE)
                .set(StudentScore.STUDENT_SCORE.ID, 4L)
                .set(StudentScore.STUDENT_SCORE.STUDENT_ID, 2L)
                .set(StudentScore.STUDENT_SCORE.SUBJECT, "chinese")
                .set(StudentScore.STUDENT_SCORE.SCORE, BigDecimal.valueOf(90))
                .set(StudentScore.STUDENT_SCORE.TIME, parseLocalDateTime("2016-06-07 00:00:00"))
                .newRecord()
                .set(StudentScore.STUDENT_SCORE.ID, 5L)
                .set(StudentScore.STUDENT_SCORE.STUDENT_ID, 2L)
                .set(StudentScore.STUDENT_SCORE.SUBJECT, "math")
                .set(StudentScore.STUDENT_SCORE.SCORE, BigDecimal.valueOf(128))
                .set(StudentScore.STUDENT_SCORE.TIME, parseLocalDateTime("2016-06-07 00:00:00"))
                .execute();

        StudentScoreRecord ssr = dslContext.newRecord(StudentScore.STUDENT_SCORE);
        ssr.setId(6L);
        ssr.setStudentId(2L);
        ssr.setSubject("science");
        ssr.setScore(BigDecimal.valueOf(254));
        ssr.setTime(parseLocalDateTime("2016-06-08 00:00:00"));
        ssr.insert();
    }

    void delete(DSLContext dslContext) {
        dslContext.truncate(Student.STUDENT)
                .execute();
        dslContext.truncate(StudentScore.STUDENT_SCORE)
                .execute();
    }
}
