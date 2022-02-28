package com.harukaze.front.vo.param;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TagParam {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String avatar;

    @NotBlank(message = "tagName不能为空")
    private String tagName;
}
