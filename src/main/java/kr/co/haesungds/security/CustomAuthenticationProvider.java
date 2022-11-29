package kr.co.haesungds.security;

import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.security.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsServiceImpl userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("############### authenticate() start #################");
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserDto userDto = null;
        /*
        원래는 로그인 프로세스를 별도로 만들어야하는데... 일단은 slo 로그인인 경우 userid 앞에 'slo_'를 붙혀서 넘어오도록 함.
        이걸로 slo 로그인 여부 체크해서 맞으면 암호체크 안하고, 틀리면 일반 로그인이므로 암호체크 한다.
         */
        boolean isSloLogin = username.startsWith("slo_") ? true : false;
        if (isSloLogin) {
            username = username.replace("slo_", "");
            userDto = userService.loadUserByUsername(username);
        } else {
            userDto = userService.loadUserByUsername(username);
            if (!password.equals(userDto.getPassword())) {
                throw new BadCredentialsException("BadCredentialsException");
            }
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDto, null, userDto.getAuthorities());

        log.debug(authenticationToken.getName());
        authenticationToken.getAuthorities().stream().forEach(o -> System.out.println(o.getAuthority()));
        log.debug("############### authenticate() end #################");
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
