package com.zhxu.model.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrganizationType {
    PUBLISH_CENTER("发布中心"),
    EMERGENCY_PLAN("预案单位"),
    EMERGENCY_MANAGEMENT("应急办");

    private String name;
}
