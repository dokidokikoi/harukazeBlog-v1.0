package com.harukaze.front.service;

import com.harukaze.front.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harukaze.front.vo.Result;
import com.harukaze.front.vo.param.PageParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author doki
 * @since 2021-11-28
 */
public interface SysLogService extends IService<SysLog> {

    Result findAllLog(PageParam pageParam);

    Result deleteById(Long id);
}
