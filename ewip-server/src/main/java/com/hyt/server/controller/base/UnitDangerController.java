package com.hyt.server.controller.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitDanger;
import com.hyt.server.entity.base.UnitHighway;
import com.hyt.server.service.base.IUnitDangerService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-危险品场所控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-危险品场所"}, description = "UnitDangerController")
@RestController
@RequestMapping("/unitDanger")
public class UnitDangerController {

    @Autowired
    private IUnitDangerService unitDangerService;

    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        UnitDanger unitDanger = JSON.parseObject(json.toJSONString(), new TypeReference<UnitDanger>() {});
        int num = this.unitDangerService.insert(unitDanger);
        if(num>0){
            return ResultResponse.make(200,"添加成功",unitDanger);
        }
        return ResultResponse.make(500,"添加失败",null);
    }

    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        UnitDanger unitDanger = JSON.parseObject(json.toJSONString(), new TypeReference<UnitDanger>() {});
        int num = this.unitDangerService.update(unitDanger);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }


    @ApiOperation(value="删除危险品场所信息",httpMethod = "DELETE", notes="根据url的危险品场所ID来删除危险品场所信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "危险品场所ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.unitDangerService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除危险品场所成功");
        }
        return ResultResponse.make(500,"删除危险品场所失败");
    }

    @ApiOperation(value="批量删除危险品场所信息",httpMethod = "POST", notes="根据一批危险品场所ID来删除危险品场所信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "危险品场所ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.unitDangerService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除危险品场所成功");
        }
        return ResultResponse.make(500,"删除危险品场所失败");
    }

    @ApiOperation(value = "查询危险品场所信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有危险品场所信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="district",value="区县", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="name",value="危险品场所名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="project",value="项目名称",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="building",value="建筑物名称", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="number",value="单体数", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="address",value="地址", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lon", value="经度", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lat", value="纬度", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="product",value="品名", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="tanks",value="储罐个数及容量", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="machine",value="加油机台数", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="lightningPeople",value="企业防雷安全责任人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lightningPhone",value="责任人联系电话", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="area",value="所属片区", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="leader",value="局领导",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lightningLeader", value="防雷所分管领导", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="cadres", value="业务中层干部", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="testLeader",value="检测片区组长", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="testMember",value="检测片区组员", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="testDate",value="最新检测日期", required = true, dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="report",value="最新报告编号", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="status",value="防雷安全隐患情况", required = true, dataType = "String",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<UnitDanger> pageInfo = this.unitDangerService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="查询危险品场所信息列表",httpMethod="GET",notes="查询所有危险品场所信息用于地图展示")
    @GetMapping("/list")
    public ResultObject<Object> selectList(@RequestParam Map<String,Object> map){
        List<UnitDanger> list = this.unitDangerService.selectList(map);
        if(list.size()>0){
            return ResultResponse.make(200,"查询成功",list);
        }
        return ResultResponse.make(500,"查询失败",null);
    }
}
