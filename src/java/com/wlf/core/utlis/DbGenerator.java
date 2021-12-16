package com.wlf.core.utlis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.level.Level;
import com.wlf.core.annotation.Column;
import com.wlf.core.annotation.Table;
import com.wlf.core.annotation.TablePk;
import com.wlf.core.domain.base.dto.TableColumn;
import com.wlf.core.enums.DbType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 读取注解初始化数据库
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:59
 */
public class DbGenerator {
    private static final Log log = LogFactory.get();
    private static final Properties load;
    private static  Set<TableColumn> temp = new HashSet<>();
    private static  Set<TableColumn> newColumn = new HashSet<>();
    private static  Set<TableColumn> oldColumn = new HashSet<>();
    private static  Set<TableColumn> findColumn = new HashSet<>();

    static {
        load = PropertiesLoadUtils.load("config.properties");
    }

    public static void initDb(Class<?> classes, String... str) {
        if (checkMode()) {
            if (!classes.isAnnotationPresent(Table.class)) return;
            String tableName = classes.getAnnotation(Table.class).value();
            if (str.length != 0) {
                for (String s : str) {
                    if (!s.equalsIgnoreCase(tableName)) return;
                    break;
                }
            }
            if (checkTableExist(tableName)) {
                updateTableField(tableName, classes);
            } else {
                dropTable(tableName);
                createTable(tableName, classes);
            }
        }

    }

    private static boolean checkMode() {
        if (!Boolean.parseBoolean(load.getProperty("GeneraMode"))) {
            log.info("生成模式未启动", Level.INFO);
            return false;
        }
        return true;
    }

    /**
     * 检查是否存在表
     *
     * @param tableName 表名
     */
    private static boolean checkTableExist(String tableName) {
        String sql = "select 1 from information_schema.tables where TABLE_SCHEMA = '" + load.getProperty("databaseName") + "' and table_name ='" + tableName + "';";
        int s = JDBCUtils.queryForInt(sql);
        if (s == 1) {
            log.warn(tableName + " 数据库中该表已经存在,执行更新操作", Level.WARN);
            return true;
        }
        return false;
    }

    /**
     * 检查表中是否有数据
     *
     * @param tableName 表名
     */
    private static boolean checkTableData(String tableName) {
        String sql = "select 1 from " + tableName + ";";
        int s = JDBCUtils.queryForInt(sql);
        if (s > 0) {
            if (!Boolean.parseBoolean(load.getProperty("forceGenera"))) {
                log.error(tableName + " 数据库中该表存在数据", Level.ERROR);
            }
            return true;
        }
        return false;
    }

