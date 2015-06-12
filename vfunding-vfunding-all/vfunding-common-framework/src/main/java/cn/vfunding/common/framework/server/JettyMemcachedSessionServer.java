package cn.vfunding.common.framework.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.eclipse.jetty.nosql.memcached.MemcachedSessionIdManager;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class JettyMemcachedSessionServer extends Server {
	
	
	private static final int defaultMaxInactiveInterval = 60*30;
	
	public JettyMemcachedSessionServer(String workerName,String memcacheAddress) throws IOException {
		super();
		build(workerName,memcacheAddress);
	}

	public JettyMemcachedSessionServer(String workerName,String memcacheAddress,InetSocketAddress addr) throws IOException {
		super(addr);
		build(workerName,memcacheAddress);
	}

	public JettyMemcachedSessionServer(String workerName,String memcacheAddress,int port) throws IOException {
		super(port);
		build(workerName,memcacheAddress);
	}

	private void build(String workerName,String memcacheAddress) throws IOException{
		MemcachedSessionIdManager idMgr = new MemcachedSessionIdManager(this,memcacheAddress); //默认父类使用 127.0.0.1:11211 连接
		idMgr.setWorkerName(workerName); // 这个变量会加到jsessionId的头尾，应该是避免不同的worker造成id重复
		this.setSessionIdManager(idMgr);
	}

	@Override
	protected void doStart() throws Exception {
		Handler[]  handlers = this.getHandlers();
		for(Handler handler : handlers){
			if(handler instanceof ServletContextHandler){
				MyMemcachedSessionManager msm = new MyMemcachedSessionManager();
				msm.setMaxInactiveInterval(defaultMaxInactiveInterval); //默认3 hours，将会被WEB.xml中的session-timeout覆盖
				SessionHandler sessionHandler = new SessionHandler();
				sessionHandler.setSessionManager(msm);				
				((ServletContextHandler)handler).setSessionHandler(sessionHandler);
			}
		}
		super.doStart();
	}

	
}
