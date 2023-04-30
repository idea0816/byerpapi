package idv.cxy.webapi.model;

/**
 * @author CXY
 * @version Create Time: 2022/9/12
 * @Description 入出庫作業表頭
 */
public class KSYD {
    private String DGLB;    // 單據類別
    private String CQDH;
    private String KSDH;    // 異動單號
    private String KSRQ;    // 異動日期
    private String LYLB;    // 來源類別
    private String LYDH;    // 來源單號
    private String BZ;  // 備註
    private String USERID;
    private String USERDATE;
    private String ZSDH;

    public String getDGLB() {
        return DGLB;
    }

    public void setDGLB(String DGLB) {
        this.DGLB = DGLB;
    }

    public String getCQDH() {
        return CQDH;
    }

    public void setCQDH(String CQDH) {
        this.CQDH = CQDH;
    }

    public String getKSDH() {
        return KSDH;
    }

    public void setKSDH(String KSDH) {
        this.KSDH = KSDH;
    }

    public String getKSRQ() {
        return KSRQ;
    }

    public void setKSRQ(String KSRQ) {
        this.KSRQ = KSRQ;
    }

    public String getLYLB() {
        return LYLB;
    }

    public void setLYLB(String LYLB) {
        this.LYLB = LYLB;
    }

    public String getLYDH() {
        return LYDH;
    }

    public void setLYDH(String LYDH) {
        this.LYDH = LYDH;
    }

    public String getBZ() {
        return BZ;
    }

    public void setBZ(String BZ) {
        this.BZ = BZ;
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getUSERDATE() {
        return USERDATE;
    }

    public void setUSERDATE(String USERDATE) {
        this.USERDATE = USERDATE;
    }

    public String getZSDH() {
        return ZSDH;
    }

    public void setZSDH(String ZSDH) {
        this.ZSDH = ZSDH;
    }
}
