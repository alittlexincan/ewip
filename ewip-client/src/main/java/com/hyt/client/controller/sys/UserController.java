package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IUserService;
import com.hyt.client.utils.ExcelUtil;
import com.hyt.client.utils.UserExceUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Author: JiangXincan
 * @Description: 受众信息控制层
 * @Date: Created in 10:07 2018-4-19
 * @Modified By:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 添加受众信息
     * @param map
     * @return
     */
    @PostMapping("/insert")
    public JSONObject insert(@RequestParam Map<String,Object> map){
        return this.userService.insert(map);
    }

    /**
     * 修改受众信息
     * @param map
     * @return
     */
    @PostMapping("/update")
    public JSONObject update(@RequestParam Map<String,Object> map){
        return this.userService.update(map);
    }

    /**
     * 根据受众ID删除受众信息
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public JSONObject deleteById(@PathVariable(value = "id") String id){
        return this.userService.deleteById(id);
    }

    /**
     * 批量删除受众信息
     * @param id
     * @return
     */
    @PostMapping("/delete")
    public JSONObject deleteBatch(@RequestParam(value = "id") String id){
        return this.userService.deleteBatch(id);
    }

    /**
     * 根据受众ID查询受众信息
     * @param id
     * @return
     */
    @PostMapping("/select/{id}")
    public JSONObject selectById(@PathVariable(value = "id") String id){
        return this.userService.selectById(id);
    }


    /**
     * 分页查询受众信息
     * @param map
     * @return
     */
    @GetMapping("/select")
    public JSONObject selectAll(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("empAreaId", employee.getString("areaId"));
        return this.userService.selectAll(map);
    }

    /**
     * 查询受众列表信息
     * @param map
     * @return
     */
    @GetMapping("/list")
    public JSONObject selectList(@RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("empAreaId", employee.getString("areaId"));
        return this.userService.selectList(map);
    }


    /**
     * 查询受众的详细信息
     * @param map
     * @return
     */
    @PostMapping("/userDetails")
    public JSONObject userDetails(@RequestParam Map<String,Object> map){
        return this.userService.userDetails(map);
    }



    /**
     * 下载文件
     * @return
     */
    @GetMapping("/downModel")
    public void downModel(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map<String,Object> map){
        Subject subject = SecurityUtils.getSubject();
        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
        map.put("empAreaId", employee.getString("areaId"));
        JSONObject jsonAll=this.userService.downModel(map);
        String channel=jsonAll.get("channelArry").toString();
        JSONArray channelArry = JSONArray.parseArray(channel);

        Workbook book = new XSSFWorkbook();
        try {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("应用程序/ x-下载");
            String filedisplay ="用户导入模板.xlsx";
            filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
            resp.addHeader("Content-Disposition","attachment; filename ="+ filedisplay);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 创建需要用户填写的sheet
        XSSFSheet sheetPro = (XSSFSheet) book.createSheet("用户模板信息");
        Row row0 = sheetPro.createRow(0);
        row0.createCell(0).setCellValue("省<必填>");
        sheetPro.setColumnWidth(0, 4000);
        row0.createCell(1).setCellValue("市<必填>");
        sheetPro.setColumnWidth(1, 4000);
        row0.createCell(2).setCellValue("区县<必填>");
        sheetPro.setColumnWidth(2, 4000);
        row0.createCell(3).setCellValue("乡镇");
        sheetPro.setColumnWidth(3, 4000);
        row0.createCell(4).setCellValue("机构<必填>");
        sheetPro.setColumnWidth(4, 4000);
        row0.createCell(5).setCellValue("群组<必填>");
        sheetPro.setColumnWidth(5, 4000);
        row0.createCell(6).setCellValue("渠道<必填>");
        sheetPro.setColumnWidth(6, 4000);
        row0.createCell(7).setCellValue("人员类型<必填>");
        sheetPro.setColumnWidth(7, 4000);
        row0.createCell(8).setCellValue("名称<必填>");
        sheetPro.setColumnWidth(8, 4000);
        row0.createCell(9).setCellValue("终端号码<必填>");
        sheetPro.setColumnWidth(9, 4000);
        row0.createCell(10).setCellValue("受众职务");
        sheetPro.setColumnWidth(10, 4000);
        row0.createCell(11).setCellValue("受众职位");
        sheetPro.setColumnWidth(11, 4000);
        row0.createCell(12).setCellValue("分管领导");
        sheetPro.setColumnWidth(12, 4000);
        row0.createCell(13).setCellValue("受众年龄");
        sheetPro.setColumnWidth(13, 4000);
        row0.createCell(14).setCellValue("受众性别");
        sheetPro.setColumnWidth(14, 4000);
        row0.createCell(15).setCellValue("详细地址");
        sheetPro.setColumnWidth(15, 4000);
        row0.createCell(16).setCellValue("经度<必填>");
        sheetPro.setColumnWidth(16, 4000);
        row0.createCell(17).setCellValue("纬度<必填>");
        sheetPro.setColumnWidth(17, 4000);
        row0.createCell(18).setCellValue("高度");
        sheetPro.setColumnWidth(18, 4000);

        String data=jsonAll.get("data").toString();
        JSONArray result = JSONArray.parseArray(data);
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
            String range = ExcelUtil.getRange(1, rowId, cityArry.size());
            Name name = book.createName();
            //key不可重复
            name.setNameName(provinceName);
            String formula = "area!" + range;
            name.setRefersToFormula(formula);
        }
        String cityArrys=jsonAll.get("cityArrys").toString();
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
            String range = ExcelUtil.getRange(1, rowId, cityArry.size());
            Name name = book.createName();
            //key不可重复
            name.setNameName(cityName);
            String formula = "area!" + range;
            name.setRefersToFormula(formula);
        }
        String countyArrys=jsonAll.get("countyArrys").toString();
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
            String range = ExcelUtil.getRange(1, rowId, countryArry.size());
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
        Sheet hideSheet1 = book.createSheet("site2");
        book.setSheetHidden(book.getSheetIndex(hideSheet1), true);
        // 查询所有的订单类型名称
