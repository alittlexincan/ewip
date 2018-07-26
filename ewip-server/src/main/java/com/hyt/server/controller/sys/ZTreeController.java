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
    public ResultObject<Object> getAreaTree(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<ZTree> areaTree = this.zTreeService.getAreaTree(map);
        return ResultResponse.ok(areaTree);
    }

    @ApiOperation(value = "查询机构树", httpMethod = "POST", notes = "根据查询条件查询机构树信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="机构ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaName",value="机构名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="机构编码", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="pId",value="上级机构", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaId",value="所属地区", dataType = "String",paramType = "query")
    })
    @PostMapping("/organization")
    public ResultObject<Object> getOrganizationTree(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<ZTree> organizationTree = this.zTreeService.getOrganizationTree(map);
        return ResultResponse.ok(organizationTree);
    }

    @ApiOperation(value = "查询灾种树", httpMethod = "POST", notes = "根据查询条件查询灾种树信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="灾种ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="灾种名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="灾种编码", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="pId",value="上级灾种", dataType = "String",paramType = "query")
    })
    @PostMapping("/disaster")
    public ResultObject<Object> getDisasterTree(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<ZTree> disasterTree = this.zTreeService.getDisasterTree(map);
        return ResultResponse.ok(disasterTree);
    }

    @ApiOperation(value = "查询灾种级别树", httpMethod = "POST", notes = "根据查询条件查询灾种树信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="灾种ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="灾种名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="灾种编码", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="pId",value="上级灾种", dataType = "String",paramType = "query")
    })
    @PostMapping("/disaster/level")
    public ResultObject<Object> getDisasterLevelTree(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<ZTree> disasterTree = this.zTreeService.getDisasterLevelTree(map);
        return ResultResponse.ok(disasterTree);
    }

    @ApiOperation(value = "查询群组树", httpMethod = "POST", notes = "根据查询条件查询群组树信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="群组ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="群组名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="pId",value="上级群组", dataType = "String",paramType = "query")
    })
    @PostMapping("/user/group")
    public ResultObject<Object> getUserGroupTree(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<ZTree> userGroupTree = this.zTreeService.getUserGroupTree(map);
        return ResultResponse.ok(userGroupTree);
    }

    @ApiOperation(value = "查询机构群组树", httpMethod = "POST", notes = "根据查询条件查询机构群组树信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="organizationId",value="机构ID", dataType = "String",paramType = "query")
    })
    @PostMapping("/organization/group")
    public ResultObject<Object> getOrganizationUserGroupTree(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<ZTree> organizationUserGroupTree = this.zTreeService.getOrganizationUserGroupTree(map);
        return ResultResponse.ok(organizationUserGroupTree);
    }

}
