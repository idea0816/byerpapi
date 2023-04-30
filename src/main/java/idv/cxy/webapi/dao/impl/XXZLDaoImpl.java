package idv.cxy.webapi.dao.impl;

import idv.cxy.webapi.dao.XXZLDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2023/4/11
 * @Description 型体資料
 *
 * 取得ARTICLEs
 */
@Component
public class XXZLDaoImpl implements XXZLDao {
    // LBY_DD
    @Resource
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 取得ARTICLEs
    @Override
    public List<String> getARTICLEs() {
        String sqlGetARTICLE = "SELECT ARTICLE FROM XXZL WHERE CQDH = 'B7U' GROUP BY ARTICLE";
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("ARTICLE");
        return lbyddJdbcTemplate.query(sqlGetARTICLE, map, rowMapper);
    }
}
