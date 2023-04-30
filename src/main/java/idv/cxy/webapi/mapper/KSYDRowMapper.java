package idv.cxy.webapi.mapper;

import idv.cxy.webapi.model.KSYD;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author CXY
 * @version Create Time: 2022/9/13
 * @Description 入出庫作業表頭
 */
public class KSYDRowMapper implements RowMapper<KSYD> {
    @Override
    public KSYD mapRow(ResultSet rs, int rowNum) throws SQLException {
        KSYD KSYD = new KSYD();
        KSYD.setDGLB(rs.getString("DGLB"));    // 單據類別
        KSYD.setCQDH(rs.getString("CQDH"));
        KSYD.setKSDH(rs.getString("KSDH"));    // 異動單號
        KSYD.setKSRQ(rs.getString("KSRQ"));    // 異動日期
        KSYD.setLYLB(rs.getString("LYLB"));    // 來源類別
        KSYD.setLYDH(rs.getString("LYDH"));    // 來源單號
        KSYD.setBZ(rs.getString("BZ"));  // 備註
        KSYD.setUSERID(rs.getString("USERID"));
        KSYD.setUSERDATE(rs.getString("USERDATE"));
        KSYD.setZSDH(rs.getString("ZSDH"));

        return KSYD;
    }
}
