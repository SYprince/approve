package com.mk.approve.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mk.approve.base.component.RestTemplateConfig;
import com.mk.approve.base.service.BaseServiceImpl;
import com.mk.approve.base.utils.DownloadCloudFile;
import com.mk.approve.base.utils.ExcelUtils;
import com.mk.approve.controller.UploadController;
import com.mk.approve.entity.CloudFileRequest;
import com.mk.approve.entity.File;
import com.mk.approve.entity.SteelDamageInfo;
import com.mk.approve.entity.UserInfo;
import com.mk.approve.mapper.GlobalTokenMapper;
import com.mk.approve.mapper.SteelDamageInfoMapper;
import com.mk.approve.mapper.UserInfoMapper;
import com.mk.approve.service.IUserInfoBaseService;
import com.mk.approve.service.SteelDamageInfoService;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description:
 * version:1.0.0
 * @Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 20:40 2020/3/12
 */
@Service
public class SteelDamageInfoServiceImpl extends BaseServiceImpl<SteelDamageInfoMapper, SteelDamageInfo> implements SteelDamageInfoService {

    private static final Logger logger = LoggerFactory.getLogger(SteelDamageInfoServiceImpl.class);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    GlobalTokenMapper globalTokenMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Autowired
    SteelDamageInfoMapper steelDamageInfoMapper;

    @Autowired
    ExcelUtils excelUtils;
    @Autowired
    DownloadCloudFile downloadCloudFile;
    /**
     * 查询列表信息
     * @param account
     * @param identityType 1发起人  2一级审批人  3二级审批人
     * @return
     */
    @Override
    public List<SteelDamageInfo> getSteelDamageInfoList(String account, Integer identityType) {
        //UserInfo userInfo = new UserInfo();
        //userInfo.setAccount(account);
        //userInfo.setDelFlag(0);
        ////
        //UserInfo loginUser = userInfoMapper.selectOne(userInfo);

        List<SteelDamageInfo> result = new ArrayList<>();
        if(1 == identityType){
            result = steelDamageInfoMapper.getSteelListByPromoterId(account);
        }else if(2 == identityType){
            result = steelDamageInfoMapper.getSteelListByFirstApproverId(account);
        }else if(3 == identityType){
            result = steelDamageInfoMapper.getSteelListBySecondaryApproverId(account);
        }

        return result;
    }

    @Override
    public SteelDamageInfo getSteelDetail(Integer steelId) {
        SteelDamageInfo steelDamageInfo = steelDamageInfoMapper.selectByPrimaryKey(steelId);
        Integer promoterId = steelDamageInfo.getPromoterId();
        Integer firstApproverId = steelDamageInfo.getFirstApproverId();
        Integer secondaryApproverId = steelDamageInfo.getSecondaryApproverId();
        if(promoterId != null){
            steelDamageInfo.setPromoterName(getUserNameByUserId(promoterId));
        }
        if(firstApproverId != null){
            steelDamageInfo.setFirstApproverName(getUserNameByUserId(firstApproverId));
        }
        if(secondaryApproverId != null){
            steelDamageInfo.setSecondaryApproverName(getUserNameByUserId(secondaryApproverId));
        }
        //steelDamageInfo.getSteelFlowDate();
        return steelDamageInfo;
    }

    private String getUserNameByUserId(Integer userId){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
        return userInfo.getUserName();
    }

    /**
     * 更新 存单状态 1.发起 2.一级审批人 3.二级审批人 4.完成
     * @param steelId
     * @param flowState
     * @return
     */
    @Override
    public int steelInfoApprove(String steelId, Integer flowState,Integer secondaryApproverId,String handwritingsignUrl ,Integer identityType) {
        SteelDamageInfo steelDamageInfo = new SteelDamageInfo();
        steelDamageInfo.setSteelId(Integer.valueOf(steelId));
        steelDamageInfo.setFlowState(flowState);
        steelDamageInfo.setSecondaryApproverId(secondaryApproverId);
        if(identityType == 2){
            steelDamageInfo.setHandwritingsignUrl1(handwritingsignUrl);
        }else if(identityType == 3){
            steelDamageInfo.setHandwritingsignUrl2(handwritingsignUrl);
        }
        return this.baseMapper.updateByPrimaryKeySelective(steelDamageInfo);
    }

    /**
     * 发起流程 添加钢轨信息
     * @param steelDamageInfo
     * @return
     */
    @Override
    public int addSteelDamageInfo(SteelDamageInfo steelDamageInfo) {
        //流程发起后 就到一级审批人 状态为2
        steelDamageInfo.setFlowState(2);
        steelDamageInfo.setSteelFlowDate(sdf.format(new Date()));
        steelDamageInfo.setDelFlag(0);

        return this.baseMapper.insertSelective(steelDamageInfo);
    }
    @Override
    public  String uploadCloudPicture(Integer steelId) throws Exception {
        SteelDamageInfo steelDamageInfo = steelDamageInfoMapper.selectByPrimaryKey(steelId);

        Workbook workbook = ExcelUtils.writeExcel(steelDamageInfo);
        HSSFSheet hssfSheet = (HSSFSheet) workbook.getSheetAt(0);



        CloudFileRequest c = new CloudFileRequest();

        File f = new File();
        f.setFileid(steelDamageInfo.getSketchUrl());
        f.setMax_age(7200);
        File f1 = new File();
        f1.setFileid(steelDamageInfo.getHandwritingsignUrl1());
        f1.setMax_age(7200);
        File f2 = new File();
        f2.setFileid(steelDamageInfo.getHandwritingsignUrl2());
        f2.setMax_age(7200);
        List<File> l = new ArrayList<>();
        l.add(f2);
        l.add(f1);
        l.add(f);
        //c.setEnv("test-ck8iu");
        c.setFile_list(l);
        JSONObject o = RestTemplateConfig.newRestTemplate().postForEntity("https://api.weixin.qq.com/tcb/batchdownloadfile?access_token=" + this.getToken() , c, JSONObject.class).getBody();
        if(o.getInteger("errcode") == 0){
            JSONArray jsonArray =  o.getJSONArray("file_list");
            String sketchName = "none.png";
            String handwriting1Name = "none.png";
            String handwriting2Name = "none.png";
            String img = jsonArray.getJSONObject(0).getString("download_url");
            String handurl1 = jsonArray.getJSONObject(1).getString("download_url");
            String handurl2 = jsonArray.getJSONObject(2).getString("download_url");
            logger.info(jsonArray.toJSONString());
            logger.info(img);
            logger.info(handurl1);
            logger.info(handurl2);
            if(!"".equals(img)){
               sketchName = downloadCloudFile.downloadImg(img);
           }

            if(!"".equals(handurl1)) {
                handwriting1Name = downloadCloudFile.downloadImg(handurl1);
            }

            if(!"".equals(handurl2)){
                handwriting2Name = downloadCloudFile.downloadImg(handurl2);
            }


            //ExcelUtils excelUtils = new ExcelUtils();
            return excelUtils.addPictureToExcel(workbook,hssfSheet,sketchName,handwriting1Name,handwriting2Name,steelId);
        }else {
            logger.error("下载图片失败："+ o.getInteger("errcode")+o.getString("errmsg"));
        }

        return "";
    }


    private String getToken(){
        return globalTokenMapper.selectAll().get(0).getAccessToken();
    }

}
