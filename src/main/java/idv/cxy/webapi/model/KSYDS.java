package idv.cxy.webapi.model;

/**
 * @author CXY
 * @version Create Time: 2022/9/12
 * @Description 入出庫作業表身
 */
public class KSYDS {
    private String DGLB;    // 單據類別
    private String KSDH;    // 異動單號
    private String CQDH;
    private String SH;  // 序號
    private String CLDH;    // 材料代號
    private String MSBZ;    // 明細備註
    private Double SL;  // 入庫數量
    private Double DJ;
    private Double GR;
    private Double SL1; // 出庫數量
    private Double GR1;
    private String NY;  // 年月
    private String USERID;
    private String USERDATE;

    public String getDGLB() {
        return DGLB;
    }

    public void setDGLB(String DGLB) {
        this.DGLB = DGLB;
    }

    public String getKSDH() {
        return KSDH;
    }

    public void setKSDH(String KSDH) {
        this.KSDH = KSDH;
    }

    public String getCQDH() {
        return CQDH;
    }

    public void setCQDH(String CQDH) {
        this.CQDH = CQDH;
    }

    public String getSH() {
        return SH;
    }

    public void setSH(String SH) {
        this.SH = SH;
    }

    public String getCLDH() {
        return CLDH;
    }

    public void setCLDH(String CLDH) {
        this.CLDH = CLDH;
    }

    public String getMSBZ() {
        return MSBZ;
    }

    public void setMSBZ(String MSBZ) {
        this.MSBZ = MSBZ;
    }

    public Double getSL() {
        return SL;
    }

    public void setSL(Double SL) {
        this.SL = SL;
    }

    public Double getDJ() {
        return DJ;
    }

    public void setDJ(Double DJ) {
        this.DJ = DJ;
    }

    public Double getGR() {
        return GR;
    }

    public void setGR(Double GR) {
        this.GR = GR;
    }

    public Double getSL1() {
        return SL1;
    }

    public void setSL1(Double SL1) {
        this.SL1 = SL1;
    }

    public Double getGR1() {
        return GR1;
    }

    public void setGR1(Double GR1) {
        this.GR1 = GR1;
    }

    public String getNY() {
        return NY;
    }

    public void setNY(String NY) {
        this.NY = NY;
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
}
