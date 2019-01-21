package com.zhxu.message.model.weibo.sina;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SinaWeiboParam {

    private String safeUrl;
    private String sinaSendUrl;
    private String access_token;
    private String content;

}
