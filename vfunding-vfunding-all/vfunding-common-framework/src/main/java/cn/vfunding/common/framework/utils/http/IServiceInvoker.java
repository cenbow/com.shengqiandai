package cn.vfunding.common.framework.utils.http;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.entity.mime.MultipartEntityBuilder;

public interface IServiceInvoker {
	/**
	 * 根据path地址以Get的方式请求并将结果转换成T对象
	 * @param path
	 * @param cls
	 * @return
	 * @author liuyijun
	 */
	<T> T get(String path, Class<T> cls) ;
	
	/**
	 * 以Get的方式请求path地址，获取集合对象T
	 * @param path
	 * @param cls
	 * @return
	 * @author liuyijun
	 */
	<T> List<T> getList(String path, Class<T> cls) ;
	/**
	 * 根据path地址以Get的方式请求并将结果转换成T对象,支持传参
	 * @param path
	 * @param parameters 参数
	 * @param cls
	 * @return
	 * @author liuyijun
	 */
	<T> T get(String path, List<NameValuePair> parameters, Class<T> cls);
	/**
	 * 以Get的方式请求path地址，获取集合对象T,带参
	 * @param path
	 * @param parameters 参数
	 * @param cls
	 * @return
	 * @author liuyijun
	 */
	<T> List<T> getList(String path, List<NameValuePair> parameters, Class<T> cls);
	/**
	 * 提交文件到path地址
	 * @param path
	 * @param multipartEntity
	 * @return
	 * @author liuyijun
	 */
	String postFiles(String path, MultipartEntityBuilder multipartEntity);
	
	/**
	 * 将一个object对象用json序列化后以post的方式提交到path地址
	 * @param path
	 * @param obj
	 * @return
	 * @author liuyijun
	 */
	String post(String path, Object obj) ;
	/**
	 * 以Post的形式请求path地址，带NameValuePair集合形式参数
	 * @param path
	 * @param nvps List<NameValuePair>形式参数
	 * @return
	 * @author liuyijun
	 */
	String post(String path, List<NameValuePair> nvps) ;

	String put(String path, Object obj) ;
	
	String put(String path, String content) ;

	String delete(String path) ;
	
	String delete(String path, List<NameValuePair> parameters) ;

}
