package com.hyt.server.controller.sys;

import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.sys.ZTree;
import com.hyt.server.service.sys.IZTreeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: ZTree树管理控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"ZTree树管理"}, description = "ZTreeController")
@RestController
@RequestMapping("/tree")
public class ZTreeController {

    @Autowired
    private IZTreeService zTreeService;

    @ApiOperation(value = "查询地区树", httpMethod = "POST", notes = "根据查询条件查询地区树信息")
    @ApiImplicitParams({
        @ApiImplicitParam(name="id",value="地区ID", dataType = "String",paramType = "query"),
        @ApiImplicitParam(name="areaName",value="地区名称", dataType = "String",paramType = "query"),
        @ApiImplicitParam(name="code",value="地区编码", dataType = "String",paramType = "query"),
        @ApiImplicitParam(name="pId",value="上级地区", dataType = "String",paramType = "query"),
        @ApiImplicitParam(name="level",value="地区级别", dataType = "Integer",paramType = "query")
    })
    @PostMapping("/area")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<ZTree> areaTree = this.zTreeService.getAreaTree(map);
        return ResultResponse.ok(areaTree);
    }


}
