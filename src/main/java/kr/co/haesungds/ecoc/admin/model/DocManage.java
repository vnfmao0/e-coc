package kr.co.haesungds.ecoc.admin.model;

import lombok.Data;

@Data
public class DocManage {
    private String docId;
    private String legCd;
    private String docTitle;
    private String docContent;
    private String parentDocId;
    private String insDttm;
    private String insUserid;
    private String updDttm;
    private String updUserid;
}
