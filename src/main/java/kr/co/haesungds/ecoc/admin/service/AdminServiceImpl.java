package kr.co.haesungds.ecoc.admin.service;

import kr.co.haesungds.ecoc.admin.mapper.AdminMapper;
import kr.co.haesungds.ecoc.admin.model.RoleUser;
import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.utils.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Override
    public JsonData selectUserList(Map<String, Object> paramMap) {
        List<UserDto> userList = adminMapper.selectUserList(paramMap);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", userList.size() > 0 ? "S" : "F");
        jsonData.addFields("userList", userList);

        return jsonData;
    }

    @Override
    public JsonData selectRoleInfoByUid(Map<String, Object> paramMap) {
        List<RoleUser> roleUserList = adminMapper.selectRoleInfoByUid(paramMap);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", "S");
        jsonData.addFields("roleUserList", roleUserList);

        return jsonData;
    }

    @Override
    public JsonData updateUserRoleInfo(List<RoleUser> roleUserList, String userid) {

        int ret = 0;
        for (RoleUser roleUser : roleUserList) {
            roleUser.setInsUserid(userid);
            roleUser.setUpdUserid(userid);
            ret += adminMapper.updateUserRoleInfo(roleUser);
        }

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", roleUserList.size() == ret ? "S" : "F");

        return jsonData;
    }
}
