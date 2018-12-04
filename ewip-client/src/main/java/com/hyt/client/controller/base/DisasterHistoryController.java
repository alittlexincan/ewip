package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IDisasterHistoryService;
import com.hyt.client.utils.UploadFileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("disasterHistory")
public class DisasterHistoryController {

        @Autowired
        private IDisasterHistoryService disasterHistoryService;

        /**
         * 获取上传的文件夹，具体路径参考application.properties中的配置
         */
        @Value("${web.upload-path}")
        private String uploadPath;

        /**
         * 历史灾情上传音频文件夹
         */
        @Value("${web.disHisAudio-path}")
        private String disHisAudio;

        /**
         * 历史灾情上传图片文件夹
         */
        @Value("${web.disHisPicture-path}")
        private String disHisPicture;

        /**
         * 历史灾情上传视频文件夹
         */
        @Value("${web.disHisVideo-path}")
        private String disHisVideo;

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/insert")
        JSONObject insert(@RequestParam Map<String,Object> map,@RequestParam("hisFile") MultipartFile[] files){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("createUser", employee.getString("id"));
                String audioUrl=null,pictureUrl=null,videoUrl=null;
                for(MultipartFile multipartFile : files){
                        String fileName = multipartFile.getOriginalFilename();
                        if(fileName.endsWith(".mp3") || fileName.endsWith(".wma") || fileName.endsWith(".wav")
                                || fileName.endsWith(".ogg")){
                                audioUrl = UploadFileUtil.upload(multipartFile, uploadPath, disHisAudio);
                        }
                        if(fileName.endsWith(".png") || fileName.endsWith(".gif") || fileName.endsWith(".jpeg")
                                ||fileName.endsWith(".bmp") || fileName.endsWith(".tiff") || fileName.endsWith(".jpg")){
                                pictureUrl = UploadFileUtil.upload(multipartFile, uploadPath, disHisPicture);
                        }
                        if(fileName.endsWith(".mp4") || fileName.endsWith(".avi") || fileName.endsWith(".rm")
                                || fileName.endsWith(".flv") || fileName.endsWith(".mov")|| fileName.endsWith(".rmvb")
                                || fileName.endsWith(".mpg") || fileName.endsWith(".mkv")|| fileName.endsWith(".wmv")
                                || fileName.endsWith(".3gp")){
                                videoUrl = UploadFileUtil.upload(multipartFile, uploadPath, disHisVideo);
                        }
                }
                if(audioUrl!=null) {
                        map.put("audio", audioUrl);
                }if(pictureUrl!=null) {
                        map.put("picture", pictureUrl);
                }if(videoUrl!=null) {
                        map.put("video", videoUrl);
                }
                return this.disasterHistoryService.insert(map);
        }

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/update")
        JSONObject update(@RequestParam Map<String,Object> map,@RequestParam("updateHisFile") MultipartFile[] files){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("updateUser", employee.getString("id"));
                String audioUrl=null,pictureUrl=null,videoUrl=null;
                for(MultipartFile multipartFiles : files){
                        String fileName = multipartFiles.getOriginalFilename();
                        if(fileName.endsWith(".mp3") || fileName.endsWith(".wma") || fileName.endsWith(".wav")
                                || fileName.endsWith(".ogg")){
                                audioUrl = UploadFileUtil.upload(multipartFiles, uploadPath, disHisAudio);
                        }
                        if(fileName.endsWith(".png") || fileName.endsWith(".gif") || fileName.endsWith(".jpeg")
                                ||fileName.endsWith(".bmp") || fileName.endsWith(".tiff") || fileName.endsWith(".jpg")){
                                pictureUrl = UploadFileUtil.upload(multipartFiles, uploadPath, disHisPicture);
                        }
                        if(fileName.endsWith(".mp4") || fileName.endsWith(".avi") || fileName.endsWith(".rm")
                                || fileName.endsWith(".flv") || fileName.endsWith(".mov")|| fileName.endsWith(".rmvb")
                                || fileName.endsWith(".mpg") || fileName.endsWith(".mkv")|| fileName.endsWith(".wmv")
                                || fileName.endsWith(".3gp")){
                                videoUrl = UploadFileUtil.upload(multipartFiles, uploadPath, disHisVideo);
                        }
                }
                if(audioUrl!=null) {
                        map.put("audio", audioUrl);
                }if(pictureUrl!=null) {
                        map.put("picture", pictureUrl);
                }if(videoUrl!=null) {
                        map.put("video", videoUrl);
                }
                return this.disasterHistoryService.update(map);
        }

        /**
         * 根据历史灾情ID删除历史灾情信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.disasterHistoryService.deleteById(id);
        }

        /**
         * 批量删除历史灾情信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.disasterHistoryService.deleteBatch(id);
        }

        /**
         * 根据历史灾情ID查询历史灾情信息
         * @param id
         * @return
         */
        @PostMapping("/select/{id}")
        public JSONObject selectById(@PathVariable(value = "id") String id){
            return this.disasterHistoryService.selectById(id);
        }


        /**
         * 分页查询历史灾情信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.disasterHistoryService.selectAll(map);
        }


        /**
         * 查询灾害历史信息列表
         * @param map
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
               return this.disasterHistoryService.selectList(map);
        }

}
