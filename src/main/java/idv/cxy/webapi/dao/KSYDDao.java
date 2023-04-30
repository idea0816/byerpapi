package idv.cxy.webapi.dao;

import idv.cxy.webapi.model.*;

import java.util.List;

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
public interface KSYDDao {
    // 取得入出庫單表頭
    List<KSYD> getKSYD(String StarDate, String EndDate);

    // 取得材料實際領出數量小計(CLDH, sum(SL1))
    List<Z2rows> getRealSL1s();

    // 取得材料分配訂單數量小計(CLDH, sum(SL1))
    List<Z2rows> getAllocateSL1s();

    // 差異數 = 取得材料實際領出數量小計 - 取得材料分配訂單數量小計
    List<Z2rows> getVariancenSL1s(String PGDATE, Integer sumPairs, String KSDH);

    // 取得領料數量分配到訂單的資料
    List<Z3rows> getAllocateDDZL(String PGDATE, Integer sumPairs, String KSDH);

    // Get Lastest LLNO of KCLL
    String getVersion(String LLNO);

    // 取得領料表頭資料(領料單號、ID of KSYD)
    List<Z3rows> getKSYDs(String KSDH);

    // 寫入領料表頭-KCLL
    void insKCLL(KCLL insKCLL);

    // 寫入領料表身-KCLLS
    void insKCLLS(KCLLS insKCLLS);
}
