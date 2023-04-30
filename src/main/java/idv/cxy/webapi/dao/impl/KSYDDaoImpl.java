package idv.cxy.webapi.dao.impl;

import idv.cxy.webapi.dao.KSYDDao;
import idv.cxy.webapi.mapper.KSYDRowMapper;
import idv.cxy.webapi.mapper.Z2rowsRowMapper;
import idv.cxy.webapi.mapper.Z3rowsRowMapper;
import idv.cxy.webapi.model.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author CXY
 * @version Create Time: 2022/9/13
 * @Description 庫存作業
 * <p>
 * 取得入出庫單表頭
 * 取得材料實際領出數量小計(CLDH, sum(SL1))
 * 取得材料分配訂單數量小計(CLDH, sum(SL1))
 * 差異數 = 取得材料實際領出數量小計 - 取得材料分配訂單數量小計
 * 取得領料數量分配到訂單的資料
 * Get Lastest LLNO of KCLL
 * 取得領料表頭資料(領料單號、ID of KSYD)
 * 寫入領料表頭-KCLL
 * 寫入領料表身-KCLLS
 */

@Component
public class KSYDDaoImpl implements KSYDDao {

    // LBY_DD
    @Resource
    private NamedParameterJdbcTemplate lbyddJdbcTemplate;
    Map<String, Object> map;

