package ke.co.kip.sec;

import ke.co.kip.sec.web.ProductTypeController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecApplicationTests {

    @Autowired
    private ProductTypeController prodTpController;

    @Test
    public void contextLoads() {
        Assert.assertTrue("Prod Type Controller should not be null",prodTpController!=null);
    }

}
