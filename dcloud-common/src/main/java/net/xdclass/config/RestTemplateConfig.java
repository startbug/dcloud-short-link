package net.xdclass.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        return new RestTemplate(clientHttpRequestFactory);
    }

    // 使用HttpComponentsClientHttpRequestFactory，池化http连接 提高效率 ---------------------------------------------------------------------------
    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    public HttpClient httpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);

        // 设置连接池最大是500个连接
        connectionManager.setMaxTotal(500);
        // MaxPerRoute是对maxTotal的细分，每个主机的并发最大是300，route是指域名
        connectionManager.setDefaultMaxPerRoute(300);

        /**
         * 场景一：
         * 只是请求xdclass.net，最大并发300(虽然连接池有500个连接，但是设置了每个域名最大并发数是300，所以最大是300)
         *
         * 场景二：
         * 两个域名并发数量累加不能超过500，每个域名并发数不超过300
         * 请求xdlass.net,最大并发300
         * 请求baidu.com,最大并发200
         */
        RequestConfig requestConfig = RequestConfig.custom()
                // 返回数据的超时时间
                .setSocketTimeout(20000)
                // 连接上服务器的超时时间
                .setConnectTimeout(10000)
                // 从连接池中获取连接的超时时间
                .setConnectionRequestTimeout(1000)
                .build();

        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();

        return closeableHttpClient;
    }


    // 使用SimpleClientHttpRequestFactory，每次请求建立一次http连接----------------------------------------------------------------
//    @Bean
//    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
//        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
//        factory.setReadTimeout(10000);
//        factory.setConnectTimeout(10000);
//        return factory;
//    }

}
