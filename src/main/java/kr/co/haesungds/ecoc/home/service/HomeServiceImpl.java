package kr.co.haesungds.ecoc.home.service;

import kr.co.haesungds.ecoc.home.mapper.HomeMapper;
import kr.co.haesungds.utils.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService {
    private final HomeMapper homeMapper;

    @Override
    public JsonData getP1612WorkCount(Map<String, Object> paramMap) {
        Map<String, Object> workCount = homeMapper.getP1612WorkCount(paramMap);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", workCount != null ? "S" : "F");
        jsonData.addFields("workCount", workCount);

        return jsonData;
    }

    @Override
    public JsonData getP1613WorkCount(Map<String, Object> paramMap) {
        Map<String, Object> workCount = homeMapper.getP1613WorkCount(paramMap);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", workCount != null ? "S" : "F");
        jsonData.addFields("workCount", workCount);

        return jsonData;
    }

    @Override
    public JsonData getP1614WorkCount(Map<String, Object> paramMap) {
        Map<String, Object> workCount = homeMapper.getP1614WorkCount(paramMap);

        JsonData jsonData = new JsonData();
        jsonData.setStatus("SUCC");
        jsonData.addFields("RESULT_CD", workCount != null ? "S" : "F");
        jsonData.addFields("workCount", workCount);

        return jsonData;
    }
}
