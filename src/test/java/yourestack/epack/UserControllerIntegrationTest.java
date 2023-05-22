package yourestack.epack;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Value(value="${local.server.port}")
    private int port;

    @Test
    public void loginPageTest() throws Exception {
        assertThat(this.restTemplate.getForObject( "http://localhost:" + port +  "/estack/loginForm",
                String.class)).contains("Please login:");
    }

    @Test
    public void signupPageTest() throws Exception {
        assertThat(this.restTemplate.getForObject( "http://localhost:" + port +  "/estack/signupForm",
                String.class)).contains("Please fill in the registration form:");
    }

    @Test
    public void allEpacksPageTest() throws Exception {
        assertThat(this.restTemplate.getForObject( "http://localhost:" + port +  "/estack/allEpacks",
                String.class)).contains("Courses available:");
    }

}