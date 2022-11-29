package kr.co.haesungds.utils.file.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HsqFile {
    private long fileSeq;
    private String bbsSeq;
    private String oriFileNm;
    private String svrFileNm;
    private String filePath;
    private String fileType;
    private long fileSize;
    private String fileCmmt;
    private LocalDateTime insDttm;
    private String insUserid;
    private LocalDateTime updDttm;
    private String updUserid;
}
