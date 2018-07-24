package com.hyt.server.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.sys.User;
import com.hyt.server.service.sys.IUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 受众管理控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"受众管理"}, description = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value="添加受众信息",httpMethod="POST",notes="根据参数列表添加受众信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name="name",value="受众名称",required = true, dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="code",value="终端号码",required = true, dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="userGroupId", value="群组ID", required = true, dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="channelId", value="渠道ID", required = true, dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="areaId",value="地区ID", dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="organizationId",value="机构ID", dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="longitude",value="地区经度", dataType = "Double",paramType = "query"),
                @ApiImplicitParam(name="latitude",value="地区纬度", dataType = "Double",paramType = "query"),
                @ApiImplicitParam(name="altitude",value="海拔高度", dataType = "Double",paramType = "query")
    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        User user = JSON.parseObject(json.toJSONString(), new TypeReference<User>() {});
        int num = this.userService.insert(user);
        if(num>0){
            return ResultResponse.make(200,"添加受众成功",user);
        }
        return ResultResponse.make(500,"添加受众失败",null);
    }

    @ApiOperation(value="修改受众信息",httpMethod="POST", notes="根据受众ID，修改参数受众信息")
    @ApiImplicitParams({
                @ApiImplicitParam(name="id",value="受众ID", dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="name",value="受众名称", dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="code",value="终端号码", dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="userGroupId", value="群组ID", dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="channelId", value="渠道ID", dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="areaId",value="地区ID", dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="organizationId",value="机构ID", dataType = "String",paramType = "query"),
                @ApiImplicitParam(name="longitude",value="地区经度", dataType = "Double",paramType = "query"),
                @ApiImplicitParam(name="latitude",value="地区纬度", dataType = "Double",paramType = "query"),
                @ApiImplicitParam(name="altitude",value="海拔高度", dataType = "Double",paramType = "query")
    })
    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        User user = JSON.parseObject(json.toJSONString(), new TypeReference<User>() {});
        int num = this.userService.update(user);
        if(num>0){
            return ResultResponse.make(200,"修改受众成功");
        }
        return ResultResponse.make(500,"修改受众失败");
    }

    @ApiOperation(value="删除受众信息",httpMethod = "DELETE", notes="根据url的受众ID来删除受众信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "受众ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.userService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除受众成功");
        }
        return ResultResponse.make(500,"删除受众失败");
    }

    @ApiOperation(value="批量删除受众信息",httpMethod = "POST", notes="根据一批受众ID来删除受众信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "受众ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.userService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除受众成功");
        }
        return ResultResponse.make(500,"删除受众失败");
    }

    @ApiOperation(value="查询受众信息",httpMethod = "POST", notes="根据url的受众ID来查询受众信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "受众ID", required = true, dataType = "String", paramType="path")
    })
    @PostMapping("/select/{id}")
    public ResultObject<Object> selectById(@PathVariable(value = "id") String id) {
        return ResultResponse.ok(this.userService.selectById(id));
    }

    @ApiOperation(value = "查询受众信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有受众信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="开始时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="endTime",value="结束时间", dataType = "Date",paramType = "query"),

            @ApiImplicitParam(name="id",value="受众ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="受众名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="code",value="终端号码", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="userGroupId", value="群组ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="channelId", value="渠道ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaId",value="地区ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="organizationId",value="机构ID", dataType = "String",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<User> pageInfo = this.userService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

}
