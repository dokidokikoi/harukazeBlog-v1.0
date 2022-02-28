package com.harukaze.front.vo.param;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * @PackageName: com.harukaze.front.vo.param
 * @ClassName: UserPageParam
 * @Description:
 * @Author: doki
 * @Date: 28/11/2021 12:38 PM
 */
@Data
public class UserPageParam {
    private Integer pageNum;
    private Integer pageSize;
    private String query;

    public Integer getPageNum() {
        if (pageNum == null) {
            return 0;
        }
        return pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return 5;
        }
        return pageSize;
    }

    public String getQuery() {
        if (!StrUtil.isBlank(query)) {
            return "%" + query + "%";
        }
        return query;
    }
}
