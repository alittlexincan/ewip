package com.hyt.server.controller.base;


import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.RiskForest;
import com.hyt.server.service.base.IRiskForestService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-森林火险灾害点控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-森林火险灾害点"}, description = "RiskForestController")
@RestController
@RequestMapping("/riskForest")
public class RiskForestController {

    @Autowired
    private IRiskForestService riskForestService;


    @ApiOperation(value="删除森林火险灾害点信息",httpMethod = "DELETE", notes="根据url的森林火险灾害点ID来删除森林火险灾害点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "森林火险灾害点ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.riskForestService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除森林火险灾害点成功");
        }
        return ResultResponse.make(500,"删除森林火险灾害点失败");
    }

    @ApiOperation(value="批量删除森林火险灾害点信息",httpMethod = "POST", notes="根据一批森林火险灾害点ID来删除森林火险灾害点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "森林火险灾害点ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.riskForestService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除森林火险灾害点成功");
        }
        return ResultResponse.make(500,"删除森林火险灾害点失败");
    }

    @ApiOperation(value = "查询森林火险灾害点信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有森林火险灾害点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="火险点名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="code",value="代码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="province",value="省", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="provinceCode",value="省代码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="city",value="市(地区/州)名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="cityCode",value="市(地区/州)代码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="district",value="区县", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="districtCode",value="区县代码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="area",value="影响面积", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="measures",value="防御措施", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitorOrgan",value="管理单位",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="monitorPeople", value="联系人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="contact", value="联系方式", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="weatherCauses", value="气象致灾因子", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="tmax", value="最高温", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lon", value="经度", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lat", value="纬度", required = true, dataType = "Double",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<RiskForest> pageInfo = this.riskForestService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

}
