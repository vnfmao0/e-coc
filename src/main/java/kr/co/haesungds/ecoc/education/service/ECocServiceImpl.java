package kr.co.haesungds.ecoc.education.service;

import kr.co.haesungds.ecoc.education.mapper.ECocMapper;
import kr.co.haesungds.ecoc.education.model.CmmLegCode;
import kr.co.haesungds.ecoc.education.model.ECocInfo;
import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.utils.JsonData;
import kr.co.haesungds.utils.file.model.EccFile;
import kr.co.haesungds.utils.file.service.EccFileServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ECocServiceImpl implements ECocService {
    private final ECocMapper eCocMapper;
    @Qualifier("eccFileService")
    private final EccFileServiceImpl fileService;

    @Value("${ECC.FILE_ROOT_PATH}")
    private String FILE_ROOT_PATH;

    @Override
    public List<CmmLegCode> selectCmmCodeList(Map<String, Object> paramMap) {
        return eCocMapper.selectCmmCodeList(paramMap);
    }

    @Override
    public JsonData selectEducationList(Map<String, Object> paramMap) {

        List<ECocInfo> educationList = eCocMapper.selectEducationList(paramMap);
        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", educationList.size() > 0 ? "S" : "F");
        jsonData.addFields("educationList", educationList);
        return jsonData;
    }

    @Override
    public ECocInfo selectEducationInfo(Map<String, Object> paramMap) {
        return eCocMapper.selectEducationInfo(paramMap);
    }

    @Override
    public JsonData uploadQRCodeImage(Map<String, Object> paramMap) {

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");

        String eduId = ObjectUtils.defaultIfNull(paramMap.get("eduId"), "").toString();
        String imageData = ObjectUtils.defaultIfNull(paramMap.get("imageData"), "").toString();
        if ("".equals(eduId) || "".equals(imageData)) {
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("교육코드 또는 QR코드 정보가 없습니다.");
        } else {

            byte[] decodedBytes = Base64.getDecoder().decode(imageData);
            String decodedString = new String(decodedBytes);

            String fileName = eduId + ".png";
            File file = new File(FILE_ROOT_PATH + fileName);
            byte[] bytes = imageData.getBytes();
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                outputStream.write(decodedBytes);
                jsonData.addFields("RESULT_CD", "S");
            } catch (IOException e) {
                e.printStackTrace();
                jsonData.addFields("RESULT_CD", "F");
                jsonData.setErrMsg("QR코드 생성시 오류가 발생했습니다.");
            }
        }



        return jsonData;
    }

    @Override
    public JsonData downloadQRCodeImage(HttpServletResponse response, Map<String, Object> paramMap) {
        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");

        String eduTitle = ObjectUtils.defaultIfNull(paramMap.get("eduTitle"), "").toString();
        String eduId = ObjectUtils.defaultIfNull(paramMap.get("eduId"), "").toString();
        String imageData = ObjectUtils.defaultIfNull(paramMap.get("imageData"), "").toString();
        if ("".equals(eduId) || "".equals(imageData)) {
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("교육코드 또는 QR코드 정보가 없습니다.");
        } else {

            byte[] decodedBytes = Base64.getDecoder().decode(imageData);

            try {
                response.setHeader("Content-Disposition", "attachment;filename=" + eduTitle + ".png"); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Expires", "0");

                OutputStream out = response.getOutputStream();

                int read = 0;
                byte[] buffer = new byte[1024];
                out.write(decodedBytes);

                jsonData.addFields("RESULT_CD", "S");
            } catch (Exception e) {
                e.printStackTrace();
                jsonData.addFields("RESULT_CD", "F");
                jsonData.setErrMsg("QR코드 생성시 오류가 발생했습니다.");
            }



        }

        return jsonData;
    }

    @Override
    public JsonData educationRegistProc(ECocInfo eCocInfo) {

        eCocInfo.setFileRootPath("/movie/");
        int ret = eCocMapper.educationRegistProc(eCocInfo);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", ret > 0 ? "S" : "F");
        jsonData.addFields("eduId", eCocInfo.getEduId());

        return jsonData;
    }

    @Override
    public JsonData educationModifyProc(ECocInfo eCocInfo) {
        int ret = eCocMapper.educationModifyProc(eCocInfo);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", ret > 0 ? "S" : "F");
        jsonData.addFields("eduId", eCocInfo.getEduId());

        return jsonData;
    }

    @Override
    public JsonData educationDeleteProc(Map<String, Object> paramMap) {

        // 파일삭제
        paramMap.put("menuId", "MOVIE");
        paramMap.put("parentId", paramMap.get("eduId"));
        List<EccFile> eccFileList = fileService.selectFileListByParentId(paramMap);
        if (eccFileList.size() > 0) {
            for (EccFile eccFile : eccFileList) {
                //동영상파일 삭제
                File movieFile = new File(FILE_ROOT_PATH + eccFile.getSvrFileNm());
                if (movieFile.exists() && movieFile.isFile()) {
                    if (movieFile.delete()) {
                        log.debug("동영상 파일삭제 성공!!!");
                    } else {
                        log.debug("동영상 파일삭제 실패!!!");

                    }
                }

                //QR 이미지 파일 삭제
                File qrImgFile = new File(FILE_ROOT_PATH + ObjectUtils.defaultIfNull(paramMap.get("eduId"), "").toString() + ".png");
                if (qrImgFile.exists() && qrImgFile.isFile()) {
                    if (qrImgFile.delete()) {
                        log.debug("QR 이미지 파일삭제 성공!!!");
                    } else {
                        log.debug("QR 이미지 파일삭제 실패!!!");

                    }
                }

            }
        }
        fileService.deleteFileByParentId(ObjectUtils.defaultIfNull(paramMap.get("eduId"), "").toString());

        // data 삭제
        int ret = eCocMapper.educationDeleteProc(paramMap);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", ret > 0 ? "S" : "F");

        return jsonData;
    }

    @Override
    public EccFile selectEducationFileInfo(Map<String, Object> paramMap) {
        return eCocMapper.selectEducationFileInfo(paramMap);
    }

    @Override
    public JsonData checkUseridProc(Map<String, Object> paramMap) {

        int chkRet = eCocMapper.checkUseridProc(paramMap);
        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", chkRet > 0 ? "S" : "F");

        return jsonData;
    }

    @Override
    public JsonData checkUseridPwProc(Map<String, Object> paramMap) {
        Map<String, Object> rsMap = eCocMapper.checkUseridPwProc(paramMap);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        //jsonData.addFields("RESULT_CD", chkRet > 0 ? "S" : "F");

        if (rsMap == null || rsMap.isEmpty()) {
            log.debug("rsMap is null!");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("사번을 확인하세요.");
        } else if ("DIFF".equals(ObjectUtils.defaultIfNull(rsMap.get("passChk"), "").toString())) {
            log.debug("rsMap : " + ObjectUtils.defaultIfNull(rsMap.get("passChk"), "").toString());
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("암호를 확인하세요.");
        } else {
            log.debug("rsMap : " + ObjectUtils.defaultIfNull(rsMap.get("passChk"), "").toString());
            jsonData.addFields("RESULT_CD", "S");

        }

        return jsonData;
    }
}
