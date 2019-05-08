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

public class GeoExcelUtil {

    /**
     * 读EXCEL文件，获取信息集合
     *
     * @return
     */
    public static List<Map<String, Object>> getExcelInfo(Map<String,String> disasterMap,MultipartFile mFile) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcel(disasterMap,mFile.getInputStream(), isExcel2003);
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
    public static List<Map<String, Object>> createExcel(Map<String,String> disasterMap, InputStream is, boolean isExcel2003) {
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
            List<Map<String, Object>> data = getData(disasterMap,sheet);
          return data;// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Map<String,Object>> getData(Map<String,String> disasterMap,Sheet sheet){
        int count = 0;
        List<Map<String,Object>> list=new ArrayList<>();
        if (!sheet.getRow(0).getCell(0).toString().equals("地灾点编号")
                && !sheet.getRow(0).getCell(1).toString().equals("地灾点名称")){
            return null;
        }
        for(Row row : sheet){
            // 跳过第一行的目录
            if(count < 1 ) {
                count++;
                continue;
            }
            //如果当前行没有数据，跳出循环
            if(!getValue(row.getCell(1)).toString().equals("")
                &&!getValue(row.getCell(2)).toString().equals("")
                &&!getValue(row.getCell(3)).toString().equals("")
                &&!getValue(row.getCell(4)).toString().equals("")){
                Map<String,Object> map=new HashMap<>();
                map.put("geoCode",getValue(row.getCell(0)).toString());
                map.put("geoName",getValue(row.getCell(1)).toString());
                String geoType=getValue(row.getCell(2)).toString();
                map.put("geoType",disasterMap.get(geoType));
                map.put("lon",getValue(row.getCell(3)).toString());
                map.put("lat",getValue(row.getCell(4)).toString());
                map.put("townCode",getValue(row.getCell(5)).toString());

                String dmFactor=getValue(row.getCell(6)).toString();
                if (dmFactor.equals("自然因素")) {
                    dmFactor = "0";
                } else if (dmFactor.equals("人为因素")) {
                    dmFactor = "1";
                } else if (dmFactor.equals("其他因素")) {
                    dmFactor = "99";
                }
                map.put("dmFactor",dmFactor);
                map.put("threatPerNum",getValue(row.getCell(7)).toString());
                map.put("economicLoss",getCountTwoValue(row.getCell(8)).toString());
                map.put("recordYear",getCountTwoValue(row.getCell(9)).toString());

                String rainfallFactor = getValue(row.getCell(10)).toString();
                String waterLineFactor = getValue(row.getCell(12)).toString();
                String soilFactor = getValue(row.getCell(14)).toString();
                if (rainfallFactor.equals("不包含")) {
                    rainfallFactor = "0";
                } else if (rainfallFactor.equals("包含")) {
                    rainfallFactor = "1";
                }
                if (waterLineFactor.equals("不包含")) {
                    waterLineFactor = "0";
                } else if (waterLineFactor.equals("包含")) {
                    waterLineFactor = "1";
                }
                if (soilFactor.equals("不包含")) {
                    soilFactor = "0";
                } else if (soilFactor.equals("包含")) {
                    soilFactor = "1";
                }
                map.put("rainfallFactor",rainfallFactor);
                map.put("waterLineFactor",waterLineFactor);
                map.put("soilFactor",soilFactor);
                map.put("rainfallThreshold",getValue(row.getCell(11)).toString());
                map.put("waterLineThreshold",getValue(row.getCell(13)).toString());
                map.put("soilThreshold",getValue(row.getCell(15)).toString());
                map.put("respUnit",getValue(row.getCell(16)).toString());
                map.put("respPerson",getValue(row.getCell(17)).toString());

                String mobilePhone=getValue(row.getCell(18)).toString();
                if(!mobilePhone.equals("")){
                    map.put("respMobilePhone",mobilePhone.substring(0,11));
                }else{
                    map.put("respMobilePhone","");
                }
                map.put("monitorPerson",getValue(row.getCell(19)).toString());
                String monMobilePhone=getValue(row.getCell(20)).toString();
                if(!monMobilePhone.equals("")){
                    map.put("monMobilePhone",monMobilePhone.substring(0,11));
                }else{
                    map.put("monMobilePhone","");
                }
                map.put("stationID",getValue(row.getCell(21)).toString());
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

    /**
     * 小数点两位
     * @param cell
     * @return
     */
    public static Object getCountTwoValue(Cell cell) {
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
                    DecimalFormat df = new DecimalFormat("0.00");
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


}