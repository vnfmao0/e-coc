package kr.co.haesungds.ecoc.admin.mapper;

import kr.co.haesungds.ecoc.admin.model.RoleUser;
import kr.co.haesungds.security.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMapper {
    List<UserDto> selectUserList(Map<String, Object> paramMap);
    List<RoleUser> selectRoleInfoByUid(Map<String, Object> paramMap);
    int updateUserRoleInfo(RoleUser roleUser);
}
