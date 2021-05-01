package com.wlf.utlis;

import com.wlf.annotation.Column;
import com.wlf.annotation.Table;
import com.wlf.annotation.TablePk;
import com.wlf.msgEnum.DbType;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 读取注解初始化数据库
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/30 4:59
 */
public class DbInit {

    public static void createTable(Object obj){
        Connection conn = JDBCUtils.openConnection();
        StringBuilder drop = new StringBuilder();
        StringBuilder create = new StringBuilder();
        boolean present = obj.getClass().isAnnotationPresent(Table.class);
        if (!present){
            return;
        }
        String tableName = obj.getClass().getAnnotation(Table.class).value();
        drop.append(" drop table if exists ").append(tableName);
        create.append(" create table ").append(tableName).append(" ( ");
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            TablePk pk = field.getAnnotation(TablePk.class);
            Column column = field.getAnnotation(Column.class);
            if (column!=null){
                create.append(column.value()).append(" ")
                        .append(column.type().getValue())
                        .append((column.type()== DbType.Varchar||column.type()==DbType.Char||column.type()==DbType.Text)?" ("+column.length()+") ":" ")
                        .append(column.notNull()?" not null ":"");
                if (pk!=null){
                    if (pk.isPk()){
                        create.append(" primary key ")
                                .append(pk.increment()?" AUTO_INCREMENT ":"");
                    }
                }
                create.append(",");
            }
        }
        //删除多的逗号
        create.deleteCharAt(create.length()-1);
        //拼接最后的括号
        create.append(")");
        try{
            JDBCUtils.update(conn, drop.toString());
            JDBCUtils.update(conn, create.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
