package com.zhxu.message.model.sinaweibo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SinaWeiBoParam {

    private String safeUrl;
    private String sinaSendUrl;
    private String access_token;
    private String content;

}
