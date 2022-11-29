package kr.co.haesungds.ecoc.home.controller;

import kr.co.haesungds.ecoc.home.service.HomeService;
import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.utils.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/home")
    public String home(HttpServletRequest request, @AuthenticationPrincipal UserDto userDto) {
        request.getSession().setAttribute("USER_INFO", userDto);
        String returnPage = "";
        //ADMIN, P1612_MANAGER, P1613_MANAGER, P1614_MANAGER, P1611_MANAGER
        //log.debug("ADMIN : " + request.isUserInRole("ADMIN"));

        // 시큐리티 컨텍스트 객체를 얻습니다.
        SecurityContext context = SecurityContextHolder.getContext(); // 인증 객체를 얻습니다.
        Authentication authentication = context.getAuthentication(); // 로그인한 사용자정보를 가진 객체를 얻습니다.

        //Principal principal = (Principal) authentication.getPrincipal(); // 사용자가 가진 모든 롤 정보를 얻습니다.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();

        while (iter.hasNext()) {
            GrantedAuthority auth = iter.next();

            if ("ADMIN".equals(auth.getAuthority())) {
                //returnPage = "redirect:/admin/roleManage";
                returnPage = "redirect:/ecoc/educationList";
                break;
            } else  {
                returnPage = "redirect:/ecoc/educationList";
                break;
            }

        }

        //return "thymeleaf/index";
        return returnPage;
    }

    @PostMapping("/home/getWorkCount")
    @ResponseBody
    public JsonData getWorkCount(@RequestBody Map<String, Object> paramMap, @AuthenticationPrincipal UserDto userDto) {

        JsonData jsonData = null;

        // 시큐리티 컨텍스트 객체를 얻습니다.
        SecurityContext context = SecurityContextHolder.getContext(); // 인증 객체를 얻습니다.
        Authentication authentication = context.getAuthentication(); // 로그인한 사용자정보를 가진 객체를 얻습니다.

        //Principal principal = (Principal) authentication.getPrincipal(); // 사용자가 가진 모든 롤 정보를 얻습니다.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();

        paramMap.put("insUserid", userDto.getUserid());
        while (iter.hasNext()) {
            GrantedAuthority auth = iter.next();

            if ("ADMIN".equals(auth.getAuthority())) {
                jsonData = new JsonData();
                jsonData.setStatus("SUCC");
                jsonData.addFields("role", auth.getAuthority());
                break;
            } else if ("P1611_MANAGER".equals(auth.getAuthority())) {
                jsonData = new JsonData();
                jsonData.setStatus("SUCC");
                jsonData.addFields("role", auth.getAuthority());
                break;
            } else if ("P1612_MANAGER".equals(auth.getAuthority())) {
                jsonData = homeService.getP1612WorkCount(paramMap);
                jsonData.addFields("role", auth.getAuthority());
                break;
            } else if ("P1613_MANAGER".equals(auth.getAuthority())) {
                jsonData = homeService.getP1613WorkCount(paramMap);
                jsonData.addFields("role", auth.getAuthority());
                break;
            } else if ("P1614_MANAGER" .equals(auth.getAuthority())) {
                jsonData = homeService.getP1614WorkCount(paramMap);
                jsonData.addFields("role", auth.getAuthority());
                break;
            } else {
                jsonData = new JsonData();
                jsonData.setStatus("SUCC");
                jsonData.addFields("role", auth.getAuthority());
            }
        }

        return jsonData;
    }


}
