package com.harukaze.front.vo.param;

import com.harukaze.front.vo.TmpUserVo;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @PackageName: com.harukaze.front.vo.param
 * @ClassName: CommentParam
 * @Description:
 * @Author: doki
 * @Date: 5/12/2021 4:34 PM
 */
@Data
public class CommentParam {
    private Long id;
    private TmpUserVo userVo;
    @NotBlank(message = "评论不能为空")
    private String content;
    private Long parentId;
    private Long articleId;
    private String toUserName;

    public Long getParentId() {
        if (parentId == null) {
            return 0L;
        }
        return parentId;
    }

    public Long getArticleId() {
        if (articleId == null) {
            return 0L;
        }
        return articleId;
    }
}