//        String[] orderTypeList = {"发布中心","预案单位","应急办"};
        int rowId1 = 0;
        // 设置第一行，存省的信息
        Row proviRow1 = hideSheet1.createRow(rowId1++);
        proviRow1.createCell(0).setCellValue("类型");

        String[] orderTypeList = new String[channelArry.size()];
        for(int j = 0; j < channelArry.size(); j ++){
            JSONObject channelJson=channelArry.getJSONObject(j);
            Cell proviCell = proviRow1.createCell(j + 1);
            proviCell.setCellValue(channelJson.get("name").toString());
            orderTypeList[j]=channelJson.get("name").toString();
        }
        XSSFDataValidationHelper dvHelper1 = new XSSFDataValidationHelper((XSSFSheet)sheetPro);
        DataValidationConstraint provConstraint1 = dvHelper1.createExplicitListConstraint(orderTypeList);
        CellRangeAddressList provRangeAddressList1 = new CellRangeAddressList(1, 200, 6, 6);
        DataValidation provinceDataValidation1 = dvHelper1.createValidation(provConstraint1, provRangeAddressList1);
        provinceDataValidation1.createErrorBox("error", "请选择机构类型");
        provinceDataValidation1.setShowErrorBox(true);
        provinceDataValidation1.setSuppressDropDownArrow(true);
        sheetPro.addValidationData(provinceDataValidation1);
        //创建一个专门用来存放地区信息的隐藏sheet页
        //因此也不能在现实页之前创建，否则无法隐藏。
        Sheet hideSheet2 = book.createSheet("groupType");
        book.setSheetHidden(book.getSheetIndex(hideSheet2), true);
        // 查询
        String[] groupTypeList = {"责任人","基层防御人员"};
        int rowId2 = 0;
        // 设置第一行，存省的信息
        Row proviRow2 = hideSheet2.createRow(rowId2++);
        proviRow2.createCell(0).setCellValue("人员类型");
        for(int j = 0; j < groupTypeList.length; j ++){
            Cell proviCell = proviRow2.createCell(j + 1);
            proviCell.setCellValue(groupTypeList[j]);
        }
        XSSFDataValidationHelper dvHelper2 = new XSSFDataValidationHelper((XSSFSheet)sheetPro);
        DataValidationConstraint provConstraint2 = dvHelper2.createExplicitListConstraint(groupTypeList);
        CellRangeAddressList provRangeAddressList2 = new CellRangeAddressList(1, 200, 7, 7);
        DataValidation provinceDataValidation2 = dvHelper2.createValidation(provConstraint2, provRangeAddressList2);
        provinceDataValidation2.createErrorBox("error", "请选择人员类型");
        provinceDataValidation2.setShowErrorBox(true);
        provinceDataValidation2.setSuppressDropDownArrow(true);
        sheetPro.addValidationData(provinceDataValidation2);

        //对前20行设置有效性
        for(int line = 2;line < 2000; line++){
            ExcelUtil.setDataValidation("A" ,sheetPro, line,2);
            ExcelUtil.setDataValidation("B" ,sheetPro, line,3);
            ExcelUtil.setDataValidation("C" ,sheetPro, line,4);
        }
        try {
            OutputStream out = resp.getOutputStream();
            book.write(out);
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入Excel信息
     * @param map
     * @return
     */
    @PostMapping("/importExcel")
    public JSONObject importExcel(@RequestParam Map<String,Object> map, @RequestParam("addFile") MultipartFile file){
        List<Map<String, Object>> list =UserExceUtil.getExcelInfo(file);
        JSONObject json=new JSONObject();
        if(list!=null && list.size()>0){
            Subject subject = SecurityUtils.getSubject();
            JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
            map.put("empAreaId", employee.getString("areaId"));
            return this.userService.importData(map,list);
        }else{
            json.put("code","500");
            json.put("msg","导入失败!请检查文件内容");
            return json;
        }
    }


}
