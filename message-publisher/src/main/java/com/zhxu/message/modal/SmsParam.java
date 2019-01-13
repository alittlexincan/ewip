package com.zhxu.message.modal;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SmsParam {
    private String url;
    private String sign;
    private String masUserId;
    private String accessToken;
    private String content;
    private List<String> mobiles;
}
