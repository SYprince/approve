package com.mk.approve.base.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.mk.approve.base.component.RestTemplateConfig;
import com.mk.approve.entity.CloudFileResponse;
import com.mk.approve.entity.GlobalToken;
import com.mk.approve.entity.SteelDamageInfo;
import com.mk.approve.mapper.GlobalTokenMapper;
import com.mk.approve.mapper.SteelDamageInfoMapper;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.pattern.PathPattern;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 访问小程序云存储文件
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 22:31 2020/3/21
 */
@Service
public class DownloadCloudFile {

    @Value("${path.filepath}")
    private String filepath;

    public String downloadImg(String imgUrl) throws Exception {
        //存储图片时  图片名用截取的、
        String picName = "";
        if(imgUrl == "" || imgUrl == null){
            picName = "none.png";
        }else {
            int start = imgUrl.lastIndexOf("/") + 1;
            picName = imgUrl.substring(start);
        }
        //SSLContext sslcontext = SSLContext.getInstance("SSL", "SunJSSE");
        //sslcontext.init(null, new TrustManager[] { new X509TrustUtil() }, new java.security.SecureRandom());
        //URL url = new URL(imgUrl);
        //HostnameVerifier ignoreHostnameVerifier = new HostnameVerifier() {
        //public boolean verify(String s, SSLSession sslsession) {
        //System.out.println("WARNING: Hostname is not matched for cert.");
        //return true;
        //}
        //};
        //HttpsURLConnection.setDefaultHostnameVerifier(ignoreHostnameVerifier);
        //HttpsURLConnection.setDefaultSSLSocketFactory(sslcontext.getSocketFactory());
        //HttpsURLConnection urlCon = (HttpsURLConnection) url.openConnection();
        //urlCon.setConnectTimeout(6000);
        //urlCon.setReadTimeout(6000);
        //int code = urlCon.getResponseCode();
        //if (code != HttpURLConnection.HTTP_OK) {
        //throw new Exception("文件读取失败");
        //}
        DataInputStream dataInputStream = new DataInputStream(new URL(imgUrl).openStream());
        //(urlCon.getInputStream());

        FileOutputStream fileOutputStream = new FileOutputStream(new File(filepath+"file/img/"+picName));

        byte[] buffer = new byte[1024];
        int length;

        while ((length = dataInputStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, length);
        }

        dataInputStream.close();
        fileOutputStream.close();

return picName;

    }



}
