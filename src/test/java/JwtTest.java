import com.wlf.core.utlis.JwtUtils;

/**
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/11 22:05
 */
public class JwtTest {
    public static void main(String[] args) {

//        JWTSigner jwtSigner = JWTSignerUtil.hs256("1223123123".getBytes(StandardCharsets.UTF_8));
//        String token = JWT.create().setPayload("id", "123123").setIssuedAt(DateUtil.date()).setExpiresAt(DateUtil.tomorrow()).setSigner(jwtSigner).sign();
//
//        System.out.println(JWTUtil.verify(token, "123123123".getBytes(StandardCharsets.UTF_8)));
//
//        JWTValidator.of(token).validateAlgorithm(JWTSignerUtil.hs256("123123123".getBytes(StandardCharsets.UTF_8))).validateDate(DateUtil.date(),0);
//
//        boolean b = JWT.of(token).setKey("123123123".getBytes()).validate(0);
//        System.out.println(b);
//
//        Object payload = JWT.of(token).getPayload("id");
//        System.out.println(payload.toString());
        String token = JwtUtils.create("123123");
        System.out.println(token);
        System.out.println(JwtUtils.getValue(token));

    }
}
