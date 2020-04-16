package com.mk.approve.controller;

import com.aspose.cells.ImageOrPrintOptions;
import com.aspose.cells.SheetRender;
import com.mk.approve.base.component.GlobalExceptionHandler;
import com.mk.approve.base.controller.BaseResponse;
import com.mk.approve.base.utils.ResponseUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * Description:
 * version:1.0.0
 * Author: prince
 * email: wangzixian@aoji.cn
 * DateTime: 11:01 2020/3/20
 */
@RestController
@RequestMapping("/uploadController")
@Slf4j
@Api(value = "文件上传接口")
public class UploadController {

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Value("${path.filepath}")
    private String filepath;

    @RequestMapping(value = "/uploadImgFile")
    public BaseResponse uploadImgFile(HttpServletRequest request, HttpServletResponse response,String prefix,String subfix) throws IllegalStateException, IOException {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("img");

        String path = filepath + "file/img/";

        // 判断存放上传文件的目录是否存在（不存在则创建）
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String uuid= UUID.randomUUID().toString().replace("-","");
        String fileName = prefix + uuid+  subfix;

        File file = new File(path+fileName);
        OutputStream out= null;
        try {
            out = new FileOutputStream(file);
            out.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //返回 用于展示   仅返回文件名 前拼接 nginx配的虚拟路径
        return ResponseUtils.setResultSuccess( fileName);
    }
}
