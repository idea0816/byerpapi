package idv.cxy.webapi.dao.impl;

import idv.cxy.webapi.dao.PGZLDao;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2023/1/31
 * @Description 派工資料
 * <p>
 * 取得當月派工總雙數
 * 取得本月派工中的最大訂單編號
 * 取得本月派工的訂單編號
 */
@Component
public class PGZLDaoImpl implements PGZLDao {
    // LBY_DD
    @Resource
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;

    Map<String, Object> map;

    // 取得當月派工總雙數
    @Override
    public Integer getSumPairs(String PGDATE) {
        String sqlgetSumPairs = "SELECT SUM(Temp.Pairs) AS sumPairs " +
                "FROM (SELECT PGZLS.cldh,DDZL.Pairs FROM PGZLS PGZLS, DDZL DDZL WHERE PGZLS.PGDATE LIKE :PGDATE AND DDZL.DDBH = PGZLS.cldh GROUP BY PGZLS.cldh,DDZL.Pairs) Temp";
        map = new HashMap<>();
        map.put("PGDATE", PGDATE);
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<Integer> rowMapper = (rs, rowNum) -> rs.getInt("sumPairs");
        return (lbyddJdbcTemplate.query(sqlgetSumPairs, map, rowMapper)).get(0);
    }

    // 取得本月派工中的最大訂單編號
    @Override
    public String getDDBH(String PGDATE) {
        String sqlgetDDBH = "SELECT Top 1 PGZLS.cldh " +
                "FROM PGZLS, DDZL " +
                "WHERE PGZLS.PGDATE LIKE :PGDATE AND DDZL.DDBH = PGZLS.cldh " +
                "GROUP BY PGZLS.cldh,DDZL.Pairs " +
                "ORDER BY DDZL.Pairs DESC ";
        map = new HashMap<>();
        map.put("PGDATE", PGDATE);
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("cldh");
        return (lbyddJdbcTemplate.query(sqlgetDDBH, map, rowMapper)).get(0);
    }

    // 取得本月派工的訂單編號
    @Override
    public List<String> getDDBHs(String PGDATE) {
        String sqlgetDDBHs = "SELECT PGZLS.cldh " +
                "FROM PGZLS, DDZL " +
                "WHERE PGZLS.PGDATE LIKE :PGDATE AND DDZL.DDBH = PGZLS.cldh " +
                "GROUP BY PGZLS.cldh,DDZL.Pairs " +
                "ORDER BY DDZL.Pairs DESC ";
        map = new HashMap<>();
        map.put("PGDATE", PGDATE);
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("cldh");
        return lbyddJdbcTemplate.query(sqlgetDDBHs, map, rowMapper);
    }
}
