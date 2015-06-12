package cn.vfunding.vfunding.biz.system.model;

@SuppressWarnings("serial")
public class PaymentWithBLOBs extends Payment {
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