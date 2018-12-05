package com.hyt.server.controller.standard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.standard.Standard;
import com.hyt.server.service.standard.IStandardService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lixiaowei
 * @Description: 规范制度控制层
 */
@RestController
@RequestMapping("/standard")
public class StandardController {

    @Autowired
    private IStandardService standardService;

    @PostMapping("/insert")
    public ResultObject<Object> insert( @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        Standard standard = JSON.parseObject(json.toJSONString(), new TypeReference<Standard>() {});
        int num = this.standardService.insert(standard);
        if(num>0){
            return ResultResponse.make(200,"添加成功",standard);
        }
        return ResultResponse.make(500,"添加失败",null);
    }

    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        Standard standard = JSON.parseObject(json.toJSONString(), new TypeReference<Standard>() {});
        int num = this.standardService.update(standard);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }

    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.standardService.deleteById(id);
        if(num>0){
          return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }

    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.standardService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }

    @PostMapping("/select/{id}")
    public ResultObject<Object> selectById(@PathVariable(value = "id") String id) {
        return ResultResponse.ok(this.standardService.selectById(id));
    }

    @GetMapping("/select")
    public ResultObject<Object> selectAll(@RequestParam Map<String,Object> map) {
        PageInfo<Standard> pageInfo = this.standardService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


}
