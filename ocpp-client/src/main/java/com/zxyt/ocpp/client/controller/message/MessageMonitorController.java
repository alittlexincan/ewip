package com.zxyt.ocpp.client.controller.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.result.ResultObject;
import com.zxyt.ocpp.client.config.common.result.ResultResponse;
import com.zxyt.ocpp.client.entity.message.MessageMonitor;
import com.zxyt.ocpp.client.entity.sys.Area;
import com.zxyt.ocpp.client.service.message.IMessageMonitorService;
import com.zxyt.ocpp.client.service.sys.IAreaService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/message/monitor")
public class MessageMonitorController {

    @Autowired
    private IMessageMonitorService messageMonitorService;

    @GetMapping("/list")
    public ResultObject<Object> findMessageMonitor(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<MessageMonitor> pageInfo = this.messageMonitorService.findMessageMonitor(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

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
