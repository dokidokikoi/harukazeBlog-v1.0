package com.harukaze.front.controller.admin;


import com.harukaze.front.aop.LogAnnotation;
import com.harukaze.front.service.SysLogService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.ArticlePageParam;
import com.harukaze.front.vo.param.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author doki
 * @since 2021-11-28
 */
@Api(tags = "日志后台数据接口")
@RestController
@RequestMapping("/admin/logs")
public class AdminSysLogController {
    @Autowired
    SysLogService logService;

    @LogAnnotation(module = "日志后台", operator = "分页查询日志")
    @ApiOperation(value = "分页查询日志", notes = "分页查询日志")
    @ApiParam(name = "pageParam")
    @GetMapping
    public Result getArticle(PageParam pageParam) {
        return logService.findAllLog(pageParam);
    }

    @LogAnnotation(module = "日志后台", operator = "删除日志")
    @PreAuthorize("hasRole('ROLE_admin')")
    @ApiOperation(value = "删除日志", notes = "删除日志")
    @ApiParam(name = "id")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return logService.deleteById(id);
    }
}
