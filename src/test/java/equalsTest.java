import com.wlf.core.utlis.EqualsUtils;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 14:40
 */
public class equalsTest {
    public static void main(String[] args) {
        boolean ll = EqualsUtils.equalsAll("ok.js");
        boolean ll2 = EqualsUtils.equalsAll("ok.j2s");
        System.out.println(ll);
        System.out.println(ll2);
    }
}
