package kr.co.haesungds.ecoc.admin.mapper;

import kr.co.haesungds.ecoc.admin.model.DocManage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DocManagerMapper {
    List<DocManage> selectDocList(Map<String, Object> paramMap);
    int insertDocManage(Map<String, Object> paramMap);
}