    // 取得入出庫單表頭
    @Override
    @SuppressWarnings("unchecked")
    public List<KSYD> getKSYD(String StarDate, String EndDate) {
        String sqlgetKSYD = "SELECT DGLB, CQDH, KSDH, KSRQ, LYLB, LYDH, BZ, USERID, USERDATE, ZSDH " +
                "FROM KSYD " +
                "WHERE KSRQ BETWEEN :StarDate AND :EndDate ";
        map = new HashMap<>();
        map.put("StarDate", StarDate);
        map.put("EndDate", EndDate);

        List<KSYD> getKSYD = lbyddJdbcTemplate.query(sqlgetKSYD, map, new KSYDRowMapper());
        if (getKSYD.size() > 0) {
            return getKSYD;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    // 取得材料實際領出數量小計(CLDH, sum(SL1))
    @Override
    public List<Z2rows> getRealSL1s() {
        String sqlgetRealSL1s = "SELECT CLDH AS XXX, SL1 AS YYY " +
                "FROM KSYDS " +
                "WHERE KSDH = 'B221200002' " +
                "ORDER BY CLDH ";
        List<Z2rows> getRealSL1s = lbyddJdbcTemplate.query(sqlgetRealSL1s, map, new Z2rowsRowMapper());
        if (getRealSL1s.size() > 0) {
            return getRealSL1s;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    // 取得材料分配訂單數量小計(CLDH, sum(SL1))
    @Override
    public List<Z2rows> getAllocateSL1s() {
        String sqlgetAllocateSL1s = "SELECT Temp.CLDH AS XXX, SUM(Temp.SL1) AS YYY " +
                "FROM ( " +
                "SELECT REPLACE(KSYDS.cldh,'A','') AS CLDH, PGZLS.cldh AS DDBH, CAST(ROUND( KSYDS.SL1 * (DDZL.Pairs/462323),2)AS NUMERIC(7,2)) AS SL1 " +
                "FROM KSYDS KSYDS,PGZLS PGZLS, DDZL DDZL " +
                "WHERE PGZLS.PGDATE LIKE 'D2212%' AND DDZL.DDBH = PGZLS.cldh AND KSYDS.KSDH = 'B221200002' " +
                "GROUP BY PGZLS.cldh,DDZL.Pairs, KSYDS.cldh, KSYDS.SL1 " +
                ") Temp " +
                "GROUP BY Temp.CLDH " +
                "ORDER BY Temp.CLDH ";
        List<Z2rows> getAllocateSL1s = lbyddJdbcTemplate.query(sqlgetAllocateSL1s, map, new Z2rowsRowMapper());
        if (getAllocateSL1s.size() > 0) {
            return getAllocateSL1s;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    // 差異數 = 取得材料實際領出數量小計 - 取得材料分配訂單數量小計
    @Override
    public List<Z2rows> getVariancenSL1s(String PGDATE, Integer sumPairs, String KSDH) {
        String sqlgetVariancenSL1s = "SELECT Temp.CLDH AS XXX, (Temp2.SL1 - SUM(Temp.SL1)) AS YYY " +
                "FROM " +
                "(SELECT REPLACE(KSYDS.cldh,'A','') AS CLDH, PGZLS.cldh AS DDBH, CAST(ROUND( KSYDS.SL1 * (DDZL.Pairs/:sumPairs),2)AS NUMERIC(7,2)) AS SL1 " +
                "FROM KSYDS KSYDS,PGZLS PGZLS, DDZL DDZL " +
                "WHERE PGZLS.PGDATE LIKE :PGDATE " +
                "AND DDZL.DDBH = PGZLS.cldh " +
                "AND KSYDS.KSDH = :KSDH " +
                "GROUP BY PGZLS.cldh,DDZL.Pairs, KSYDS.cldh, KSYDS.SL1" +
                ") Temp " +
                "LEFT JOIN " +
                "(SELECT REPLACE(KSYDS.cldh,'A','') AS CLDH, SL1 FROM KSYDS WHERE KSDH = :KSDH) Temp2 " +
                "ON Temp.CLDH = Temp2.CLDH " +
                "GROUP BY Temp.CLDH, Temp2.SL1 " +
                "ORDER BY Temp.CLDH ";
        map = new HashMap<>();
        map.put("PGDATE", PGDATE);
        map.put("sumPairs", sumPairs);
        map.put("KSDH", KSDH);
        List<Z2rows> getVariancenSL1s = lbyddJdbcTemplate.query(sqlgetVariancenSL1s, map, new Z2rowsRowMapper());
        if (getVariancenSL1s.size() > 0) {
            return getVariancenSL1s;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    // 取得領料數量分配到訂單的資料
    @Override
    public List<Z3rows> getAllocateDDZL(String PGDATE, Integer sumPairs, String KSDH) {
        String sqlgetAllocateDDZL = "SELECT replace(KSYDS.cldh,'A','') AS XXX, PGZLS.cldh AS YYY, cast(round( KSYDS.SL1 * (DDZL.Pairs/:sumPairs),2)as numeric(7,2)) AS ZZZ " +
                "FROM KSYDS,PGZLS, DDZL " +
                "WHERE PGZLS.PGDATE like :PGDATE " +
                "AND DDZL.DDBH = PGZLS.cldh " +
                "AND KSYDS.KSDH = :KSDH " +
                "GROUP BY PGZLS.cldh,DDZL.Pairs, KSYDS.cldh, KSYDS.SL1 " +
                "ORDER BY PGZLS.cldh, KSYDS.cldh ";
        map = new HashMap<>();
        map.put("PGDATE", PGDATE);
        map.put("sumPairs", sumPairs);
        map.put("KSDH", KSDH);
        List<Z3rows> getAllocateDDZL = lbyddJdbcTemplate.query(sqlgetAllocateDDZL, map, new Z3rowsRowMapper());
        if (getAllocateDDZL.size() > 0) {
            return getAllocateDDZL;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    // Get Lastest LLNO of KCLL
    @Override
    public String getVersion(String LLNO) {
        String selGetVersion = "SELECT TOP 1 LLNO FROM LBY_ERP.dbo.KCLL " +
                "WHERE LLNO LIKE :LLNO " +
                "ORDER BY LLNO DESC";
        map = new HashMap<>();
        map.put("LLNO", LLNO);
        // 只查一个欄位資料的 RowMapper 寫法 // Lambda 寫法
        RowMapper<String> rowMapper = (rs, rowNum) -> rs.getString("LLNO");
        return (lbyddJdbcTemplate.query(selGetVersion, map, rowMapper)).get(0);
    }

    // 取得領料表頭資料(領料單號、ID of KSYD)
    @Override
    public List<Z3rows> getKSYDs(String KSDH) {
        String sqlGetKSYDs = "SELECT KSDH AS XXX, USERID AS YYY, 0.0 AS ZZZ " +
                "FROM KSYD " +
                "WHERE KSDH LIKE :KSDH ";
        map = new HashMap<>();
        map.put("KSDH", KSDH);
        List<Z3rows> getKSYDs = lbyddJdbcTemplate.query(sqlGetKSYDs, map, new Z3rowsRowMapper());
        if (getKSYDs.size() > 0) {
            return getKSYDs;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    // 寫入領料表頭-KCLL
    @Override
    public void insKCLL(KCLL insKCLL) {
        String sqlinsKCLL = "INSERT INTO LBY_ERP.dbo.KCLL " +
                "VALUES (:LLNO, :GSBH, :CKBH, :SCBH, :DepID, :USERDATE, :USERID, :CFMDate, :CFMID, :YN, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, :YN_Date) ";
        map = new HashMap<>();
        map.put("LLNO", insKCLL.getLLNO());
        map.put("GSBH", insKCLL.getGSBH());
        map.put("CKBH", insKCLL.getCKBH());
        map.put("SCBH", insKCLL.getSCBH());
        map.put("DepID", insKCLL.getDepID());
        map.put("USERDATE", insKCLL.getUSERDATE());
        map.put("USERID", insKCLL.getUSERID());
        map.put("CFMDate", insKCLL.getCFMDate());
        map.put("CFMID", insKCLL.getCFMID());
        map.put("YN", insKCLL.getYN());
        map.put("YN_Date", insKCLL.getYN_Date());
        lbyddJdbcTemplate.update(sqlinsKCLL, map);
    }

    // 寫入領料表身-KCLLS
    @Override
    public void insKCLLS(KCLLS insKCLLS) {
        String sqlinsKCLLS = "INSERT INTO LBY_ERP.dbo.KCLLS " +
                "VALUES (:LLNO, :CLBH, :DFL, :SCBH, :TempQty, :Qty, null, null, null, :CostID, :USERDATE, :USERID, :YN, null, null, null, null, null, null, null, :HGLB, null, null, null, null, null, null, null) ";
        map = new HashMap<>();
        map.put("LLNO", insKCLLS.getLLNO());
        map.put("CLBH", insKCLLS.getCLBH());
        map.put("DFL", insKCLLS.getDFL());
        map.put("SCBH", insKCLLS.getSCBH());
        map.put("TempQty", insKCLLS.getTempQty());
        map.put("Qty", insKCLLS.getQty());
        map.put("CostID", insKCLLS.getCostID());
        map.put("USERDATE", insKCLLS.getUSERDATE());
        map.put("USERID", insKCLLS.getUSERID());
        map.put("YN", insKCLLS.getYN());
        map.put("HGLB", insKCLLS.getHGLB());
        lbyddJdbcTemplate.update(sqlinsKCLLS, map);
    }
}
