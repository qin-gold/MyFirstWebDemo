package com.wlf.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询条件
 *
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/10/9 2:53
 */
@Data
@AllArgsConstructor
public class QueryConditions {
    private int page;
    private int count;
    private String str;

    public QueryConditions() {
        this.count = 10;
        this.str = "";
    }

}
