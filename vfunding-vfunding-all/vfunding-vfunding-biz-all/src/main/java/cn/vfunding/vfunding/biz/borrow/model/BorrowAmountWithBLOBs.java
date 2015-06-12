package cn.vfunding.vfunding.biz.borrow.model;

public class BorrowAmountWithBLOBs extends BorrowAmount {
    private String content;

    private String remark;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}