package cn.vfunding.vfunding.biz.sina.model;

public class SinaSendLogWithBLOBs extends SinaSendLog {
    private String args;

    private String responseContent;

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args == null ? null : args.trim();
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent == null ? null : responseContent.trim();
    }
}