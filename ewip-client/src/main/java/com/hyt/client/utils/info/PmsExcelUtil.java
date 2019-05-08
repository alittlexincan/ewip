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

public class PmsExcelUtil {

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
        if (!sheet.getRow(0).getCell(0).toString().equals("学校名称")
                && !sheet.getRow(0).getCell(1).toString().equals("学校类型")){
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
                &&!getValue(row.getCell(2)).toString().equals("")
                &&!getValue(row.getCell(3)).toString().equals("")){
                Map<String,Object> map=new HashMap<>();
                map.put("schoolName",getValue(row.getCell(0)).toString());
                String schoolType = getValue(row.getCell(1)).toString();
                if(schoolType.equals("小学")){
                    schoolType="1";
                }else if(schoolType.equals("初级中学")){
                    schoolType="2";
                }else if(schoolType.equals("高级中学")){
                    schoolType="3";
                }else if(schoolType.equals("职业高中")){
                    schoolType="4";
                }else if(schoolType.equals("中等专业学校")){
                    schoolType="5";
                }else if(schoolType.equals("技工学校")){
                    schoolType="6";
                }else if(schoolType.equals("特殊教育学校")){
                    schoolType="7";
                }else if(schoolType.equals("其他类型")){
                    schoolType="8";
                }
                map.put("schoolType",schoolType);
                map.put("lon",getValue(row.getCell(2)));
                map.put("lat",getValue(row.getCell(3)));
                map.put("contact",getValue(row.getCell(4)).toString());
                map.put("mobilePhone",getValue(row.getCell(5)).toString());
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