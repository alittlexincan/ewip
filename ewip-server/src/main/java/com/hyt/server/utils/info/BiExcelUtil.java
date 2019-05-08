package com.hyt.server.utils.info;
import com.hyt.server.entity.info.BiInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BiExcelUtil {

    /**
     * 读EXCEL文件，获取信息集合
     *
     * @return
     */
    public static List<BiInfo> getExcelInfo(MultipartFile mFile) {
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
     *
     * @param is          输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public static List<BiInfo> createExcel(InputStream is, boolean isExcel2003) {
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
            List<BiInfo> data = getData(sheet);
            return data;// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<BiInfo> getData(Sheet sheet) {
        int count = 0;
        List<BiInfo> listNew = new ArrayList<>();
        if (!sheet.getRow(0).getCell(0).toString().equals("中小河流代码")
                && !sheet.getRow(0).getCell(1).toString().equals("中小河流名称")) {
            return null;
        }
        for (Row row : sheet) {
            // 跳过第一行的目录
            if (count < 1) {
                count++;
                continue;
            }

            //如果当前行没有数据，跳出循环
            if (!getValue(row.getCell(1)).toString().equals("")
                    && !getValue(row.getCell(2)).toString().equals("")
                    && !getValue(row.getCell(3)).toString().equals("")) {
                BiInfo biInfo = new BiInfo();
                biInfo.setBasinCode(getValue(row.getCell(0)).toString());
                biInfo.setBasinName(getValue(row.getCell(1)).toString());
                biInfo.setLon(Double.parseDouble(getValue(row.getCell(2)).toString()));
                biInfo.setLat(Double.parseDouble(getValue(row.getCell(3)).toString()));
                biInfo.setBasinArea(Double.parseDouble(getValue(row.getCell(4)).toString()));
                biInfo.setPopulation(Double.parseDouble(getValue(row.getCell(5)).toString()));
                biInfo.setTownCode(getValue(row.getCell(6)).toString());
                biInfo.setBasinLength(Double.parseDouble(getValue(row.getCell(7)).toString()));
                biInfo.setEstsiteAddrName(getValue(row.getCell(8)).toString());
                biInfo.setEstsiteAddrHeight(Double.parseDouble(getValue(row.getCell(9)).toString()));
                String rainfallFactor = getValue(row.getCell(10)).toString();
                String waterLineFactor = getValue(row.getCell(11)).toString();
                String soilFactor = getValue(row.getCell(12)).toString();
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
                biInfo.setRainfallFactor(rainfallFactor);
                biInfo.setWaterLineFactor(waterLineFactor);
                biInfo.setSoilFactor(soilFactor);
                biInfo.setRainfallThreshold(getValue(row.getCell(13)).toString());
                biInfo.setWaterLineThreshold(getValue(row.getCell(14)).toString());
                biInfo.setSoilFactor(getValue(row.getCell(15)).toString());
                biInfo.setContact(getValue(row.getCell(16)).toString());
                biInfo.setMobilePhone(getValue(row.getCell(17)).toString().substring(0, 11));
                biInfo.setStationID(getValue(row.getCell(18)).toString().substring(0, 11));
                listNew.add(biInfo);
            }
        }
        System.out.println("有效行：" + listNew.size());
        return listNew;
    }

    public static Object getValue(Cell cell) {
        Object obj = null;
        if (cell != null && cell.getCellType() != CellType.BLANK) {
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
            obj.toString().replaceAll(" +", "");
        } else {
            obj = "";
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

//    public static void main(String[] args) {
//        Workbook workbook = null;
//        File excelFile = new File("D:\\学校.xlsx"); // 创建文件对象
//        try {
//            FileInputStream in = new FileInputStream(excelFile); // 文件流
//            try {
//                workbook = new XSSFWorkbook(in);
//                Sheet sheet = workbook.getSheetAt(0);
//                int countRow = sheet.getLastRowNum();          // 获取sheet总行数
//                System.out.println("总条数： " + countRow);
//
//                List<BiInfo> data = getData(sheet);
//                System.out.println(data);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

//    public static void main(String[] args) {
//        String str = "232ljsfsf.sdfl23.ljsdfsdfsdfss.23423.sdfsdfsfd";
//        //获得第一个点的位置
//        int index = str.indexOf(".");
//        System.out.println(index);
//        //根据第一个点的位置 获得第二个点的位置
//        index = str.indexOf(".", index + 1);
//        //根据第二个点的位置，截取 字符串。得到结果 result
//        String result = str.substring(index);
//        //输出结果
//        System.out.println(result);
//    }
}