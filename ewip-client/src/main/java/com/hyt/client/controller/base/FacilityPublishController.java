package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IFacilityPublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("facilityPublish")
public class FacilityPublishController {

        @Autowired
        private IFacilityPublishService facilityPublishService;

        /**
         * 根据发布设施ID删除发布设施信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.facilityPublishService.deleteById(id);
        }

        /**
         * 批量删除发布设施信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.facilityPublishService.deleteBatch(id);
        }

        /**
         * 分页查询发布设施信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.facilityPublishService.selectAll(map);
        }

}
