package com.zxl.controller;

import com.zxl.service.IBookFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: ZXL
 * @Date: 2018/9/10
 * @Description:
 */
@RestController
@RequestMapping("/bookFileController")
@Slf4j
public class BookFileController {

    @Autowired
    private IBookFileService bookFileService;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Map<String, String> uploadBook(MultipartFile file) {
        Map<String, String> map = new HashMap<>();
        map.put("result", "成功");
        if (file.isEmpty()) {
            map.put("result", "请选择文件");
        }
        try {
            bookFileService.saveBookFileByFile(file);
        } catch (IOException e) {
            log.error("上传文件出错{}", e.getMessage());
            map.put("result", "失败");
        }
        return map;
    }
}
