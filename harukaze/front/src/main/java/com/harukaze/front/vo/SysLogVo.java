package com.harukaze.front.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @PackageName: com.harukaze.front.vo
 * @ClassName: SysLogVo
 * @Description:
 * @Author: doki
 * @Date: 28/11/2021 4:45 PM
 */
@Data
public class SysLogVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Long createDate;

    private String ip;

    private String method;

    private String module;

    private String address;

    private String operation;

    private String params;

    private Long time;

    private String browser;

    private String username;
}
