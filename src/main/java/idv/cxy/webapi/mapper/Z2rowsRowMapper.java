package idv.cxy.webapi.mapper;

import idv.cxy.webapi.model.Z2rows;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2023/1/31
 * @Description String, Double 2Rows資料
 */
public class Z2rowsRowMapper implements RowMapper<Z2rows> {
    @Override
    public Z2rows mapRow(ResultSet rs, int rowNum) throws SQLException {
        Z2rows z2rows = new Z2rows();
        z2rows.setXxx(rs.getString("XXX"));
        z2rows.setYyy(rs.getDouble("YYY"));
        return z2rows;
    }
}
