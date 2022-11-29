package kr.co.haesungds.login.dto;

import lombok.Data;

@Data
public class MemberDto {

    private Long id;

    private String loginId;

    private String name;

    private String password;
}
