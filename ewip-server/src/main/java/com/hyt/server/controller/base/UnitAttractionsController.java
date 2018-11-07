package com.hyt.server.controller.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitAttractions;
import com.hyt.server.service.base.IUnitAttractionsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-旅游景区控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-旅游景区"}, description = "UnitAttractionsController")
@RestController
@RequestMapping("/unitAttractions")
public class UnitAttractionsController {

    @Autowired
    private IUnitAttractionsService unitAttractionsService;


    @ApiOperation(value="删除旅游景区信息",httpMethod = "DELETE", notes="根据url的旅游景区ID来删除旅游景区信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "旅游景区ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.unitAttractionsService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除旅游景区成功");
        }
        return ResultResponse.make(500,"删除旅游景区失败");
    }

    @ApiOperation(value="批量删除旅游景区信息",httpMethod = "POST", notes="根据一批旅游景区ID来删除旅游景区信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "旅游景区ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.unitAttractionsService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除旅游景区成功");
        }
        return ResultResponse.make(500,"删除旅游景区失败");
    }

    @ApiOperation(value = "查询旅游景区信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有旅游景区信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="景区名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="district",value="区县",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="unit",value="所属部门",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="area",value="占地面积", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="capacity",value="可容纳人数", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="worker",value="工作人员人数", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="address",value="详细地址", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="principal", value="负责人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="phone", value="联系电话", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="description",value="景区描述", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lon", value="负责人", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lat", value="联系电话", required = true, dataType = "Double",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<UnitAttractions> pageInfo = this.unitAttractionsService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="查询旅游景区信息列表",httpMethod="GET",notes="查询所有旅游景区信息用于地图展示")
    @GetMapping("/list")
    public ResultObject<Object> selectList(){
        List<UnitAttractions> list = this.unitAttractionsService.selectAll();
        if(list.size()>0){
            return ResultResponse.make(200,"查询成功",list);
        }
        return ResultResponse.make(500,"查询失败",null);
    }
}
