package kr.co.haesungds.login.controller;

import kr.co.haesungds.login.dto.LoginDto;
import kr.co.haesungds.login.dto.MemberDto;
import kr.co.haesungds.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
//@Controller
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute LoginDto loginDto) {
        log.debug("login() get");
        return "thymeleaf/login/loginForm";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Validated LoginDto loginDto,
                        BindingResult bindingResult,
                        @RequestParam(defaultValue = "/home") String redirectURL,
                        HttpServletRequest request) {
        log.debug("login() post");
        if (bindingResult.hasErrors()) {
            return "thymeleaf/login/loginForm";
        }

        MemberDto loginMember = loginService.login(loginDto.getLoginId(), loginDto.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "thymeleaf/login/loginForm";
        }

        // 로그인 성공 처리
        HttpSession session = request.getSession();                         // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성하여 반환
        session.setAttribute(SessionConstants.LOGIN_MEMBER, loginMember);   // 세션에 로그인 회원 정보 보관

        return "redirect:" + redirectURL;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();   // 세션 날림
        }

        return "redirect:/";
    }
}
