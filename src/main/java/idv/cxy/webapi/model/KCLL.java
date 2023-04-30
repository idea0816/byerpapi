package idv.cxy.webapi.model;

/**
 * @author CXY
 * @version Create Time: 2023/1/31
 * @Description ERP領料主檔-KCLL
 */
public class KCLL {
    private String LLNO;    // 出材料單號
    private String GSBH;    // 公司編號
    private String CKBH;    // 倉庫編號
    private String SCBH;    // 制令號	Z補料單.W膠水領料.C紙箱領料.X總務領料.R化學品領料.D樣品領料
    private String DepID;   // 部門編號
    private String USERDATE;    // 最後異動日
    private String USERID;  // 用戶編號
    private String CFMDate; // 確認日期
    private String CFMID;   // 確認人員
    private String YN;  // 識別	5為財務已確認
    private String YN_Date;

    public String getLLNO() {
        return LLNO;
    }

    public void setLLNO(String LLNO) {
        this.LLNO = LLNO;
    }

    public String getGSBH() {
        return GSBH;
    }

    public void setGSBH(String GSBH) {
        this.GSBH = GSBH;
    }

    public String getCKBH() {
        return CKBH;
    }

    public void setCKBH(String CKBH) {
        this.CKBH = CKBH;
    }

    public String getSCBH() {
        return SCBH;
    }

    public void setSCBH(String SCBH) {
        this.SCBH = SCBH;
    }

    public String getDepID() {
        return DepID;
    }

    public void setDepID(String depID) {
        DepID = depID;
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

    public String getCFMDate() {
        return CFMDate;
    }

    public void setCFMDate(String CFMDate) {
        this.CFMDate = CFMDate;
    }

    public String getCFMID() {
        return CFMID;
    }

    public void setCFMID(String CFMID) {
        this.CFMID = CFMID;
    }

    public String getYN() {
        return YN;
    }

    public void setYN(String YN) {
        this.YN = YN;
    }

    public String getYN_Date() {
        return YN_Date;
    }

    public void setYN_Date(String YN_Date) {
        this.YN_Date = YN_Date;
    }
}
