package kr.co.haesungds.utils.file.service;

import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.utils.JsonData;
import kr.co.haesungds.utils.file.mapper.HsqFileMapper;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service(value = "hsqFileService")
@RequiredArgsConstructor
public class HsqFileServiceImpl implements CmmFileService{
    @Value("${ECC.FILE_PATH}")
    private String FILE_PATH;

    @Value("${ECC.FILE_DOWN_PATH}")
    private String FILE_DOWN_PATH;

    @Value("${ECC.FILE_SEPARATOR}")
    private String FILE_SEPARATOR;

    @Autowired
    private HsqFileMapper hsqFileMapper;

    @Override
    public JsonData singleFileUpload(MultipartFile file, HttpServletRequest request) {
        JsonData jsonData = new JsonData();
//		String file_path = "E:\\stwMis\\STWC\\upload\\board";
        String datePath = getDatePath(FILE_PATH);

        if(file != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

            //물리파일 저장 - start
            Path copyOfLocation = Paths.get(FILE_PATH + datePath + FILE_SEPARATOR + StringUtils.cleanPath(file.getOriginalFilename()) + "." + FilenameUtils.getExtension(file.getOriginalFilename()));

            try {

                Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                e.printStackTrace();
                jsonData.setStatus("FAIL");
                jsonData.addFields("RESULT_CD", "F");
                jsonData.setErrMsg(e.getMessage());
            }
            //물리파일 저장 - end
            String dbFilePath = copyOfLocation.getParent() + FILE_SEPARATOR;
            dbFilePath = dbFilePath.substring(dbFilePath.indexOf("hsq") + 3);
            //파일 정보 저장 - start
            int fileSort = 1;
            HsqFile hsqFile = new HsqFile();
            hsqFile.setOriFileNm(file.getOriginalFilename());
            hsqFile.setSvrFileNm(UUID.randomUUID().toString());
            hsqFile.setFileSize(file.getSize());
            hsqFile.setFilePath(dbFilePath);
            hsqFile.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));

            hsqFileMapper.insertFileInfo(hsqFile);	//등록 후 FILE_SEQ가 hsqFileDto에 담겨서 넘어옴.

            jsonData.setStatus("SUCC");
            jsonData.addFields("RESULT_CD", "S");
            jsonData.addFields("FILE_INFO", hsqFile);

