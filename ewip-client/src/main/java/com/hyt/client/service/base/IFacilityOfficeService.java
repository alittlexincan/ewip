package com.hyt.client.service.base;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description:基础数据-办公场所接口层
 * @Date: Created in 15:30 2018-11-2
 * @Modified By:
 */
@Service("facilityOfficeService")
@FeignClient("EWIP-SERVER")
public interface IFacilityOfficeService {

        /**
         * 根据办公场所ID删除办公场所信息
         * @param id
         * @return
         */
        @DeleteMapping("/facilityOffice/delete/{id}")
        JSONObject deleteById(@PathVariable(value = "id") String id);

        /**
         * 批量删除办公场所信息
         * @param id
         * @return
         */
        @PostMapping("/facilityOffice/delete")
        JSONObject deleteBatch(@RequestParam(value = "id") String id);


        /**
         * 分页查询办公场所信息
         * @param map
         * @return
         */
        @GetMapping("/facilityOffice/select")
        JSONObject selectAll(@RequestParam Map<String, Object> map);
}
