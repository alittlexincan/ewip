package com.hyt.server.controller.config;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.config.ChannelConfig;
import com.hyt.server.service.config.IChannelConfigService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lixiaowei
 * @Description: 渠道配置管理
 * @Modified By:
 */
@Api(tags = {"渠道配置管理"}, description = "ChannelConfigController")
@RestController
@RequestMapping("/channel/config")
public class ChannelConfigController {

    /**
     * 注入渠道配置管理接口
     */
    @Autowired
    private IChannelConfigService channelConfigService;

    @ApiOperation(value = "查询渠道配置信息列表", httpMethod = "GET", notes = "根据查询条件分页查询渠道配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="开始时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="endTime",value="结束时间", dataType = "Date",paramType = "query"),

            @ApiImplicitParam(name="id",value="渠道手段ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="channelCode",value="渠道编码", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="content",value="配置内容", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaId", value="所属地区",  dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="organizationId",value="所属机构", dataType = "String",paramType = "query")
    })

    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<ChannelConfig> pageInfo = this.channelConfigService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="添加渠道配置信息",httpMethod="POST",notes="根据参数列表添加渠道配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="name",value="渠道手段名称",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="icon",value="渠道手段图标",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="pId", value="所属渠道", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="channelCode",value="渠道编码", dataType = "String",paramType = "query")
    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        int num = this.channelConfigService.insert(map);
        if(num > 0){
            return ResultResponse.make(200,"渠道配置成功",map);
        }
        return ResultResponse.make(500,"渠道配置失败",null);
    }

    @ApiOperation(value="根据渠道类型查询渠道配置信息",httpMethod="POST",notes="根据渠道类型查询渠道配置信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="channelCode",value="渠道编码", dataType = "String",paramType = "query")
    })
    @GetMapping("/select/type")
    public ResultObject<Object> selectByType(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        List<ChannelConfig> list = this.channelConfigService.selectByType(map);
        if(list.size() > 0){
            return ResultResponse.make(200,"查询渠道配置成功",list);
        }
        return ResultResponse.make(500,"查询渠道配置失败",null);
    }

}
