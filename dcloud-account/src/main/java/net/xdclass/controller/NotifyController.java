package net.xdclass.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.xdclass.service.NotifyService;
import net.xdclass.util.JsonData;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/4/19 21:06
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notify")
public class NotifyController {

    private final NotifyService notifyService;

    /**
     * 测试发送验证码接口-主要是用于对比优化前后区别
     */
    @GetMapping("/send_code")
    public JsonData sendCode() {
        notifyService.testSend();
        return JsonData.buildSuccess();
    }

}
