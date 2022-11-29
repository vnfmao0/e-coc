package kr.co.haesungds.ecoc.admin.controller;

import kr.co.haesungds.ecoc.admin.model.RoleUser;
import kr.co.haesungds.ecoc.admin.service.AdminService;
import kr.co.haesungds.security.dto.UserDto;
import kr.co.haesungds.utils.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    @Value("${LEGACY_SYSTEM_CODE}")
    private String LEGACY_SYSTEM_CODE;

    private final AdminService adminService;

    @GetMapping("/admin/roleManage")
    public String roleManage() {
        /*
        role : 화면 접속 권한...
            USER    : 일반사용자
            MANGER  : 담당자
            P1612_MANGER  : 전산기기구매 담당자
            P1613_MANGER  : 공사입찰 담당자
            P1614_MANGER  : 부산물관리 담당자
            ADMIN   : 시스템관리자

        privileges : 화면 버튼 처리용 권한...
            COMMUNICATION_AUTHORITY : 읽기만 가능
            P1612_AUTHORITY         : 전산기기구매
            P1613_AUTHORITY         : 공사입찰
            P1614_AUTHORITY         : 부산물관리
         */

        return "thymeleaf/e-coc/admin/roleManage";
    }

    @PostMapping("/admin/selectUserList")
    @ResponseBody
    public JsonData selectUserList(@RequestBody Map<String, Object> paramMap) {
        paramMap.put("legCd", LEGACY_SYSTEM_CODE);
        JsonData jsonData = adminService.selectUserList(paramMap);

        return jsonData;
    }

    @PostMapping("/admin/selectRoleInfoByUid")
    @ResponseBody
    public JsonData selectRoleInfoByUid(@RequestBody Map<String, Object> paramMap) {
        paramMap.put("legCd", LEGACY_SYSTEM_CODE);
        JsonData jsonData = adminService.selectRoleInfoByUid(paramMap);

        return jsonData;
    }

    @PostMapping("/admin/updateUserRoleInfo")
    @ResponseBody
    public JsonData updateUserRoleInfo(@RequestBody RoleUser roleUser, @AuthenticationPrincipal UserDto userDto) {

        JsonData jsonData = adminService.updateUserRoleInfo(roleUser.getRoleList(), userDto.getUserid());

        return jsonData;
    }
}
