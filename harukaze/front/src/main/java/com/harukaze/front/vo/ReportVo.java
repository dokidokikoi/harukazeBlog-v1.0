package com.harukaze.front.vo;

import lombok.Data;

/**
 * @PackageName: com.harukaze.front.vo
 * @ClassName: TagReportVo
 * @Description:
 * @Author: doki
 * @Date: 4/12/2021 9:55 AM
 */
@Data
public class ReportVo {
    private String name;
    private Integer articleCounts;
    private Integer viewCounts;
    private Integer commentCounts;
}
