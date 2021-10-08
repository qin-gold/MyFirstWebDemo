import com.wlf.dao.LoginDao;
import com.wlf.dao.impl.LoginDaoImpl;
import com.wlf.domain.Account;
import com.wlf.utlis.MD5Utils;
import com.wlf.utlis.Result;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * @author Qin
 * @createDate 2021/4/27 0:10
 * @updateDate 2021/4/27 0:10
 */
public class LoginTest {
//    public static void main(String[] args) {
//        Account account = new Account();
//        account.setUsername("123");
//        account.setPassword("123");
//        LoginDao loginDao =new LoginDaoImpl();
//        Result result = loginDao.checkAccount("123");
//        Result result2 = loginDao.checkAccount("123");
//        Result result3 = loginDao.checkAccount("123");
//        System.out.println(result.getMsg());
//        System.out.println(result2.getMsg());
//        System.out.println(result3.getMsg());
//    }
    @Test
    public void TestInsert(){
        Account account =new Account();
        LoginDao loginDao =new LoginDaoImpl();
        account.setId(UUID.randomUUID().toString());
        account.setUsername("qsj123");
        account.setPassword(MD5Utils.md5("qsj123"));
        account.setUserId("123");
        account.setCreateTime(new Timestamp(System.currentTimeMillis()));
        account.setRemark("这是一个简单的账号");
        Result result = loginDao.insert(account);
        System.out.println(result.getMsg());
    }

    @Test
    public void TestUpdate(){
        Account account =new Account();
        LoginDao loginDao =new LoginDaoImpl();
        account.setId("63f01739-6939-4c79-ad39-70ec6231dcb5");
        account.setUsername("qsj123");
        account.setPassword(MD5Utils.md5("qsj123"));
        account.setUserId("1234");
        account.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        account.setRemark("爱好安慰");
        Result result = loginDao.update(account);
        System.out.println(result.getMsg());
    }

    @Test
    public void TestDelete(){
        LoginDao loginDao =new LoginDaoImpl();
        Result delete = loginDao.delete("63f01739-6939-4c79-ad39-70ec6231dcb5");
        System.out.println(delete.getMsg());
    }

    @Test
    public void TestIsLogin(){
        LoginDao loginDao =new LoginDaoImpl();
        Result delete = loginDao.isLogin("11");
        System.out.println(delete.getMsg());
    }
}
