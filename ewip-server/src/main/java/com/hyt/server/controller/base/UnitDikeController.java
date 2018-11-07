package com.hyt.server.controller.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitDike;
import com.hyt.server.service.base.IUnitDikeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-提防控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-提防"}, description = "UnitDikeController")
@RestController
@RequestMapping("/unitDike")
public class UnitDikeController {

    @Autowired
    private IUnitDikeService unitDikeService;


    @ApiOperation(value="删除提防信息",httpMethod = "DELETE", notes="根据url的提防ID来删除提防信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "提防ID", required = true, dataType = "String", paramType="path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.unitDikeService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除提防成功");
        }
        return ResultResponse.make(500,"删除提防失败");
    }

    @ApiOperation(value="批量删除提防信息",httpMethod = "POST", notes="根据一批提防ID来删除提防信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "提防ID", required = true, dataType = "String", paramType="query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.unitDikeService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除提防成功");
        }
        return ResultResponse.make(500,"删除提防失败");
    }

    @ApiOperation(value = "查询提防信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有提防信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="当前页数", defaultValue="0", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="size",value="每页条数", defaultValue="10", dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name="sortName",value="排序字段名称", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="sortOrder",value="排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC",dataType = "String",paramType = "query"),

            @ApiImplicitParam(name="name",value="提防名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="river",value="河流",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="floodPrevention",value="防洪标准", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="length",value="长度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="altitude",value="高程", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="distance",value="平均堤距", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="height",value="高度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="width",value="堤顶宽度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="soil",value="堤身土质", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="slopeLength",value="堤岸堤坡长度", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="province",value="所属省份", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="unit",value="所属部门",required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="principal", value="负责人", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="phone", value="联系电话", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="description",value="提防描述", required = true, dataType = "String",paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        PageInfo<UnitDike> pageInfo = this.unitDikeService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value="查询提防信息列表",httpMethod="GET",notes="查询所有提防信息用于地图展示")
    @GetMapping("/selectList")
    public ResultObject<Object> selectList(){
        return ResultResponse.ok(this.unitDikeService.selectAll());
    }
}
