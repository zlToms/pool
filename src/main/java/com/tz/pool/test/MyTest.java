package com.tz.pool.test;
import com.tz.pool.start.SpringbootTest;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootTest.class)
@WebAppConfiguration
public class MyTest {

    private static final Logger logger = LoggerFactory.getLogger(MyTest.class);

    @Autowired
    private RestHighLevelClient client;

    private String name;


}
