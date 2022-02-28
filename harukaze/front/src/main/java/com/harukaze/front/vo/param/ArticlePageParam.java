package com.harukaze.front.vo.param;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @PackageName: com.harukaze.front.vo.param
 * @ClassName: PageParam
 * @Description:
 * @Author: doki
 * @Date: 27/11/2021 1:56 PM
 */
@Data
public class ArticlePageParam {
    private Integer pageNum;
    private Integer pageSize;
    private String query;
    private String year;
    private String month;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long categoryId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long tagId;

    public String getMonth() {
        if (!StrUtil.isBlank(month) && month.length() == 1) {
            return "0" + month;
        }
        return month;
    }

    public String getQuery() {
        if (!StrUtil.isBlank(query)) {
            return "%" + query + "%";
        }
        return query;
    }

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
