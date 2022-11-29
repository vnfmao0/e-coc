package kr.co.haesungds.ecoc.admin.service;

import kr.co.haesungds.ecoc.admin.model.CmmLegCode;
import kr.co.haesungds.utils.JsonData;

import java.util.List;
import java.util.Map;

public interface CmmLegCodeService {
    //공통코드
    List<CmmLegCode> selectCmmCodeList(Map<String, Object> paramMap);
    List<CmmLegCode> selectCmmLegCodeProLv2List(Map<String, Object> paramMap);
    JsonData insertCmmCode(CmmLegCode cmmLegCode);
    JsonData updateCmmCode(CmmLegCode cmmLegCode);
    JsonData applyCmmLegCode(Map<String, Object> paramMap);
    JsonData deleteCmmCode(CmmLegCode cmmLegCode);
}
