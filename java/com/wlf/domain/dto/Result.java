package com.wlf.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这是一个数据封转类
 * @author QinShijiao
 * @version 1.0
 * @createTime 2021/4/26 23:56
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;
}
