package com.hyt.server.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.HashMap;
import java.util.Map;

/**
 * 拼音处理类
 *
 */
public class PinyinUtils {

    /**
     * 多音字处理方案
     * 
     * <pre>
     *      key：中文字符 可一个或者一个词组
     *      value：Integer 数组 
     *              对应获取多个拼音中的第几个，从 0开始
     *              中文字有几个 数组值就有几个
     * 
     * <pre>
     * 
     */
    private static Map<String, Integer[]> polyphone = new HashMap<>();

    static {
        polyphone.put("长沙", new Integer[] { 1, 0 });
    }

    /**
     * 将汉字转换为全拼
     * 
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        //下角标
        boolean isPolyphone = false;
        //在多拼音常量中获取下角标
        Integer[] subscripts = polyphone.get(src.trim());
        if (null != subscripts && subscripts.length > 0) {
            isPolyphone = true;
        }

        char[] t1 = src.toCharArray();
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();

        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符  
                if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += isPolyphone ? t2[subscripts[i]] : t2[0];
                } else
                    t4 += Character.toString(t1[i]);
            }
            // System.out.println(t4);  
            return t4;
        } catch(BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

   
}