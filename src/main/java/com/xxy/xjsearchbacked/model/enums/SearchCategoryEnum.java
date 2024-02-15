package com.xxy.xjsearchbacked.model.enums;

import cn.hutool.core.util.StrUtil;
import com.xxy.xjsearchbacked.common.ErrorCode;
import com.xxy.xjsearchbacked.exception.BusinessException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 搜索类别枚举
 *
 * @author xiaxinyu
 * @date 2024/02/15
 */
public enum SearchCategoryEnum {
    PICTURE("图片","picture"),
    POST("文章","post"),
    USER("用户","user");
    private String text;
    private String value;


    SearchCategoryEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public List<String> getValues() {
        List<String> valuesList = Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
        return valuesList;
    }

    /**
     * 按值获取枚举
     *
     * @param value 价值
     * @return {@link SearchCategoryEnum}
     */
    public SearchCategoryEnum getEnumByValue(String value) {
        if (StrUtil.isBlank(value)) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"value 不能为空");
        }
        for (SearchCategoryEnum Enum : SearchCategoryEnum.values()) {
            if (Enum.value.equals(value)) {
                return Enum;
            }
        }
        return null;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
