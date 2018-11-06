package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-发布设施接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("facilityPublishService")
@FeignClient("EWIP-SERVER")
public interface IFacilityPublishService {

        /**
         * 根据发布设施ID删除发布设施信息
         * @param id
         * @return
         */
        @DeleteMapping("/facilityPublish/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除发布设施信息
         * @param id
         * @return
         */
        @PostMapping("/facilityPublish/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询发布设施信息
         * @param map
         * @return
         */
        @GetMapping("/facilityPublish/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);
}
