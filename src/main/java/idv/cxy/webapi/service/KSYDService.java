package idv.cxy.webapi.service;

import idv.cxy.webapi.model.KSYD;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/9/13
 * @Description 庫存作業
 * <p>
 * 取得入出庫單表頭
 * 領料分配到訂單並回寫LBY_ERP
 */


public interface KSYDService {

    // 取得入出庫單表頭
    List<KSYD> getKSYD(String StarDate, String EndDate);

    // 領料分配到訂單並回寫LBY_ERP
    void setAllocateDDZL(String selectYM);

}
