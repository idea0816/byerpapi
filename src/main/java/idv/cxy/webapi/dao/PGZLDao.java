package idv.cxy.webapi.dao;

import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2023/1/30
 * @Description 派工資料
 * <p>
 * 取得當月派工總雙數
 * 取得本月派工中的最大訂單編號
 * 取得本月派工的訂單編號
 */

public interface PGZLDao {
    // 取得當月派工訂單總雙數
    Integer getSumPairs(String PGDATE);

    // 取得本月派工中的最大訂單編號
    String getDDBH(String PGDATE);

    // 取得本月派工的訂單編號
    List<String> getDDBHs(String PGDATE);
}
