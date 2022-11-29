package kr.co.haesungds.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    public CustomLoginSuccessHandler(String defaultUrl) {
        setDefaultTargetUrl(defaultUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.debug("in onAuthenticationSuccess()");
        //접속 ip
        //((UserDto) authentication.getPrincipal()).setLastIp(getClientIp(request));
        //UserDto userDto = (UserDto)authentication.getPrincipal();

        //메뉴조회
//        List<Menu> menuList = null;
//        try {
//            menuList = menuService.selectParentMenu().orElse(null);
//            if (menuList != null) {
//                //menuList.stream().forEach(menu -> menu.setChildMenuList(menuService.selectChildMenu(menu.getMenuId())));
//                for (Menu menu : menuList) {
//                    List<Menu> childMenuList = menuService.selectChildMenu(menu.getMenuId()).orElse(null);
//                    menu.setChildMenuList(childMenuList);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        HttpSession session = request.getSession();
        super.onAuthenticationSuccess(request, response, authentication);
//        if (session != null) {
//            String redirectUrl = (String) session.getAttribute("prevPage");
//
//            //기존 사용하던 url이 있더라도 메인화면으로 강제 이동시킨다.
//            // 이유: 현재 메뉴 정보를 세션에 담아두고 있는데, 재접속시 해당 정보가 다 날라가서...
//            redirectUrl = "/main";
////            session.removeAttribute("MENU_LIST");
////            session.setAttribute("MENU_LIST", menuList);
//            if (redirectUrl != null) {
//                session.removeAttribute("prevPage");
//                getRedirectStrategy().sendRedirect(request, response, redirectUrl);
//            } else {
//                super.onAuthenticationSuccess(request, response, authentication);
//            }
//        } else {
//            super.onAuthenticationSuccess(request, response, authentication);
//        }
    }

    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
