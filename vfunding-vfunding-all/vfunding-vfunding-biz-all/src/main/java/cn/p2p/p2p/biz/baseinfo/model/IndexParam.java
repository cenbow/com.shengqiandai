package cn.p2p.p2p.biz.baseinfo.model;

import java.math.BigDecimal;
import java.util.Date;

public class IndexParam {
    private Integer id;

    private String borrowintroduces1;

    private String borrowintroduces2;

    private BigDecimal borrowapr;

    private BigDecimal borrowmoney;

    private String borrowlimit;

    private String currentintroduces1;

    private String currentintroduces2;

    private BigDecimal currentapr;

    private BigDecimal currentmoney;

    private String currentsale;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBorrowintroduces1() {
        return borrowintroduces1;
    }

    public void setBorrowintroduces1(String borrowintroduces1) {
        this.borrowintroduces1 = borrowintroduces1 == null ? null : borrowintroduces1.trim();
    }

    public String getBorrowintroduces2() {
        return borrowintroduces2;
    }

    public void setBorrowintroduces2(String borrowintroduces2) {
        this.borrowintroduces2 = borrowintroduces2 == null ? null : borrowintroduces2.trim();
    }

    public BigDecimal getBorrowapr() {
        return borrowapr;
    }

    public void setBorrowapr(BigDecimal borrowapr) {
        this.borrowapr = borrowapr;
    }

    public BigDecimal getBorrowmoney() {
        return borrowmoney;
    }

    public void setBorrowmoney(BigDecimal borrowmoney) {
        this.borrowmoney = borrowmoney;
    }

    public String getBorrowlimit() {
        return borrowlimit;
    }

    public void setBorrowlimit(String borrowlimit) {
        this.borrowlimit = borrowlimit == null ? null : borrowlimit.trim();
    }

    public String getCurrentintroduces1() {
        return currentintroduces1;
    }

    public void setCurrentintroduces1(String currentintroduces1) {
        this.currentintroduces1 = currentintroduces1 == null ? null : currentintroduces1.trim();
    }

    public String getCurrentintroduces2() {
        return currentintroduces2;
    }

    public void setCurrentintroduces2(String currentintroduces2) {
        this.currentintroduces2 = currentintroduces2 == null ? null : currentintroduces2.trim();
    }

    public BigDecimal getCurrentapr() {
        return currentapr;
    }

    public void setCurrentapr(BigDecimal currentapr) {
        this.currentapr = currentapr;
    }

    public BigDecimal getCurrentmoney() {
        return currentmoney;
    }

    public void setCurrentmoney(BigDecimal currentmoney) {
        this.currentmoney = currentmoney;
    }

	public String getCurrentsale() {
		return currentsale;
	}

	public void setCurrentsale(String currentsale) {
		this.currentsale = currentsale;
	}
}