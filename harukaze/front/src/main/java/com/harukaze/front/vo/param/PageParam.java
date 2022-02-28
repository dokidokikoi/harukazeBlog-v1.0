package com.harukaze.front.vo.param;

import lombok.Data;

/**
 * @PackageName: com.harukaze.front.vo.param
 * @ClassName: PageParam
 * @Description:
 * @Author: doki
 * @Date: 27/11/2021 3:15 PM
 */
@Data
public class PageParam {
    private Integer pageNum;
    private Integer pageSize;

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
}
