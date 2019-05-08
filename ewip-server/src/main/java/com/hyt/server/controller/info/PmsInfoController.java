package com.hyt.server.controller.info;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitSchool;
import com.hyt.server.entity.info.PmsInfo;
import com.hyt.server.service.base.IUnitSchoolService;
import com.hyt.server.service.info.IPmsInfoService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pmsInfo")
public class PmsInfoController {

    @Autowired
    private IPmsInfoService pmsInfoService;

    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        PmsInfo pmsInfo = JSON.parseObject(json.toJSONString(), new TypeReference<PmsInfo>() {});
        int num = this.pmsInfoService.insert(pmsInfo);
        if(num>0){
            return ResultResponse.make(200,"添加成功",pmsInfo);
        }
        return ResultResponse.make(500,"添加失败",null);
    }


    @PostMapping("/update")
    public ResultObject<Object> update(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        PmsInfo pmsInfo = JSON.parseObject(json.toJSONString(), new TypeReference<PmsInfo>() {});
        int num = this.pmsInfoService.update(pmsInfo);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }


    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.pmsInfoService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }


    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.pmsInfoService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }


    @GetMapping("/select")
    public ResultObject<Object> selectAll( @RequestParam Map<String,Object> map) {
        PageInfo<PmsInfo> pageInfo = this.pmsInfoService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


    @GetMapping("/list")
    public ResultObject<Object> selectList(@RequestParam Map<String,Object> map){
        List<PmsInfo> list = this.pmsInfoService.selectList(map);
        if(list.size()>0){
            return ResultResponse.make(200,"查询成功",list);
        }
        return ResultResponse.make(500,"查询失败",null);
    }


    /**
     * 数据导入
     * @return
     */
    @PostMapping("/importData")
    public JSONObject importData(@RequestParam Map<String,Object> map, @RequestBody List<Map<String,Object>> list) {
        JSONObject json=this.pmsInfoService.importData(map,list);
        return json;
    }

}



