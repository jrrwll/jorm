package org.dreamcat.jorm.exmaple;

import static org.dreamcat.jorm.JormPreclude.eq;
import static org.dreamcat.jorm.JormPreclude.gt;
import static org.dreamcat.jorm.JormPreclude.like;
import static org.dreamcat.jorm.JormPreclude.selectAll;
import static org.dreamcat.jorm.JormPreclude.selectAny;

import java.util.Date;
import java.util.List;
import org.dreamcat.jorm.Jorm;
import org.dreamcat.jorm.exmaple.domain.Permission;
import org.dreamcat.jorm.exmaple.domain.Role;
import org.dreamcat.jorm.exmaple.domain.RolePermission;
import org.dreamcat.jorm.sql.JormSql;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Jerry Will
 * @version 2021-10-27
 */
class DslDemo {

    @Test
    void simple() {
        JormSql sql = selectAll()
                .from(Role.class)
                .where(like(Role::getName, "%Jerry%"))
                .and(eq(Role::getActive, true))
                .orderByDesc(Role::getCreatedAt).orderBy(Role::getName)
                .limit(10, 10);
        System.out.println(sql);
        List<Role> roles = jorm.queryForList(sql, Role.class);
    }

    @Test
    void join1() {
        class Temp extends RolePermission {

            Long rid;
            String r_name;
        }
        JormSql sql = selectAny("r.id as rid, r.name as r_name, rp.*")
                .from(Role.class, "r")
                .innerJoin(RolePermission.class, "rp")
                .on(eq(Role::getId, RolePermission::getRoleId))
                .innerJoin(Permission.class, "p")
                .on(eq(RolePermission::getPermissionId, Permission::getId))
                .where(gt(RolePermission::getCreatedAt, new Date()));
        System.out.println(sql);
        List<Temp> temps = jorm.queryForList(sql, Temp.class);
    }

    Jorm jorm;

    @BeforeEach
    void init() {
        String jdbcUrl = "jdbc://mysql://127.0.0.1:3306/jorm";
    }
}
