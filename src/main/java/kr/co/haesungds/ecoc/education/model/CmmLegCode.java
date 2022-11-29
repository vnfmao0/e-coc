package kr.co.haesungds.ecoc.education.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CmmLegCode {
    private String legCd;
    private String grpCd;
    private String subGrpCd;
    private String code;
    private String codeNm;
    private String codeDesc;
    private String sortOrd;
    private String info1;
    private String info2;
    private String info3;
    private String info4;
    private String info5;
    private String info6;
    private String info7;
    private String info8;
    private String info9;
    private String info10;
    private String remk;
    private String useYn;
    private LocalDateTime insDttm;
    private String insUserid;
    private LocalDateTime updDttm;
    private String updUserid;

    private String oriGrpCd;
    private String oriCode;

}
