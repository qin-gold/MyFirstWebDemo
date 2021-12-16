package com.wlf.core.utlis;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.wlf.core.domain.base.LoginStatus;
import com.wlf.core.domain.base.User;
import com.wlf.core.domain.base.dto.UserData;
import com.wlf.core.domain.base.permission.Menu;

import java.util.List;
import java.util.Properties;

/**
 * 操作缓存的基本类
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 16:57
 */
public class CacheUtils {
    private static final TimedCache<String, Object> userCache;
    private static final Properties load;

    static {
        load = PropertiesLoadUtils.load("config.properties");
        userCache = CacheUtil.newTimedCache(Long.getLong(load.getProperty("userCacheTime")));
//        TimedCache<String, Object> menuCache = CacheUtil.newTimedCache(Long.getLong(load.getProperty("userCacheTime")));
    }

    public static void initCache() {

    }

    public static void setCache(String id) {
        setCache(id, null, Long.getLong(load.getProperty("userCacheTime")));
    }

    public static void setCache(String id, Object data) {
        setCache(id, data, Long.getLong(load.getProperty("userCacheTime")));
    }

    public static void setCache(String id, Object data, Long time) {
        userCache.put(id, data, time);
    }

    public static UserData getUserCache(String id) {
        return (UserData)userCache.get(id);
    }

    public static User getUser(String id){
        return getUserCache(id).getUser();
    }

    public static List<Menu> getMenu(String id) {
        return getUserCache(id).getMenus();
    }

    public static LoginStatus getLoginStatus(String id) {
        return getUserCache(id).getLoginStatus();
    }

    public static String getId(String id) {
        return getUserCache(id).getId();
    }

}