            //파일 정보 저장 - end
        } else {
            jsonData.setStatus("FAIL");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("업로드할 파일이 업습니다.");
        }

        return jsonData;
    }


    //게시물 등록시 파일 업로드...
    @Override
    public JsonData multiFileUpload(MultipartFile[] files, HttpServletRequest request) {
        JsonData jsonData = new JsonData();
//		String file_path = "E:\\stwMis\\STWC\\upload\\board";
        String datePath = getDatePath(FILE_PATH);

        if(files != null) {
            List<HsqFile> fileInfos = new ArrayList<HsqFile>();
            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

            //파일명에 bbs_seq가 붙어서 등록된다.

            //물리파일 저장 - start
            int fileSort = 1;
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
                    dbFilePath = dbFilePath.substring(dbFilePath.indexOf("hsq") + 3);
                    log.debug("dbFilePath2 :: " + dbFilePath);

                    HsqFile hsqFile = new HsqFile();
                    hsqFile.setOriFileNm(file.getOriginalFilename());
                    hsqFile.setSvrFileNm(fileOrgName.toString());
                    hsqFile.setFileSize(file.getSize());
                    hsqFile.setFilePath(dbFilePath);
                    hsqFile.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));

                    UserDto userDto = (UserDto) request.getSession().getAttribute("USER_INFO");
                    hsqFile.setInsUserid(userDto.getUserid());
                    hsqFile.setUpdUserid(userDto.getUserid());

                    hsqFileMapper.insertFileInfo(hsqFile);	//등록 후 FILE_SEQ가 hsqFileDto에 담겨서 넘어옴.

                    fileInfos.add(hsqFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    jsonData.setStatus("FAIL");
                    jsonData.addFields("RESULT_CD", "F");
                    jsonData.setErrMsg(e.getMessage());
                }

            }
            //물리파일 저장 - end

            //파일 정보 저장 - start
            jsonData.setStatus("SUCC");
            jsonData.addFields("RESULT_CD", "S");
            jsonData.addFields("FILE_INFOS", fileInfos);

            //파일 정보 저장 - end
        } else {
            jsonData.setStatus("FAIL");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("업로드할 파일이 업습니다.");
        }

        return jsonData;
    }


    @Override
    public EccFile selectFileInfoByFId(String fileSeq) {
        return hsqFileMapper.selectFileInfoByFId(fileSeq);
    }

    @Override
    public List<EccFile> selectFileListByParentId(Map<String, Object> paramMap) {
        return null;
    }

    @Override
    public int deleteFileById(Map<String, Object> paramMap) {
        return hsqFileMapper.deleteFileById(paramMap);
    }

    @Override
    public int deleteFileByParentId(String parentId) {
        List<StwFile> fileList = hsqFileMapper.selectFileListByBbsSeq2(parentId);
        int retCnt = 0;
        if (fileList != null && !fileList.isEmpty()) {
            for (StwFile stwFile : fileList) {
                File file = new File(FILE_PATH + stwFile.getFilePath() + stwFile.getSvrFileNm());
                if (file.exists() && !file.isDirectory()) {
                    file.delete();
                    hsqFileMapper.deleteFileByFileSeq(stwFile.getFileSeq());
                    retCnt++;
                }
            }
            //hsqFileMapper.deleteFileByParentId(parentId);;
        }

        return retCnt;
    }

    @Override
    public JsonData multiFileuploadAndInsert(Map<String, Object> paramMap, HttpServletRequest request) {
        JsonData jsonData = new JsonData();
//		String file_path = "E:\\stwMis\\STWC\\upload\\board";
        String datePath = getDatePath(FILE_PATH);
        MultipartFile[] files = (MultipartFile[]) paramMap.get("files");

        if(files != null) {
            List<HsqFile> fileInfos = new ArrayList<HsqFile>();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

            //물리파일 저장 - start
            //해당 게시물의 last fileSort를 가져온다.
            //int fileSort = hsqFileMapper.getMaxFileSort(paramMap) + 1;
            for(MultipartFile file : files) {
                UUID fileOrgName = UUID.randomUUID();
                //String fileOrgName = dateFormat.format(new Date()) + String.format("%02d", fileSort);

                Path copyOfLocation = Paths.get(FILE_PATH + datePath + FILE_SEPARATOR + StringUtils.cleanPath(fileOrgName.toString()) + "." + FilenameUtils.getExtension(file.getOriginalFilename()));
                try {
                    Files.copy(file.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
                    String dbFilePath = copyOfLocation.getParent() + FILE_SEPARATOR;
                    dbFilePath = dbFilePath.substring(dbFilePath.indexOf("hsq") + 3);

                    //파일 정보 저장 - start
                    HsqFile hsqFile = new HsqFile();
                    hsqFile.setOriFileNm(file.getOriginalFilename());
                    hsqFile.setSvrFileNm(fileOrgName.toString());
                    hsqFile.setFileSize(file.getSize());
                    hsqFile.setFilePath(dbFilePath);
                    hsqFile.setFileType(FilenameUtils.getExtension(file.getOriginalFilename()));
                    hsqFile.setBbsSeq((String)paramMap.get("CONTENT_ID"));

                    UserDto userDto = (UserDto) request.getSession().getAttribute("USER_INFO");
                    hsqFile.setInsUserid(userDto.getUserid());
                    hsqFile.setUpdUserid(userDto.getUserid());

                    hsqFileMapper.insertFileInfo(hsqFile);	//등록 후 FILE_SEQ가 hsqFileDto에 담겨서 넘어옴.
                    //파일 정보 저장 - end
                    //fileSort++;

                } catch (IOException e) {
                    e.printStackTrace();
                    jsonData.setStatus("FAIL");
                    jsonData.addFields("RESULT_CD", "F");
                    jsonData.setErrMsg(e.getMessage());
                }

            }
            //물리파일 저장 - end


            jsonData.setStatus("SUCC");
            jsonData.addFields("RESULT_CD", "S");
            jsonData.addFields("FILE_INFOS", fileInfos);


        } else {
            jsonData.setStatus("FAIL");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("업로드할 파일이 업습니다.");
        }


        return jsonData;
    }

    public int updateFileInfo(HsqFile fileInfo) {
        return hsqFileMapper.updateFileInfo(fileInfo);
    }

    public List<Map<String, Object>> selectFileListByBbsSeq(Map<String, Object> paramMap) {
        return hsqFileMapper.selectFileListByBbsSeq(paramMap);
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
