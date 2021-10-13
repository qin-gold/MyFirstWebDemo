import com.wlf.utlis.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/13 22:17
 */
public class JdbcTest {
    public static void main(String[] args) {
        JDBCUtils.initConnection();
        List<String> list2 =new ArrayList<String>(10);
        List<Map<String, Object>> list = JDBCUtils.queryForList("show full columns from b_log");
        for (Map<String, Object> map : list) {
            list2.add((String) map.get("Field"));
        }
        System.out.println(list2);
    }
}
