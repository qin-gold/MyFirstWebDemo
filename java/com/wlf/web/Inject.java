package com.wlf.web;

import com.wlf.utlis.DataKitUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 通过传入request对象和类对象将根据name将数据封装到类中
 * 通过暴力注入getBeanForce(慎用）推荐使用第二个getBean方法
 * 当前方法同一个name存在多个值存在只会读取第一个值
 * 功能类似于SpringMvc的自动装配
 * 类似于JFinal的getBean方法
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-28 15:57
 */
public class Inject<T> {
    /** 暴力注入
     * @param request
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T getBeanForce(HttpServletRequest request, Class<T> t){
        try{
            if (request==null){
                throw new RuntimeException("实例不存在");
            }
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (parameterMap.size()<=0){
                throw new RuntimeException("实例不存在");
            }
            Set<String> set = new HashSet<>();
            Field[] fields = t.getDeclaredFields();
            String simpleName = t.getSimpleName();
            T ts = t.newInstance();
            for (int i =0 ; i<fields.length;i++){
              set.add(fields[i].getName());
            }
            set.stream().filter(item->parameterMap.containsKey(simpleName+'.'+item)).forEach(item->{
                try {
                    Field field = t.getDeclaredField(item);
                    //暴力注入
                    field.setAccessible(true);
                    field.set(ts,parameterMap.get(simpleName+'.'+item)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } );
            return ts;
        }catch (Exception e){
            e.printStackTrace();
        }
       return null;
    }

    /** 非暴力注入
     * @param request
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T getBean(HttpServletRequest request, Class<T> t){
        try{
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (parameterMap.size()<=0){
                throw new RuntimeException("实例不存在");
            }
            Set<String> set = new HashSet<>();
            Field[] fields = t.getDeclaredFields();
            String simpleName = t.getSimpleName();
            T ts = t.newInstance();
            for (int i =0 ; i<fields.length;i++){
                set.add(fields[i].getName());
            }
            set.stream().filter(item->parameterMap.containsKey(simpleName+'.'+item)).forEach(item->{
                try {
                    Method method = t.getMethod("set"+ DataKitUtils.firstCharToLowerCase(item),String.class);
                    method.invoke(ts,parameterMap.get(simpleName+'.'+item)[0]);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } );
            return ts;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
