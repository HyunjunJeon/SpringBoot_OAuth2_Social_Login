package security_oauth.org.config;

/**
 * Created by jeonhj920@gmail.com on 2018. 3. 2.
 * Blog : http://HyunjunJeon.github.io
 * Github : http://github.com/HyunjunJeon
 */
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "spring.config.location=classpath:/google.yml")
public class OAuthConfigTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    public void google로그인_시도하면_OAuth인증창_등장한다 () throws Exception {
        given()
                .when()
                .redirects().follow(false) // 리다이렉트 방지
                .get("/login")
                .then()
                .statusCode(302)
                .header("Location", containsString("https://accounts.google.com/o/oauth2/auth"));
    }
    /*
        구글 로그인 역시 해당 서비스의 로그인이 필요하게 되어, 웹 페이지에서 리다이렉션이 발생합니다.
        그래서 로그인 진행시 (/login) OAuth 리다이렉션이 진행되는지 테스트 코드를 작성해보겠습니다.
        src/test/java 아래에 config 패키지를 추가하여 OAuthConfigTest.java 파일을 생성하겠습니다

        고정된 포트(8080)으로 사용할 것이기에 SpringBootTest.WebEnvironment.DEFINED_PORT 옵션을 추가하였습니다.
        추가로 google.yml을 Junit 테스트시에도 설정으로 적용할 수 있게 @TestPropertySource으로 옵션을 추가하였습니다.
        (이게 없으면 google.yml을 테스트시에는 누락된채로 진행됩니다.)
        로그인 URL은 /login으로 할 예정입니다.

        then()이후 옵션들은 OAuth 로그인 검증 코드입니다.
     */
}