package cn.vfunding.common.framework.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.vfunding.common.framework.utils.beans.VmHelper;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;

public class TestMem {
	
	//Logger logger = LoggerFactory.getLogger(this.getClass());
	private String memcacheAddress="192.168.3.11:11211";
	private MemcachedClient memcachedClient;
     
	public void init() throws Exception{
		memcachedClient = new MemcachedClient(
		AddrUtil.getAddresses(memcacheAddress));
	}

	public boolean set(String namespace, String key, int exp, Object object) throws Exception {
		try {
			CASValue<Object> prevValue = memcachedClient.gets(key);
			if (prevValue == null) {
				memcachedClient.set(key, exp, VmHelper.getInstance()
						.objectToBytes(object));
				return true;
			} else {
				CASResponse casResponse = memcachedClient
						.cas(key, prevValue.getCas(), VmHelper.getInstance()
								.objectToBytes(object));
				if (!casResponse.equals(CASResponse.OK)) {
					return false;
				} else {
					memcachedClient.set(key, exp, VmHelper.getInstance()
							.objectToBytes(object));
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("Set cache error", e);
			throw new Exception("Set cache error", e);
		}
	}
	
	public Object get(String namespace, String key) throws Exception {
		Object obj = memcachedClient.get(key);
		if(obj==null) return null;
		return VmHelper.getInstance().bytesToObject((byte[])obj);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		TestMem te=new TestMem();
		te.init();
		te.set("test", "test11", 10, "test");
		String v=(String) te.get("test", "test11");
		System.out.println(v);
	}

}
