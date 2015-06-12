package cn.vfunding.common.framework.utils.http;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class RestInvoker implements IServiceInvoker {

	private static final String CONTENT_TYPE = "application/json";

	private String baseURL;

	private CloseableHttpClient httpClient;

	public RestInvoker() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(2000);
		connectionManager.setDefaultMaxPerRoute(200);
		this.httpClient = HttpClients.custom()
				.setConnectionManager(connectionManager).build();
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String path, Class<T> cls) {
		List<NameValuePair> params = null;
		String text = this.get(path, params);
		T obj = null;
		if (text == null || text.equals(""))
			return null;

		if (cls.equals(String.class)) {
			obj = (T) text;
		} else {
			obj = JSON.parseObject(text, cls);
		}
		return obj;
	}

	@Override
	public <T> List<T> getList(String path, Class<T> cls) {
		List<NameValuePair> parameters = null;
		String text = this.get(path, parameters);
		List<T> obj = JSON.parseArray(text, cls);
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(String path, List<NameValuePair> parameters, Class<T> cls) {
		String text = this.get(path, parameters);
		T obj = null;
		if (cls.equals(String.class)) {
			obj = (T) text;
		} else {
			obj = JSON.parseObject(text, cls);
		}
		return obj;
	}

	@Override
	public <T> List<T> getList(String path, List<NameValuePair> parameters,
			Class<T> cls) {
		String text = this.get(path, parameters);

		List<T> obj = JSON.parseArray(text, cls);
		return obj;
	}

	public String get(String path, List<NameValuePair> parameters) {
		if (parameters != null && parameters.size() > 0) {
			String queryParamPart = URLEncodedUtils.format(parameters, "UTF-8");
			path = path + "?" + queryParamPart;
		}
		HttpGet get = new HttpGet(buildPath(path));
		CloseableHttpResponse response = null;
		try {

			get.addHeader("Content-Type", CONTENT_TYPE);
			response = httpClient.execute(get);

			if (response.getEntity() == null)
				return null;
			String responseText = EntityUtils.toString(response.getEntity(),
					"UTF-8");

			if (response.getStatusLine().getStatusCode() == 404) {
				System.out.println(responseText);
				throw new RuntimeException("path not found");
			}

			return responseText;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
	}

	@Override
	public String post(String path, Object obj) {
		String text = "";
		if (obj != null) {
			if (obj instanceof String) {
				text = (String) obj;
			} else {
				text = JSON.toJSONString(obj);
			}
		}
		HttpPost post = new HttpPost(buildPath(path));
		CloseableHttpResponse response = null;
		try {
			post.addHeader("Content-Type", CONTENT_TYPE);

			StringEntity entityTemplate = new StringEntity(text, "UTF-8");
			entityTemplate.setContentEncoding("UTF-8");
			post.setEntity(entityTemplate);
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return "";
			}
			return EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
	}

	@Override
	public String post(String path, List<NameValuePair> nvps) {
		HttpPost post = new HttpPost(buildPath(path));
		CloseableHttpResponse response = null;
		try {
			if (nvps != null) {
				post.setEntity(new UrlEncodedFormEntity(nvps));
			}
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return "";
			}
			return EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
	}

	@Override
	public String postFiles(String path, MultipartEntityBuilder multipartEntity) {
		HttpPost filePost = new HttpPost(buildPath(path));
		CloseableHttpResponse response = null;
		try {
			// 以浏览器兼容模式运行，防止文件名乱码。
			filePost.setEntity(multipartEntity
					.setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
					.setCharset(CharsetUtils.get("UTF-8")).build());
			response = httpClient.execute(filePost);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return "";
			}
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == 200 || statusCode == 204) {
				return EntityUtils.toString(entity, "UTF-8");
			} else {
				String errMsg = "请求错误:" + response.getStatusLine() + " "
						+ EntityUtils.toString(entity, "UTF-8");
				logger.error(errMsg);
				throw new RuntimeException(errMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			HttpClientUtils.closeQuietly(response);
		}

	}

	@Override
	public String put(String path, Object obj) {
		String text = "";
		if (obj != null) {
			if (obj instanceof String) {
				text = (String) obj;
			} else {
				// text = JsonUtil.serializeToJson(obj);
				text = JSON.toJSONString(obj);
			}

		}

		return put(path, text);
	}

	@Override
	public String put(String path, String content) {
		HttpPut put = new HttpPut(buildPath(path));
		CloseableHttpResponse response = null;
		try {
			// put.addHeader("Accept", accept);
			put.addHeader("Content-Type", CONTENT_TYPE);
			StringEntity entityTemplate = new StringEntity(content == null ? ""
					: content, CONTENT_TYPE);
			entityTemplate.setContentEncoding("UTF-8");
			put.setEntity(entityTemplate);
			response = httpClient.execute(put);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return "";
			}
			return EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
	}

	@Override
	public String delete(String path) {
		return this.delete(path, null);
	}

	@Override
	public String delete(String path, List<NameValuePair> parameters) {
		CloseableHttpResponse response = null;
		try {
			// delete.addHeader("Accept", accept);
			// delete.addHeader("Content-Type", CONTENT_TYPE);
			if (parameters != null && parameters.size() > 0) {
				String queryParamPart = URLEncodedUtils.format(parameters,
						"UTF-8");
				path = path + "?" + queryParamPart;
			}

			HttpDelete delete = new HttpDelete(buildPath(path));

			response = httpClient.execute(delete);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return "";
			}
			return EntityUtils.toString(entity, "UTF-8");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		} finally {
			HttpClientUtils.closeQuietly(response);
		}
	}

	private String buildPath(String path) {
		String result = baseURL;
		if (baseURL.endsWith("/")) {
			if (!path.startsWith("/") && !path.startsWith("?")) {
				result = baseURL + path;
			} else {
				result = baseURL.substring(0, baseURL.lastIndexOf("/")) + path;
			}
		} else {
			if (!path.startsWith("/") && !path.startsWith("?")) {
				result = baseURL + "/" + path;
			} else {
				result = baseURL + path;
			}
		}
		return result;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	 
	

}
