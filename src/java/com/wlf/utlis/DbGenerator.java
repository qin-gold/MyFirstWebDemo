package com.wlf.utlis;

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
import java.util.Properties;

/**
 * 读取注解初始化数据库
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:59
 */
public class DbGenerator {
    private static final Log log = LogFactory.get();

    public static void createTable(Class<?> classes) {
        Properties pro = PropertiesLoadUtils.load("config.properties");
        if (!Boolean.parseBoolean(pro.getProperty("GeneraMode"))) {
            log.info("生成模式未启动", Level.INFO);
            return;
        }
        Connection conn = JDBCUtils.openConnection();
        StringBuilder drop = new StringBuilder();
        StringBuilder create = new StringBuilder();
        boolean present = classes.isAnnotationPresent(Table.class);
        if (!present) {
            return;
        }
        String tableName = classes.getAnnotation(Table.class).value();
        String sql = "select 1 from information_schema.tables where TABLE_SCHEMA = '" + pro.getProperty("databaseName") + "' and table_name ='" + tableName + "';";
        int s = JDBCUtils.queryForInt(conn, sql);
        if (s == 1) {
            if (!Boolean.parseBoolean(pro.getProperty("forceGenera"))) {
                log.warn(tableName + " 数据库中该表已经存在", Level.WARN);
                return;
            }
        }
        drop.append(" drop table if exists ").append(tableName);
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
        try {
            log.log(Level.INFO, drop.toString());
            JDBCUtils.update(conn, drop.toString());
            log.log(Level.INFO, create.toString());
            JDBCUtils.update(conn, create.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
