package kr.co.haesungds.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private String userid;
    private String passwd;
    private String userNm;
    private String deptCd;
    private String deptNm;
    private String posCd;
    private String posNm;
    private String jikgunCd;
    private String workTp;
    private String prodSite;
    private String mobNo;
    private String telNo;
    private String userMail;
    private String useYn;
    private String pwCrtDttm;
    private String faultXnt;
    private String lastDttm;
    private String lastIp;
    private String pwinitYn;
    private String randomPwd;
    private String attGrpId;
    private String lastDeptCd;
    private String epLoginid;
    private String epid;
    private String epIntfDttm;
    private String passwdChgDt;
    private String nmAbil;
    private String retire;
    private String cdDept;
    private String deptname;
    private String cdUpperDept;
    private String nmUpperDept;
    private String deptname2;
    private String insDttm;
    private String insUserid;
    private String updDttm;
    private String updUserid;

    private Collection<GrantedAuthority> authorities;
    private String roles;
    private String privileges;

    public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.passwd;
    }

    @Override
    public String getUsername() {
        return this.userNm;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
