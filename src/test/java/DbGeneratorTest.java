import com.wlf.domain.base.User;
import com.wlf.domain.dto.ReturnMsg;
import com.wlf.msgEnum.MsgCode;
import com.wlf.utlis.DbGenerator;
import org.junit.Test;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 14:28
 */
public class DbGeneratorTest {

    public static void main(String[] args) {
        ReturnMsg msg = new ReturnMsg(MsgCode.ERR001);
        System.out.println(msg);
//        DbGenerator.createTable(User.class);
    }
    public void TestCreate(){
    }
}
