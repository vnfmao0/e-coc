package kr.co.haesungds.utils.file.service;

import kr.co.haesungds.utils.JsonData;
import kr.co.haesungds.utils.file.model.EccFile;
import kr.co.haesungds.utils.file.model.HsqFile;
import kr.co.haesungds.utils.file.model.StwFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CmmFileService {
    JsonData singleFileUpload(MultipartFile file, HttpServletRequest request);

    JsonData multiFileUpload(MultipartFile[] files, HttpServletRequest request);

    EccFile selectFileInfoByFId(String fileSeq);
    List<EccFile> selectFileListByParentId(Map<String, Object> paramMap);

    JsonData multiFileuploadAndInsert(Map<String, Object> paramMap, HttpServletRequest request);

    int deleteFileById(Map<String, Object> paramMap);

    int deleteFileByParentId(String parentId);
}
