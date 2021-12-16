import com.wlf.core.annotation.Log;
import com.wlf.core.web.base.servlet.login.LoginAccountServlet;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/12 22:16
 */
public class AnnotationTest {
    public static void main(String[] args) {
        Log log = LoginAccountServlet.class.getAnnotation(Log.class);
        System.out.println(log.remark());
    }
}
