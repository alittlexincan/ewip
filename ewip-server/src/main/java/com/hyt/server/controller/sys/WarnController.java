package com.hyt.server.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.sys.Warn;
import com.hyt.server.service.sys.IWarnService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 预警配置管理控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"预警配置管理"}, description = "WarnController")
@RestController
@RequestMapping("/warn")
public class WarnController {

    @Autowired
    private IWarnService warnService;

    @ApiOperation(value="添加预警配置信息",httpMethod="POST",notes="根据参数列表添加预警配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="area_id",value="地区ID",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="organization_id",value="机构ID",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disaster_id", value="灾种ID", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disaster_name",value="灾种名称", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disaster_color",value="灾种颜色", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="disaster_level",value="灾种级别", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="content",value="预警内容", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="measure",value="政府应对措施", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="instruction",value="防御指南", dataType = "String", paramType = "query")
    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        Warn earnConfig = JSON.parseObject(json.toJSONString(), new TypeReference<Warn>() {});
        int num = this.warnService.insert(earnConfig);
        if(num>0){
            return ResultResponse.make(200,"添加预警配置成功",earnConfig);
        }
        return ResultResponse.make(500,"添加预警配置失败",null);
    }

    @ApiOperation(value="修改预警配置信息",httpMethod="POST", notes="根据预警配置ID，修改参数列表预警配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="预警配置ID", dataType = "String", required = true,paramType = "query"),
            @ApiImplicitParam(name="area_id",value="地区ID",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="organization_id",value="机构ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disaster_id", value="灾种ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disaster_name",value="灾种名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disaster_color",value="灾种颜色", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="disaster_level",value="灾种级别", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="content",value="预警内容", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="measure",value="政府应对措施", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="instruction",value="防御指南", dataType = "String", paramType = "query")
    })
    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        Warn earnConfig = JSON.parseObject(json.toJSONString(), new TypeReference<Warn>() {});
        int num = this.warnService.update(earnConfig);
        if(num>0){
            return ResultResponse.make(200,"修改预警配置成功");
        }
        return ResultResponse.make(500,"修改预警配置失败");
    }

    @ApiOperation(value="删除预警配置信息",httpMethod = "DELETE", notes="根据url的预警配置ID来删除预警配置信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "预警配置ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.warnService.deleteById(id);
        if(num>0){
          return  ResultResponse.make(200,"删除预警配置成功");
        }
        return ResultResponse.make(500,"删除预警配置失败");
    }

    @ApiOperation(value="批量删除预警配置信息",httpMethod = "POST", notes="根据一批预警配置ID来删除预警配置信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "地区ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.warnService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除预警配置成功");
        }
        return ResultResponse.make(500,"删除预警配置失败");
    }

    @ApiOperation(value="查询预警配置信息",httpMethod = "POST", notes="根据url的预警配置ID来查询预警配置信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "预警配置ID", required = true, dataType = "String", paramType="path")
    })
    @PostMapping("/select/{id}")
    public ResultObject<Object> selectById(@PathVariable(value = "id") String id) {
        return ResultResponse.ok(this.warnService.selectById(id));
    }

    @ApiOperation(value = "查询预警配置信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有预警配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="开始时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="endTime",value="结束时间", dataType = "Date",paramType = "query"),

            @ApiImplicitParam(name="id",value="预警配置ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="area_id",value="地区ID",  dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="organization_id",value="机构ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disaster_id", value="灾种ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disaster_name",value="灾种名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disaster_color",value="灾种颜色", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="disaster_level",value="灾种级别", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="content",value="预警内容", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="measure",value="政府应对措施", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="instruction",value="防御指南", dataType = "String", paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<Warn> pageInfo = this.warnService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


}
