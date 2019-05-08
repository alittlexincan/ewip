package com.hyt.client.utils.info;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DsExcelUtil {

    /**
     * 读EXCEL文件，获取信息集合
     *
     * @return
     */
    public static List<Map<String, Object>> getExcelInfo(MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
               e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据excel里面的内容读取信息
     * @param is      输入流
     * @param isExcel2003   excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public static List<Map<String, Object>> createExcel(InputStream is, boolean isExcel2003) {
        try {
            Workbook workbook = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                workbook = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                workbook = new XSSFWorkbook(is);
            }
            Sheet sheet = workbook.getSheetAt(0);
            int countRow = sheet.getLastRowNum();          // 获取sheet总行数
            System.out.println("总条数： " + countRow);
            List<Map<String, Object>> data = getData(sheet);
          return data;// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Map<String,Object>> getData(Sheet sheet){
        int count = 0;
        List<Map<String,Object>> list=new ArrayList<>();
        if (!sheet.getRow(0).getCell(0).toString().equals("终端编号")
                && !sheet.getRow(0).getCell(1).toString().equals("厂商编号")){
            return null;
        }
        for(Row row : sheet){
            // 跳过第一行的目录
            if(count < 1 ) {
                count++;
                continue;
            }
            //如果当前行没有数据，跳出循环
            if(!getValue(row.getCell(0)).toString().equals("")
                &&!getValue(row.getCell(1)).toString().equals("")
                &&!getValue(row.getCell(5)).toString().equals("")
                &&!getValue(row.getCell(6)).toString().equals("")){
                Map<String,Object> map=new HashMap<>();
                map.put("clientID",getValue(row.getCell(0)).toString());
                String factoryID = getValue(row.getCell(1)).toString();
                if(factoryID.equals("双顺达")){
                    factoryID="0001";
                }else if(factoryID.equals("伍豪科技")){
                    factoryID="0002";
                }else if(factoryID.equals("沈阳恒远")){
                    factoryID="0003";
                }else if(factoryID.equals("强讯公司")){
                    factoryID="0004";
                }else if(factoryID.equals("华泰德丰")){
                    factoryID="0005";
                }else if(factoryID.equals("联合远航")){
                    factoryID="0006";
                }else if(factoryID.equals("赛乐科技")){
                    factoryID="0007";
                }else if(factoryID.equals("瑞彩")){
                    factoryID="0008";
                }else if(factoryID.equals("天齐信息")){
                    factoryID="0015";
                }else if(factoryID.equals("安徽中科金诚")){
                    factoryID="0016";
                }else if(factoryID.equals("深圳昆特")){
                    factoryID="0017";
                }else if(factoryID.equals("成都奥天")){
                    factoryID="0018";
                }else if(factoryID.equals("河南物理所")){
                    factoryID="0019";
                }else if(factoryID.equals("平治东方")){
                    factoryID="0020";
                }else if(factoryID.equals("花冠")){
                    factoryID="0021";
                }else if(factoryID.equals("畅运")){
                    factoryID="0022";
                }else if(factoryID.equals("锦州创安")){
                    factoryID="0023";
                }else if(factoryID.equals("电视台")){
                    factoryID="0024";
                }else if(factoryID.equals("广播电台")){
                    factoryID="0025";
                }else if(factoryID.equals("其他厂家")){
                    factoryID="0099";
                }else {
                    factoryID="0099";
                }
                map.put("factoryID",factoryID);
                map.put("factoryServerID",getValue2(row.getCell(2)).toString());
                map.put("originalClientID",getValue(row.getCell(3)).toString());

                String clientStyle = getValue(row.getCell(4)).toString();
                if(clientStyle.equals("大喇叭")){
                    clientStyle="0";
                }else if(clientStyle.equals("电子屏")){
                    clientStyle="1";
                }else if(clientStyle.equals("北斗")){
                    clientStyle="2";
                }else if(clientStyle.equals("呼叫中心")){
                    clientStyle="3";
                }else if(clientStyle.equals("短信")){
                    clientStyle="4";
                }else if(clientStyle.equals("传真")){
                    clientStyle="5";
                }else if(clientStyle.equals("邮件")){
                    clientStyle="6";
                }else if(clientStyle.equals("电视")){
                    clientStyle="7";
                }else if(clientStyle.equals("广播")){
                    clientStyle="8";
                }else if(clientStyle.equals("微博")){
                    clientStyle="9";
                }else if(clientStyle.equals("微信")){
                    clientStyle="10";
                }else if(clientStyle.equals("网站")){
                    clientStyle="11";
                }else if(clientStyle.equals("手机客户端")){
                    clientStyle="12";
                }else if(clientStyle.equals("海洋广播")){
                    clientStyle="13";
                }else if(clientStyle.equals("气象频道")){
                    clientStyle="14";
                }else if(clientStyle.equals("预警智能盒子")){
                    clientStyle="15";
                }else if(clientStyle.equals("其他设备")){
                    clientStyle="99";
                }else{
                    clientStyle="99";
                }
                map.put("clientStyle",clientStyle);
                map.put("lon",getValue(row.getCell(5)));
                map.put("lat",getValue(row.getCell(6)));
                map.put("address",getValue(row.getCell(7)));
                map.put("clientPerson",getValue(row.getCell(8)));

                String mobilePhone=getValue(row.getCell(9)).toString();
                if(!mobilePhone.equals("")){
                    map.put("clientTel",mobilePhone.substring(0,11));
                }else{
                    map.put("clientTel","");
                }
                list.add(map);
            }
        }
        System.out.println("有效行："+list.size());
        return list;
    }

    public static Object getValue(Cell cell) {
        Object obj = null;
        if(cell!=null && cell.getCellType() !=CellType.BLANK){
            switch (cell.getCellTypeEnum()) {
                case BOOLEAN:
                    obj = cell.getBooleanCellValue();
                    break;
                case ERROR:
                    obj = cell.getErrorCellValue();
                    break;
                case NUMERIC:
                    DecimalFormat df = new DecimalFormat("0.000000");
                    obj = df.format(cell.getNumericCellValue());
                    break;
                case STRING:
                    obj = cell.getStringCellValue();
                    break;
                default:
                    break;
            }
            obj.toString().replaceAll(" +","");
        }else{
            obj="";
        }
        return obj;
    }

    public static Object getValue2(Cell cell) {
        Object obj = null;
        if(cell!=null && cell.getCellType() !=CellType.BLANK){
            switch (cell.getCellTypeEnum()) {
                case BOOLEAN:
                    obj = cell.getBooleanCellValue();
                    break;
                case ERROR:
                    obj = cell.getErrorCellValue();
                    break;
                case NUMERIC:
                    DecimalFormat df = new DecimalFormat("#");
                    obj = df.format(cell.getNumericCellValue());
                    break;
                case STRING:
                    obj = cell.getStringCellValue();
                    break;
                default:
                    break;
            }
            obj.toString().replaceAll(" +","");
        }else{
            obj="";
        }
        return obj;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }
    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }
    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    public static void main(String[] args) {
        Workbook workbook = null;
        File excelFile = new File("D:\\学校.xlsx"); // 创建文件对象
        try {
            FileInputStream in = new FileInputStream(excelFile); // 文件流
            try {
                workbook = new XSSFWorkbook(in);
                Sheet sheet = workbook.getSheetAt(0);
                int countRow = sheet.getLastRowNum();          // 获取sheet总行数
                System.out.println("总条数： " + countRow);

                List<Map<String,Object>> data = getData(sheet);
                System.out.println(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}