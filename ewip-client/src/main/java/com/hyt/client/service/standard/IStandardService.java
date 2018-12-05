package com.hyt.client.service.standard;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 规范制度接口层
 * @Author: lixiaowei
 * @Description:
 * @Modified By:
 */
@Service("standardService")
@FeignClient("EWIP-SERVER")
public interface IStandardService {

    /**
     * 添加信息
     * @param map
     * @return
     */
    @PostMapping("/standard/insert")
    JSONObject insert(@RequestParam Map<String, Object> map);

    /**
     * 修改信息
     * @param map
     * @return
     */
    @PostMapping("/standard/update")
    JSONObject update(@RequestParam Map<String, Object> map);

    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @DeleteMapping("/standard/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);

    /**
     * 根据ids批量删除信息
     * @param id
     * @return
     */
    @PostMapping("/standard/delete")
    JSONObject deleteBatch(@RequestParam("id") String id);

    /**
     * 根据id查询详细信息
     * @param id
     * @return
     */
    @GetMapping("/standard/select/{id}")
    JSONObject selectById(@PathVariable(value = "id") String id);

    /**
     * 分页查询信息
     * @param map
     * @return
     */
    @GetMapping("/standard/select")
    JSONObject selectAll(@RequestParam Map<String, Object> map);

}
