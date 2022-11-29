package kr.co.haesungds.ecoc.education.model;

import lombok.Data;

@Data
public class ECocInfo {
    private String eduId;
    private String eduGrpCd1;
    private String eduGrpCd2;
    private String eduType;
    private String eduTitle;
    private String eduContent;
    private String qrUse;
    private String qrUrl;
    private String insDttm;
    private String insUserid;
    private String updDttm;
    private String updUserid;

    private String eduGrpCd1Nm;
    private String eduGrpCd2Nm;
    private String eduTypeNm;
    private String insUserNm;
    private String updUserNm;

    private String fileExt;
    private String fileRootPath;
}
