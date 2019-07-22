package com.lm.pag.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author scaf_xs
 * @ClassName: FileRestController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2019/5/14 16:58
 */
@RestController
public class FileRestController {

    private static final Logger log = LoggerFactory.getLogger(FileRestController.class);

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file) {
        if (file.isEmpty()) {
            return "请选择需要上传的文件！";
        }

        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String path = "F:\\";
        path = path + fileName;
        File dest = new File(path);

        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功！";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/download")
    public String download(){






        return null;
    }
}
