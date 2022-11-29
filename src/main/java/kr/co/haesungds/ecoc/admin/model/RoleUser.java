package kr.co.haesungds.ecoc.admin.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoleUser {
    private String legCd;
    private String roleId;
    private String roleNm;
    private String userid;
    private String aplyYn;
    private String readAble;
    private String writeAble;
    private String updateAble;
    private String deleteAble;
    private String downloadAble;
    private String fn1;
    private String fn2;
    private String fn3;
    private String fn4;
    private String fn5;
    private String insUserid;
    private LocalDateTime insDttm;
    private String updUserid;
    private LocalDateTime updDttm;

    private List<RoleUser> roleList;
}
