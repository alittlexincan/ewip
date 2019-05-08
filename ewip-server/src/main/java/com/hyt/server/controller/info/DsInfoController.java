package com.hyt.server.controller.info;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.info.DsInfo;
import com.hyt.server.entity.info.PmsInfo;
import com.hyt.server.service.info.IDsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dsInfo")
public class DsInfoController {

    @Autowired
    private IDsInfoService dsInfoService;

    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DsInfo dsInfo = JSON.parseObject(json.toJSONString(), new TypeReference<DsInfo>() {});
        int num = this.dsInfoService.insert(dsInfo);
        if(num>0){
            return ResultResponse.make(200,"添加成功",dsInfo);
        }
        return ResultResponse.make(500,"添加失败",null);
    }


    @PostMapping("/update")
    public ResultObject<Object> update(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        DsInfo dsInfo = JSON.parseObject(json.toJSONString(), new TypeReference<DsInfo>() {});
        int num = this.dsInfoService.update(dsInfo);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }


    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.dsInfoService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }


    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.dsInfoService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }


    @GetMapping("/select")
    public ResultObject<Object> selectAll( @RequestParam Map<String,Object> map) {
        PageInfo<DsInfo> pageInfo = this.dsInfoService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


    @GetMapping("/list")
    public ResultObject<Object> selectList(@RequestParam Map<String,Object> map){
        List<DsInfo> list = this.dsInfoService.selectList(map);
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
        JSONObject json=this.dsInfoService.importData(map,list);
        return json;
    }

}



