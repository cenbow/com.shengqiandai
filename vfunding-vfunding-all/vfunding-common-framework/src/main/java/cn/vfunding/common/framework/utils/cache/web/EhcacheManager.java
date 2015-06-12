package cn.vfunding.common.framework.utils.cache.web;


import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.blocking.BlockingCache;


public class EhcacheManager {

	private static CacheManager cacheManager = CacheManager.getInstance();
	public static void addCacheUrl(String url) {
		boolean extis = false;
		for (String cacheURL : PageEhCacheFilter.cacheURLList) {
			if (cacheURL.equals(url)) {
				extis = true;
				break;
			}
		}

		if (!extis) {
			PageEhCacheFilter.cacheURLList.add(url);
		}

	}

	public static void delCache(String url) {	
		BlockingCache webCache = new BlockingCache(cacheManager.getEhcache("SimplePageCachingFilter"));
		String key=PageEhCacheFilter.getCacheKey(url);
		webCache.remove(key);
	}
	
	
}
