import com.wlf.ext.dao.StudentDao;
import com.wlf.ext.dao.impl.StudentDaoImpl;
import com.wlf.core.domain.dto.Result;

/**
 * @author Qin
 * @createDate 2021/4/27 0:52
 * @updateDate 2021/4/27 0:52
 */
public class StudentTest {
    public static void main(String[] args) {
        StudentDao studentDao = new StudentDaoImpl();
//        Result byId = studentDao.findById("1");
//        System.out.println(byId.getCode());
//        System.out.println(byId.getMsg());
//        System.out.println(byId.getData());
        System.out.println("---------");
        Result all = studentDao.findAll(0, 5, "");
        System.out.println(all.getData());
    }
}
