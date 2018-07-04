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

import java.util.List;
import java.util.Map;

/**
 * @Author: XincanJiang
 * @Description: 用户控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"用户管理"}, description = "UserController")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value="用户登录信息",httpMethod="POST",notes="根据参数列表查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="loginName",value="登陆名称",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="loginPassword",value="登陆密码",required = true, dataType = "String",paramType = "query")
    })
    @PostMapping("/login")
    public ResultObject<User> login(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        System.out.println(map);
        User user = this.userService.login(map);
        return ResultResponse.ok(user);
    }


    @ApiOperation(value="添加用户信息",httpMethod="POST",notes="根据参数列表添加用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="loginName",value="登陆名称",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="loginPassword",value="登陆密码",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="真实名称", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sex",value="用户性别", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="phone",value="用户电话",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaId",value="用户所属地区",required = true, dataType = "String",paramType = "query")
    })
    @PostMapping("/insert")
    public ResultObject<Object> insert(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        JSONObject json = new JSONObject(map);
        User user = JSON.parseObject(json.toJSONString(), new TypeReference<User>() {});
        int num = this.userService.insert(user);
        if(num>0){
            return ResultResponse.make(200,"添加用户成功",user);
        }
        return ResultResponse.err("添加用户失败");
    }

    @ApiOperation(value="修改用户信息",httpMethod="POST", notes="根据用户ID，修改参数列表用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="用户ID", dataType = "String", required = true,paramType = "query"),
            @ApiImplicitParam(name="loginName",value="登陆名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="loginPassword",value="登陆密码", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="真实名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sex",value="用户性别", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="phone",value="用户电话", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaId",value="用户所属地区", dataType = "String",paramType = "query")
    })
    @PostMapping("/update")
    public ResultObject<Object> update(@ApiParam(hidden = true) @RequestParam Map<String,Object> map){
        System.out.println(map);
        JSONObject json = new JSONObject(map);
        User user = JSON.parseObject(json.toJSONString(), new TypeReference<User>() {});
        int num = this.userService.update(user);
        if(num>0){
            return ResultResponse.make(200,"修改用户成功");
        }
        return ResultResponse.err("修改用户失败");
    }

    @ApiOperation(value="删除用户详细信息",httpMethod = "DELETE", notes="根据url的用户id来删除用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.userService.deleteById(id);
        if(num>0){
          return  ResultResponse.make(200,"删除用户成功");
        }
        return ResultResponse.err("删除用户失败");
    }

    @ApiOperation(value="批量删除用户详细信息",httpMethod = "POST", notes="根据一批用户id来删除用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete/batch")
    public ResultObject<Object> deleteByIds(@RequestParam String id) {
        id = "'"+id.replaceAll(",","','")+"'";
        Integer num = this.userService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除用户成功");
        }
        return ResultResponse.err("删除用户失败");
    }

    @ApiOperation(value="查询用户详细信息",httpMethod = "GET", notes="根据url的用户id来查询用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType="path")
    })
    @GetMapping("/select/{id}")
    public ResultObject<User> selectById(@PathVariable(value = "id") String id) {
        User user = this.userService.selectById(id);
        return ResultResponse.ok(user);
    }

    @ApiOperation(value = "查询用户信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="开始时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="endTime",value="结束时间", dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name="id",value="用户ID", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="loginName",value="登陆名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="loginPassword",value="登陆密码", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="name",value="真实名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sex",value="用户性别", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="phone",value="用户电话", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="areaId",value="用户所属地区", dataType = "String",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<List<User>> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<User> pageInfo = this.userService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }


}
