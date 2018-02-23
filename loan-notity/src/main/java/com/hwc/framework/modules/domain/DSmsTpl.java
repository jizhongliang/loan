package com.hwc.framework.modules.domain;

import lombok.Data;

@Data
public class DSmsTpl {

    /**
     * ID
     */
    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * tpl模板
     */
    private String tpl;

    /**
     * 模板编号
     */
    private String number;

    /**
     * 短信模板状态 10 -启用 20 - 禁用'
     */
    private String state;

}
