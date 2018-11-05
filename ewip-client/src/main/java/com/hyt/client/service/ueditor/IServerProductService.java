package com.hyt.client.service.ueditor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Map;

/**
 * @Auther: lxv
 * @Date: 2018/11/2 11:03
 * @Description:
 */
@Service("serverProductService")
@FeignClient("EWIP-SERVER")
public interface IServerProductService {

    /**
     * 分页查询信息
     * @param map
     * @return
     */
    @GetMapping("/serverProduct/select")
    JSONObject selectAll(@RequestParam Map<String,Object> map);


    /**
     * 根据id删除信息
     * @param id
     * @return
     */
    @DeleteMapping("/serverProduct/delete/{id}")
    JSONObject deleteById(@PathVariable(value = "id") String id);
}
