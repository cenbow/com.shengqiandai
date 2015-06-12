package cn.p2p.p2p.prd.mobile.method.request.vo;


public class PageUtilVO extends GeneralRequestVO {
		//分页查询
		private Integer page = 1;
		private Integer rows = 10;
		public Integer getPage() {
			return page;
		}
		public void setPage(Integer page) {
			this.page = page;
		}
		public Integer getRows() {
			return rows;
		}
		public void setRows(Integer rows) {
			this.rows = rows;
		}
		
		
}
