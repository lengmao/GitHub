package com.frame.rest;

import com.frame.entity.User;
import com.frame.file.ExportExcel;
import com.frame.mapper.UserMapper;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * @author scaf_xs
 * @ClassName: ExcleController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2018/1/17 11:15
 */
@RestController
@RequestMapping(value = "excle")
public class ExcleController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(value = "excleExport",method = RequestMethod.GET)
    public String excleExport(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("信息表");

        List<User> userList = userMapper.getAll();

        //设置要导出的文件的名字
        String fileName = "userinf"  + ".xls";

        //新增数据行，并且设置单元格数据
        int rowNum = 1;

        String[] headers = { "ID", "名称", "密码", "性别","昵称"};

        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);

        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (User user : userList) {
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(user.getId());
            row1.createCell(1).setCellValue(user.getUserName());
            row1.createCell(2).setCellValue(user.getPassWord());
            row1.createCell(3).setCellValue(user.getUserSex());
            row1.createCell(4).setCellValue(user.getNickName());
            rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());

        return "success";
    }

    @RequestMapping(value = "excleExport01",method = RequestMethod.GET)
    public void excleExport01() throws FileNotFoundException {
        String[] headers = { "ID", "名称", "密码", "性别","昵称"};
        Collection<User> dataset=userMapper.getAll();
        OutputStream out = new FileOutputStream("E://a.xls");
       ExportExcel<User>  X=new ExportExcel();
        X.exportExcel(headers,dataset,out);
    }
}
