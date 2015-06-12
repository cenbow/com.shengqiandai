package cn.vfunding.vfunding.biz.account.model;
@SuppressWarnings("serial")
public class AccountPaymentWithBLOBs extends AccountPayment {
    private String config;

    private String description;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config == null ? null : config.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}