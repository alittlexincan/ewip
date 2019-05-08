package com.hyt.client.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.sys.IUserService;
import com.hyt.client.utils.ExcelUtil;
import com.hyt.client.utils.UserExceUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
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
        String areaCode=employee.getString("areaCode").toString();
        if(employee.getString("level").equals("1")){
            areaCode=areaCode.substring(0,2);
        }else if(employee.getString("level").equals("2")){
            areaCode=areaCode.substring(0,4);
        }else if(employee.getString("level").equals("3")){
            areaCode=areaCode.substring(0,6);
        }
        map.put("areaCode", areaCode);
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
        String areaCode=employee.getString("areaCode").toString();
        if(employee.getString("level").equals("1")){
            areaCode=areaCode.substring(0,2);
        }else if(employee.getString("level").equals("2")){
            areaCode=areaCode.substring(0,4);
        }else if(employee.getString("level").equals("3")){
            areaCode=areaCode.substring(0,6);
        }
        map.put("areaCode", areaCode);
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



    //创建下拉框
    private static void createDropDownList(Sheet taskInfoSheet, DataValidationHelper helper, String[] list,
                                           Integer firstRow, Integer lastRow, Integer firstCol, Integer lastCol) {
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        //设置下拉框数据
        DataValidationConstraint constraint = helper.createExplicitListConstraint(list);
        DataValidation dataValidation = helper.createValidation(constraint, addressList);
        //处理Excel兼容性问题
        if (dataValidation instanceof XSSFDataValidation) {
            dataValidation.setSuppressDropDownArrow(true);
            dataValidation.setShowErrorBox(true);
        } else {
            dataValidation.setSuppressDropDownArrow(false);
        }
        taskInfoSheet.addValidationData(dataValidation);
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
        String areaCode=employee.getString("areaCode").toString();
        if(employee.getString("level").equals("1")){
            areaCode=areaCode.substring(0,2);
        }else if(employee.getString("level").equals("2")){
            areaCode=areaCode.substring(0,4);
        }else if(employee.getString("level").equals("3")){
            areaCode=areaCode.substring(0,6);
        }
        map.put("areaCode", areaCode);
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
        XSSFRow row0 = sheetPro.createRow(0);

        String[] keyWord = {"省<必填>","市<必填>","区县<必填>","渠道<必填>",
                "人员类型<必填>","名称<必填>","终端号码<必填>","受众职务","受众职位",
                "分管领导","受众年龄","受众性别","详细地址","经度", "纬度"};
        for (int i = 0; i < keyWord.length; i++) {
            XSSFCell cell = row0.createCell(i);//第一个单元格
            cell.setCellValue(keyWord[i]);
            sheetPro.setColumnWidth(i, 4000);
        }

        String data=jsonAll.get("data").toString();
        System.out.println(data);
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
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheetPro);
        createDropDownList(sheetPro,dvHelper,province,1,100000,0,0);

        //创建一个专门用来存放类型信息的隐藏sheet页
        Sheet hideSheet1 = book.createSheet("channel");
        book.setSheetHidden(book.getSheetIndex(hideSheet1), true);
        int rowId1 = 0;
        Row proviRow1 = hideSheet1.createRow(rowId1++);
        proviRow1.createCell(0).setCellValue("渠道类型");
        String[] channelTypeList = new String[channelArry.size()];
        for(int j = 0; j < channelArry.size(); j ++){
            JSONObject channelJson=channelArry.getJSONObject(j);
            Cell channelCell = proviRow1.createCell(j + 1);
            channelCell.setCellValue(channelJson.get("name").toString());
            channelTypeList[j]=channelJson.get("name").toString();
        }
        XSSFDataValidationHelper dvHelper1 = new XSSFDataValidationHelper((XSSFSheet)sheetPro);
        createDropDownList(sheetPro,dvHelper1,channelTypeList,1,100000,3,3);


        Sheet hideSheet2 = book.createSheet("type");
        book.setSheetHidden(book.getSheetIndex(hideSheet2), true);
        String[] typeList = {"责任人","信息员"};
        int rowId2 = 0;
        Row personRow = hideSheet2.createRow(rowId2++);
        personRow.createCell(0).setCellValue("人员类型");
        for(int j = 0; j < typeList.length; j ++){
            Cell personCell = personRow.createCell(j + 1);
            personCell.setCellValue(typeList[j]);
        }
        XSSFDataValidationHelper dvHelper2 = new XSSFDataValidationHelper((XSSFSheet)sheetPro);
        createDropDownList(sheetPro,dvHelper2,typeList,1,100000,4,4);

        //对前20行设置有效性
        for(int line = 2;line < 2000; line++){
            ExcelUtil.setDataValidation("A" ,sheetPro, line,2);
            ExcelUtil.setDataValidation("B" ,sheetPro, line,3);
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
            String areaCode=employee.getString("areaCode").toString();
            if(employee.getString("level").equals("1")){
                areaCode=areaCode.substring(0,2);
            }else if(employee.getString("level").equals("2")){
                areaCode=areaCode.substring(0,4);
            }else if(employee.getString("level").equals("3")){
                areaCode=areaCode.substring(0,6);
            }
            map.put("areaCode", areaCode);
            return this.userService.importData(map,list);
        }else{
            json.put("code","500");
            json.put("msg","导入失败!请检查文件内容");
            return json;
        }
    }


}
