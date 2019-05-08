package com.hyt.client.controller.info;

import com.alibaba.fastjson.JSONObject;
import com.hyt.client.controller.common.BaseController;
import com.hyt.client.service.info.IPmsInfoService;
import com.hyt.client.service.info.IRsvInfoService;
import com.hyt.client.utils.info.PmsExcelUtil;
import com.hyt.client.utils.info.RsvExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("rsvInfo")
public class RsvInfoController extends BaseController {

        /**
         * 获取上传的文件夹，具体路径参考application.properties中的配置
         */
        @Value("${web.upload-path}")
        private String uploadPath;

        @Autowired
        private IRsvInfoService rsvInfoService;
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
                return this.rsvInfoService.insert(map);
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
                return this.rsvInfoService.update(map);
        }
        /**
         * 根据学校ID删除信息
         * @param id
         * @return
         */
        @DeleteMapping("/delete/{id}")
        public JSONObject deleteById(@PathVariable(value = "id") String id){
            return this.rsvInfoService.deleteById(id);
        }
        /**
         * 批量删除信息
         * @param id
         * @return
         */
        @PostMapping("/delete")
        public JSONObject deleteBatch(@RequestParam(value = "id") String id){
            return this.rsvInfoService.deleteBatch(id);
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
            return this.rsvInfoService.selectAll(map);
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
                return this.rsvInfoService.selectList(map);
        }
        @GetMapping("/downModel")
        public void downModel(HttpServletRequest req, HttpServletResponse resp){
                //第一步创建workbook
                XSSFWorkbook book = new XSSFWorkbook();
                try {
                        req.setCharacterEncoding("UTF-8");
                        resp.setCharacterEncoding("UTF-8");
                        resp.setContentType("应用程序/ x-下载");
                        String fileName ="山塘水库导入模板.xlsx";
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
                //第四步创建单元格
                String[] keyWord = {"水库名称","经度", "纬度", "联系人", "电话"};
                for (int i = 0; i < keyWord.length; i++) {
                        XSSFCell cell = row.createCell(i);//第一个单元格
                        cell.setCellValue(keyWord[i]);
                        cell.setCellStyle(style);
                        sheet.setColumnWidth(i, 256 * 15);
                }
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
                List<Map<String, Object>> list =RsvExcelUtil.getExcelInfo(file);
                JSONObject json=new JSONObject();
                if(list!=null && list.size()>0){
                        Subject subject = SecurityUtils.getSubject();
                        JSONObject employee = (JSONObject) subject.getSession().getAttribute("employee");
                        map.put("organizationCode", employee.getString("organizationCode"));
                        return this.rsvInfoService.importData(map,list);
                }else{
                        json.put("code","500");
                        json.put("msg","导入失败!请检查文件内容");
                        return json;
                }
        }


}
