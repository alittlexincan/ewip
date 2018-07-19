package com.hyt.client.service.upload;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 上传接口层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 17:04 2018-4-18
 * @Modified By:
 */
@Service("uploadService")
@FeignClient("EWIP-SERVER")
public interface IUploadService {

    /**
     * 单个文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload/one")
    JSONObject uploadOne(HttpServletRequest request, @RequestParam("file") MultipartFile file);
}
