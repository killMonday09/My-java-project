package com.szxs.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "alipay")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlipayConfig {

    private String app_id = "";

    private String merchant_private_key = "";

    private String alipay_public_key = "";

    private String notify_url = "";

    private String return_url = "";

    private String format = "json";

    private String sign_type = "RSA2";

    private String charset = "utf-8";

    private String gatewayUrl = "https://openapi.com/gateway.do";
}
