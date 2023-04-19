package net.xdclass.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.xdclass.config.SmsConfig;

/**
 * @Author starbug
 * @Description
 * @Datetime 2023/4/19 18:46
 */
@Slf4j
@Configuration
public class SmsComponent {

    /**
     * 发送地址
     */
    private static final String SMS_BASE_URL = "https://jmsms.market.alicloudapi.com/sms/send?mobile=%s&templateId=%s&value=%s";

    /**
     * 请求头
     */
    private static final String SMS_AUTHORIZATION = "APPCODE %s";

    @Autowired
    private SmsConfig smsConfig;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 文档地址:https://market.aliyun.com/products/57000002/cmapi00046920.html#sku=yuncode4092000002
     */
    public void send(String to, String templateId, String value) {
        String url = String.format(SMS_BASE_URL, to, templateId, value);
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, String.format(SMS_AUTHORIZATION, smsConfig.getAppCode()));

        HttpEntity<String> entity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        log.info("url={},body={}", url, response.getBody());
        if (response.getStatusCode() == HttpStatus.OK) {
            log.info("发送短信成功,响应信息:{}", response.getBody());
        } else {
            log.info("发送短信失败,响应信息:{}", response.getBody());
        }
    }

}
