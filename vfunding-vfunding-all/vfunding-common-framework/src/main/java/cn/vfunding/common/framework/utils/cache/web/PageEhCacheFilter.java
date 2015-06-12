package cn.vfunding.common.framework.utils.cache.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PageEhCacheFilter extends SimplePageCachingFilter {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private final static String FILTER_URL_PATTERNS = "patterns";
	private static String[] cacheURLs;
	public static List<String> cacheURLList = new ArrayList<String>();

	private void init() throws CacheException {
		String patterns = filterConfig.getInitParameter(FILTER_URL_PATTERNS);
		// cacheURLs = StringUtils.split(patterns, ",");
		cacheURLs = patterns.split(",");
		for (String url : cacheURLs) {
			cacheURLList.add(url);
		}
	}

	@Override
	protected void doFilter(final HttpServletRequest request,
			final HttpServletResponse response, final FilterChain chain)
			throws AlreadyGzippedException, AlreadyCommittedException,
			FilterNonReentrantException, LockTimeoutException, Exception {
		if (cacheURLList == null || cacheURLList.size() < 1) {
			init();
		}

		String url = request.getRequestURI();
		boolean flag = false;
		if (cacheURLList != null && cacheURLList.size() > 0) {
			for (String cacheURL : cacheURLList) {
				// if (url.contains(cacheURL.trim())) {
				System.out.println(cacheURL);
				if (url.equals(cacheURL.trim())) {
					flag = true;
					break;
				}
			}
		}
		// 如果包含我们要缓存的url 就缓存该页面，否则执行正常的页面转向
		if (flag) {
			String query = request.getQueryString();
			if (query != null) {
				query = "?" + query;
			}
			logger.info("当前请求被缓存：" + url + query);
			super.doFilter(request, response, chain);
		} else {
			chain.doFilter(request, response);
		}
	}

	private boolean headerContains(final HttpServletRequest request,
			final String header, final String value) {
		logRequestHeaders(request);
		final Enumeration<String> accepted = request.getHeaders(header);
		while (accepted.hasMoreElements()) {
			final String headerValue = (String) accepted.nextElement();
			if (headerValue.indexOf(value) != -1) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @see net.sf.ehcache.constructs.web.filter.Filter#acceptsGzipEncoding(javax.servlet.http.HttpServletRequest)
	 *      <b>function:</b> 兼容ie6/7 gzip压缩
	 * @author hoojo
	 * @createDate 2012-7-4 上午11:07:11
	 */
	@Override
	protected boolean acceptsGzipEncoding(HttpServletRequest request) {
		boolean ie6 = headerContains(request, "User-Agent", "MSIE 6.0");
		boolean ie7 = headerContains(request, "User-Agent", "MSIE 7.0");
		return acceptsEncoding(request, "gzip") || ie6 || ie7;
	}

	public static String getCacheKey(String url) {
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("GET").append(url).append("null");
		String key = stringBuffer.toString();
		return key;
	}

}
