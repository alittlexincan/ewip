package com.hyt.server.controller.base;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.base.UnitReservoir;
import com.hyt.server.service.base.IUnitReservoirService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: lxm
 * @Description: 基础数据-水库控制层
 * @Date: Created in 18:04 2018-11-2
 * @Modified By:
 */
@Api(tags = {"基础数据-水库"}, description = "UnitReservoirController")
@RestController
@RequestMapping("/unitReservoir")
public class UnitReservoirController {

    @Autowired
    private IUnitReservoirService unitReservoirService;


    @ApiOperation(value = "删除水库信息", httpMethod = "DELETE", notes = "根据url的水库ID来删除水库信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "水库ID", required = true, dataType = "String", paramType = "path")
    })
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.unitReservoirService.deleteById(id);
        if (num > 0) {
            return ResultResponse.make(200, "删除水库成功");
        }
        return ResultResponse.make(500, "删除水库失败");
    }

    @ApiOperation(value = "批量删除水库信息", httpMethod = "POST", notes = "根据一批水库ID来删除水库信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "水库ID", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.unitReservoirService.deleteByIds(id);
        if (num > 0) {
            return ResultResponse.make(200, "删除水库成功");
        }
        return ResultResponse.make(500, "删除水库失败");
    }

    @ApiOperation(value = "查询水库信息列表", httpMethod = "GET", notes = "根据查询条件分页查询所有水库信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", defaultValue = "0", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页条数", defaultValue = "10", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "sortName", value = "排序字段名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "sortOrder", value = "排序规则(ASC,DESC)，默认DESC", defaultValue = "DESC", dataType = "String", paramType = "query"),

            @ApiImplicitParam(name = "name", value = "水库名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "district", value = "区县", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "districtCode", value = "区县编码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "address", value = "详细地址", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "level", value = "级别", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "storage", value = "总库容(万m3)", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "limitStorage", value = "防限库容(万m3)", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "waterLimit", value = "防限水位(m)", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "waterNormal", value = "正常蓄水位(m)", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "principal", value = "负责人", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "waterLine", value = "有无水位", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "lon", value = "负责人", required = true, dataType = "Double", paramType = "query"),
            @ApiImplicitParam(name = "lat", value = "联系电话", required = true, dataType = "Double", paramType = "query")
    })
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String, Object> map) {
        PageInfo<UnitReservoir> pageInfo = this.unitReservoirService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @ApiOperation(value = "查询水库信息列表", httpMethod = "GET", notes = "查询所有水库信息用于地图展示")
    @GetMapping("/list")
    public ResultObject<Object> selectList() {
        List<UnitReservoir> list = this.unitReservoirService.selectAll();
        if (list.size() > 0) {
            return ResultResponse.make(200, "查询成功", list);
        }
        return ResultResponse.make(500, "查询失败", null);
    }
}