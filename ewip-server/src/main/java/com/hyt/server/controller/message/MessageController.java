package com.hyt.server.controller.message;

import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.message.Message;
import com.hyt.server.service.message.IMessageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            @ApiImplicitParam(name="organizationId", value="机构名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="type", value="业务类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="template", value="是否发布", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name="sendTime", value="预警编辑时间", required = true, dataType = "Date", paramType = "query")
    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){

        Message message = this.messageService.insert(map);
        if(message.getId() != null){
            return ResultResponse.make(200,"一键发布成功",map);
        }
        return ResultResponse.make(500,"一键发布失败",null);
    }
}
