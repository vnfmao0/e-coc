package kr.co.haesungds.login.service;

import kr.co.haesungds.login.dto.MemberDto;
import kr.co.haesungds.login.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
//@Service
public class LoginServiceImpl implements LoginService {
    private final MemberMapper memberMapper;

    @Override
    public MemberDto login(String loginId, String password) {
        return memberMapper.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
