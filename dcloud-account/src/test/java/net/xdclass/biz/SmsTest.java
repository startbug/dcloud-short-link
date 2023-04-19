package net.xdclass.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.AccountApplication;
import net.xdclass.component.SmsComponent;
import net.xdclass.config.SmsConfig;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/4/19 20:33
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountApplication.class)
public class SmsTest {

    @Autowired
    private SmsComponent smsComponent;

    @Autowired
    private SmsConfig smsConfig;

    @Test
    public void testSendSms() {
        smsComponent.send("18027424035", smsConfig.getTemplateId(), "666888");
    }

}
