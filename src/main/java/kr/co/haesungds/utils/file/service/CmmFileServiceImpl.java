package kr.co.haesungds.utils.file.service;

import kr.co.haesungds.utils.JsonData;
import kr.co.haesungds.utils.file.model.EccFile;
import kr.co.haesungds.utils.file.model.HsqFile;
import kr.co.haesungds.utils.file.model.StwFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@Service(value = "cmmFileService")
@RequiredArgsConstructor
public class CmmFileServiceImpl implements CmmFileService{
    @Override
    public JsonData singleFileUpload(MultipartFile file, HttpServletRequest request) {
        JsonData jsonData = new JsonData();

        String file_path = "D:";

        Path copyOfLocation = Paths.get(file_path + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

        try {
            Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);

            jsonData.setStatus("SUCC");
            jsonData.addFields("RESULT_CD", "S");

        } catch (IOException e) {
            e.printStackTrace();
            jsonData.setStatus("SUCC");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg(e.getMessage());
        }
        return jsonData;
    }

    @Override
    public JsonData multiFileUpload(MultipartFile[] files, HttpServletRequest request) {
        JsonData jsonData = new JsonData();
        String file_path = "D:";

        if(files != null) {
            for(MultipartFile file : files) {
                Path copyOfLocation = Paths.get(file_path + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));

                try {
                    Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);

                    jsonData.setStatus("SUCC");
                    jsonData.addFields("RESULT_CD", "S");

                } catch (IOException e) {
                    e.printStackTrace();
                    jsonData.setStatus("FAIL");
                    jsonData.addFields("RESULT_CD", "F");
                    jsonData.setErrMsg(e.getMessage());
                }
            }
        } else {
            jsonData.setStatus("FAIL");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("업로드할 파일이 업습니다.");
        }
        return jsonData;
    }

    @Override
    public EccFile selectFileInfoByFId(String fileSeq) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EccFile> selectFileListByParentId(Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public JsonData multiFileuploadAndInsert(Map<String, Object> paramMap, HttpServletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int deleteFileById(Map<String, Object> paramMap) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int deleteFileByParentId(String parentId) {
        return 0;
    }
}
