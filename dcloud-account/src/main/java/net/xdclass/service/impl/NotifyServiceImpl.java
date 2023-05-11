package net.xdclass.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.service.NotifyService;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/4/19 21:07
 */
@Slf4j
@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
//    @Async(value = "threadPoolTaskExecutor")
    public void testSend() {
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        ResponseEntity<String> forEntity = restTemplate.getForEntity("https://www2c1.53kf.com/company_collection.php", String.class);
        String body = forEntity.getBody();
        log.info("body={}", body);
    }

}
