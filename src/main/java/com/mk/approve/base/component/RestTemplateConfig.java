package com.mk.approve.base.component;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * Description: 注入新的restTemplate（不验证ssl证书）访问https
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 23:04 2020/3/21
 */
//@Configuration
public class RestTemplateConfig {

    static final String appid = "wx6008b649e0679e3e";
    static final String appsecret = "657bd4db6eff32a3ca9da686ebd8e805";
    //@Bean
    //public RestTemplate restTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
    //    TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
    //
    //    SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
    //            .loadTrustMaterial(null, acceptingTrustStrategy)
    //            .build();
    //
    //    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
    //
    //    CloseableHttpClient httpClient = HttpClients.custom()
    //            .setSSLSocketFactory(csf)
    //            .build();
    //
    //    HttpComponentsClientHttpRequestFactory requestFactory =
    //            new HttpComponentsClientHttpRequestFactory();
    //
    //    requestFactory.setHttpClient(httpClient);
    //    RestTemplate restTemplate = new RestTemplate(requestFactory);
    //    return restTemplate;
    //}

    public static RestTemplate newRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        return restTemplate;
    }
}
