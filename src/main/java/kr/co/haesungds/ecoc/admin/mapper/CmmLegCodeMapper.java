package kr.co.haesungds.ecoc.admin.mapper;

import kr.co.haesungds.ecoc.admin.model.CmmLegCode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CmmLegCodeMapper {
    //공통코드
    List<CmmLegCode> selectCmmCodeList(Map<String, Object> paramMap);
    List<CmmLegCode> selectCmmLegCodeProLv2List(Map<String, Object> paramMap);
    int insertCmmCode(CmmLegCode cmmLegCode);
    int updateCmmCode(CmmLegCode cmmLegCode);
    int insertCmmLegCode(CmmLegCode cmmLegCode);
    int updateCmmLegCode(CmmLegCode cmmLegCode);
    int deleteCmmLegCode(CmmLegCode cmmLegCode);
    int deleteCmmCode(CmmLegCode cmmLegCode);
}
