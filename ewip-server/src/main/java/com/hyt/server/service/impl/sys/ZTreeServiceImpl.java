package com.hyt.server.service.impl.sys;

import com.hyt.server.config.common.universal.AbstractService;
import com.hyt.server.entity.sys.ZTree;
import com.hyt.server.mapper.sys.IZTreeMapper;
import com.hyt.server.service.sys.IZTreeService;
import com.xincan.utils.disaster.DisasterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * zTree树接口实现层
 * @Author: JiangXincan
 * @Description:
 * @Date: Created in 16:29 2018-4-18
 * @Modified By:
 */
@Service("zTreeService")
public class ZTreeServiceImpl extends AbstractService<ZTree> implements IZTreeService {

    @Autowired
    private IZTreeMapper zTreeMapper;

    @Override
    public List<ZTree> getAreaTree(Map<String, Object> map) {
        List<ZTree> list = this.zTreeMapper.getAreaTree(map);
        if(list.size() == 0) return null;
        for(ZTree tree : list){
           tree.setOpen(true);
        }
        return list;
    }

    @Override
    public List<ZTree> getOrganizationTree(Map<String, Object> map) {
        List<ZTree> list = this.zTreeMapper.getOrganizationTree(map);
        if(list.size() == 0) return null;
        for(ZTree tree : list){
            if(tree.getLevel() == 0 || tree.getLevel() == 1){
                tree.setOpen(true);
            }else{
                tree.setOpen(false);
            }
        }
        return list;
    }

    @Override
    public List<ZTree> getDisasterTree(Map<String, Object> map) {
        List<ZTree> list = this.zTreeMapper.getDisasterTree(map);
        if (list.size() == 0) return null;
        for (ZTree tree : list) {
            tree.setOpen(true);
        }
        return list;
    }

    @Override
    public List<ZTree> getDisasterLevelTree(Map<String, Object> map) {
        List<ZTree> list = this.zTreeMapper.getDisasterLevelTree(map);
        if (list.size() == 0) return null;
        for (ZTree tree : list) {
            if(tree.getIsConfig() == 1){
                tree.setName(tree.getName() + "[" + DisasterUtil.parseColorString(tree.getDisasterColor()) + "][" + DisasterUtil.parseLevelString(tree.getDisasterLevel()) + "]");
            }
            tree.setOpen(true);
        }
        return list;
    }

    @Override
    public List<ZTree> getUserGroupTree(Map<String, Object> map) {
        List<ZTree> list = this.zTreeMapper.getUserGroupTree(map);
        if(list.size() == 0) return null;
        for(ZTree tree : list) {
            tree.setOpen(true);
        }
        return list;
    }

    /**
     * 查询用户组对用受众个数树
     * @param map
     * @return
     */
    @Override
    public List<ZTree> getUserGroupCountTree(Map<String, Object> map) {
        List<ZTree> list = this.zTreeMapper.getUserGroupCountTree(map);
        if(list.size() == 0) return null;
        for(ZTree tree : list) {
            tree.setName(tree.getName() + "(" +tree.getCount()+ ")");
            tree.setOpen(true);
        }
        return list;
    }


    @Override
    public List<ZTree> getOrganizationUserGroupTree(Map<String, Object> map) {
        List<ZTree> list = this.zTreeMapper.getOrganizationUserGroupTree(map);
        if(list.size() == 0) return null;
        for(ZTree tree : list) {
            tree.setOpen(true);
        }
        return list;
    }
}
