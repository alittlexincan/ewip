package com.hyt.client.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.base.IUnitDikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("unitDike")
public class UnitDikeController {

        @Autowired
        private IUnitDikeService unitDikeService;

        /**
         * 根据提防ID删除提防信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.unitDikeService.deleteById(id);
        }

        /**
         * 批量删除提防信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.unitDikeService.deleteBatch(id);
        }

        /**
         * 分页查询提防信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
            return this.unitDikeService.selectAll(map);
        }

        /**
         * 查询提防列表
         * @return
         */
        @GetMapping("/selectList")
        public JSONObject selectList(){
                return this.unitDikeService.selectList();
        };

}
