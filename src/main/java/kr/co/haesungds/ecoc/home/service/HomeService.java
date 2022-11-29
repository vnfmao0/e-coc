package kr.co.haesungds.ecoc.home.service;

import kr.co.haesungds.utils.JsonData;

import java.util.Map;

public interface HomeService {
    JsonData getP1612WorkCount(Map<String, Object> paramMap);
    JsonData getP1613WorkCount(Map<String, Object> paramMap);
    JsonData getP1614WorkCount(Map<String, Object> paramMap);
}
