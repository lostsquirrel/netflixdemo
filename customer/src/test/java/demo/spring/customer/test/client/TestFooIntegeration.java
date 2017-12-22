package demo.spring.customer.test.client;

import demo.spring.customer.integration.FooIntegration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("junit")
public class TestFooIntegeration {

    @Autowired
    FooIntegration fooIntegration;

    @Test
    public void testNormal() {
        Map<String, Object> param = new HashMap<>();
        Assert.assertEquals("normal result", fooIntegration.getStores(param));
    }

    @Test
    public void testDefault() {
        Map<String, Object> param = new HashMap<>();
        param.put("sleep", 123);
        Assert.assertEquals("default result", fooIntegration.getStores(param));
    }
}
