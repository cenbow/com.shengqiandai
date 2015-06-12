package cn.vfunding.common.framework.server;

import org.eclipse.jetty.nosql.kvs.session.ISerializableSession;
import org.eclipse.jetty.nosql.kvs.session.TranscoderException;
import org.eclipse.jetty.nosql.memcached.MemcachedSessionManager;

public class MyMemcachedSessionManager extends MemcachedSessionManager{

	@Override
	protected ISerializableSession getKey(String idInCluster)
			throws TranscoderException {
		try{
			return super.getKey(idInCluster);
		}catch(Exception e){
			//org.eclipse.jetty.nosql.kvs.session.TranscoderException: java.io.StreamCorruptedException: invalid stream header: 775F6D69
			/**
			 * 仍然返回对象，但不能被反序列化，则认为已过期，memcache的默认数据
			 */
			return null;
		}
	}

}
