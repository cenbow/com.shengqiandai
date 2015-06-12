package cn.p2p.p2p.prd.mobile.cache;

public interface IFilterUnlawfulRequest {

	boolean filterRequest(Integer userId, String password);
}
