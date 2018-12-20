package com.hyt.client.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {

        public static void main(String[] args) {
            Map<String, Object> map=new HashMap<>();
            Cascade2007(map);
//            getExcel("","",null);
        }
        public static void Cascade2007(Map<String, Object> map) {
            // 创建一个excel
            @SuppressWarnings("resource")
            Workbook book = new XSSFWorkbook();
            // 创建需要用户填写的sheet
            XSSFSheet sheetPro = (XSSFSheet) book.createSheet("机构信息");
            Row row0 = sheetPro.createRow(0);

            row0.createCell(0).setCellValue("省");
            sheetPro.setColumnWidth(0, 4000);
            row0.createCell(1).setCellValue("市");
            sheetPro.setColumnWidth(1, 4000);
            row0.createCell(2).setCellValue("区县(可空)");
            sheetPro.setColumnWidth(2, 4000);
            row0.createCell(3).setCellValue("乡镇(可空)");
            sheetPro.setColumnWidth(3, 4000);
            row0.createCell(4).setCellValue("机构类型");
            sheetPro.setColumnWidth(4, 4000);
            row0.createCell(5).setCellValue("机构名称");
            sheetPro.setColumnWidth(5, 4000);
            String json="[{\n" +
                    "\t'level': 1,\n" +
                    "\t'children': [{\n" +
                    "\t\t'level': 2,\n" +
                    "\t\t'children': [{\n" +
                    "\t\t\t'level': 3,\n" +
                    "\t\t\t'children': [{\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '二龙山镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': '46087aa0e40411e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '宏胜镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': '880e4720e40411e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '富锦镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': 'b60e4d59e3f811e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '锦山镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': 'be10f345e40411e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '头林镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': 'cc463aede40411e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '上街基镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': 'd0f63d04e3f811e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '向阳川镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': 'd342b242e40411e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '大榆树镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': 'd9ba6aa6e3fa11e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '兴隆岗镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': 'da63ab01e40411e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '长安镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': 'e0fee567e3f411e88da08cec4b81c244'\n" +
                    "\t\t\t}, {\n" +
                    "\t\t\t\t'level': 4,\n" +
                    "\t\t\t\t'name': '砚山镇',\n" +
                    "\t\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t\t'id': 'e1b66ecbe40411e88da08cec4b81c244'\n" +
                    "\t\t\t}],\n" +
                    "\t\t\t'name': '富锦市',\n" +
                    "\t\t\t'pid': 'db374362e3f111e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'cf6edfbae3f311e88da08cec4b81c244'\n" +
                    "\t\t}],\n" +
                    "\t\t'name': '佳木斯市',\n" +
                    "\t\t'pid': '10e8679ddc3e11e8bfa38cec4b81c244',\n" +
                    "\t\t'id': 'db374362e3f111e88da08cec4b81c244'\n" +
                    "\t}],\n" +
                    "\t'name': '黑龙江省',\n" +
                    "\t'pid': '',\n" +
                    "\t'id': '10e8679ddc3e11e8bfa38cec4b81c244'\n" +
                    "}]";
//            String json=map.get("data").toString();
            JSONArray result = JSONArray.parseArray(json);
            //创建一个专门用来存放地区信息的隐藏sheet页
            //因此也不能在现实页之前创建，否则无法隐藏。
            Sheet hideSheet = book.createSheet("area");
            //这一行作用是将此sheet隐藏，功能未完成时注释此行,可以查看隐藏sheet中信息是否正确
            book.setSheetHidden(book.getSheetIndex(hideSheet), true);
            int rowId = 0;
            // 设置所有行
            Row rows = hideSheet.createRow(rowId++);

            rows.createCell(0).setCellValue("省列表");
            String[] province = new String[result.size()];
            for(int i=0;i<result.size();i++){
                JSONObject provinceJson=result.getJSONObject(i);
                Cell provinceCell = rows.createCell(i + 1);
                provinceCell.setCellValue(provinceJson.get("name").toString());
                province[i] = provinceJson.get("name").toString();
            }

            // 将具体的数据写入到每一行中，行开头为父级区域，后面是子区域。
            for(int c = 0;c < result.size(); c++){
                JSONObject provinceJson=result.getJSONObject(c);
                String provinceName=provinceJson.get("name").toString();
                String city=provinceJson.get("children").toString();
                JSONArray cityArry = JSONArray.parseArray(city);
                Row cityRow = hideSheet.createRow(rowId++);
                cityRow.createCell(0).setCellValue(provinceName);
                for(int j = 0; j < cityArry.size(); j ++){
                    JSONObject cityJson=cityArry.getJSONObject(j);
                    Cell cityCell = cityRow.createCell(j + 1);
                    cityCell.setCellValue(cityJson.get("name").toString());
                }
                // 添加名称管理器
                String range = getRange(1, rowId, cityArry.size());
                Name name = book.createName();
                //key不可重复
                name.setNameName(provinceName);
                String formula = "area!" + range;
                name.setRefersToFormula(formula);
            }
            String cityArrys="[{\n" +
                    "\t'level': 2,\n" +
                    "\t'children': [{\n" +
                    "\t\t'level': 3,\n" +
                    "\t\t'children': [{\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '二龙山镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': '46087aa0e40411e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '宏胜镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': '880e4720e40411e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '富锦镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'b60e4d59e3f811e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '锦山镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'be10f345e40411e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '头林镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'cc463aede40411e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '上街基镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'd0f63d04e3f811e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '向阳川镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'd342b242e40411e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '大榆树镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'd9ba6aa6e3fa11e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '兴隆岗镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'da63ab01e40411e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '长安镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'e0fee567e3f411e88da08cec4b81c244'\n" +
                    "\t\t}, {\n" +
                    "\t\t\t'level': 4,\n" +
                    "\t\t\t'name': '砚山镇',\n" +
                    "\t\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t\t'id': 'e1b66ecbe40411e88da08cec4b81c244'\n" +
                    "\t\t}],\n" +
                    "\t\t'name': '富锦市',\n" +
                    "\t\t'pid': 'db374362e3f111e88da08cec4b81c244',\n" +
                    "\t\t'id': 'cf6edfbae3f311e88da08cec4b81c244'\n" +
                    "\t}],\n" +
                    "\t'name': '佳木斯市',\n" +
                    "\t'pid': '10e8679ddc3e11e8bfa38cec4b81c244',\n" +
                    "\t'id': 'db374362e3f111e88da08cec4b81c244'\n" +
                    "}]";
//            String cityArrys=map.get("cityArrys").toString();
            JSONArray cityArry = JSONArray.parseArray(cityArrys);
            for(int c = 0;c < cityArry.size(); c++){
                JSONObject cityJson=cityArry.getJSONObject(c);
                String cityName=cityJson.get("name").toString();
                String county=cityJson.get("children").toString();
                JSONArray countyArry = JSONArray.parseArray(county);
                Row cityRow = hideSheet.createRow(rowId++);
                cityRow.createCell(0).setCellValue(cityName);
                for(int j = 0; j < countyArry.size(); j ++){
                    JSONObject countyJson=countyArry.getJSONObject(j);
                    Cell cityCell = cityRow.createCell(j + 1);
                    cityCell.setCellValue(countyJson.get("name").toString());
                }
                // 添加名称管理器
                String range = getRange(1, rowId, cityArry.size());
                Name name = book.createName();
                //key不可重复
                name.setNameName(cityName);
                String formula = "area!" + range;
                name.setRefersToFormula(formula);
            }

            String countyArrys="[{\n" +
                    "\t'level': 3,\n" +
                    "\t'children': [{\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '二龙山镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': '46087aa0e40411e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '宏胜镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': '880e4720e40411e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '富锦镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': 'b60e4d59e3f811e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '锦山镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': 'be10f345e40411e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '头林镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': 'cc463aede40411e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '上街基镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': 'd0f63d04e3f811e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '向阳川镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': 'd342b242e40411e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '大榆树镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': 'd9ba6aa6e3fa11e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '兴隆岗镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': 'da63ab01e40411e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '长安镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': 'e0fee567e3f411e88da08cec4b81c244'\n" +
                    "\t}, {\n" +
                    "\t\t'level': 4,\n" +
                    "\t\t'name': '砚山镇',\n" +
                    "\t\t'pid': 'cf6edfbae3f311e88da08cec4b81c244',\n" +
                    "\t\t'id': 'e1b66ecbe40411e88da08cec4b81c244'\n" +
                    "\t}],\n" +
                    "\t'name': '富锦市',\n" +
                    "\t'pid': 'db374362e3f111e88da08cec4b81c244',\n" +
                    "\t'id': 'cf6edfbae3f311e88da08cec4b81c244'\n" +
                    "}]";
//            String countyArrys=map.get("countyArrys").toString();
            JSONArray countyArry = JSONArray.parseArray(countyArrys);
            for(int c = 0;c < countyArry.size(); c++){
                JSONObject countyJson=countyArry.getJSONObject(c);
                String countyName=countyJson.get("name").toString();
                String country=countyJson.get("children").toString();
                JSONArray countryArry = JSONArray.parseArray(country);
                Row countyRow = hideSheet.createRow(rowId++);
                countyRow.createCell(0).setCellValue(countyName);
                for(int j = 0; j < countryArry.size(); j ++){
                    JSONObject countryJson=countryArry.getJSONObject(j);
                    Cell countyCell = countyRow.createCell(j + 1);
                    countyCell.setCellValue(countryJson.get("name").toString());
                }
                // 添加名称管理器
                String range = getRange(1, rowId, countryArry.size());
                Name name = book.createName();
                //key不可重复
                name.setNameName(countyName);
                String formula = "area!" + range;
                name.setRefersToFormula(formula);
            }

            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet)sheetPro);
            // 省规则
            DataValidationConstraint provConstraint = dvHelper.createExplicitListConstraint(province);
            // 四个参数分别是：起始行、终止行、起始列、终止列
            CellRangeAddressList provRangeAddressList = new CellRangeAddressList(1, 200, 0, 0);
            DataValidation provinceDataValidation = dvHelper.createValidation(provConstraint, provRangeAddressList);
            //验证
            provinceDataValidation.createErrorBox("error", "请选择正确的省份");
            provinceDataValidation.setShowErrorBox(true);
            provinceDataValidation.setSuppressDropDownArrow(true);
            sheetPro.addValidationData(provinceDataValidation);

            //创建一个专门用来存放地区信息的隐藏sheet页
            //因此也不能在现实页之前创建，否则无法隐藏。
            Sheet hideSheet2 = book.createSheet("site2");
            book.setSheetHidden(book.getSheetIndex(hideSheet2), true);
            // 查询所有的订单类型名称
            String[] orderTypeList = {"发布中心","预案单位","应急办"};
            int rowId1 = 0;
            // 设置第一行，存省的信息
            Row proviRow1 = hideSheet2.createRow(rowId1++);
            proviRow1.createCell(0).setCellValue("类型");
            for(int j = 0; j < orderTypeList.length; j ++){
                Cell proviCell = proviRow1.createCell(j + 1);
                proviCell.setCellValue(orderTypeList[j]);
            }
            XSSFDataValidationHelper dvHelper1 = new XSSFDataValidationHelper((XSSFSheet)sheetPro);
            DataValidationConstraint provConstraint1 = dvHelper1.createExplicitListConstraint(orderTypeList);
            CellRangeAddressList provRangeAddressList1 = new CellRangeAddressList(1, 200, 4, 4);
            DataValidation provinceDataValidation1 = dvHelper1.createValidation(provConstraint1, provRangeAddressList1);
            provinceDataValidation1.createErrorBox("error", "请选择机构类型");
            provinceDataValidation1.setShowErrorBox(true);
            provinceDataValidation1.setSuppressDropDownArrow(true);
            sheetPro.addValidationData(provinceDataValidation1);

            //对前20行设置有效性
            for(int line = 2;line < 2000; line++){
                setDataValidation("A" ,sheetPro, line,2);
                setDataValidation("B" ,sheetPro, line,3);
                setDataValidation("C" ,sheetPro, line,4);
            }
            FileOutputStream os = null;
            try {
                os = new FileOutputStream("D:/ewip/model/机构模板.xlsx");
                book.write(os);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtils.closeQuietly(os);
            }
        }

        /**
         * 设置有效性
         * @param offset 主影响单元格所在列，即此单元格由哪个单元格影响联动
         * @param sheet
         * @param rowNum 行数
         * @param colNum 列数
         */
        public static void setDataValidation(String offset,XSSFSheet sheet, int rowNum,int colNum) {
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
            DataValidation data_validation_list;
            data_validation_list = getDataValidationByFormula(
                    "INDIRECT($" + offset + (rowNum) + ")", rowNum, colNum,dvHelper);
            sheet.addValidationData(data_validation_list);
        }

        /**
         * 加载下拉列表内容
         * @param formulaString
         * @param naturalRowIndex
         * @param naturalColumnIndex
         * @param dvHelper
         * @return
         */
        private static  DataValidation getDataValidationByFormula(String formulaString, int naturalRowIndex, int naturalColumnIndex,XSSFDataValidationHelper dvHelper) {
            // 加载下拉列表内容
            // 举例：若formulaString = "INDIRECT($A$2)" 表示规则数据会从名称管理器中获取key与单元格 A2 值相同的数据，
            //如果A2是江苏省，那么此处就是江苏省下的市信息。
            XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint(formulaString);
            // 设置数据有效性加载在哪个单元格上。
            // 四个参数分别是：起始行、终止行、起始列、终止列
            int firstRow = naturalRowIndex -1;
            int lastRow = naturalRowIndex - 1;
            int firstCol = naturalColumnIndex - 1;
            int lastCol = naturalColumnIndex - 1;
            CellRangeAddressList regions = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
            // 数据有效性对象
            // 绑定
            XSSFDataValidation data_validation_list = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
            data_validation_list.setEmptyCellAllowed(false);
            if (data_validation_list instanceof XSSFDataValidation) {
                data_validation_list.setSuppressDropDownArrow(true);
                data_validation_list.setShowErrorBox(true);
            } else {
                data_validation_list.setSuppressDropDownArrow(false);
            }
            // 设置输入信息提示信息
            data_validation_list.createPromptBox("下拉选择提示", "请使用下拉方式选择合适的值！");
            // 设置输入错误提示信息
            //data_validation_list.createErrorBox("选择错误提示", "你输入的值未在备选列表中，请下拉选择合适的值！");
            return data_validation_list;
        }

        /**
         *  计算formula
         * @param offset 偏移量，如果给0，表示从A列开始，1，就是从B列
         * @param rowId 第几行
         * @param colCount 一共多少列
         * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
         *
         */
        public static String getRange(int offset, int rowId, int colCount) {
            char start = (char)('A' + offset);
            if (colCount <= 25) {
                char end = (char)(start + colCount - 1);
                return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
            } else {
                char endPrefix = 'A';
                char endSuffix = 'A';
                if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
                    if ((colCount - 25) % 26 == 0) {// 边界值
                        endSuffix = (char)('A' + 25);
                    } else {
                        endSuffix = (char)('A' + (colCount - 25) % 26 - 1);
                    }
                } else {// 51以上
                    if ((colCount - 25) % 26 == 0) {
                        endSuffix = (char)('A' + 25);
                        endPrefix = (char)(endPrefix + (colCount - 25) / 26 - 1);
                    } else {
                        endSuffix = (char)('A' + (colCount - 25) % 26 - 1);
                        endPrefix = (char)(endPrefix + (colCount - 25) / 26);
                    }
                }
                return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
            }
        }

    public static void getExcel(String url, String fileName, HttpServletResponse response){
        try {
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            //2.设置文件头：最后一个参数是设置下载文件名
//            response.setHeader("Content-disposition", "attachment; filename=\""
//                    + encodeChineseDownloadFileName(request, fileName+".xls") +"\"");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String("机构模板".getBytes("UTF-8"), "ISO-8859-1") + ".xls"); //中文文件名
            //通过文件路径获得File对象
            File file = new File("D:/ewip/model/机构模板.xlsx");
            FileInputStream in = new FileInputStream(file);
            //3.通过response获取OutputStream对象(out)
            OutputStream out = new BufferedOutputStream(response.getOutputStream());

            int b = 0;
            byte[] buffer = new byte[2048];
            while ((b=in.read(buffer)) != -1){
                out.write(buffer,0,b); //4.写到输出流(out)中
            }
            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
