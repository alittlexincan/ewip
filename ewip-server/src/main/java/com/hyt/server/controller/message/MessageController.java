package com.hyt.server.controller.message;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.message.Message;
import com.hyt.server.service.message.IMessageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 一键发布控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"一键发布"}, description = "MessageController")
@RestController
@RequestMapping("message")
public class MessageController {


    @Autowired
    private IMessageService messageService;


    @ApiOperation(value="添加一键发布信息",httpMethod="POST",notes="根据参数列表添加一键发布信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="title", value="发布标题",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="areaId", value="地区ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="areaName", value="地区名称",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="organizationId", value="机构ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="organizationName", value="机构名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="type", value="业务类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="template", value="微信模板类型", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="sendTime", value="一键发布时间", required = true, dataType = "Date", paramType = "query")
    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){

        Message message = this.messageService.insert(map);
        if(message.getId() != null){
            return ResultResponse.make(200,"一键发布成功",map);
        }
        return ResultResponse.make(500,"一键发布失败",null);
    }

    @ApiOperation(value = "查询一键发布信息列表", httpMethod = "GET", notes = "根据查询条件分页查询一键发布信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="开始时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="endTime",value="结束时间", dataType = "Date",paramType = "query"),

            @ApiImplicitParam(name="id",value="地区ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="title",value="标题", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="type",value="业务类型", dataType = "Double",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<Message> pageInfo = this.messageService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="查询一键发布信息",httpMethod="POST",notes="根据消息ID查询一键发布信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="消息主表ID", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/detail")
    public ResultObject<Object> detail(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){

        JSONObject result = this.messageService.detail(map);
        if(result != null){
            return ResultResponse.make(200,"查询成功",result);
        }
        return ResultResponse.make(500,"查询失败",null);
    }
}
