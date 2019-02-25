package com.hyt.server.controller.ueditor;

import com.github.pagehelper.PageInfo;
import com.hyt.server.config.common.result.ResultObject;
import com.hyt.server.config.common.result.ResultResponse;
import com.hyt.server.entity.ueditor.Ueditor;
import com.hyt.server.service.ueditor.IServerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Auther: lxv
 * @Date: 2018/11/2 11:05
 * @Description:
 */
@RestController
@RequestMapping("serverProduct")
public class ServerProductController {

    @Autowired
    private IServerProductService serverProductService;

    /**
     * 查询所有产品信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    public ResultObject<Object> selectAll(@RequestParam Map<String,Object> map) {
        PageInfo<Ueditor> pageInfo = this.serverProductService.selectAll(map);
        return ResultResponse.page(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除产品
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultObject<Object> deleteById(@PathVariable(value = "id") String id) {
        Integer num = this.serverProductService.deleteById(id);
        if(num>0){
            return ResultResponse.make(200,"删除成功");
        }
        return ResultResponse.make(500,"删除失败");
    }

    /**
     * 查询产品列表
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ResultObject<Object> selectList(@RequestParam Map<String,Object> map) {
        List<Ueditor> ueditors = this.serverProductService.selectList(map);
        if(ueditors.size()>0){
            return  ResultResponse.make(200,"删除成功",ueditors);
        }
        return ResultResponse.make(500,"删除失败",null);
    }
}
