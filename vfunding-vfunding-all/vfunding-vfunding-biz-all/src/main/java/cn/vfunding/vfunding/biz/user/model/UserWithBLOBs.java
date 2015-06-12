package cn.vfunding.vfunding.biz.user.model;

public class UserWithBLOBs extends User {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String remind;

    private String privacy;

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind == null ? null : remind.trim();
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy == null ? null : privacy.trim();
    }
}