package uk.co.inhealthcare.ops.carson;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uk.co.inhealthcare.ops.carson.web.CarsonWebappApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CarsonWebappApplication.class)
@WebAppConfiguration
public class CarsonWebappApplicationTests {

	@Ignore
	@Test
	public void contextLoads() {
	}

}
