package com.harukaze.front.controller.admin;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.entity.Link;
import com.harukaze.front.service.LinkService;
import com.harukaze.front.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-12-15
 */
@Api(tags = "友链后台数据接口")
@RestController
@RequestMapping("/admin/link")
public class AdminLinkController {
    @Autowired
    LinkService linkService;

    @LogAnnotation(module = "友链后台", operator = "添加友链")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "添加友链", notes = "添加友链")
    @PostMapping
    public Result saveLink(@RequestBody Link linkParam) {
        return linkService.saveLink(linkParam);
    }

    @LogAnnotation(module = "友链后台", operator = "修改友链")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "修改友链", notes = "修改友链")
    @PutMapping
    public Result modifyLink(@RequestBody Link linkParam) {
        return linkService.modifyLink(linkParam);
    }

    @LogAnnotation(module = "友链后台", operator = "删除友链")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "删除友链", notes = "删除友链")
    @DeleteMapping("/{id}")
    public Result deleteLink(@PathVariable Long id) {
        return linkService.removeLink(id);
    }
}
