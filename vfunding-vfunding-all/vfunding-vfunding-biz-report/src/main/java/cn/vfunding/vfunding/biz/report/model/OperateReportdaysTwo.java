package cn.vfunding.vfunding.biz.report.model;

public class OperateReportdaysTwo extends OperateReportdays {
	private Long ordinaryusersday;

	private Long internalusersday;

	private Double ordinaryuserssumaccount;

	private Long tenderordinaryuserscount;

	private Double internaluserssumaccount;

	private Long tenderinternaluserscount;

	private Double repaymentsumyesaccount;

	private Double tendersumaccount;

	private Double repaymentsumyesaccountmonth;

	private Long borrowsumcountmonth;

	private Long borrowcountmonth;

	private Long borrowcountday;

	private Double borrowsumaccountmonth;

	private Double borrowsumaccount;

	public Long getOrdinaryusersday() {
		if(ordinaryusersday==null){
			ordinaryusersday=0l;
		}
		return ordinaryusersday;
	}

	public void setOrdinaryusersday(Long ordinaryusersday) {
		this.ordinaryusersday = ordinaryusersday;
	}

	public Long getInternalusersday() {
		if(internalusersday==null){
			internalusersday=0l;
		}
		return internalusersday;
	}

	public void setInternalusersday(Long internalusersday) {
		this.internalusersday = internalusersday;
	}

	public Double getOrdinaryuserssumaccount() {
		if (ordinaryuserssumaccount == null) {
			ordinaryuserssumaccount = 0d;
		}
		return ordinaryuserssumaccount;
	}

	public void setOrdinaryuserssumaccount(Double ordinaryuserssumaccount) {
		this.ordinaryuserssumaccount = ordinaryuserssumaccount;
	}

	public Long getTenderordinaryuserscount() {
		if(tenderordinaryuserscount==null){
			tenderordinaryuserscount=0l;
		}
		return tenderordinaryuserscount;
	}

	public void setTenderordinaryuserscount(Long tenderordinaryuserscount) {
		this.tenderordinaryuserscount = tenderordinaryuserscount;
	}

	public Double getInternaluserssumaccount() {
		if(internaluserssumaccount==null){
			internaluserssumaccount=0d;
		}
		return internaluserssumaccount;
	}

	public void setInternaluserssumaccount(Double internaluserssumaccount) {
		this.internaluserssumaccount = internaluserssumaccount;
	}

	public Long getTenderinternaluserscount() {
		if(tenderinternaluserscount==null){
			tenderinternaluserscount=0l;
		}
		return tenderinternaluserscount;
	}

	public void setTenderinternaluserscount(Long tenderinternaluserscount) {
		this.tenderinternaluserscount = tenderinternaluserscount;
	}

	public Double getRepaymentsumyesaccount() {
		if(repaymentsumyesaccount==null){
			repaymentsumyesaccount=0d;
		}
		return repaymentsumyesaccount;
	}

	public void setRepaymentsumyesaccount(Double repaymentsumyesaccount) {
		this.repaymentsumyesaccount = repaymentsumyesaccount;
	}

	public Double getTendersumaccount() {
		if(tendersumaccount==null){
			tendersumaccount=0d;
		}
		return tendersumaccount;
	}

	public void setTendersumaccount(Double tendersumaccount) {
		this.tendersumaccount = tendersumaccount;
	}

	public Double getRepaymentsumyesaccountmonth() {
		if(repaymentsumyesaccountmonth==null){
			repaymentsumyesaccountmonth=0d;
		}
		return repaymentsumyesaccountmonth;
	}

	public void setRepaymentsumyesaccountmonth(
			Double repaymentsumyesaccountmonth) {
		this.repaymentsumyesaccountmonth = repaymentsumyesaccountmonth;
	}

	public Long getBorrowsumcountmonth() {
		if(borrowsumcountmonth==null){
			borrowsumcountmonth=0l;
		}
		return borrowsumcountmonth;
	}

	public void setBorrowsumcountmonth(Long borrowsumcountmonth) {
		this.borrowsumcountmonth = borrowsumcountmonth;
	}

	public Long getBorrowcountmonth() {
		if(borrowcountmonth==null){
			borrowcountmonth=0l;
		}
		return borrowcountmonth;
	}

	public void setBorrowcountmonth(Long borrowcountmonth) {
		this.borrowcountmonth = borrowcountmonth;
	}

	public Long getBorrowcountday() {
		if(borrowcountday==null){
			borrowcountday=0l;
		}
		return borrowcountday;
	}

	public void setBorrowcountday(Long borrowcountday) {
		this.borrowcountday = borrowcountday;
	}

	public Double getBorrowsumaccountmonth() {
		if(borrowsumaccountmonth==null){
			borrowsumaccountmonth=0d;
		}
		return borrowsumaccountmonth;
	}

	public void setBorrowsumaccountmonth(Double borrowsumaccountmonth) {
		this.borrowsumaccountmonth = borrowsumaccountmonth;
	}

	public Double getBorrowsumaccount() {
		if(borrowsumaccount==null){
			borrowsumaccount=0d;
		}
		return borrowsumaccount;
	}

	public void setBorrowsumaccount(Double borrowsumaccount) {
		this.borrowsumaccount = borrowsumaccount;
	}

}