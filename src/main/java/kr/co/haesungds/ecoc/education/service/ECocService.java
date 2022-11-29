package kr.co.haesungds.ecoc.education.service;

import kr.co.haesungds.ecoc.education.model.CmmLegCode;
import kr.co.haesungds.ecoc.education.model.ECocInfo;
import kr.co.haesungds.utils.JsonData;
import kr.co.haesungds.utils.file.model.EccFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface ECocService {
    List<CmmLegCode> selectCmmCodeList(Map<String, Object> paramMap);

    JsonData selectEducationList(Map<String, Object> paramMap);

    ECocInfo selectEducationInfo(Map<String, Object> paramMap);
    JsonData uploadQRCodeImage(Map<String, Object> paramMap);
    JsonData downloadQRCodeImage(HttpServletResponse response, Map<String, Object> paramMap);

    JsonData educationRegistProc(ECocInfo eCocInfo);
    JsonData educationModifyProc(ECocInfo eCocInfo);
    JsonData educationDeleteProc(Map<String, Object> paramMap);

    EccFile selectEducationFileInfo(Map<String, Object> paramMap);

    JsonData checkUseridProc(Map<String, Object> paramMap);
    JsonData checkUseridPwProc(Map<String, Object> paramMap);
}
