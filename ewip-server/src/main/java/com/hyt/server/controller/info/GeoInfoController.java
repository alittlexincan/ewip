package com.hyt.server.controller.info;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.info.GeoInfo;
import com.hyt.server.service.info.IGeoInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/geoInfo")
public class GeoInfoController {

    @Autowired
    private IGeoInfoService geoInfoService;

    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        GeoInfo geoInfo = JSON.parseObject(json.toJSONString(), new TypeReference<GeoInfo>() {});
        int num = this.geoInfoService.insert(geoInfo);
        if(num>0){
            return ResultResponse.make(200,"添加成功",geoInfo);
        }
        return ResultResponse.make(500,"添加失败",null);
    }


    @PostMapping("/update")
    public ResultObject<Object> update(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        GeoInfo geoInfo = JSON.parseObject(json.toJSONString(), new TypeReference<GeoInfo>() {});
        int num = this.geoInfoService.update(geoInfo);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }


    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.geoInfoService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }


    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.geoInfoService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }


    @GetMapping("/select")
    public ResultObject<Object> selectAll( @RequestParam Map<String,Object> map) {
        PageInfo<GeoInfo> pageInfo = this.geoInfoService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


    @GetMapping("/list")
    public ResultObject<Object> selectList(@RequestParam Map<String,Object> map){
        List<GeoInfo> list = this.geoInfoService.selectList(map);
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
        JSONObject json=this.geoInfoService.importData(map,list);
        return json;
    }


    @GetMapping("/selectDisasterList")
    public  List<Map<String,Object>> selectDisasterList(@RequestParam Map<String,Object> map){
        return this.geoInfoService.selectDisasterList(map);
    }

}



