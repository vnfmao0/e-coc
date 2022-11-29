package kr.co.haesungds.utils.file.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StwFile {
    private long fileSeq;
    private String menuId;
    private String parentId;
    private String oriFileNm;
    private String svrFileNm;
    private String filePath;
    private String fileType;
    private long   fileSize;
    private String info1;
    private String info2;
    private String info3;
    private String info4;
    private String info5;
    private String insUserid;
    private LocalDateTime insDttm;
    private String updUserid;
    private LocalDateTime updDttm;
}
