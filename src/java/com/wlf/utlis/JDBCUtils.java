package com.wlf.utlis;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.*;

/**
 * 数据库操作类
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/27 12:04
 */
@Slf4j
public class JDBCUtils {
    private static final Properties load ;
    private static Connection connection ;

    static {
        load = PropertiesLoadUtils.load("config.properties");
    }

    /**
     * 数据库更新
     *
     * @author Qin ShiJiao
     * @createTime 2021/4/30 6:06
     */
    public static int update(Connection conn, String sql, Object... obj) {
        PreparedStatement ps = null;
        int res;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            res = -1;
        } finally {
            releaseResources(ps);
        }
        return res;
    }

    public static int update( String sql, Object... obj){
        return update(connection,sql,obj);
    }

    //传入参数变为List数组，这种情况有时候比上面的那个uodate更加好用，因为参数变成动态的了，在程序执行前谁都不知道到底有多少个参数
    public static int updateList(Connection conn, String sql, List<Object> datalist) {
        PreparedStatement ps = null;
        int res;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < datalist.size(); i++) {
                ps.setObject(i + 1, datalist.get(i));
            }
            res = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            res = -1;
        } finally {
            releaseResources(ps);
        }
        return res;
    }

    public static int updateList( String sql,List<Object> datalist){
        return updateList(connection,sql,datalist);
    }

    /**
     * 简单查询语句,返回一个字符串
     *
     * @author Qin ShiJiao
     * @createTime 2021/4/30 6:04
     */
    public static String queryForString(Connection conn, String sql, Object... obj) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String res = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            if (rs.next()) {
                res = rs.getString(rsmd.getColumnLabel(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = null;
        } finally {
            releaseResources(ps);
            releaseResources(rs);

        }
        return res;
    }

    public static String queryForString( String sql,Object... obj){
        return queryForString(connection,sql,obj);
    }

    /**
     * 简单查询语句,返回int值
     *
     * @author Qin ShiJiao
     * @createTime 2021/4/30 6:04
     */
    public static Integer queryForInt(Connection conn, String sql, Object... obj) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int res = -1;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            if (rs.next()) {
                res = rs.getInt(rsmd.getColumnLabel(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = -1;
        } finally {
            releaseResources(ps);
            releaseResources(rs);
        }
        return res;
    }

    public static Integer queryForInt( String sql,Object... obj){
        return queryForInt(connection,sql,obj);
    }

    public static Integer queryForIntList(Connection conn, String sql, List<Object> datalist) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer res = -1;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < datalist.size(); i++) {
                ps.setObject(i + 1, datalist.get(i));
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            if (rs.next()) {
                res = rs.getInt(rsmd.getColumnLabel(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            res = -1;
        } finally {
            releaseResources(ps);
            releaseResources(rs);
        }
        return res;
    }

    public static Integer queryForIntList( String sql,List<Object> datalist){
        return queryForIntList(connection,sql,datalist);
    }

    /**
     * 可变参数查询,查询结果记录在一个Map集合中
     *
     * @author Qin ShiJiao
     * @createTime 2021/4/30 6:06
     */
    public static List<Map<String, Object>> queryForList(Connection conn, String sql, Object... obj) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            Map<String, Object> map = null;
            hasNext(rs, list, rsmd, columnCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseResources(ps);
            releaseResources(rs);
        }
        return list;
    }

    public static List<Map<String, Object>> queryForList( String sql,Object... obj){
        return queryForList(connection,sql,obj);
    }

    private static void hasNext(ResultSet rs, List<Map<String, Object>> list, ResultSetMetaData rsmd, int columnCount) throws SQLException {
        Map<String, Object> map;
        while (rs.next()) {
            map = new HashMap<String, Object>();
            for (int i = 0; i < columnCount; i++) {
                String colunmName = rsmd.getColumnLabel(i + 1);
                Object columnValue = rs.getObject(colunmName);
                map.put(colunmName, columnValue);
            }
            list.add(map);
        }
    }

    //重载
    public static List<Map<String, Object>> queryForList_list(Connection conn, String sql, List<Object> datalist) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < datalist.size(); i++) {
                ps.setObject(i + 1, datalist.get(i));
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            Map<String, Object> map = null;
            hasNext(rs, list, rsmd, columnCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseResources(ps);
            releaseResources(rs);
        }
        return list;
    }

    public static List<Map<String, Object>> queryForList_list( String sql,List<Object> datalist){
        return queryForList(connection,sql,datalist);
    }

    /*当形参为Object[]数组时，调用该方法必须为一个数组
       当形参为Object...objects时，调用就相当灵活了，可以不带参数，可以带一个参数或者多个参数，也可以带数组作为参数*/
    public static List<Map<String, Object>> queryForListForKeyLower(Connection conn, String sql, Object... obj) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            Map<String, Object> map = null;
            while (rs.next()) {
                map = new HashMap<String, Object>();
                for (int i = 0; i < columnCount; i++) {
                    String colunmName = rsmd.getColumnLabel(i + 1);
                    Object columnValue = rs.getObject(colunmName);
                    map.put(colunmName.toLowerCase(), columnValue);
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            releaseResources(ps);
            releaseResources(rs);
        }
        return list;
    }

    public static List<Map<String, Object>> queryForListForKeyLower( String sql,Object... obj){
        return queryForListForKeyLower(connection,sql,obj);
    }

    /**
     * 开启数据库连接,使用druid连接池
     *
     * @author Qin ShiJiao
     * @createTime 2021/4/30 6:07
     * @updateDate 2021/10/13
     * 修改通过静态代码块加载
     */
    public static Connection openConnection() {
        Connection con = null;
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(load);
            con = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * 归还连接池连接
     *
     * @author Qin ShiJiao
     * @createTime 2021/4/30 6:08
     */
    public static <T> void releaseResources(T t) {
        if (t != null) {
            try {
                // 利用反射，获取class对象
                Class<?> aClass = t.getClass();
                // 获取class对象中的方法对象
                Method close = aClass.getMethod("close");
                // 执行方法
                close.invoke(t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化connection
     */
    public static void initConnection(){
        if (connection==null)
        connection = openConnection();
        log.info("init DB");
    }
}

