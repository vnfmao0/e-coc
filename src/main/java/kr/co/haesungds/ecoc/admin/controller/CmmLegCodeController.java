package kr.co.haesungds.ecoc.admin.controller;

import kr.co.haesungds.ecoc.admin.model.CmmLegCode;
import kr.co.haesungds.ecoc.admin.service.CmmLegCodeService;
import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.utils.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CmmLegCodeController {

    @Value("${LEGACY_SYSTEM_CODE}")
    private String LEGACY_SYSTEM_CODE;

    private final CmmLegCodeService codeService;

    /*
    ======================================================================================================
    공통코드 관리
    ======================================================================================================
    */
    //공통코드 관리 페이지 이동
    @GetMapping("/admin/cmmLegCodeManage")
    public String p161103List(Model model) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        //비자재구매 공통코드 조회
        paramMap.put("legCd", LEGACY_SYSTEM_CODE);
        paramMap.put("grpCd", "ITEM_LV1");
        codeService.selectCmmCodeList(paramMap);

        return "thymeleaf/e-coc/admin/cmmLegCodeManage";
    }

    @PostMapping("/admin/selectCmmLegCodeList")
    @ResponseBody
    public JsonData selectCmmCodeList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        //비자재구매 공통코드 조회
        paramMap.put("legCd", LEGACY_SYSTEM_CODE);
        //paramMap.put("grpCd", "ITEM_LV1");
        List<CmmLegCode> codeList = codeService.selectCmmCodeList(paramMap);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD" , "S");
        jsonData.addFields("codeList", codeList);

        return jsonData;
    }

    @PostMapping("/p1611/selectCmmCodeList_grid")
    @ResponseBody
    public List<CmmLegCode> selectCmmCodeListGrid() {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        //비자재구매 공통코드 조회
        paramMap.put("legCd", LEGACY_SYSTEM_CODE);
        paramMap.put("grpCd", "ITEM_LV1");

        return codeService.selectCmmCodeList(paramMap);
    }

    //공통코드 등록처리
    @PostMapping("/p1611/insertCmmCode")
    @ResponseBody
    public JsonData insertCmmCode(@RequestBody CmmLegCode cmmLegCode
            , @AuthenticationPrincipal UserDto userDto) {
        JsonData jsonData = new JsonData();
        log.debug(cmmLegCode.toString());
        try {
            cmmLegCode.setLegCd(LEGACY_SYSTEM_CODE);
            cmmLegCode.setInsUserid(userDto.getUserid());
            cmmLegCode.setUpdUserid(userDto.getUserid());

            if (ObjectUtils.defaultIfNull(cmmLegCode.getUseYn(), "").equalsIgnoreCase("Y")
                    || ObjectUtils.defaultIfNull(cmmLegCode.getUseYn(), "").equalsIgnoreCase("true")) {
                cmmLegCode.setUseYn("Y");
            } else {
                cmmLegCode.setUseYn("N");
            }

            jsonData = codeService.insertCmmCode(cmmLegCode);
        } catch (Exception e) {
            e.printStackTrace();
            jsonData.setErrMsg(e.getMessage());
        }

        if (log.isDebugEnabled()) {
            log.debug("jsonData = " + jsonData);
        }
        return jsonData;
    }

    //공통코드 수정처리
    @PostMapping("/p1611/updateCmmCode")
    @ResponseBody
    public JsonData updateCmmCode(@RequestBody CmmLegCode cmmLegCode
            , @AuthenticationPrincipal UserDto userDto) {
        JsonData jsonData = new JsonData();

        try {
            cmmLegCode.setLegCd(LEGACY_SYSTEM_CODE);
            cmmLegCode.setInsUserid(userDto.getUserid());
            cmmLegCode.setUpdUserid(userDto.getUserid());

            if (ObjectUtils.defaultIfNull(cmmLegCode.getUseYn(), "").equalsIgnoreCase("Y")
                    || ObjectUtils.defaultIfNull(cmmLegCode.getUseYn(), "").equalsIgnoreCase("true")) {
                cmmLegCode.setUseYn("Y");
            } else {
                cmmLegCode.setUseYn("N");
            }

            jsonData = codeService.updateCmmCode(cmmLegCode);
        } catch (Exception e) {
            e.printStackTrace();
            jsonData.setErrMsg(e.getMessage());
        }

        if (log.isDebugEnabled()) {
            log.debug("jsonData = " + jsonData);
        }
        return jsonData;
    }

    //공통코드 수정처리
    @PostMapping("/admin/updateCmmLegCodeList")
    @ResponseBody
    public JsonData updateCmmLegCodeList(@RequestBody Map<String, Object> paramMap
            , @AuthenticationPrincipal UserDto userDto) {
        JsonData jsonData = new JsonData();

        log.debug(paramMap.get("cmmLegCodeList") + "");

        paramMap.put("legCd", LEGACY_SYSTEM_CODE);
        paramMap.put("insUserid", userDto.getUserid());
        paramMap.put("updUserid", userDto.getUserid());
        jsonData = codeService.applyCmmLegCode(paramMap);

//

//        try {
//            cmmLegCode.setLegCd(LEGACY_SYSTEM_CODE);
//            cmmLegCode.setInsUserid(userDto.getUserid());
//            cmmLegCode.setUpdUserid(userDto.getUserid());
//
//            if (ObjectUtils.defaultIfNull(cmmLegCode.getUseYn(), "").equalsIgnoreCase("Y")
//                    || ObjectUtils.defaultIfNull(cmmLegCode.getUseYn(), "").equalsIgnoreCase("true")) {
//                cmmLegCode.setUseYn("Y");
//            } else {
//                cmmLegCode.setUseYn("N");
//            }
//
//            jsonData = p1611Service.updateCmmCode(cmmLegCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsonData.setErrMsg(e.getMessage());
//        }
//
//        if (log.isDebugEnabled()) {
//            log.debug("jsonData = " + jsonData);
//        }
        return jsonData;
    }

    //공통코드 삭제처리
    @PostMapping("/p1611/deleteCmmCode")
    @ResponseBody
    public JsonData deleteCmmCode(@RequestBody CmmLegCode cmmLegCode
            , @AuthenticationPrincipal UserDto userDto) {
        JsonData jsonData = new JsonData();

        try {
            cmmLegCode.setLegCd(LEGACY_SYSTEM_CODE);
            cmmLegCode.setInsUserid(userDto.getUserid());
            cmmLegCode.setUpdUserid(userDto.getUserid());
            jsonData = codeService.deleteCmmCode(cmmLegCode);
        } catch (Exception e) {
            e.printStackTrace();
            jsonData.setErrMsg(e.getMessage());
        }

        if (log.isDebugEnabled()) {
            log.debug("jsonData = " + jsonData);
        }
        return jsonData;
    }
}
