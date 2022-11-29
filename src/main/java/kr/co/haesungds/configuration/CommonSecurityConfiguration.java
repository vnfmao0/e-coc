package kr.co.haesungds.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

//@Configuration
//@RequiredArgsConstructor // final이나 @notnull인 필드값만 파라미터로 받는 생성자 생성
public class CommonSecurityConfiguration {
    // spring security에서 허용할 web 리소스 path
    public static final String[] SECURITY_EXCLUDE_PATTERN_ARR = {"/" // resource
            , "/error/**", "/favicon.ico", "/resources/**" // api
            , "/api/**" // */ // User 관련
            , "/user/login*" // Admin 관련
            , "/admin/login*"};

    // 각종 Bean 추가
    /**
     * 접근 권한 검사
     * - https://docs.spring.io/spring-security/site/docs/4.1.5.RELEASE/reference/html/core-web-filters.html#filter-security-interceptor
     * - https://blog.naver.com/myh814/221934064615
     *
     * @return * @throws Exception
     */
    @Bean(name = "filterSecurityInterceptor")
    public FilterSecurityInterceptor getFilterSecurityInterceptor() throws Exception {
        FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
        interceptor.setAccessDecisionManager(getAffirmativeBased());
        // AccessDecisionManager에 권한검사 위임
        //interceptor.setSecurityMetadataSource(getReloadableFilterInvocationSecurityMetadataSource());
        return interceptor;
    }

    /**
     * 권한 설정용 * - spring security 권한 필터 개념 내용 참고
     * - https://docs.spring.io/spring-security/site/docs/3.0.x/reference/technical-overview.html#tech-intro-access-control
     * - https://docs.spring.io/spring-security/site/docs/4.1.5.RELEASE/reference/html/ns-config.html#ns-access-manager
     *
     * @return
     */
    @Bean(name = "accessDecisionManager")
    public AffirmativeBased getAffirmativeBased() {
        List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<AccessDecisionVoter<?>>();
        RoleVoter roleVoter = new RoleVoter();
        roleVoter.setRolePrefix("");
        decisionVoters.add(roleVoter);
        AffirmativeBased affirm = new AffirmativeBased(decisionVoters);
        affirm.setAllowIfAllAbstainDecisions(false);
        return affirm;
    }

    /**
     * 사용자가 수동으로 권한 갱신하기 위해 securedObjectService 처리
     *
     * @return
     */
//    @Bean
//    public ReloadableFilterInvocationSecurityMetadataSource getReloadableFilterInvocationSecurityMetadataSource() {
//        List<RequestMatcher> matchers = new ArrayList<>();
//        for (String pattern : SECURITY_EXCLUDE_PATTERN_ARR) {
//            matchers.add(new AntPathRequestMatcher(pattern));
//        }
//        return new ReloadableFilterInvocationSecurityMetadataSource(matchers);
//    }

    /**
     * (여러개의 슬래시(//)가 포함되어있는) 경로의 패턴 불일치 방지
     * - spring security Request Matching 내용 참고
     * - https://docs.spring.io/spring-security/site/docs/5.0.0.RELEASE/reference/htmlsingle/#request-matching
     * @return
     */
    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }

    /**
     * 패스워드 암호화 * * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
