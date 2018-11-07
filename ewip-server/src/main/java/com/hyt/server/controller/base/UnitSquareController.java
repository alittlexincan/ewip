package com.hyt.server.controller.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitSquare;
import com.hyt.server.service.base.IUnitSquareService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-广场控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-广场"}, description = "UnitSquareController")
@RestController
@RequestMapping("/unitSquare")
public class UnitSquareController {

    @Autowired
    private IUnitSquareService unitSquareService;


    @ApiOperation(value="删除广场信息",httpMethod = "DELETE", notes="根据url的广场ID来删除广场信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广场ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.unitSquareService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除广场成功");
        }
        return ResultResponse.make(500,"删除广场失败");
    }

    @ApiOperation(value="批量删除广场信息",httpMethod = "POST", notes="根据一批广场ID来删除广场信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广场ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.unitSquareService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除广场成功");
        }
        return ResultResponse.make(500,"删除广场失败");
    }

    @ApiOperation(value = "查询广场信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有广场信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="广场名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="district",value="区县",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="unit",value="所属部门",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="area",value="占地面积", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="capacity",value="可容纳人数", required = true, dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="principal", value="负责人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="phone", value="联系电话", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="description",value="广场描述", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="lon", value="负责人", required = true, dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name="lat", value="联系电话", required = true, dataType = "Double",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<UnitSquare> pageInfo = this.unitSquareService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="查询广场信息列表",httpMethod="GET",notes="查询所有广场信息用于地图展示")
    @GetMapping("/list")
    public ResultObject<Object> selectList(){
        List<UnitSquare> list = this.unitSquareService.selectAll();
        if(list.size()>0){
            return ResultResponse.make(200,"查询成功",list);
        }
        return ResultResponse.make(500,"查询失败",null);
    }
}
