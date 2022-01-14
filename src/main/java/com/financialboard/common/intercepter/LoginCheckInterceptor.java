package com.financialboard.common.intercepter;

import com.financialboard.annotation.LoginCheck;
import com.financialboard.exception.user.NotAuthorizedException;
import com.financialboard.exception.user.UnauthenticatedUserException;
import com.financialboard.model.user.UserLevel;
import com.financialboard.service.SessionLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final SessionLoginService sessionLoginService;

    @Inject
    private Environment environment;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String [] activeProfiles = environment.getActiveProfiles();
        if("test".equals(activeProfiles[0])){
            return true;
        }
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

            if(loginCheck == null){
                return true;
            }

            if(sessionLoginService.getLoginUser() == null){
                throw new UnauthenticatedUserException("로그인 후 이용가능합니다.");
            }

            UserLevel userLevel = loginCheck.authority();

            switch (userLevel){
                case USER:
                    userLevel();
                    break;

                case ADMIN:
                    adminLevel();
                    break;
            }
        }

        return true;
    }

    private void adminLevel() {
        UserLevel userLevel = sessionLoginService.getUserLevel();
        if (userLevel == UserLevel.NON_USER){
            throw new NotAuthorizedException("인증 후 이용하실수 있습니다.");
        }
    }

    private void userLevel() {
        UserLevel userLevel = sessionLoginService.getUserLevel();
        if (userLevel != UserLevel.ADMIN){
            throw new NotAuthorizedException("관리자만 접근할 수 있는 리소스 입니다.");
        }
    }
}
