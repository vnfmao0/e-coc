package kr.co.haesungds.utils.file.service;

import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.utils.JsonData;
import kr.co.haesungds.utils.file.mapper.StwFileMapper;
import kr.co.haesungds.utils.file.model.EccFile;
import kr.co.haesungds.utils.file.model.HsqFile;
import kr.co.haesungds.utils.file.model.StwFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.text.DecimalFormat;
import java.util.*;

@Slf4j
@Service(value = "stwFileService")
@RequiredArgsConstructor
public class StwFileServiceImpl implements CmmFileService {
    @Value("${ECC.FILE_PATH}")
    private String FILE_PATH;

    @Value("${ECC.FILE_DOWN_PATH}")
    private String FILE_DOWN_PATH;

    @Value("${ECC.FILE_SEPARATOR}")
    private String FILE_SEPARATOR;

    @Autowired
    private StwFileMapper stwFileMapper;

    @Override
    public JsonData singleFileUpload(MultipartFile file, HttpServletRequest request) {
        JsonData jsonData = new JsonData();
        String datePath = getDatePath(FILE_PATH);

        if(file != null) {
            List<EccFile> fileInfos = new ArrayList<EccFile>();
            UUID fileOrgName = UUID.randomUUID();
            //String fileOrgName = dateFormat.format(new Date()) + String.format("%02d", fileSort);
            //Path copyOfLocation = Paths.get(FILE_PATH + datePath + FILE_SEPARATOR + StringUtils.cleanPath(file.getOriginalFilename()));

            Path copyOfLocation = Paths.get(FILE_PATH + datePath + FILE_SEPARATOR + StringUtils.cleanPath(fileOrgName.toString()) + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
            log.debug("/////////////");
            log.debug(FILE_PATH + datePath + FILE_SEPARATOR + StringUtils.cleanPath(fileOrgName.toString()) + "." + FilenameUtils.getExtension(file.getOriginalFilename()));

            try {
                Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
                String dbFilePath = copyOfLocation.getParent() + FILE_SEPARATOR;

                //copyOfLocation.getParent() 시 window 경로 '\' 로 리턴되어 '/'로 강제 변환함.
                dbFilePath = dbFilePath.replaceAll("\\\\", FILE_SEPARATOR);
                log.debug("dbFilePath1 :: " + dbFilePath);
                dbFilePath = dbFilePath.substring(dbFilePath.indexOf("stw") + 3);
                log.debug("dbFilePath2 :: " + dbFilePath);

                EccFile stwFile = new EccFile();
                stwFile.setOriFileNm(file.getOriginalFilename());
                stwFile.setSvrFileNm(fileOrgName.toString());
                stwFile.setFileSize(file.getSize());
                stwFile.setFilePath(dbFilePath);
                stwFile.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
                stwFile.setMenuId(request.getParameter("menuId"));
                stwFile.setParentId(request.getParameter("parentId"));

                UserDto userDto = (UserDto) request.getSession().getAttribute("USER_INFO");
                stwFile.setInsUserid(userDto.getUserid());
                stwFile.setUpdUserid(userDto.getUserid());
                log.debug(stwFile.toString());
                stwFileMapper.insertFileInfo(stwFile);	//등록 후 FILE_SEQ가 hsqFileDto에 담겨서 넘어옴.

                fileInfos.add(stwFile);
            } catch (IOException e) {
                e.printStackTrace();
                jsonData.setStatus("FAIL");
                jsonData.addFields("RESULT_CD", "F");
                jsonData.setErrMsg(e.getMessage());
            }
        } else {
            jsonData.setStatus("FAIL");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("업로드할 파일이 업습니다.");
        }

        return jsonData;
    }

    @Override
    public JsonData multiFileUpload(MultipartFile[] files, HttpServletRequest request) {
        JsonData jsonData = new JsonData();
        String datePath = getDatePath(FILE_PATH);

        if(files != null) {
            List<EccFile> fileInfos = new ArrayList<EccFile>();
            for(MultipartFile file : files) {
                UUID fileOrgName = UUID.randomUUID();
                //String fileOrgName = dateFormat.format(new Date()) + String.format("%02d", fileSort);
                //Path copyOfLocation = Paths.get(FILE_PATH + datePath + FILE_SEPARATOR + StringUtils.cleanPath(file.getOriginalFilename()));

                Path copyOfLocation = Paths.get(FILE_PATH + datePath + FILE_SEPARATOR + StringUtils.cleanPath(fileOrgName.toString()) + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
                log.debug("/////////////");
                log.debug(FILE_PATH + datePath + FILE_SEPARATOR + StringUtils.cleanPath(fileOrgName.toString()) + "." + FilenameUtils.getExtension(file.getOriginalFilename()));

                try {
                    Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
                    String dbFilePath = copyOfLocation.getParent() + FILE_SEPARATOR;

                    //copyOfLocation.getParent() 시 window 경로 '\' 로 리턴되어 '/'로 강제 변환함.
                    dbFilePath = dbFilePath.replaceAll("\\\\", FILE_SEPARATOR);
                    log.debug("dbFilePath1 :: " + dbFilePath);
                    dbFilePath = dbFilePath.substring(dbFilePath.indexOf("stw") + 3);
                    log.debug("dbFilePath2 :: " + dbFilePath);

                    EccFile stwFile = new EccFile();
                    stwFile.setOriFileNm(file.getOriginalFilename());
                    stwFile.setSvrFileNm(fileOrgName.toString());
                    stwFile.setFileSize(file.getSize());
                    stwFile.setFilePath(dbFilePath);
                    stwFile.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
                    stwFile.setMenuId(request.getParameter("menuId"));
                    stwFile.setParentId(request.getParameter("parentId"));
                    stwFile.setInfo1(request.getParameter("info1"));
                    stwFile.setInfo2(request.getParameter("info2"));
                    stwFile.setInfo3(request.getParameter("info3"));
                    stwFile.setInfo4(request.getParameter("info4"));
                    stwFile.setInfo5(request.getParameter("info5"));

                    UserDto userDto = (UserDto) request.getSession().getAttribute("USER_INFO");
                    stwFile.setInsUserid(userDto.getUserid());
                    stwFile.setUpdUserid(userDto.getUserid());

                    stwFileMapper.insertFileInfo(stwFile);	//등록 후 FILE_SEQ가 hsqFileDto에 담겨서 넘어옴.

                    fileInfos.add(stwFile);
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
        return stwFileMapper.selectFileInfoByFId(fileSeq);
    }

    @Override
    public List<EccFile> selectFileListByParentId(Map<String, Object> paramMap) {
        return stwFileMapper.selectFileListByParentId(paramMap);
    }

    @Override
    public JsonData multiFileuploadAndInsert(Map<String, Object> paramMap, HttpServletRequest request) {
        return null;
    }

    @Override
    public int deleteFileById(Map<String, Object> paramMap) {
        return stwFileMapper.deleteFileById(paramMap);
    }

    @Override
    public int deleteFileByParentId(String parentId) {

        return stwFileMapper.deleteFileByParentId(parentId);
    }

    //날짜 폴더명 추출
    private String getDatePath(String uploadPath) {
        Calendar calendar = Calendar.getInstance();
        String yearPath = FILE_SEPARATOR + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + FILE_SEPARATOR + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
        String datePath = monthPath + FILE_SEPARATOR + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        //makeDateDir(uploadPath, yearPath, monthPath, datePath);
        makeDateDir(uploadPath, yearPath, monthPath);

        return monthPath;
    }

    //날짜별 폴더 생성
    private void makeDateDir(String uploadPath, String... paths) {
        //날짜별 폴더가 이미 존재하면 메서드 종료
        if (new File(uploadPath + paths[paths.length - 1]).exists()) {
            return;
        }

        for (String path : paths) {
            File dirPath = new File(uploadPath + path);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        }
    }
}
