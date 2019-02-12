package com.zxyt.ocpp.client.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.zxyt.ocpp.client.config.common.result.ResultObject;
import com.zxyt.ocpp.client.config.common.result.ResultResponse;
import com.zxyt.ocpp.client.entity.sys.WarnConfig;
import com.zxyt.ocpp.client.service.sys.IWarnConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: msm
 * @Description: 预警管理控制层
 * @Date: Created in 18:04 2018-4-18
 * @Modified By:
 */
@Api(tags = {"预警管理"}, description = "WarnConfigController")
@RestController
@RequestMapping("/warnConfig")
public class WarnConfigController {

    @Autowired
    private IWarnConfigService warnConfigService;

    @GetMapping("/select")
    public ResultObject<Object> selectAll(@ApiParam(hidden = true) @RequestParam Map<String,Object> map) {
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("empAreaId", employee.getString("areaId"));

        PageInfo<WarnConfig> pageInfo = this.warnConfigService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    @PostMapping("/insert")
    public ResultObject<Object> insert(@RequestParam Map<String,Object> map){

        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("areaId", employee.getString("areaId"));
        map.put("organizationId", employee.getString("organizationId"));

        JSONObject json = new JSONObject(map);
        WarnConfig warnConfig = JSON.parseObject(json.toJSONString(), new TypeReference<WarnConfig>() {});
        int num = this.warnConfigService.insert(warnConfig);
        if(num>0){
            return ResultResponse.make(200,"添加地区成功",warnConfig);
        }
        return ResultResponse.make(500,"添加地区失败",null);
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public ResultObject<Object> deleteBatch(@RequestParam(value = "id") String id) {
        Integer num = this.warnConfigService.deleteByIds(id);
        if(num>0){
            return  ResultResponse.make(200,"删除预警配置成功");
        }
        return ResultResponse.make(500,"删除预警配置失败");
    }

    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.warnConfigService.deleteById(id);
        if(num>0){
            return  ResultResponse.make(200,"删除预警配置成功");
        }
        return ResultResponse.make(500,"删除预警配置失败");
    }

    @GetMapping("/config")
    public ResultObject<Object> selectConfig(@RequestParam Map<String,Object> map){
        try {
            return  ResultResponse.make(200,"查询预警配置成功", this.warnConfigService.selectConfig(map));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  ResultResponse.make(500,"查询预警配置失败", null);
    }


}
