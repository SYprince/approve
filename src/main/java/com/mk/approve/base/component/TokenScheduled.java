package com.mk.approve.base.component;

import com.alibaba.fastjson.JSONObject;
import com.mk.approve.entity.GlobalToken;
import com.mk.approve.mapper.GlobalTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 0:19 2020/3/22
 */
@Component
@Service
public class TokenScheduled {

    @Autowired
    GlobalTokenMapper globalTokenMapper;

    /**
     * access_token 的有效期目前为 2 个小时，需定时刷新，重复获取将导致上次获取的 access_token 失效；
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     * @throws KeyManagementException
     */
    //@Scheduled(cron="0 */120 * * * ? ")
    @Scheduled(fixedDelay = 60*60*1000*2)
    public void text1() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        JSONObject o   = RestTemplateConfig.newRestTemplate().getForEntity("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+RestTemplateConfig.appid+"&secret="+RestTemplateConfig.appsecret,JSONObject.class).getBody();
        //成功返回 access_token expires_in     失败返回errcode  errmsg
        Integer errcode = (Integer) o.get("errcode");
        if(errcode == null){
            //成功
            Date date = new Date();
            String gmtString = date.toLocaleString();
            GlobalToken globalToken = new GlobalToken();
            globalToken.setAccessToken(o.getString("access_token"));
            globalToken.setUpdateTime(gmtString);

            List<GlobalToken> l = globalTokenMapper.selectAll();
            if(l.size() == 0){
                globalTokenMapper.insertSelective(globalToken);
            }else {
                globalToken.setId(l.get(0).getId());
                globalTokenMapper.updateByPrimaryKeySelective(globalToken);
            }

        }else {
            System.out.println("获取token接口失败："+o.get("errcode")+o.get("errmsg"));
        }

    }
}
