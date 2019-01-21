package com.zhxu.message.utils;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.io.OutputFormat;

import java.io.FileOutputStream;
import java.io.File;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

public class Dom4jXmlUtil {

    /**
     * 生成xml方法
     */
    public static void createXml(JSONObject json){
        try {
            // 1、创建document对象
            Document document = DocumentHelper.createDocument();
            // 2、创建根节点rss
            Element alert = document.addElement("Alert");
            // 4、生成子节点及子节点内容
            Element platformID = alert.addElement("PlatformID");
            platformID.setText(json.getString("platformID"));

            Element identifier = alert.addElement("Identifier");
            identifier.setText(json.getString("identifier"));

            Element sender = alert.addElement("Sender");
            sender.setText(json.getString("sender"));

            Element senderCode = alert.addElement("SenderCode");
            senderCode.setText(json.getString("senderCode"));

            Element sendTime = alert.addElement("SendTime");
            sendTime.setText(json.getString("sendTime"));

            Element expires = alert.addElement("Expires");
            expires.setText(json.getString("expires"));

            Element msgType = alert.addElement("MsgType");
            msgType.setText(json.getString("msgType"));

            Element references = alert.addElement("References");
            references.setText(json.getString("references"));

            Element almType = alert.addElement("AlmType");
            almType.setText(json.getString("almType"));

            Element channelPlatformID = alert.addElement("ChannelPlatformID");
            channelPlatformID.setText(json.getString("channelPlatformID"));

            Element code = alert.addElement("Code");
            Element method = code.addElement("Method");
            Element methodType = method.addElement("MethodType");
            methodType.setText(json.getString("methodType"));

            Element methodName = method.addElement("MethodName");
            methodName.setText(json.getString("methodName"));

            Element list = method.addElement("List");
            Element message = list.addElement("PublishInfo");
            message.setText(json.getString("message"));

            Element devID = list.addElement("DevID");
            devID.setText(json.getString("devID"));

            Element info = alert.addElement("Info");
            Element eventType = info.addElement("EventType");
            eventType.setText(json.getString("eventType"));

            Element severity = info.addElement("Severity");
            severity.setText(json.getString("severity"));

            Element headline = info.addElement("Headline");
            headline.setText(json.getString("headline"));

            Element resource = info.addElement("Resource");
            Element digest = resource.addElement("Digest");
            digest.setText(json.getString("digest"));

            Element size = resource.addElement("Size");
            size.setText(json.getString("size"));

            Element resourceDesc = resource.addElement("ResourceDesc");
            resourceDesc.setText(json.getString("resourceDesc"));


            Element area = info.addElement("Area");
            Element areaDesc = area.addElement("AreaDesc");
            areaDesc.setText(json.getString("areaDesc"));

            Element geocode = area.addElement("Geocode");
            geocode.setText(json.getString("geocode"));


            // 5、设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码格式
            format.setEncoding("UTF-8");

            // 6、生成xml文件
            File file = new File(json.getString("path"));
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认使用转义字符
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            System.out.println("生成rss.xml成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成rss.xml失败");
        }
    }

    public static File isDirectory(String path, String fileName) {
        File fileDir = new File(path);
        if (!fileDir.isDirectory()) {
            fileDir.mkdir();
        }

        return new File(fileDir.getPath(), fileName);
    }





}
