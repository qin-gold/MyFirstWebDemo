import cn.hutool.crypto.SmUtil;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 17:26
 */
public class TestSM4 {
    public static void main(String[] args) {
        String hex = SmUtil.sm3("123");
        System.out.println(hex);
    }
}
