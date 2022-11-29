package kr.co.haesungds.utils.file.controller;

import kr.co.haesungds.utils.JsonData;
import kr.co.haesungds.utils.file.model.EccFile;
import kr.co.haesungds.utils.file.model.HsqFile;
import kr.co.haesungds.utils.file.model.StwFile;
import kr.co.haesungds.utils.file.service.CmmFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@Slf4j
@Controller
public class CmmFileController {

    @Value("${ECC.FILE_ROOT_PATH}")
    private String FILE_ROOT_PATH;

    @Value("${ECC.FILE_PATH}")
    private String FILE_PATH;

    @Value("${ECC.IMG_DOWN_FILE_PATH}")
    private String IMG_DOWN_FILE_PATH;

    @Value("${ECC.FILE_DOWN_PATH}")
    private String FILE_DOWN_PATH;

    @Value("${ECC.OUT_FILE_DOWN_PATH}")
    private String OUT_FILE_DOWN_PATH;

    @Autowired
    @Qualifier("eccFileService")
    private CmmFileService fileService;

    /**
     * 공통 싱글파일 업로드
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/cmmfile/singleFileUpload", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public JsonData singleFileUpload(@RequestParam(required = false, name = "upfiles") MultipartFile file
            , HttpServletRequest request) {

        JsonData jsonData;

        log.debug("file :: " + file);

        if(file != null) {
            //물리 파일 저장
            jsonData = fileService.singleFileUpload(file, request);

            //파일 정보 디비 저장
            //fileService.insertFileInfo();

        }else {
            jsonData = new JsonData();
            jsonData.setStatus("FAIL");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("업로드할 파일이 없습니다.");
        }

        return jsonData;
    }

    /**
     * 공통 멀티파일 업로드
     *
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "/cmmfile/multiFileupload", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public JsonData multiFileupload(@RequestParam(required = false, name = "upfiles") MultipartFile[] files
            , HttpServletRequest request) {

        JsonData jsonData = new JsonData();
        log.debug("files :: " + files);
        if(files != null) {
            //물리 파일 저장
            log.debug("fileService :: " + fileService);
            jsonData = fileService.multiFileUpload(files, request);

            //파일 정보 디비 저장
            //fileService.insertFileInfos();
        }else {
            jsonData = new JsonData();
            jsonData.setStatus("FAIL");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("업로드할 파일이 없습니다.");
        }

        return jsonData;
    }

    /**
     * 공통 멀티파일 업로드(수정화면에서 사용)
     *
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(value = "/cmmfile/multiFileuploadAndInsert", method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public JsonData multiFileuploadAndInsert(@RequestParam(required = false, name = "upfiles") MultipartFile[] files
            , @RequestParam(required = true, name = "MENU_ID") String menuId
            , @RequestParam(required = true, name = "CONTENT_ID") String contentId
            , HttpServletRequest request) {

        JsonData jsonData = new JsonData();

        if(files != null) {
            //물리 파일 저장
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("files", files);
            paramMap.put("MENU_ID", menuId);
            paramMap.put("CONTENT_ID", contentId);

            jsonData = fileService.multiFileuploadAndInsert(paramMap, request);

            //파일 정보 디비 저장
            //fileService.insertFileInfos();
        }else {
            jsonData = new JsonData();
            jsonData.setStatus("FAIL");
            jsonData.addFields("RESULT_CD", "F");
            jsonData.setErrMsg("업로드할 파일이 없습니다.");
        }

        return jsonData;
    }

    /**
     * 공통 파일삭제(수정화면에서 사용)
     *
     * @return
     */
    @RequestMapping(value = "/cmmfile/deleteFile/{fileId}", method = RequestMethod.POST)
    @ResponseBody
    public JsonData deleteFile(@PathVariable("fileId") String fileId) {

        JsonData jsonData = new JsonData();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fileSeq", fileId);

        fileService.deleteFileById(paramMap);

        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", "S");

        return jsonData;
    }

    /**
     * 공통 파일목록조회
     *
     * @return
     */
    @RequestMapping(value = "/cmmfile/selectFileListByParentId", method = RequestMethod.POST)
    @ResponseBody
    public JsonData selectFileListByParentId(@RequestBody Map<String, Object> paramMap) {

        JsonData jsonData = new JsonData();
        List<EccFile> fileList = fileService.selectFileListByParentId(paramMap);

        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", "S");
        jsonData.addFields("fileList", fileList);

        return jsonData;
    }

