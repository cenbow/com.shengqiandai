package cn.vfunding.vfunding.biz.sinaResend.model;

public class SinaResendLog {
    private Integer id;

    private String outTradeNo;

    private String outPayNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo == null ? null : outTradeNo.trim();
    }

    public String getOutPayNo() {
        return outPayNo;
    }

    public void setOutPayNo(String outPayNo) {
        this.outPayNo = outPayNo == null ? null : outPayNo.trim();
    }
}