package kr.co.haesungds.ecoc.admin.controller;

import kr.co.haesungds.ecoc.admin.model.DocManage;
import kr.co.haesungds.ecoc.admin.service.DocManageService;
import kr.co.haesungds.utils.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequiredArgsConstructor
public class DocManageController {

    private final DocManageService docManageService;

    @GetMapping("/admin/docManager")
    public String docManagerList(Model model) {

        return "thymeleaf/e-coc/admin/docManager";
    }


    //문서관리 조회
    @PostMapping("/admin/docManageSearch")
    @ResponseBody
    public JsonData docManageSearch(@RequestBody Map<String, Object> paramMap) {

        List<DocManage> docManageList = docManageService.selectDocList(paramMap);
        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", docManageList != null ? "S" : "F");
        jsonData.addFields("docManageList", docManageList);

        return jsonData;
    }

    //문서관리 등록
    @PostMapping("/admin/docManageInsertProc")
    @ResponseBody
    public JsonData docManageInsertProc(@RequestBody Map<String, Object> paramMap) {

        return docManageService.insertDocManage(paramMap);
    }

}
