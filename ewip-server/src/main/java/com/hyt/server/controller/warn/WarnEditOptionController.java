package com.hyt.server.controller.warn;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.warn.WarnEditOption;
import com.hyt.server.service.publish.IPublishService;
import com.hyt.server.service.warn.IWarnEditOptionService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 预警编辑控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"预警编辑操作"}, description = "WarnEditOptionController")
@RestController
@RequestMapping("/warn/option")
public class WarnEditOptionController {


    @Autowired
    private IWarnEditOptionService warnEditOptionService;

    @Autowired
    private IPublishService publishService;

    @ApiOperation(value="分页查询预警编辑流程信息",httpMethod="POST",notes="根据参数列表分页查询预警编辑流程信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name="areaId", value="地区ID",required = true, dataType = "String", paramType = "query"),
                @ApiImplicitParam(name="organizationId", value="机构ID", required = true, dataType = "String", paramType = "query"),
                @ApiImplicitParam(name="flow", value="流程标识", required = true, dataType = "String", paramType = "query")
    })
    @GetMapping("/select/flow")
    public ResultObject<Object> selectFlowByParam(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<WarnEditOption> pageInfo = this.warnEditOptionService.selectFlowByParam(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="添加预警编辑流程信息",httpMethod="POST",notes="根据参数列表添加预警编辑流程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="warnEditId", value="预警编辑基本信息ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="flow", value="流程标识",required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="organizationId", value="机构ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="organizationName", value="机构名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="employeeId", value="操作用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="employeeName", value="操作用户名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="advice", value="操作意见", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/insert/flow")
    public ResultObject<Object> insertFlow(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject result = this.warnEditOptionService.insert(map);

        int status = result.getInteger("status");
        System.out.println(result.toJSONString());
        if(status > 0){
            // 发布后调用分发接口
            if(status == 4){
                Map<String, Object> param = new HashMap<>(result);
                this.publishService.publish(param);
            }

            return ResultResponse.make(200,result.getString("msg"),map);
        }
        return ResultResponse.make(500,"操作失败",null);
    }

    @ApiOperation(value="修改预警状态",httpMethod="POST",notes="根据参数列表修改预警状态信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="预警编辑基本信息ID",required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="status", value="预警状态", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/update/status")
    public ResultObject<Object> updateStatus(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        int num = this.warnEditOptionService.updateStatus(map);
        if(num > 0){
            return ResultResponse.make(200,"添加预警编辑流程成功",map);
        }
        return ResultResponse.make(500,"添加预警编辑流程失败",null);
    }

    @ApiOperation(value="微信获取当天所有预警信息",httpMethod="GET",notes="微信获取当天所有预警信息")
    @GetMapping("/wechat")
    public ResultObject<Object> getWechatWarnInfo(){
        List<Map<String, Object>> list = this.warnEditOptionService.getWechatWarnInfo();
        if (list.size() > 0){
            return ResultResponse.make(200,"微信获取当天所有预警信息成功",list);
        }
        return ResultResponse.make(500,"微信获取当天所有预警信息失败",null);
    }
}