    @GetMapping("/cmmfile/singleFileDownload/{fileSeq}")
    public void download(HttpServletResponse response, @RequestHeader("User-Agent") String agent, @PathVariable(name = "fileSeq")String fileSeq) throws Exception {
        try {

            EccFile attFileInfo = fileService.selectFileInfoByFId(fileSeq);
            if(attFileInfo == null) return;
            String fileDir = attFileInfo.getFilePath();
            String reFileNm = attFileInfo.getSvrFileNm();
            String orgFileNm = attFileInfo.getOriFileNm();
            String fileType =  attFileInfo.getFileType();

            File file = new File(FILE_DOWN_PATH + fileDir + reFileNm + "." + fileType);

            if(file.exists()) {
                if(agent.contains("Trident")) {
                    orgFileNm = URLEncoder.encode(orgFileNm, "UTF-8").replaceAll("\\+", " ");
                }else if(agent.contains("Edge")) {
                    orgFileNm = URLEncoder.encode(orgFileNm, "UTF-8");
                }else {
                    orgFileNm = new String(orgFileNm.getBytes("UTF-8"), "ISO-8859-1");
                }
                orgFileNm=orgFileNm.replace(',', ' ');

                response.setHeader("Content-Disposition", "attachment;filename=" + orgFileNm); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Expires", "0");

                FileInputStream fileInputStream = new FileInputStream(file); // 파일 읽어오기
                OutputStream out = response.getOutputStream();

                int read = 0;
                byte[] buffer = new byte[1024];
                while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                    out.write(buffer, 0, read);
                }
            }

        } catch (Exception e) {
            throw new Exception("download error");
        }
    }

    //비자재구매_업체등록 파일 다운로드
    @GetMapping("/cmmfile/singleFileDownloadStwout/{fileSeq}")
    public void downloadStwout(HttpServletResponse response, @RequestHeader("User-Agent") String agent, @PathVariable(name = "fileSeq")String fileSeq) throws Exception {
        try {

            EccFile attFileInfo = fileService.selectFileInfoByFId(fileSeq);
            if(attFileInfo == null) return;
            String fileDir = attFileInfo.getFilePath();
            String reFileNm = attFileInfo.getSvrFileNm();
            String orgFileNm = attFileInfo.getOriFileNm();
            String fileType =  attFileInfo.getFileType();

            File file = new File(OUT_FILE_DOWN_PATH + fileDir + reFileNm + "." + fileType);

            if(file.exists()) {
                if(agent.contains("Trident")) {
                    orgFileNm = URLEncoder.encode(orgFileNm, "UTF-8").replaceAll("\\+", " ");
                }else if(agent.contains("Edge")) {
                    orgFileNm = URLEncoder.encode(orgFileNm, "UTF-8");
                }else {
                    orgFileNm = new String(orgFileNm.getBytes("UTF-8"), "ISO-8859-1");
                }
                orgFileNm=orgFileNm.replace(',', ' ');

                response.setHeader("Content-Disposition", "attachment;filename=" + orgFileNm); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Expires", "0");

                FileInputStream fileInputStream = new FileInputStream(file); // 파일 읽어오기
                OutputStream out = response.getOutputStream();

                int read = 0;
                byte[] buffer = new byte[1024];
                while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                    out.write(buffer, 0, read);
                }
            }

        } catch (Exception e) {
            throw new Exception("download error");
        }
    }

    @GetMapping("/cmmfile/downloadPotEncoder")
    public void downloadPotEncoder(HttpServletResponse response, @RequestHeader("User-Agent") String agent) throws Exception {
        try {
            File file = new File(FILE_DOWN_PATH + "/DaumPotEncoder21453.exe");

            if(file.exists()) {
                response.setHeader("Content-Disposition", "attachment;filename=DaumPotEncoder21453.exe"); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setHeader("Expires", "0");

                FileInputStream fileInputStream = new FileInputStream(file); // 파일 읽어오기
                OutputStream out = response.getOutputStream();

                int read = 0;
                byte[] buffer = new byte[1024];
                while ((read = fileInputStream.read(buffer)) != -1) { // 1024바이트씩 계속 읽으면서 outputStream에 저장, -1이 나오면 더이상 읽을 파일이 없음
                    out.write(buffer, 0, read);
                }
            }

        } catch (Exception e) {
            throw new Exception("download error");
        }
    }

    @GetMapping(value = "/cmmfile/getThumbImage")
    public void getThumbImage(HttpServletRequest request, HttpServletResponse response, @RequestParam("IMG_PATH") String imgPath) {
        log.debug("in getThumbImage() : " + imgPath);
        String imgFile = "";
        response.setContentType("image/gif");

        try {
            ServletOutputStream sout = response.getOutputStream();
            File f = new File(IMG_DOWN_FILE_PATH + imgPath);

            if(f.exists()) {
                imgFile = f.getPath();
            } else {
                //imgFile = FilePath.WEBAPP_PATH + "img/bs-images.jpg";
                return;
            }


            int len;
            byte [] buffer = new byte[10];
            FileInputStream fis = new FileInputStream(imgFile);

            while((len = fis.read(buffer)) != -1) {
                sout.write(buffer, 0, len);
            }
            fis.close();
            sout.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


        //return null;
    }
}
