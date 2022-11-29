package kr.co.haesungds.utils;

import org.springframework.jdbc.support.JdbcUtils;

import java.util.HashMap;

public class LowerHashMap extends HashMap{
    private static final long serialVersionUID = -1961964931705631703L;

    @Override
    public Object put(Object key, Object value) {
        return super.put(JdbcUtils.convertUnderscoreNameToPropertyName((String) key), value);
    }
}
