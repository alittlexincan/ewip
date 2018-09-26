package com.hyt.monitor.controller.demo;

import com.hyt.monitor.config.common.result.ResultObject;
import com.hyt.monitor.config.common.result.ResultResponse;
import com.hyt.monitor.entity.sys.Area;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api(tags = {"测试管理"}, description = "DemoRestController")
@RestController
@RequestMapping("demo")
public class DemoRestController {

    @ApiOperation(value = "查询地区信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有地区信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="开始时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="endTime",value="结束时间", dataType = "Date",paramType = "query"),

            @ApiImplicitParam(name="id",value="地区ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaName",value="地区名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="地区编码", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="pId",value="上级地区", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="level",value="地区级别", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="longitude",value="地区经度", dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="latitude",value="地区纬度", dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="altitude",value="海拔高度", dataType = "Double",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {

        List<Area> areas = new ArrayList<>();
        Area a = new Area();
        a.setId("afdsasdf");
        a.setAreaName("世噶似");
        areas.add(a);
        return ResultResponse.make(200, "成功",areas);
    }
}
