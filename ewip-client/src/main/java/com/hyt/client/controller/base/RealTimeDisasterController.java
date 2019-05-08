package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IRealTimeDisasterService;
import com.hyt.client.utils.UploadFileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("realTimeDisaster")
public class RealTimeDisasterController {

        @Autowired
        private IRealTimeDisasterService realTimeDisasterService;

        /**
         * 获取上传的文件夹，具体路径参考application.properties中的配置
         */
        @Value("${web.upload-path}")
        private String uploadPath;



        /**
         * 实时灾情上传音频文件夹
         */
        @Value("${web.disHisAudio-path}")
        private String disasterAudio;

        /**
         * 实时灾情上传图片文件夹
         */
        @Value("${web.disHisPicture-path}")
        private String disasterPicture;

        /**
         * 实时灾情上传视频文件夹
         */
        @Value("${web.disHisVideo-path}")
        private String disasterVideo;

        /**
         * 实时灾情上传文件夹
         */
        @Value("${web.disasterFile-path}")
        private String disasterFile;


        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/insert")
        JSONObject insert(@RequestParam Map<String,Object> map,@RequestParam("disasterFile") MultipartFile[] files){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("createUser", employee.getString("id"));

                // 文件开始上传
                JSONArray file = UploadFileUtil.upload(files, uploadPath, disasterFile);
                map.put("files", file != null ? file.toJSONString() : "");

                return this.realTimeDisasterService.insert(map);
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
                                audioUrl = UploadFileUtil.upload(multipartFiles, uploadPath, disasterAudio);
                        }
                        if(fileName.endsWith(".png") || fileName.endsWith(".gif") || fileName.endsWith(".jpeg")
                                ||fileName.endsWith(".bmp") || fileName.endsWith(".tiff") || fileName.endsWith(".jpg")){
                                pictureUrl = UploadFileUtil.upload(multipartFiles, uploadPath, disasterPicture);
                        }
                        if(fileName.endsWith(".mp4") || fileName.endsWith(".avi") || fileName.endsWith(".rm")
                                || fileName.endsWith(".flv") || fileName.endsWith(".mov")|| fileName.endsWith(".rmvb")
                                || fileName.endsWith(".mpg") || fileName.endsWith(".mkv")|| fileName.endsWith(".wmv")
                                || fileName.endsWith(".3gp")){
                                videoUrl = UploadFileUtil.upload(multipartFiles, uploadPath, disasterVideo);
                        }
                }
                if(audioUrl!=null) {
                        map.put("audio", audioUrl);
                }if(pictureUrl!=null) {
                        map.put("picture", pictureUrl);
                }if(videoUrl!=null) {
                        map.put("video", videoUrl);
                }
                return this.realTimeDisasterService.update(map);
        }

        /**
         * 根据实时灾情ID删除实时灾情信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.realTimeDisasterService.deleteById(id);
        }

        /**
         * 批量删除实时灾情信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.realTimeDisasterService.deleteBatch(id);
        }

        /**
         * 根据实时灾情ID查询灾情信息
         * @param id
         * @return
         */
        @PostMapping("/select/{id}")
        public JSONObject selectById(@PathVariable(value = "id") String id){
            return this.realTimeDisasterService.selectById(id);
        }


        /**
         * 分页查询实时灾情信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("empAreaId", employee.getString("areaId"));
                String areaCode=employee.getString("areaCode");
                if(employee.getString("level").equals("1")){
                        areaCode=areaCode.substring(0,2);
                }else if(employee.getString("level").equals("2")){
                        areaCode=areaCode.substring(0,4);
                }else if(employee.getString("level").equals("3")){
                        areaCode=areaCode.substring(0,6);
                }
                map.put("areaCode", areaCode);
            return this.realTimeDisasterService.selectAll(map);
        }


        /**
         * 查询实时灾情信息列表
         * @param map
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("empAreaId", employee.getString("areaId"));
                String areaCode=employee.getString("areaCode");
                if(employee.getString("level").equals("1")){
                        areaCode=areaCode.substring(0,2);
                }else if(employee.getString("level").equals("2")){
                        areaCode=areaCode.substring(0,4);
                }else if(employee.getString("level").equals("3")){
                        areaCode=areaCode.substring(0,6);
                }
                map.put("areaCode", areaCode);
               return this.realTimeDisasterService.selectList(map);
        }


        /**
         * 根据实时灾情ID查询灾情图片视频等
         * @param map
         * @return
         */
        @PostMapping("/selectFile")
        public JSONObject selectFile(@RequestParam Map<String,Object> map){
                return this.realTimeDisasterService.selectFile(map);
        }

}
