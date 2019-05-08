package com.hyt.server.utils.info;

import com.hyt.server.entity.info.BiInfo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lxv
 * @Date: 2019/4/1 14:33
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("basinCode","xxxxxx");
        map.put("lon", 12.33);
        BiInfo biInfo = null;
        try {

            biInfo = mapToObject(map,BiInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(biInfo);
    }

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        if (map == null)
            return null;
        T obj = beanClass.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
            return obj;
        }
}
