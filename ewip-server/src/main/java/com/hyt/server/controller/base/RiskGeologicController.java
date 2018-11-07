package com.hyt.server.controller.base;


import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.RiskGeologic;
import com.hyt.server.service.base.IRiskGeologicService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-地质灾害隐患点控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-地质灾害隐患点"}, description = "RiskGeologicController")
@RestController
@RequestMapping("/riskGeologic")
public class RiskGeologicController {

    @Autowired
    private IRiskGeologicService riskGeologicService;


    @ApiOperation(value="删除地质灾害隐患点信息",httpMethod = "DELETE", notes="根据url的地质灾害隐患点ID来删除地质灾害隐患点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "地质灾害隐患点ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.riskGeologicService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除地质灾害隐患点成功");
        }
        return ResultResponse.make(500,"删除地质灾害隐患点失败");
    }

    @ApiOperation(value="批量删除地质灾害隐患点信息",httpMethod = "POST", notes="根据一批地质灾害隐患点ID来删除地质灾害隐患点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "地质灾害隐患点ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.riskGeologicService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除地质灾害隐患点成功");
        }
        return ResultResponse.make(500,"删除地质灾害隐患点失败");
    }

    @ApiOperation(value = "查询地质灾害隐患点信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有地质灾害隐患点信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="隐患点名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="district",value="区县", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="field_number",value="野外编号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="indoor_number",value="室内编号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="type",value="灾害点类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="street",value="镇街道办", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="rock",value="岩土成因", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="scale",value="灾害点规模", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="stability",value="稳定性", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="economicLoss",value="直接经济损失", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="threadPeople",value="威胁人口", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="threadProperty",value="威胁资产",required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="level", value="险情等级", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="weatherCauses", value="气象致灾因子", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="prec24", value="过去24小时雨量", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="prec1", value="过去1小时雨量", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lon", value="经度", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lat", value="纬度", required = true, dataType = "Double",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<RiskGeologic> pageInfo = this.riskGeologicService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value = "查询地质灾害隐患点信息列表", httpMethod = "GET", notes = "查询所有地质灾害隐患点信息")
    @GetMapping("/list")
    public ResultObject<Object> selectList(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        List<RiskGeologic> list = this.riskGeologicService.selectList(map);
        if(list.size()>0){
            return ResultResponse.make(200,"查询地质灾害隐患点成功",list);
        }
        return ResultResponse.make(500,"查询地质灾害隐患点失败",null);
    }
    
    
}
