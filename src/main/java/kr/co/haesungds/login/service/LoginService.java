package kr.co.haesungds.login.service;

import kr.co.haesungds.login.dto.MemberDto;

public interface LoginService {
    public MemberDto login(String loginId, String password);
}