    /**
     * 创建表
     *
     * @param tableName 表名
     * @param classes   传入
     */
    private static void createTable(String tableName, Class<?> classes) {
        StringBuilder create = new StringBuilder();
        create.append(" create table ").append(tableName).append(" ( ");
        Field[] fields = classes.getDeclaredFields();
        for (Field field : fields) {
            TablePk pk = field.getAnnotation(TablePk.class);
            Column column = field.getAnnotation(Column.class);
            if (column != null) {
                create.append(column.value()).append(" ")
                        .append(column.type().getValue())
                        .append((column.type() == DbType.Varchar) ? " (" + (Math.max(column.length(), 32)) + ") " : " ")
                        .append((column.type() == DbType.Char) ? " (" + (Math.max(column.length(), 4)) + ") " : " ")
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
        log.log(Level.INFO, create.toString());
        JDBCUtils.update(create.toString());
    }

    private static void updateTableField(String tableName, Class<?> classes) {
        initTableData(tableName, classes);
        Set<TableColumn> temp2 = new HashSet<>(findColumn);
        findColumn.forEach(findItem -> {
            temp = temp.stream()
                    .filter(item->!item.equalsColumnOne(findItem))
                    .collect(Collectors.toSet());
            oldColumn = Stream.concat(oldColumn.stream(),temp.stream())
                    .filter(item->{
                        if (!item.equalsColumn(findItem)) {
                            return true;
                        }
                        temp2.remove(findItem);
                        return false;
                    })
                    .collect(Collectors.toSet());
            newColumn = newColumn.stream()
                    .filter(item->!temp.contains(item))
                    .filter(item->!item.equalsColumn(findItem))
                    .collect(Collectors.toSet());
        });
        findColumn=temp2;
        if (oldColumn.size()>0)
        tableUpdateColumn(tableName);
        if (newColumn.size()>0)
        tableInsertColumn(tableName);

        temp2.clear();
        findColumn.clear();
        newColumn.clear();
        oldColumn.clear();
    }

    /**
     * 读取数据库表字段信息 写入集合
     *
     * @param tableName 表名
     */
    private static void initTableData(String tableName, Class<?> classes) {
        Field[] fields = classes.getDeclaredFields();
        for (Field field : fields) {
            Column column = field.getAnnotation(Column.class);
            TablePk tablePk = field.getAnnotation(TablePk.class);
            if (column != null) {
                if (StrUtil.isNotBlank(column.oldValue())) {
                    oldColumn.add(new TableColumn(column, tablePk));
                } else {
                    temp.add(new TableColumn(column, tablePk));
                    newColumn.add(new TableColumn(column, tablePk));
                }
            }
        }
        String fieldSql = "show full columns from " + tableName;
        List<Map<String, Object>> list = JDBCUtils.queryForList(fieldSql);
        for (Map<String, Object> map : list) {
            findColumn.add(new TableColumn(map));
        }
    }

    /**
     * 删除表格
     *
     * @param tableName 表名
     */
    private static void dropTable(String tableName) {
        String drop = " drop table if exists " + tableName;
        log.log(Level.INFO, drop);
        JDBCUtils.update(drop);
    }

    /**
     * 更新表字段名称 以及字段其他属性
     *
     * @param tableName 表名
     */
    private static void tableUpdateColumn(String tableName) {
        StringBuilder updateTable = new StringBuilder("alter table ");
        updateTable.append(tableName);
        findColumn.forEach(find -> oldColumn.forEach(item -> {
            if (find.getField().equals(item.getField()) || find.getField().equals(item.getOldField())) {
                if (find.getField().equals(item.getField())) {
                    updateTable.append(" modify ")
                            .append(item.getField())
                            .append(" ");
                } else {
                    updateTable.append(" change ")
                            .append(item.getOldField())
                            .append(" ")
                            .append(item.getField())
                            .append(" ");
                }
                updateTable.append(item.column.type().getValue())
                        .append(" ")
                        .append((item.column.type() == DbType.Varchar) ? " (" + (Math.max(item.column.length(), 32)) + ") " : " ")
                        .append((item.column.type() == DbType.Char) ? " (" + (Math.max(item.column.length(), 4)) + ") " : " ")
                        .append(item.column.notNull() ? " not null " : "");
                if (item.pk != null) {
                    if (item.pk.isPk()) {
                        updateTable.append(" default ")
                                .append(item.pk.increment() ? " AUTO_INCREMENT " : "");
                    }
                }
                appendSql(updateTable, item);
            }
        }));
        if (updateTable.length() > 30) {
            updateTable.deleteCharAt(updateTable.length() - 1);
            log.info(updateTable.toString(), Level.INFO);
            JDBCUtils.update(updateTable.toString());
        }
    }

    /**
     * 插入表字段
     *
     * @param tableName 表名
     */
    private static void tableInsertColumn(String tableName) {
        StringBuilder insertColumn = new StringBuilder("alter table ");
        insertColumn.append(tableName);
        newColumn.forEach(item -> {
            insertColumn.append(" add ")
                    .append(item.getField())
                    .append(" ")
                    .append(item.column.type().getValue())
                    .append(" ")
                    .append((item.column.type() == DbType.Varchar) ? " (" + (Math.max(item.column.length(), 32)) + ") " : " ")
                    .append((item.column.type() == DbType.Char) ? " (" + (Math.max(item.column.length(), 4)) + ") " : " ")
                    .append(item.column.notNull() ? " not null " : "");
            if (item.pk != null) {
                if (item.pk.isPk()) {
                    insertColumn.append(" primary key ")
                            .append(item.pk.increment() ? " AUTO_INCREMENT " : "");
                }
            }
            appendSql(insertColumn, item);
        });
        if (insertColumn.length() > 30) {
            insertColumn.deleteCharAt(insertColumn.length() - 1);
            log.info(insertColumn.toString(), Level.INFO);
            JDBCUtils.update(insertColumn.toString());
        }
    }

    /**
     * 拼接sql语句
     *
     * @param insertColumn 上部分sql
     * @param item         字段
     */
    private static void appendSql(StringBuilder insertColumn, TableColumn item) {
        if (!"".equals(item.column.remark())) {
            insertColumn.append(" comment '");
            insertColumn.append(item.column.remark());
            insertColumn.append("' ");
        }
        insertColumn.append(",");
    }
}
