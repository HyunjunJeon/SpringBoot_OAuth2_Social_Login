package security_oauth.org;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Before
	public void setup() {
		RestAssured.port = 8080;
	}

	@Test
	public void 기본path로_접근하면_index_html_호출된다 () throws Exception {
		given()
				.when()
				.get("/")
				.then()
				.statusCode(200)
				.contentType("text/html")
				.body(containsString("권한 관리"));
	}
}
/*
	이걸 그대로 실행시키면 404 에러
	그 어떤 Controller도 생성되지 않았기 때문

    resources/static/index.html
    추가 후 Application.java를 실행시켜 localhost:8080으로 접속하셔서 확인해보셔도 됩니다.
    Controller가 없어도 index.html을 /로 호출할 수 있는 이유는 스프링부트에서 기본적으로 static 디렉토리 아래에 있는
    index.html을 / path로 지정해줌
    앞으로는 설정이 변경될때마다 다시 스프링부트를 실행시킬 필요 없이 테스트 코드를 바로 실행시켜 테스트 하면 되겠죠?
 */
