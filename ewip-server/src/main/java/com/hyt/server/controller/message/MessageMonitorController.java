package com.hyt.server.controller.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.message.MessageMonitor;
import com.hyt.server.service.message.IMessageMonitorService;
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
@Api(tags = {"一键发布监控"}, description = "MessageMonitorController")
@RestController
@RequestMapping("/message/monitor")
public class MessageMonitorController {

    @Autowired
    private IMessageMonitorService messageMonitorService;


    /**
     * 统计一键发布列表信息
     * @param map
     * @return
     */
    @ApiOperation(value="一键发布信息统计",httpMethod="GET",notes="统计一键发布信息统计（列表）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="messageId", value="一键消息ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="startTime", value="开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="endTime", value="结束时间", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/list")
    public ResultObject<Object> findMessageMonitor(@ApiParam(hidden = true) @RequestParam Map<String, Object> map){
        PageInfo<MessageMonitor> pageInfo = this.messageMonitorService.findMessageMonitor(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 统计一键发布列表信息
     * @param map
     * @return
     */
    @ApiOperation(value="一键发布信息受众详情统计",httpMethod="GET",notes="根据条件查询一键发布信息受众接收详情（列表）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="messageId", value="一键消息ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="channelCode", value="渠道编码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="code", value="受众编码", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/users")
    public ResultObject<Object> findMessageMonitorUsers(@ApiParam(hidden = true) @RequestParam Map<String, Object> map){
        PageInfo<MessageMonitor> pageInfo = this.messageMonitorService.findMessageMonitorUsers(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


    @ApiOperation(value="一键发布信息类型占比",httpMethod="POST",notes="统计一键发布信息类型占比（饼图）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="messageId", value="一键消息ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="startTime", value="开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="endTime", value="结束时间", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/type")
    public ResultObject<Object> findMessageTypeTotal(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONArray result = this.messageMonitorService.findMessageTypeTotal(map);
        if(result != null){
            return ResultResponse.make(200,"查询成功",result);
        }
        return ResultResponse.make(500,"查询失败",null);
    }

    @ApiOperation(value="一键发布信息渠道统计",httpMethod="POST",notes="根据一键发布渠道进行统计（柱状图）")
    @ApiImplicitParams({
            @ApiImplicitParam(name="messageId", value="一键消息ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="startTime", value="开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="endTime", value="结束时间", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/channel")
    public ResultObject<Object> findMessageChannelTotal(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject result = this.messageMonitorService.findMessageChannelTotal(map);
        if(result != null){
            return ResultResponse.make(200,"查询成功",result);
        }
        return ResultResponse.make(500,"查询失败",null);
    }

}
