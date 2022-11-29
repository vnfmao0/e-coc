package kr.co.haesungds.utils.file.mapper;

import kr.co.haesungds.utils.file.model.EccFile;
import kr.co.haesungds.utils.file.model.HsqFile;
import kr.co.haesungds.utils.file.model.StwFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface HsqFileMapper {
    int getMaxFileSort(Map<String, Object> paramMap);
    int insertFileInfo(HsqFile dto);
    int updateFileInfo(HsqFile fileInfo);
    EccFile selectFileInfoByFId(@Param("fileSeq")String fileSeq);
    List<Map<String, Object>> selectFileListByBbsSeq(Map<String, Object> paramMap);
    List<StwFile> selectFileListByBbsSeq2(String bbsSeq);
    int deleteFileById(Map<String, Object> paramMap);
    int deleteFileByFileSeq(long fileSeq);

}
