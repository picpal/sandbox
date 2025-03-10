package com.picpal.framework.core.Interceptor;

import com.picpal.framework.core.utils.SecureRandomUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CommonInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(true); // 세션이 없는 경우 신규 생성
        String sessGuid = SecureRandomUtils.getRandomIntWithPrefix("SESS_", 15);

        session.setAttribute("sessGuid", sessGuid);
        MDC.put("sessGuid", sessGuid);

        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        MDC.clear();
    }


}
