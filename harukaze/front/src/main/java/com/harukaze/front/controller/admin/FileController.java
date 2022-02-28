package com.harukaze.front.controller.admin;

import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.util.QiniuUtil;
import com.harukaze.front.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @PackageName: com.harukaze.front.controller
 * @ClassName: FileController
 * @Description:
 * @Author: doki
 * @Date: 3/12/2021 2:33 PM
 */
@Api(tags = "上传文件接口")
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class FileController {
    @Autowired
    private QiniuUtil qiniuUtil;

    @LogAnnotation(module = "上传文件接口", operator = "上传图片")
    @ApiOperation(value = "上传图片", notes = "上传图片")
    @PostMapping("/upload")
    public Result upload(@RequestParam("image") MultipartFile file){
        String name = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "." + name.split("\\.")[1];
        boolean flag = qiniuUtil.upload(file, fileName);
        if (flag) {
            return Result.succ(QiniuUtil.url + fileName, 0);
        }
        return Result.fail("上传失败");
    }
}
