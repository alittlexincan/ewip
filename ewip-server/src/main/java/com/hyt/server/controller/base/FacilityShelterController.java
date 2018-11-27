package com.hyt.server.controller.base;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.FacilityPublish;
import com.hyt.server.entity.base.FacilityShelter;
import com.hyt.server.service.base.IFacilityShelterService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-应急避难所控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-应急避难所"}, description = "FacilityShelterController")
@RestController
@RequestMapping("/facilityShelter")
public class FacilityShelterController {

    @Autowired
    private IFacilityShelterService facilityShelterService;


    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        FacilityShelter facilityShelter = JSON.parseObject(json.toJSONString(), new TypeReference<FacilityShelter>() {});
        int num = this.facilityShelterService.insert(facilityShelter);
        if(num>0){
            return ResultResponse.make(200,"添加成功",facilityShelter);
        }
        return ResultResponse.make(500,"添加失败",null);
    }


    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        FacilityShelter facilityShelter = JSON.parseObject(json.toJSONString(), new TypeReference<FacilityShelter>() {});
        int num = this.facilityShelterService.update(facilityShelter);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }



    @ApiOperation(value="删除应急避难所信息",httpMethod = "DELETE", notes="根据url的应急避难所ID来删除应急避难所信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "应急避难所ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.facilityShelterService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除应急避难所成功");
        }
        return ResultResponse.make(500,"删除应急避难所失败");
    }

    @ApiOperation(value="批量删除应急避难所信息",httpMethod = "POST", notes="根据一批应急避难所ID来删除应急避难所信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "应急避难所ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.facilityShelterService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除应急避难所成功");
        }
        return ResultResponse.make(500,"删除应急避难所失败");
    }

    @ApiOperation(value = "查询应急避难所信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有应急避难所信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="district",value="区县", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="address",value="详细地址", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="area",value="面积", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="capacity",value="容纳人口", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="unit",value="所属部门",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="地区编码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="principal", value="负责人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="phone", value="手机", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="tel",value="电话", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lon", value="经度", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lat", value="纬度", required = true, dataType = "Double",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<FacilityShelter> pageInfo = this.facilityShelterService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value = "查询应急避难所信息列表", httpMethod = "GET", notes = "查询所有应急避难所信息")
    @GetMapping("/list")
    public ResultObject<Object> selectList(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<FacilityShelter> list = this.facilityShelterService.selectList(map);
        if(list.size()>0){
            return ResultResponse.make(200,"查询应急避难所成功",list);
        }
        return ResultResponse.make(500,"查询应急避难所失败",null);
    }
}
