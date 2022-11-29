package kr.co.haesungds.ecoc.education.controller;

import kr.co.haesungds.ecoc.education.model.CmmLegCode;
import kr.co.haesungds.ecoc.education.model.ECocInfo;
import kr.co.haesungds.ecoc.education.service.ECocService;
import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.utils.JsonData;
import kr.co.haesungds.utils.file.model.EccFile;
import kr.co.haesungds.utils.file.service.EccFileServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ECocController {
    private final ECocService eCocService;

    @Value("${ECC.FILE_ROOT_PATH}")
    private String FILE_ROOT_PATH;

    //Legacy 공통코드 조회
    @PostMapping("/ecoc/selectCmmLegCode")
    @ResponseBody
    public JsonData selectCmmLegCode(@RequestBody Map<String, Object> paramMap) {
        JsonData jsonData = new JsonData();

        List<CmmLegCode> cmmLegCodeList = eCocService.selectCmmCodeList(paramMap);
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", cmmLegCodeList != null ? "S" : "F");
        jsonData.addFields("cmmLegCodeList", cmmLegCodeList);

        return jsonData;
    }

    @GetMapping("/ecoc/educationList")
    public String educationList(Model model) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        //품목대분류
        paramMap.put("legCd", "ECC");
        paramMap.put("grpCd", "EDU_GRP_LV1");
        List<CmmLegCode> eduLv1List = eCocService.selectCmmCodeList(paramMap);

        model.addAttribute("eduLv1List", eduLv1List);

        return "thymeleaf/e-coc/edu/educationList";
    }

    @PostMapping("/ecoc/educationSelect")
    @ResponseBody
    public JsonData educationSelect(@RequestBody Map<String, Object> paramMap) {

        return eCocService.selectEducationList(paramMap);
    }

    @GetMapping("/ecoc/educationView")
    public String educationView(@RequestParam Map<String, Object> paramMap, Model model) {

        //대분류
        paramMap.put("legCd", "ECC");
        paramMap.put("grpCd", "EDU_GRP_LV1");
        List<CmmLegCode> eduLv1List = eCocService.selectCmmCodeList(paramMap);
        ECocInfo educationInfo = eCocService.selectEducationInfo(paramMap);

        model.addAttribute("eduLv1List", eduLv1List);
        model.addAttribute("educationInfo", educationInfo);

        return "thymeleaf/e-coc/edu/educationView";
    }


    @PostMapping("/ecoc/uploadQRCodeImage")
    @ResponseBody
    public JsonData uploadQRCodeImage(@RequestBody Map<String, Object> paramMap) {

        return eCocService.uploadQRCodeImage(paramMap);
    }

    @GetMapping("/ecoc/downloadQRCodeImage")
    public void downloadQRCodeImage(HttpServletResponse response
            , @RequestHeader("User-Agent") String agent
            , @RequestParam(name = "eduId")String eduId
            , @RequestParam(name = "eduTitle")String eduTitle) {

        File file = new File(FILE_ROOT_PATH + eduId + ".png");

        try {
            if(agent.contains("Trident")) {
                eduTitle = URLEncoder.encode(eduTitle, "UTF-8").replaceAll("\\+", " ");
            }else if(agent.contains("Edge")) {
                eduTitle = URLEncoder.encode(eduTitle, "UTF-8");
            }else {
                eduTitle = new String(eduTitle.getBytes("UTF-8"), "ISO-8859-1");
            }


            response.setHeader("Content-Disposition", "attachment;filename=" + eduTitle + ".png"); // 다운로드 되거나 로컬에 저장되는 용도로 쓰이는지를 알려주는 헤더
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/ecoc/viewMovie")
    public String viewMovie(@RequestParam(name = "eduId")String eduId, Model model) {

        File file = new File(FILE_ROOT_PATH + eduId + ".png");

        return "movie/" + eduId + ".mp4";
    }

//    동영상 스트리밍...
    @GetMapping(value="/mp4Stream")
    //@GetMapping(value="/onpage/mp4Stream")
    public void viewMp4Stream (@RequestParam String fileNm,
                               HttpServletRequest request , HttpServletResponse response)throws IOException {
        File file = new File(FILE_ROOT_PATH + fileNm);
        if (file.exists() && file.isFile()) {
            RandomAccessFile randomFile = new RandomAccessFile(file, "r");
            long rangeStart = 0; //요청 범위의 시작 위치
            long rangeEnd = 0; //요청 범위의 끝 위치
            boolean isPart = false; //부분 요청일 경우 true, 전체 요청의 경우 false
            try { //동영상 파일 크기
                long movieSize = randomFile.length(); //스트림 요청 범위, request의 헤더에서 range를 읽는다.
                String range = request.getHeader("range");
                if (range != null) {
                    if (range.endsWith("-")) {
                        range = range + (movieSize - 1);
                    }
                    int idxm = range.trim().indexOf("-");
                    rangeStart = Long.parseLong(range.substring(6, idxm));
                    rangeEnd = Long.parseLong(range.substring(idxm + 1));
                    if (rangeStart > 0) {
                        isPart = true;
                    }
                } else {
                    rangeStart = 0;
                    rangeEnd = movieSize - 1;
                }
                long partSize = rangeEnd - rangeStart + 1;
                response.reset();
                response.setStatus(isPart ? 206 : 200);
                response.setContentType("video/mp4");
                response.setHeader("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + movieSize);
                response.setHeader("Accept-Ranges", "bytes");
                response.setHeader("Content-Length", "" + partSize);
                OutputStream out = response.getOutputStream();
                randomFile.seek(rangeStart);
                int bufferSize = 8 * 1024;
                byte[] buf = new byte[bufferSize];
                do {
                    int block = partSize > bufferSize ? bufferSize : (int) partSize;
                    int len = randomFile.read(buf, 0, block);
                    out.write(buf, 0, len);
                    partSize -= block;
                } while (partSize > 0);
            } catch (IOException e) {
            } finally {
                randomFile.close();
            }
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('해당 파일이 없습니다.'); location.href='https://ecc.haesungds.net:9006/sloLogin';</script>");
            out.flush();
        }

    }

    //모바일기기에서 동영상 실시간 스트리밍 시 실행되는 기본 동영상 플레이어의 영상다운로드 기능을 막을 방법이 없어서
    //별도의 <video> 태그만 있는 html을 만들어 동영상 재생한다.
//    @GetMapping(value="/mp4Stream")
//    public String mp4Stream(@RequestParam String fileNm, Model model) {
//        log.debug("fileNm :: " + fileNm);
//        String[] fileNmSplit = fileNm.split("\\.");
//        log.debug(fileNmSplit[0]);
//        log.debug("fileNmSplit.length :: " + fileNmSplit.length);
//        String eduId = fileNmSplit.length > 0 ? fileNmSplit[0] : "";
//        log.debug("eduId :: " + eduId);
//        ECocInfo educationInfo = null;
//        if (!"".equals(eduId)) {
//            Map<String, Object> paramMap = new HashMap<>();
//            paramMap.put("eduId", eduId);
//
//            educationInfo = eCocService.selectEducationInfo(paramMap);
//        }
//
//        model.addAttribute("educationInfo", educationInfo);
//
//        return "thymeleaf/e-coc/edu/playVideoOnHtml";
//    }

    @GetMapping("/ecoc/educationRegist")
    public String educationRegist(Model model) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        //대분류
        paramMap.put("legCd", "ECC");
        paramMap.put("grpCd", "EDU_GRP_LV1");
        List<CmmLegCode> eduLv1List = eCocService.selectCmmCodeList(paramMap);

        model.addAttribute("eduLv1List", eduLv1List);

        return "thymeleaf/e-coc/edu/educationRegist";
    }

    @PostMapping("/ecoc/educationRegistProc")
    @ResponseBody
    public JsonData educationRegistProc(@RequestBody ECocInfo eCocInfo
            , @AuthenticationPrincipal UserDto userDto) {

        eCocInfo.setInsUserid(userDto.getUserid());
        eCocInfo.setUpdUserid(userDto.getUserid());

        JsonData jsonData = eCocService.educationRegistProc(eCocInfo);

        return jsonData;
    }

    @GetMapping("/ecoc/educationModify")
    public String educationModify(@RequestParam Map<String, Object> paramMap, Model model) {

        ECocInfo educationInfo = eCocService.selectEducationInfo(paramMap);

        //대분류
        paramMap.put("legCd", "ECC");
        paramMap.put("grpCd", "EDU_GRP_LV1");
        List<CmmLegCode> eduLv1List = eCocService.selectCmmCodeList(paramMap);

        //중분류
        paramMap.put("legCd", "ECC");
        paramMap.put("grpCd", "EDU_GRP_LV2");
        paramMap.put("info1", educationInfo.getEduGrpCd1());
        List<CmmLegCode> eduLv2List = eCocService.selectCmmCodeList(paramMap);


        //첨부파일 조회
        paramMap.put("menuId", "MOVIE");
        paramMap.put("parentId", paramMap.get("eduId"));
        EccFile educationFile = eCocService.selectEducationFileInfo(paramMap);


        model.addAttribute("eduLv1List", eduLv1List);
        model.addAttribute("eduLv2List", eduLv2List);
        model.addAttribute("educationInfo", educationInfo);
        model.addAttribute("educationFile", educationFile);

        return "thymeleaf/e-coc/edu/educationModify";
    }

    @PostMapping("/ecoc/educationModifyProc")
    @ResponseBody
    public JsonData educationModifyProc(@RequestBody ECocInfo eCocInfo
            , @AuthenticationPrincipal UserDto userDto) {

        eCocInfo.setInsUserid(userDto.getUserid());
        eCocInfo.setUpdUserid(userDto.getUserid());

        JsonData jsonData = eCocService.educationModifyProc(eCocInfo);

        return jsonData;
    }


    @PostMapping("/ecoc/educationDeleteProc")
    @ResponseBody
    public JsonData educationDeleteProc(@RequestBody Map<String, Object> paramMap) {

        return eCocService.educationDeleteProc(paramMap);
    }

    @GetMapping("/checkUserid")
    public String checkUserid(@RequestParam(name="qrUrl", required = true) String qrUrl, Model model) {

        model.addAttribute("qrUrl", qrUrl);
        return "thymeleaf/e-coc/edu/checkUserid";
    }

    @PostMapping("/checkUseridProc")
    @ResponseBody
    public JsonData checkUseridProc(@RequestBody Map<String, Object> paramMap) {

        return eCocService.checkUseridPwProc(paramMap);
    }
}
