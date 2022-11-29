package kr.co.haesungds.ecoc.admin.service;

import kr.co.haesungds.ecoc.admin.model.DocManage;
import kr.co.haesungds.utils.JsonData;

import java.util.List;
import java.util.Map;

public interface DocManageService {
    List<DocManage> selectDocList(Map<String, Object> paramMap);
    JsonData insertDocManage(Map<String, Object> paramMap);
}
