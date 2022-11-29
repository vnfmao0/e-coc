package kr.co.haesungds.ecoc.admin.service;

import kr.co.haesungds.ecoc.admin.model.RoleUser;
import kr.co.haesungds.utils.JsonData;

import java.util.List;
import java.util.Map;

public interface AdminService {
    JsonData selectUserList(Map<String, Object> paramMap);
    JsonData selectRoleInfoByUid(Map<String, Object> paramMap);
    JsonData updateUserRoleInfo(List<RoleUser> roleUserList, String userid);
}
