import com.wlf.utlis.DataKitUtils;
import org.junit.Test;

/**
 * @author QinShijiao
 * @version 1.0
 * @date 2021-04-29 8:58
 */
public class KitTest {
    @Test
    public void KisTest(){
        String name = DataKitUtils.firstCharToLowerCase("asdasda");
        System.out.println(name);
    }
}
