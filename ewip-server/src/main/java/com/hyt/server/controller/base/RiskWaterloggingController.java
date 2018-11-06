package com.hyt.server.controller.base;


import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.RiskWaterlogging;
import com.hyt.server.service.base.IRiskWaterloggingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-内涝隐患点控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-内涝隐患点"}, description = "RiskWaterloggingController")
@RestController
@RequestMapping("/riskWaterlogging")
public class RiskWaterloggingController {

    @Autowired
    private IRiskWaterloggingService riskWaterloggingService;


    @ApiOperation(value="删除内涝隐患点信息",httpMethod = "DELETE", notes="根据url的内涝隐患点ID来删除内涝隐患点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "内涝隐患点ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.riskWaterloggingService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除内涝隐患点成功");
        }
        return ResultResponse.make(500,"删除内涝隐患点失败");
    }

    @ApiOperation(value="批量删除内涝隐患点信息",httpMethod = "POST", notes="根据一批内涝隐患点ID来删除内涝隐患点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "内涝隐患点ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.riskWaterloggingService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除内涝隐患点成功");
        }
        return ResultResponse.make(500,"删除内涝隐患点失败");
    }

    @ApiOperation(value = "查询内涝隐患点信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有内涝隐患点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="code",value="代码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="lon", value="经度", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lat", value="纬度", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="alt", value="高程", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="province",value="省", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="provinceCode",value="省代码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="city",value="市(地区/州)名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="cityCode",value="市(地区/州)代码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="district",value="区县", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="districtCode",value="区县代码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="street",value="社区（街道）名", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="streetCode",value="社区（街道）代码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="startTime",value="内涝灾害开始时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="endTime",value="内涝灾害结束时间", required = true, dataType = "Date", paramType = "query"),
            @ApiImplicitParam(name="depth",value="最大积水深度", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="area",value="最大淹没面积", required = true, dataType = "Double",paramType = "query"),

            @ApiImplicitParam(name="prec1", value="过去1小时雨量", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="prec2", value="过去2小时雨量", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="prec3", value="过去3小时雨量", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="prec6", value="过去6小时雨量", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="prec12", value="过去12小时雨量", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="prec24", value="过去24小时雨量", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="rangeLon", value="受影响经度范围", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="rangeLat", value="受影响纬度范围", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitorOrgan",value="管理单位",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitorPeople", value="联系人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="contact", value="联系方式", required = true, dataType = "String",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<RiskWaterlogging> pageInfo = this.riskWaterloggingService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

}
