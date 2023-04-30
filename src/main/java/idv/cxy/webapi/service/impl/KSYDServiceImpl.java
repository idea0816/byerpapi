package idv.cxy.webapi.service.impl;

import idv.cxy.webapi.dao.KSYDDao;
import idv.cxy.webapi.dao.PGZLDao;
import idv.cxy.webapi.model.*;
import idv.cxy.webapi.service.KSYDService;
import idv.cxy.webapi.service.timeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author CXY
 * @version Create Time: 2022/9/13
 * @Description 庫存作業
 * <p>
 * 取得入出庫單表頭
 * 領料分配到訂單並回寫LBY_ERP
 */

@Component
public class KSYDServiceImpl implements KSYDService {

    @Resource
    private KSYDDao ksydDao;
    @Resource
    private PGZLDao pgzlDao;
    @Resource
    private timeService timeService;

    // 取得入出庫單表頭
    @Override
    public List<KSYD> getKSYD(String StarDate, String EndDate) {
        String transSD = timeService.date2String(StarDate);
        String transED = timeService.date2String(EndDate);
        List<KSYD> tranDate = ksydDao.getKSYD(transSD, transED);
        if (tranDate.size() != 0) {
            for (KSYD value : tranDate) {
                value.setKSRQ(timeService.forWebDate(value.getKSRQ()));
            }
        }
        return tranDate;
    }

    //領料分配到訂單並回寫LBY_ERP
    @Override
    public void setAllocateDDZL(String selectYM) {
        // 取得指定月最後一天
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(selectYM.substring(0, 4))); // Set Year
        cal.set(Calendar.MONTH, Integer.parseInt(selectYM.substring(4)));   // Set Month
        int lastDay = cal.getMinimum(Calendar.DAY_OF_MONTH);    // 當月最小值
        cal.set(Calendar.DAY_OF_MONTH, lastDay - 1);    // 當月+1月-1天=當月最後一天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDatetime = sdf.format(cal.getTime()) + " 21:00:00";
        // 取得派工單號
        String PGDATE = "D" + selectYM.substring(2) + "%";
        // 取得總雙數
        Integer sumPairs = pgzlDao.getSumPairs(PGDATE);
        // 取得本月派工中的最大訂單編號
        String getDDBH = pgzlDao.getDDBH(PGDATE);
        // 取得本月派工的訂單編號
        List<String> getDDBHs = pgzlDao.getDDBHs(PGDATE);
        // 取得領料資料(領料單號、ID) - B221100001
        List<Z3rows> getKSYDs = ksydDao.getKSYDs("B" + selectYM.substring(2) + "%");

        // 計算領料分配並寫入ERP資料庫(KSDH:ksyds.getXxx(), ID:ksyds.getYyy())
        for (Z3rows ksyds : getKSYDs) {
            // 差異數 = 取得材料實際領出數量小計 - 取得材料分配訂單數量小計(在最大訂單中做修正)
            List<Z2rows> variancenSL1s = ksydDao.getVariancenSL1s(PGDATE, sumPairs, ksyds.getXxx());
            // 取得領料數量分配到訂單的資料
            List<Z3rows> allocateDDZL = ksydDao.getAllocateDDZL(PGDATE, sumPairs, ksyds.getXxx());

            // 修正上傳數字
            // 物編：variancen.getXxx(), 差異數：variancen.getYyy()
            for (Z2rows variancen : variancenSL1s) {
                if (variancen.getYyy() != 0) {
                    // 物編：allocate.getXxx(), 訂單編號：allocate.getYyy(), 分配數字：allocate.getZzz()
                    for (Z3rows allocate : allocateDDZL) {
                        if (variancen.getXxx().equals(allocate.getXxx()) && allocate.getYyy().equals(getDDBH)) {
                            Double newZZZ = Double.valueOf(allocate.getZzz()) + Double.valueOf(variancen.getYyy());
                            allocate.setZzz(Double.valueOf(new java.text.DecimalFormat("###.##").format(newZZZ)));
                        } else {
                            allocate.setZzz(Double.valueOf(new java.text.DecimalFormat("###.##").format(allocate.getZzz())));
                        }
                    }
                }
            }

            for (String ddbh : getDDBHs) {
                // Get Lastest LLNO of KCLL
                String getLLNO = ksydDao.getVersion(selectYM + "%"); // 20230104235
                String newLLNO = selectYM + String.format("%05d", Integer.parseInt(getLLNO.substring(6)) + 1);

                // set KCLL & Write to SQL
                KCLL insKCLL = new KCLL();
                insKCLL.setLLNO(newLLNO);
                insKCLL.setGSBH("B07V");
                if (ksyds.getYyy().equals("05E00377")) {
                    insKCLL.setCKBH("7VB");
                } else {
                    insKCLL.setCKBH("7VA");
                }
                insKCLL.setSCBH("RRRRRRRRR");
                insKCLL.setDepID("A1AF01V");
                insKCLL.setUSERDATE(lastDatetime);
                insKCLL.setUSERID(ksyds.getYyy());
                insKCLL.setCFMDate(lastDatetime);
                insKCLL.setCFMID(ksyds.getYyy());
                insKCLL.setYN("5");
                insKCLL.setYN_Date(lastDatetime);
                ksydDao.insKCLL(insKCLL);

                // set KCLLS & Write to SQL
                KCLLS insKCLLS = new KCLLS();
                insKCLLS.setLLNO(newLLNO);
                // 物編：allocate.getXxx(), 訂單編號：allocate.getYyy(), 分配數字：allocate.getZzz()
                for (Z3rows allocate : allocateDDZL) {
                    if (ddbh.equals(allocate.getYyy())) {
                        insKCLLS.setCLBH(allocate.getXxx());
                        insKCLLS.setDFL("N");
                        insKCLLS.setSCBH(allocate.getYyy());
                        insKCLLS.setTempQty(allocate.getZzz());
                        insKCLLS.setQty(allocate.getZzz());
                        insKCLLS.setCostID("621");
                        insKCLLS.setUSERDATE(lastDatetime);
                        insKCLLS.setUSERID(ksyds.getYyy());
                        insKCLLS.setYN("1");
                        insKCLLS.setHGLB("HD");
//                        System.out.println(insKCLLS.getLLNO()+","+insKCLLS.getCLBH()+","+insKCLLS.getSCBH()+","+insKCLLS.getTempQty()+","+insKCLLS.getQty()+","+insKCLLS.getUSERDATE());
                        ksydDao.insKCLLS(insKCLLS);
                    }
                }

            }
        }

    }
}


