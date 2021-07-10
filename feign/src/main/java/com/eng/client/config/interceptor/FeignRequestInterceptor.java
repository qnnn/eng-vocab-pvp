package com.eng.client.config.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author 郭富城
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                requestTemplate.header(name, value);
            }
        }

        Authentication userPrincipal = (Authentication)request.getUserPrincipal();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails)userPrincipal.getDetails();
        String accessToken = details.getTokenValue();

        // 将token放入请求头
        if (accessToken!=null){
            requestTemplate.header("authorization","Bearer "+ accessToken);
        }





        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder body = new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);

                // 将令牌放入 Header
                if ("access_token".equals(name)) {
                    requestTemplate.header("authorization", "Bearer " + value);
                }

                // 其他参数封装成请求体
                else {
                    body.append(name).append("=").append(value).append("&");
                }

            }
        }



        if (body.length() > 0) {
            // 去掉最后一位 "&"
            body.deleteCharAt(body.length() - 1);
            requestTemplate.body(body.toString());
        }



    }


}
