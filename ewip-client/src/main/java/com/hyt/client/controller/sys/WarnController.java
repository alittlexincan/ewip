package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IWarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 预警配置信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("warn")
public class WarnController {

    @Autowired
    private IWarnService warnService;

    /**
     * 添加预警配置信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    public JSONObject insert(@RequestParam Map<String,Object> map){
        return this.warnService.insert(map);
    }

    /**
     * 修改预警配置信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    public JSONObject update(@RequestParam Map<String,Object> map){
        return this.warnService.update(map);
    }

    /**
     * 根据预警配置ID删除预警配置信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.warnService.deleteById(id);
    }

    /**
     * 批量删除预警配置信息
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public JSONObject deleteBatch(@RequestParam(value = "id") String id){
        return this.warnService.deleteBatch(id);
    }

    /**
     * 根据预警配置ID查询预警配置信息
     * @param id
     * @return
     */
    @PostMapping("/select/{id}")
    public JSONObject selectById(@PathVariable(value = "id") String id){
        return this.warnService.selectById(id);
    }


    /**
     * 分页查询预警配置信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    public JSONObject selectAll(@RequestParam Map<String,Object> map){
        return this.warnService.selectAll(map);
    }

    /**
     * 多条件查询预警配置信息
     * @param map
     * @return
     */
    @GetMapping("/config")
    public JSONObject selectConfig(@RequestParam Map<String,Object> map){
        return this.warnService.selectConfig(map);
    }

}
