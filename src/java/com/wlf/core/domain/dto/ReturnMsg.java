package com.wlf.core.domain.dto;

import com.wlf.core.utlis.PropertiesLoadUtils;
import lombok.*;

/**
 * 返回消息公共模板
 *
 * @author QinShijiao
 * @version 1.0
 * @createDate 2021/4/27 0:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnMsg {
    private String code;
    private String msg;

    public ReturnMsg(String code) {
        this.code = code;
        this.msg = code == null ? "" : PropertiesLoadUtils.loadMsg(code);
    }
}
