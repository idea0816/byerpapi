package idv.cxy.webapi.mapper;

import idv.cxy.webapi.model.Z2rows;
import idv.cxy.webapi.model.Z3rows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2023/1/31
 * @Description String, String, Double 3Rows資料
 */
public class Z3rowsRowMapper implements RowMapper<Z3rows> {
    @Override
    public Z3rows mapRow(ResultSet rs, int rowNum) throws SQLException {
        Z3rows z3rows = new Z3rows();
        z3rows.setXxx(rs.getString("XXX"));
        z3rows.setYyy(rs.getString("YYY"));
        z3rows.setZzz(rs.getDouble("ZZZ"));
        return z3rows;
    }
}
