package idv.cxy.webapi.model;

/**
 * @author CXY
 * @version Create Time: 2023/1/31
 * @Description ERP領料明細-KCLLS
 */
public class KCLLS {
    private String LLNO;    // 出材料單號
    private String CLBH;    // 材料編號
    private String DFL; // 大分類
    private String SCBH;    // 制令號
    private Double TempQty; // 開單數量
    private Double Qty; // 數量
    private String CostID;  // 科目
    private String USERDATE;    // 最後異動日
    private String USERID;  // 用戶編號
    private String YN;  // 識別
    private String HGLB;    // NK、KD、HD、GC、XT倉庫分類

    public String getLLNO() {
        return LLNO;
    }

    public void setLLNO(String LLNO) {
        this.LLNO = LLNO;
    }

    public String getCLBH() {
        return CLBH;
    }

    public void setCLBH(String CLBH) {
        this.CLBH = CLBH;
    }

    public String getDFL() {
        return DFL;
    }

    public void setDFL(String DFL) {
        this.DFL = DFL;
    }

    public String getSCBH() {
        return SCBH;
    }

    public void setSCBH(String SCBH) {
        this.SCBH = SCBH;
    }

    public Double getTempQty() {
        return TempQty;
    }

    public void setTempQty(Double tempQty) {
        TempQty = tempQty;
    }

    public Double getQty() {
        return Qty;
    }

    public void setQty(Double qty) {
        Qty = qty;
    }

    public String getCostID() {
        return CostID;
    }

    public void setCostID(String costID) {
        CostID = costID;
    }

    public String getUSERDATE() {
        return USERDATE;
    }

    public void setUSERDATE(String USERDATE) {
        this.USERDATE = USERDATE;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getYN() {
        return YN;
    }

    public void setYN(String YN) {
        this.YN = YN;
    }

    public String getHGLB() {
        return HGLB;
    }

    public void setHGLB(String HGLB) {
        this.HGLB = HGLB;
    }
}
