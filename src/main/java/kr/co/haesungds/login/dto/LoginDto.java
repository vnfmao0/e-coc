package kr.co.haesungds.login.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;
}