package kr.co.haesungds.security.controller;

import kr.co.haesungds.security.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class SecurityController {
    @GetMapping("/security/login")
    public String secLogin(@ModelAttribute UserDto user) {

        return "thymeleaf/login/secLoginForm";
    }

    @GetMapping("/security/logoutProc")
    public String logoutProc(@ModelAttribute UserDto user) {

        return "thymeleaf/login/logout";
    }

    @RequestMapping("/security/error")
    public String securityError(HttpServletRequest request
            , @RequestParam(value = "error", required = false) String error
            , @RequestParam(value = "exception", required = false) String exception
            , Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "thymeleaf/error/error";
    }

    @RequestMapping("/security/denied")
    public String denied(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return "thymeleaf/error/access_denied";
    }


    @GetMapping("/sloLogin")
    public String sloLogin(@RequestParam(value = "EP_SABUN", required = false) String EP_SABUN
            , @RequestParam(value = "EP_MAIL", required = false) String EP_MAIL
            , @RequestParam(value = "EP_USERID", required = false) String EP_USERID
            , @RequestParam(value = "error", required = false) String error
            , @RequestParam(value = "exception", required = false) String exception
            , Model model) {

        model.addAttribute("EP_SABUN", EP_SABUN);
        model.addAttribute("EP_MAIL", EP_MAIL);
        model.addAttribute("EP_USERID", EP_USERID);
        model.addAttribute("userDto", new UserDto());
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "thymeleaf/login/sloLoginForm";
    }
}
