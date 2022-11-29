package kr.co.haesungds.ecoc.admin.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.haesungds.ecoc.admin.mapper.CmmLegCodeMapper;
import kr.co.haesungds.ecoc.admin.model.CmmLegCode;
import kr.co.haesungds.utils.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CmmLegCodeServiceImpl implements CmmLegCodeService {

    private final CmmLegCodeMapper codeMapper;

    //공통코드
    @Override
    public List<CmmLegCode> selectCmmCodeList(Map<String, Object> paramMap) {
        return codeMapper.selectCmmCodeList(paramMap);
    }

    @Override
    public List<CmmLegCode> selectCmmLegCodeProLv2List(Map<String, Object> paramMap) {
        return codeMapper.selectCmmLegCodeProLv2List(paramMap);
    }

    @Override
    public JsonData insertCmmCode(CmmLegCode cmmLegCode) {
        int ret = codeMapper.insertCmmCode(cmmLegCode);
        log.debug("ret :: " + ret);
        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", "S");

        return jsonData;
    }

    @Override
    public JsonData updateCmmCode(CmmLegCode cmmLegCode) {
        int ret = codeMapper.updateCmmCode(cmmLegCode);
        log.debug("ret :: " + ret);
        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", ret == 1 ? "S" : "F");

        return jsonData;
    }

    @Override
    public JsonData applyCmmLegCode(Map<String, Object> paramMap) {

        boolean insSucc = true;
        if(paramMap.get("insertedRows") != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Object> insertedRows = objectMapper.convertValue(paramMap.get("insertedRows"), ArrayList.class);

            int insRet = 0;
            for (int i = 0; i < insertedRows.size(); i++) {
                CmmLegCode cmmLegCode = objectMapper.convertValue(insertedRows.get(i), CmmLegCode.class);

                log.debug(cmmLegCode.toString());
                cmmLegCode.setLegCd(String.valueOf(paramMap.get("legCd")));
                cmmLegCode.setInsUserid(String.valueOf(paramMap.get("insUserid")));

                insRet += codeMapper.insertCmmLegCode(cmmLegCode);
            }
            if(insertedRows.size() != insRet) insSucc = false;
        }

        boolean updSucc = true;
        if(paramMap.get("updatedRows") != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Object> updatedRows = objectMapper.convertValue(paramMap.get("updatedRows"), ArrayList.class);

            int updRet = 0;
            for (int i = 0; i < updatedRows.size(); i++) {
                CmmLegCode cmmLegCode = objectMapper.convertValue(updatedRows.get(i), CmmLegCode.class);

                log.debug(cmmLegCode.toString());
                cmmLegCode.setLegCd(String.valueOf(paramMap.get("legCd")));
                cmmLegCode.setUpdUserid(String.valueOf(paramMap.get("updUserid")));

                updRet += codeMapper.updateCmmLegCode(cmmLegCode);
            }
            if(updatedRows.size() != updRet) updSucc = false;
        }

        boolean delSucc = true;
        if(paramMap.get("deletedRows") != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Object> deletedRows = objectMapper.convertValue(paramMap.get("deletedRows"), ArrayList.class);

            int delRet = 0;
            for(int i = 0; i < deletedRows.size(); i++) {
                CmmLegCode cmmLegCode = objectMapper.convertValue(deletedRows.get(i), CmmLegCode.class);

                log.debug(cmmLegCode.toString());
                cmmLegCode.setLegCd(String.valueOf(paramMap.get("legCd")));
                cmmLegCode.setUpdUserid(String.valueOf(paramMap.get("updUserid")));

                delRet += codeMapper.deleteCmmLegCode(cmmLegCode);
            }
            if(deletedRows.size() != delRet) delSucc = false;
        }

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", insSucc && updSucc && delSucc ? "S" : "F");

        StringBuilder sbErrMsg = new StringBuilder();
        sbErrMsg.append("처리 결과:").append("\n");
        if (insSucc == false || updSucc == false || delSucc == false) {
            sbErrMsg.append(insSucc ? "등록 성공." : "등록 일부 실패").append("\n");
            sbErrMsg.append(updSucc ? "수정 성공." : "수정 일부 실패").append("\n");
            sbErrMsg.append(delSucc ? "삭제 성공." : "삭제 일부 실패").append("\n");

            jsonData.setErrMsg(sbErrMsg.toString());
        }

        return jsonData;
    }

    @Override
    public JsonData deleteCmmCode(CmmLegCode cmmLegCode) {
        int ret = codeMapper.deleteCmmCode(cmmLegCode);
        log.debug("ret :: " + ret);
        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", ret == 1 ? "S" : "F");

        return jsonData;
    }
}
