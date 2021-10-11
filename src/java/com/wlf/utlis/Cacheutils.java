package com.wlf.utlis;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;

import java.util.Properties;

/**
 * 操作缓存的基本类
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 16:57
 */
public class Cacheutils {
    private static final TimedCache<String, Object> userCache;
    private static final Properties load;
    static {
        load = PropertiesLoadUtils.load("config.properties");
        userCache = CacheUtil.newTimedCache(Long.getLong(load.getProperty("userCacheTime")));
//        TimedCache<String, Object> menuCache = CacheUtil.newTimedCache(Long.getLong(load.getProperty("userCacheTime")));
    }

    public static void setCache(String name){
        setCache(name,null,Long.getLong(load.getProperty("userCacheTime")));
    }

    public static void setCache(String name,Object data){
        setCache(name,data,Long.getLong(load.getProperty("userCacheTime")));
    }

    public static void setCache(String name,Object data,Long time){
        userCache.put(name,data,time);
    }

    public static void initCache(){

    }

    public static TimedCache<String, Object> getCache(){
        return null;
    }

}
