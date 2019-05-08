package com.hyt.client.controller.info;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.service.info.IGeoInfoService;
import com.hyt.client.utils.info.GeoExcelUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("geoInfo")
public class GeoInfoController {

        @Autowired
        private IGeoInfoService geoInfoService;

        /**
         * 添加信息
         * @param map
         * @return
         */
        @PostMapping("/insert")
        JSONObject insert(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("reportUnitCode", employee.getString("organizationCode"));
                return this.geoInfoService.insert(map);
        }

        /**
         * 修改信息
         * @param map
         * @return
         */
        @PostMapping("/update")
        JSONObject update(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                map.put("reportUnitCode", employee.getString("organizationCode"));
                return this.geoInfoService.update(map);
        }

        /**
         * 根据ID删除信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.geoInfoService.deleteById(id);
        }

        /**
         * 批量删除信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.geoInfoService.deleteBatch(id);
        }

        /**
         * 分页查询信息
         * @param map
         * @return
         */
        @GetMapping("/select")
        public JSONObject selectAll(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                String level=employee.getString("level");
                String organizationCode=employee.getString("organizationCode");
                if(level.equals("1")){
                        organizationCode=organizationCode.substring(0,2);
                }else if(level.equals("2")){
                        organizationCode=organizationCode.substring(0,4);
                }else if(level.equals("3")){
                        organizationCode=organizationCode.substring(0,6);
                }
                map.put("reportUnitCode", organizationCode);
            return this.geoInfoService.selectAll(map);
        }

        /**
         * 查询列表
         * @return
         */
        @GetMapping("/list")
        public JSONObject selectList(@RequestParam Map<String,Object> map){
                Subject subject = SecurityUtils.getSubject();
                JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                String level=employee.getString("level");
                String organizationCode=employee.getString("organizationCode");
                if(level.equals("1")){
                        organizationCode=organizationCode.substring(0,2);
                }else if(level.equals("2")){
                        organizationCode=organizationCode.substring(0,4);
                }else if(level.equals("3")){
                        organizationCode=organizationCode.substring(0,6);
                }
                map.put("reportUnitCode", organizationCode);
                return this.geoInfoService.selectList(map);
        }


        @GetMapping("/downModel")
        public void downModel(HttpServletRequest req, HttpServletResponse resp){
                //第一步创建workbook
                XSSFWorkbook book = new XSSFWorkbook();
                try {
                        req.setCharacterEncoding("UTF-8");
                        resp.setCharacterEncoding("UTF-8");
                        resp.setContentType("应用程序/ x-下载");
                        String fileName ="地灾隐患点监控信息导入模板.xlsx";
                        fileName = URLEncoder.encode(fileName,"UTF-8");
                        resp.addHeader("Content-Disposition","attachment; filename ="+ fileName);
                } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                }
                //第二步创建sheet
                XSSFSheet sheet = book.createSheet("信息");
                //第三步创建行row:添加表头0行
                XSSFRow row = sheet.createRow(0);
                XSSFCellStyle style = book.createCellStyle();
                style.setAlignment(HorizontalAlignment.CENTER);
                XSSFDataFormat format = book.createDataFormat();
                style.setDataFormat(format.getFormat("@"));
                //第四步创建单元格
                String[] keyWord = {"地灾点编号","地灾点名称","灾种类型","位置经度", "位置纬度",
                        "影响村镇","诱发因素","威胁人数","潜在经济损失","资料记录年",
                        "致灾因子：降水","降水阈值",
                        "致灾因子：水位","水位阈值",
                        "致灾因子：土壤","土壤阈值",
                        "防灾责任单位","防灾责任人","防灾责任人电话",
                        "监测人", "监测人电话", "关联监测站ID" };
                for (int i = 0; i < keyWord.length; i++) {
                        XSSFCell cell = row.createCell(i);//第一个单元格
                        cell.setCellValue(keyWord[i]);
                        cell.setCellStyle(style);
                        sheet.setDefaultColumnStyle(i, style);
                        sheet.setColumnWidth(i, 256 * 15);
                }
                String[] dmFactorList = {"自然因素","人为因素","其他因素"};
                String[] rainList = {"包含","不包含"};
                String[] waterList = {"包含","不包含"};
                String[] soilList = {"包含","不包含"};
                XSSFDataValidationHelper helper = new XSSFDataValidationHelper(sheet);
                createDropDownList(sheet,helper,dmFactorList,1,100000,6,6);
                createDropDownList(sheet,helper,rainList,1,100000,10,10);
                createDropDownList(sheet,helper,waterList,1,100000,12,12);
                createDropDownList(sheet,helper,soilList,1,100000,14,14);
                System.out.println("Excel文件生成成功...");
                try {
                        OutputStream out = resp.getOutputStream();
                        book.write(out);
                        out.close();
                }catch (Exception e) {
                        e.printStackTrace();
                }
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
         * 导入Excel信息
         * @param map
         * @return
         */
        @PostMapping("/importExcel")
        public JSONObject importExcel(@RequestParam Map<String,Object> map, @RequestParam("addFile") MultipartFile file){
                List<Map<String, Object>> disList =geoInfoService.selectDisasterList(map);
                Map<String, String> disasterMap = new HashMap<>(disList.size());
                disList.forEach(entry -> {
                        disasterMap.put(entry.get("name").toString(), entry.get("code").toString());
                });
                List<Map<String, Object>> list =GeoExcelUtil.getExcelInfo(disasterMap,file);
                JSONObject json=new JSONObject();
                if(list!=null && list.size()>0){
                        Subject subject = SecurityUtils.getSubject();
                        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                        map.put("organizationCode", employee.getString("organizationCode"));
                        return this.geoInfoService.importData(map,list);
                }else{
                        json.put("code","500");
                        json.put("msg","导入失败!请检查文件内容");
                        return json;
                }
        }


}
