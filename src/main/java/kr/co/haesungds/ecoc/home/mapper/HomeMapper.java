package kr.co.haesungds.ecoc.home.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface HomeMapper {
    Map<String, Object> getP1612WorkCount(Map<String, Object> paramMap);
    Map<String, Object> getP1613WorkCount(Map<String, Object> paramMap);
    Map<String, Object> getP1614WorkCount(Map<String, Object> paramMap);
}
