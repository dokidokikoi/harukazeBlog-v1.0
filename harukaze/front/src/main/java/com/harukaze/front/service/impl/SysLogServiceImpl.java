package com.harukaze.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harukaze.front.entity.SysLog;
import com.harukaze.front.mapper.SysLogMapper;
import com.harukaze.front.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author doki
 * @since 2021-11-28
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    @Autowired
    SysLogMapper logMapper;

    @Override
    public Result findAllLog(PageParam pageParam) {
        Page<SysLog> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize());
        IPage<SysLog> sysLogIPage = logMapper.selectPage(page, new LambdaQueryWrapper<SysLog>().orderByDesc(SysLog::getCreateDate));
        return Result.succ(sysLogIPage.getRecords(), (int) sysLogIPage.getTotal());
    }

    @Override
    public Result deleteById(Long id) {
        int i = logMapper.deleteById(id);
        return Result.succ(null, 0);
    }
}
