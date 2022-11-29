package kr.co.haesungds.ecoc.admin.service;


import kr.co.haesungds.ecoc.admin.mapper.DocManagerMapper;
import kr.co.haesungds.ecoc.admin.model.DocManage;
import kr.co.haesungds.utils.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class DocManageServiceImpl implements DocManageService {
    private final DocManagerMapper docManagerMapper;

    @Override
    public List<DocManage> selectDocList(Map<String, Object> paramMap) {
        return docManagerMapper.selectDocList(paramMap);
    }

    @Override
    public JsonData insertDocManage(Map<String, Object> paramMap) {
        JsonData jsonData = new JsonData();
        int ret = docManagerMapper.insertDocManage(paramMap);

        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", ret > 0 ? "S" : "F");
        jsonData.addFields("DOC_ID", paramMap.get("docId"));

        return jsonData;
    }
}
