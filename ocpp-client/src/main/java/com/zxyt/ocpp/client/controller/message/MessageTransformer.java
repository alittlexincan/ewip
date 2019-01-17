package com.zxyt.ocpp.client.controller.message;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhxu.model.message.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageTransformer {
    public PubInfo transform(JSONObject msg) {
        String content = msg.getString("content");

        String record = msg.getString("record");
        JSONArray channels = msg.getJSONArray("channels");
        JSONArray areas = msg.getJSONArray("areas");
        JSONObject groups = msg.getJSONObject("groups");
        JSONObject users = msg.getJSONObject("users");
        JSONArray files = msg.getJSONArray("files");
        msg.getString("content");

        List<File> fi = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {

            JSONObject fil = files.getJSONObject(i);

            File f = new File();
            f.setName(fil.getString("name"));
            f.setSize(fil.getInteger("size"));
            f.setUrl(fil.getString("url"));

            fi.add(f);
        }

        Area area = new Area();
        area.setId(msg.getString("areaId"));
        area.setName(msg.getString("areaName"));

//        // 遍历 Channels
//        for (int i = 0; i < channels.size(); i++) {
//            JSONObject channel = channels.getJSONObject(i);
//            Channel c = new Channel();
//            c.setId(channel.getString("channelId"));
//            c.setName(channel.getString("channelName"));
//            c.setType(ChannelType.valueOf(channel.getString("channelCode")));
//
//            // 遍历 Areas
//            for (int j = 0; j < areas.size(); j++) {
//                JSONObject a = areas.getJSONObject(j);
//                Area
//
//            }
//        }

        List<Message> mss = new ArrayList<>();

        // 遍历 Areas
        for (int i = 0; i < areas.size(); i++) {
            JSONObject a = areas.getJSONObject(i);
            Area ar = new Area();
            ar.setId(a.getString("areaId"));
            ar.setName(a.getString("areaName"));
            ar.setCode(a.getString("areaCode"));

            for (int j = 0; j < channels.size(); j++) {
                JSONObject channel = channels.getJSONObject(j);
                Channel c = new Channel();
                c.setId(channel.getString("channelId"));
                c.setName(channel.getString("channelName"));
                c.setType(ChannelType.valueOf(channel.getString("channelCode")));


                JSONArray gs = groups.getJSONArray(c.getId());

                List<Group> gss = new ArrayList<>();

                for (int x = 0; x < gs.size(); x++) {
                    JSONObject g = gs.getJSONObject(x);
                    Group gg = new Group();
                    gg.setId(g.getString("userGroupId"));
                    gg.setName(g.getString("userGroupName"));

                    JSONArray us = users.getJSONArray(gg.getId());

                    List<User> uss = new ArrayList<>();

                    for (int y = 0; y < us.size(); y++) {
                        JSONObject u = us.getJSONObject(y);

                        User uu = new User();
                        uu.setName(u.getString("userName"));
                        switch (c.getType()) {
                            case SMS: uu.setMobile(u.getString("userCode")); break;
                            case EMAIL: uu.setEmail(u.getString("userCode")); break;
                        }
                        uss.add(uu);
                    }

                    gg.setUsers(uss);

                    gss.add(gg);
                }

                Message mms = new Message();

                mms.setArea(ar);
                mms.setChannel(c);
                mms.setContent(content);
                mms.setGroups(gss);
                mms.setFiles(fi);

                mss.add(mms);
            }

        }

        PubInfo pubInfo = new PubInfo();
        pubInfo.setTitle(msg.getString("title"));
        pubInfo.setType(msg.getString("type"));
        pubInfo.setRecord(record);
        pubInfo.setArea(area);

        pubInfo.setMessages(mss);
        return pubInfo;
    }
}
