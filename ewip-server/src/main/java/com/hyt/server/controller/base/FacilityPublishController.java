package com.hyt.server.controller.base;


import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.FacilityPublish;
import com.hyt.server.service.base.IFacilityPublishService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-发布设施控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-发布设施"}, description = "FacilityPublishController")
@RestController
@RequestMapping("/facilityPublish")
public class FacilityPublishController {

    @Autowired
    private IFacilityPublishService facilityPublishService;


    @ApiOperation(value="删除发布设施信息",httpMethod = "DELETE", notes="根据url的发布设施ID来删除发布设施信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "发布设施ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.facilityPublishService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除发布设施成功");
        }
        return ResultResponse.make(500,"删除发布设施失败");
    }

    @ApiOperation(value="批量删除发布设施信息",httpMethod = "POST", notes="根据一批发布设施ID来删除发布设施信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "发布设施ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.facilityPublishService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除发布设施成功");
        }
        return ResultResponse.make(500,"删除发布设施失败");
    }

    @ApiOperation(value = "查询发布设施信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有发布设施信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="设备名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="code",value="设备面好", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="factory",value="设备厂家", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="type",value="场所类型",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="address",value="详细地址", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="unit",value="所属部门",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="use",value="设备用途", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="principal", value="负责人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="phone", value="联系电话", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lon", value="经度", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lat", value="纬度", required = true, dataType = "Double",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<FacilityPublish> pageInfo = this.facilityPublishService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

}
