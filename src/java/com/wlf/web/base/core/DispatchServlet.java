package com.wlf.web.base.core;

import com.wlf.web.base.annotation.*;
import org.springframework.core.DefaultParameterNameDiscoverer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/12/16 11:04
 */
public class DispatchServlet extends HttpServlet {
    //保存application.properties配置文件中的内容
    private final Properties contextConfig = new Properties();
    //保存扫描的所有的类名
    private final List<String> classNames = new ArrayList<>();
    // 传说中的IOC容器，为了简化程序只做演示，不考虑线程安全问题，不考虑ConcurrentHashMap
    private final Map<String, Object> ioc = new ConcurrentHashMap<>();
    //保存url和method 的对应关系
    // 为什么不用map?
    // 用Map，key只能是url ，但是HandlerMapping本身功能就是把url跟method关系对应，已经具备map的功能
    //根据设计原则：单一职能原则，最少知道原则。
    private final List<HandlerMapping> handlerMapping = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6、调用，运行阶段
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception , Detail:" + Arrays.toString(e.getStackTrace()));
        }
    }


    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HandlerMapping handlerMapping = getHandle(req);
        if (handlerMapping == null) {
            resp.getWriter().write("My DispatcherServlet 404 Not Found");
            return;
        }
        Object[] paramValues = new Object[handlerMapping.paramTypes.length];
        Map<String, String[]> params = req.getParameterMap();
        for (Map.Entry<String, String[]> param : params.entrySet()) {
            if (!handlerMapping.paramIndexMapping.containsKey(param.getKey())) continue;
            Integer index = handlerMapping.paramIndexMapping.get(param.getKey());
            paramValues[index] = convert(handlerMapping.paramTypes[index], param.getValue());
        }

        if (handlerMapping.paramIndexMapping.containsKey(HttpServletRequest.class.getName()))
            paramValues[handlerMapping.paramIndexMapping.get(HttpServletRequest.class.getName())] = req;
        if (handlerMapping.paramIndexMapping.containsKey(HttpServletResponse.class.getName()))
            paramValues[handlerMapping.paramIndexMapping.get(HttpServletResponse.class.getName())] = resp;

        handlerMapping.method.invoke(handlerMapping.controller, paramValues);
    }

    private HandlerMapping getHandle(HttpServletRequest req) {
        if (this.handlerMapping.isEmpty()) return null;
        // 绝对路径->处理成相对路径
        String url = req.getRequestURI().replaceAll(req.getContextPath(), "").replaceAll("/+", "/");
        for (HandlerMapping mapping : this.handlerMapping) {
            if (mapping.getUrl().equals(url)) return mapping;
        }
        return null;
    }

    //进行数据类型转换
    //Spring 做了顶层转换策略  public interface Converter<S, T> 实现了很多转换类型
    private Object convert(Class<?> type, String[] value) {
        //如果是int
        if (Integer.class == type) {
            return Integer.valueOf(value[0]);
        } else if (Integer[].class == type) {
            //do something
            return null;
        } else if (String.class == type) {
            return value[0];
        } else if (String[].class == type) {
            return value;
        }
        //如果还有double或者其他类型，继续加if
        //这时候，我们应该想到策略模式了,在这里暂时不实现
        return value;
    }

    //初始化阶段
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 1、加载配置文件
        doLoadConfig(config.getInitParameter("application"));
        //2、扫描相关的类
        doScanner(contextConfig.getProperty("ScannerWeb"));
        //3、初始化扫描到的类，并且将它们放到IOC容器之中
        doInstance();
        //4、完成依赖注入
        doAutowired();
        //5、初始化HandlerMapping
        initHandlerMapping();

        System.out.println("My Spring Framework Is Init");
    }

    // 初始化url和Method的一对一的关系
    private void initHandlerMapping() {
        if (ioc.isEmpty()) return;

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(Controller.class)) continue;

            //保存写在类上面的@RequestMapping("/demo")
            String baseUrl = "";
            if (clazz.isAnnotationPresent(RequestMapping.class)) {
                RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
                baseUrl = requestMapping.value();
            }
            //默认获取所有的public方法
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(RequestMapping.class)) continue;
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                this.handlerMapping.add(new HandlerMapping(url, entry.getValue(), method));
                System.out.println("Mapped :" + url + "," + method);
            }
        }
    }

    private void doAutowired() {
        if (ioc.isEmpty()) return;

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //Declared 所有的，特定的，字段。包括private，protected，default
            // 正常来讲，普通的OOP编程只能拿到public的属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(Autowired.class)) continue;
                Autowired autowired = field.getAnnotation(Autowired.class);

                // 如果没有自定义beanName 则采用类型注入
                String beanName = autowired.value().trim();
                //field.getType().getName()  获得接口类型作为key去取
                if ("".equals(beanName)) beanName = field.getType().getName();

                //强吻 暴力访问
                field.setAccessible(true);
                try {
                    //用反射机制，动态给字段属性复制
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void doInstance() {
        //初始化，为DI做准备

        //如果为null 就不往下走
        if (classNames.isEmpty()) return;

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                //这里只需要初始化加了我们自定义注解的类
                //这里只是做演示，体会其流程，设计思想，只举例@Controller 和@Service...

                if (clazz.isAnnotationPresent(Controller.class)) {
                    //Spring默认类名首字母小写
                    ioc.put(toLowerFirstCase(clazz.getSimpleName()), clazz.getConstructor().newInstance());
                } else if (clazz.isAnnotationPresent(Service.class)) {
                    //1、自定义beanName
                    Service service = clazz.getAnnotation(Service.class);
                    String beanName = service.value();
                    //2、如果没有自定义beanName，则默认类名首字母小写
                    if ("".equals(beanName.trim())) beanName = toLowerFirstCase(clazz.getSimpleName());
                    Object newInstance = clazz.getDeclaredConstructor().newInstance();
                    ioc.put(beanName, newInstance);

                    //3、根据类型自动赋值 这里是找到他的所有接口然后给他实现类的值，是为了Autowired的时候方便（在注入的时候直接用接口类型去ioc取）
                    for (Class<?> anInterface : clazz.getInterfaces()) {
                        if (ioc.containsKey(anInterface.getName()))
                            throw new Exception("The" + anInterface.getName() + " is exists!!!");
                        // 把接口的类型直接当成key
                        ioc.put(anInterface.getName(), newInstance);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //转换首字母小写
    private String toLowerFirstCase(String beanName) {
        return beanName.replaceFirst("^.", String.valueOf(beanName.charAt(0)).toLowerCase());
    }


    //扫描出相关的类
    private void doScanner(String scanPackage) {
        //scanPackage = com.wlf.web
        //需要把包路径转换为文件路径 classpath 路径
        URL url = this.getClass().getClassLoader().getResource( scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(Objects.requireNonNull(url).getFile());
        for (File file : Objects.requireNonNull(classPath.listFiles())) {
            //如果是文件夹，就还需要往下循环一层一层的找
            if (file.isDirectory()) doScanner(scanPackage + "." + file.getName());
            else {
                //只扫描class文件
                if (!file.getName().endsWith(".class")) continue;
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                classNames.add(className);
            }
        }
    }

    //加载配置文件
    private void doLoadConfig(String contextConfigLocation) {
        //直接从类路径下找到Spring主配置文件所在的路径，并且将其读取出来放到Properties对象中
        //相当于把scanPackage=com.jarvisy.demo.mvc 从文件中保存到了内存中。
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation)) {
            contextConfig.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //保存一个url和一个Method的关系
    static class HandlerMapping {
        //请求url
        private final String url;
        // url对应的method
        private final Method method;
        private final Object controller;
        //形参列表 参数的名字作为key，参数的顺序位置作为值
        private final Map<String, Integer> paramIndexMapping;
        private final Class<?>[] paramTypes;


        public HandlerMapping(String url, Object controller, Method method) {
            this.url = url;
            this.method = method;
            this.controller = controller;
            paramIndexMapping = new HashMap<>();
            paramTypes = method.getParameterTypes();
            putParamIndexMapping(method);
        }

        private void putParamIndexMapping(Method method) {
            DefaultParameterNameDiscoverer discover = new DefaultParameterNameDiscoverer();
            //获取方法参数的真实名称
            String[] parameterNames = discover.getParameterNames(method);
            //提取方法中加了注解的参数
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < paramTypes.length; i++) {
                Class<?> type = paramTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramIndexMapping.put(type.getName(), i);
                    continue;
                }
                String paramName = "";
                for (Annotation a : pa[i]) {
                    if (a instanceof RequestParam) {
                        paramName = ((RequestParam) a).value();
                        break;
                    }
                }
                if ("".equals(paramName)) {
                    assert parameterNames != null;
                    paramName = parameterNames[i];
                }
                paramIndexMapping.put(paramName, i);
            }
        }


        public String getUrl() {
            return url;
        }

        public Method getMethod() {
            return method;
        }

        public Object getController() {
            return controller;
        }

        public Class<?>[] getParamTypes() {
            return paramTypes;
        }
    }
}
