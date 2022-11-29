package kr.co.haesungds.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(AccessDeniedException.class)
    public ModelAndView AccessDeniedExceptionHandler(HttpServletRequest request, Exception exception) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> AccessDeniedExceptionHandler >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(exception);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ModelAndView mv = new ModelAndView("thymeleaf/error/access_denied");
        mv.addObject("exception", exception);

        log.error("exception", exception);

        return mv;
    }

    //Exception.class로 처리하는 defaultExceptionHandler 가장 아래에 위치해야함.
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception exception) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> ExceptionHandler >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(exception);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ModelAndView mv = new ModelAndView("thymeleaf/error/error_default");
        mv.addObject("exception", exception);

        log.error("exception", exception);

        return mv;
    }
}
