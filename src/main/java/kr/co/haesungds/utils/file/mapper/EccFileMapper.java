package kr.co.haesungds.utils.file.mapper;

import kr.co.haesungds.utils.file.model.EccFile;
import kr.co.haesungds.utils.file.model.StwFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EccFileMapper {
    int insertFileInfo(EccFile eccFile);
    EccFile selectFileInfoByFId(String fileSeq);
    List<EccFile> selectFileListByParentId(Map<String, Object> paramMap);
    int deleteFileById(Map<String, Object> paramMap);
    int deleteFileByParentId(String parentId);
}
