package kr.co.haesungds.security.mapper;

import kr.co.haesungds.ecoc.admin.model.RoleUser;
import kr.co.haesungds.security.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface UserMapper {
    public Optional<UserDto> loadUserByUsername(Map<String, Object> paramMap);

    List<RoleUser> selectUserRoleList(Map<String, Object> paramMap);
    List<RoleUser> selectUserPrivilegesList(Map<String, Object> paramMap);
}
