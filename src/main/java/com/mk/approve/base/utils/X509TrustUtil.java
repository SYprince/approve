package com.mk.approve.base.utils;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Description:
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 21:20 2020/3/22
 */
public class X509TrustUtil implements X509TrustManager {


      @Override
public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

}

@Override
public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

}

@Override
public X509Certificate[] getAcceptedIssuers() {

return null;
}

}