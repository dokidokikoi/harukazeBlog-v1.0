package com.harukaze.front.controller;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.LinkService;
import com.harukaze.front.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-12-15
 */
@Api(tags = "友链数据接口")
@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    LinkService linkService;

    @LogAnnotation(module = "友链", operator = "获取友链")
    @ApiOperation(value = "获取友链", notes = "获取友链")
    @GetMapping
    public Result getLink() {
        return linkService.findLink();
    }
}
