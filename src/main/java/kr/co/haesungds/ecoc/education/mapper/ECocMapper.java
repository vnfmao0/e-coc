package kr.co.haesungds.ecoc.education.mapper;

import kr.co.haesungds.ecoc.education.model.CmmLegCode;
import kr.co.haesungds.ecoc.education.model.ECocInfo;
import kr.co.haesungds.utils.file.model.EccFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ECocMapper {

    List<CmmLegCode> selectCmmCodeList(Map<String, Object> paramMap);

    List<ECocInfo> selectEducationList(Map<String, Object> paramMap);

    ECocInfo selectEducationInfo(Map<String, Object> paramMap);

    int educationRegistProc(ECocInfo eCocInfo);
    int educationModifyProc(ECocInfo eCocInfo);
    int educationDeleteProc(Map<String, Object> paramMap);

    EccFile selectEducationFileInfo(Map<String, Object> paramMap);

    int checkUseridProc(Map<String, Object> paramMap);
    Map<String, Object> checkUseridPwProc(Map<String, Object> paramMap);

}
