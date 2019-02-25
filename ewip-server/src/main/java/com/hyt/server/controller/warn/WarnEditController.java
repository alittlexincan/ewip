package com.hyt.server.controller.warn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitSchool;
import com.hyt.server.entity.warn.WarnEdit;
import com.hyt.server.service.warn.IWarnEditService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.applet.Main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: JiangXincan
 * @Description: 预警编辑控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"预警编辑"}, description = "WarnEditController")
@RestController
@RequestMapping("/warn/edit")
public class WarnEditController {

    /**
     * 注入预警编辑接口
     */
    @Autowired
    private IWarnEditService warnEditService;

    @ApiOperation(value="添加预警编辑信息",httpMethod="POST",notes="根据参数列表添加预警编辑信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title", value="预警标题",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="areaId", value="地区ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="organizationId", value="机构ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="disasterId", value="灾种ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="disasterName", value="灾种名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="disasterColor", value="灾种颜色", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="disasterLevel", value="灾种级别", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="warnType", value="预警信息类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="editTime", value="预警编辑时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="forecastTime", value="预计发生时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="invalid_time", value="失效时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="record", value="国突备案", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="measure", value="政府应对措施", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="instruction", value="防御指南", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="isSend", value="是否发布", required = true, dataType = "Integet", paramType = "query")
    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){

        WarnEdit warnEdit = this.warnEditService.insert(map);
        if(warnEdit.getId() != null){
            return ResultResponse.make(200,"添加预警信息成功",map);
        }
        return ResultResponse.make(500,"添加预警信息失败",null);
    }

    @ApiOperation(value="重发预警编辑信息",httpMethod="POST",notes="根据参数列表重发预警编辑信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title", value="预警标题",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="warnEditId", value="重发预警编辑ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="areaId", value="地区ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="organizationId", value="机构ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="disasterId", value="灾种ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="disasterName", value="灾种名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="disasterColor", value="灾种颜色", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="disasterLevel", value="灾种级别", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="warnType", value="预警信息类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="editTime", value="预警编辑时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="forecastTime", value="预计发生时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="invalid_time", value="失效时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="record", value="国突备案", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="measure", value="政府应对措施", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="instruction", value="防御指南", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="isSend", value="是否发布", required = true, dataType = "Integet", paramType = "query")
    })
    @PostMapping("/resend")
    public ResultObject<Object> resend(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        WarnEdit warnEdit = this.warnEditService.resend(map);
        if(warnEdit.getId() != null){
            return ResultResponse.make(200,"重发预警信息成功",map);
        }
        return ResultResponse.make(500,"重发预警信息失败",null);
    }


    @ApiOperation(value = "查询预警发布信息列表", httpMethod = "GET", notes = "根据查询条件分页查询预警发布信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="开始时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="endTime",value="结束时间", dataType = "Date",paramType = "query"),

            @ApiImplicitParam(name="disasterId",value="灾种ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterColor",value="灾种颜色", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="disasterLevel",value="灾种级别", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="flow",value="预警流程", dataType = "Integer",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<WarnEdit> pageInfo = this.warnEditService.findAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="查询预警信息",httpMethod="GET",notes="根据条件查询预警信息")
    @GetMapping("/info")
    public ResultObject<Object> getHomeWarnInfo(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        List<WarnEdit> list = this.warnEditService.selectWarnInfo(map);
        if (list.size() > 0){
            return ResultResponse.make(200,"查询预警信息成功",list);
        }
        return ResultResponse.make(500,"查询预警信息失败",null);
    }


    @PostMapping("/stopWarn")
    public ResultObject<Object> stopWarn(@RequestParam Map<String,Object> map){
        int num = this.warnEditService.stopWarn(map);
        if(num>0){
            return ResultResponse.make(200,"修改成功");
        }
        return ResultResponse.make(500,"修改失败");
    }


    public static void main(String[] args) {

        // 定义一个数组
        String[] array = {
                "尼尔机械纪元",
                "尼尔机械纪元",
                "关于我转生成为史莱姆这件事",
                "关于我转生成为史莱姆这件事",
                "实力至上主义教师",
                "实力至上主义教师",
                "地狱少女",
                "地狱少女"
        };
        // 转换成集合
        List<String> acgs = Arrays.asList(array);
        // Stream 去重复
        acgs = acgs.stream().distinct().collect(Collectors.toList());

        System.out.println(acgs);
    }

}
