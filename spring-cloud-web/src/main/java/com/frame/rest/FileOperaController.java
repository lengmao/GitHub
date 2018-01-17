package com.frame.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author scaf_xs
 * @ClassName: FileOperaController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2018/1/16 10:40
 */
@Controller
@RequestMapping("file")
public class FileOperaController {


    //文件上传
    @RequestMapping(value = "uploadFile",method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        System.out.println("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        System.out.println("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "E:/test/";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    //文件下载
    @RequestMapping(value = "download",method = RequestMethod.GET)
    public String download(HttpServletRequest request, HttpServletResponse response){
        String fileName="mybatis-config.xml";
        if(fileName!=null){
            String realPath="E:\\Idea\\spring-cloud-web\\src\\main\\resources\\config";
            File file=new File(realPath,fileName);
            if(file.exists()){
                // 设置强制下载不打开
                response.setContentType("application/force-download");

                // 设置文件名
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" +  fileName);
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;

                try {
                    fis=new FileInputStream(file);
                    bis=new BufferedInputStream(fis);
                    OutputStream os=response.getOutputStream();
                    int i=bis.read(buffer);
                    while (i!=-1){
                        os.write(buffer,0,i);
                        i=bis.read(buffer);
                    }
                    System.out.println("success");
                }catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
        return null;
    }

    //多文件上传
    @RequestMapping(value = "uploadFile01",method = RequestMethod.POST)
    public String uploadFile01(@RequestParam("file") MultipartFile[] files){
        String fileName = null;
        String msg = "";
        if (files != null && files.length >0) {
            for(int i =0 ;i< files.length; i++){
                try {
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File("E:/test/" + fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    msg += "You have successfully uploaded " + fileName +"<br/>";
                } catch (Exception e) {
                    return "You failed to upload " + fileName + ": " + e.getMessage() +"<br/>";
                }
            }
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }

}

