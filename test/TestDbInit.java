import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Qin
 * @createTime 2021/4/30 5:07
 */
public class TestDbInit {
    @Test
    public void Test1(){
//        DbInit.createTable(new UserGroup());
//        System.out.println("".equals("") | "" == null);
    }

    @Test
    public void Test2(){
        ArrayList<String> arrayList =new ArrayList();
        arrayList.add("张三");
        arrayList.add("张四");
        arrayList.add("李无");
        arrayList.add("张三风");
        arrayList.add("张三s");
        arrayList.add("张三3");

        long count = arrayList.stream().filter(item -> item.length() < 3).filter(item -> item.charAt(0) == '张').count();
        System.out.println(count);
    }
}
