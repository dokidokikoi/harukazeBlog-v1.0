package com.harukaze.front.controller;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.GoodWordsService;
import com.harukaze.front.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器W
 * </p>
 *
 * @author doki
 * @since 2021-12-18
 */
@Api(tags = "名言数据接口")
@RestController
@RequestMapping("/goodWords")
public class GoodWordsController {
    @Autowired
    GoodWordsService goodWordsService;

    @LogAnnotation(module = "名言", operator = "获取名言")
    @ApiOperation(value = "获取名言", notes = "获取名言")
    @GetMapping
    public Result getGoodWord() {
        return goodWordsService.findOne();
    }
}
