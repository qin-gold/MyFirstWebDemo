package com.wlf.utlis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * 读取注解初始化数据库
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:59
 */
public class DbGenerator {
    private static final Connection conn = JDBCUtils.openConnection();
    private static final Log log = LogFactory.get();
    private static final Properties load;
    private static Set<String> set = new HashSet<>();

    static {
        load = PropertiesLoadUtils.load("config.properties");
    }

    public static void createTable(Class<?> classes) {
        if (!Boolean.parseBoolean(load.getProperty("GeneraMode"))) {
            log.info("生成模式未启动", Level.INFO);
            return;
        }
        if (!classes.isAnnotationPresent(Table.class)) return;
        String tableName = classes.getAnnotation(Table.class).value();
        if (checkTableExist(tableName)) return;

        String createTableSql = createTable(tableName, classes);


        log.log(Level.INFO, createTableSql);
        JDBCUtils.update(conn, createTableSql);
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkTableExist(String tableName) {
        String sql = "select 1 from information_schema.tables where TABLE_SCHEMA = '" + load.getProperty("databaseName") + "' and table_name ='" + tableName + "';";
        int s = JDBCUtils.queryForInt(conn, sql);
        if (s == 1) {
            log.warn(tableName + " 数据库中该表已经存在,执行更新操作", Level.WARN);
            return true;
        }
        return false;
    }

    private static boolean checkTableData(String tableName) {
        String sql = "select 1 from " + tableName + ";";
        int s = JDBCUtils.queryForInt(conn, sql);
        if (s > 0) {
            if (!Boolean.parseBoolean(load.getProperty("forceGenera"))) {
                log.error(tableName + " 数据库中该表存在数据", Level.ERROR);
            }
            return true;
        }
        return false;
    }

    private static String createTable(String tableName, Class<?> classes) {
        StringBuilder create = new StringBuilder();
        create.append(" create table ").append(tableName).append(" ( ");
        Field[] fields = classes.getDeclaredFields();
        for (Field field : fields) {
            TablePk pk = field.getAnnotation(TablePk.class);
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                create.append(column.value()).append(" ")
                        .append(column.type().getValue())
                        .append((column.type() == DbType.Varchar || column.type() == DbType.Char) ? " (" + column.length() + ") " : " ")
                        .append(column.notNull() ? " not null " : "");
                if (pk != null) {
                    if (pk.isPk()) {
                        create.append(" primary key ")
                                .append(pk.increment() ? " AUTO_INCREMENT " : "");
                    }
                }
                if (!"".equals(column.remark())) {
                    create.append(" comment ' ");
                    create.append(column.remark());
                    create.append(" ' ");
                }
                create.append(",");
            }
        }
        //删除多的逗号
        create.deleteCharAt(create.length() - 1);
        //拼接最后的括号
        create.append(")");
        return create.toString();
    }

    private static String updateTableField(String tableName, Class<?> classes) {
        Field[] fields = classes.getDeclaredFields();
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                if (StrUtil.isNotBlank(column.oldValue()))
                set.add(column.value());
            }
        }
        String fieldSql = "show full columns from " + tableName;
        List<Map<String, Object>> list = JDBCUtils.queryForList(conn, fieldSql);
            for (Map<String, Object> map : list) {
                set.remove((String) map.get("Field"));
            }

        StringBuilder update = new StringBuilder();
        for (Field field : fields) {
            TablePk pk = field.getAnnotation(TablePk.class);
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                update.append(column.value()).append(" ")
                        .append(column.type().getValue())
                        .append((column.type() == DbType.Varchar || column.type() == DbType.Char) ? " (" + column.length() + ") " : " ")
                        .append(column.notNull() ? " not null " : "");
                if (pk != null) {
                    if (pk.isPk()) {
                        update.append(" primary key ")
                                .append(pk.increment() ? " AUTO_INCREMENT " : "");
                    }
                }
                if (!"".equals(column.remark())) {
                    update.append(" comment ' ");
                    update.append(column.remark());
                    update.append(" ' ");
                }
                update.append(",");
            }
        }
        return update.toString();
    }

    private static void dropTable(String tableName) {
        String drop = " drop table if exists " + tableName;
        log.log(Level.INFO, drop);
        JDBCUtils.update(conn, drop);
    }
}
