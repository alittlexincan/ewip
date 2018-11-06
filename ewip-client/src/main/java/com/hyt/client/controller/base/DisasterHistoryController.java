package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IDisasterHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("disasterHistory")
public class DisasterHistoryController {

        @Autowired
        private IDisasterHistoryService disasterHistoryService;

        /**
         * 添加历史灾情信息
         * @param map
         * @return
         */
        @PostMapping("/insert")
        public JSONObject insert(@RequestParam Map<String,Object> map){
            return this.disasterHistoryService.insert(map);
        }

        /**
         * 修改历史灾情信息
         * @param map
         * @return
         */
        @PostMapping("/update")
        public JSONObject update(@RequestParam Map<String,Object> map){
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

}
