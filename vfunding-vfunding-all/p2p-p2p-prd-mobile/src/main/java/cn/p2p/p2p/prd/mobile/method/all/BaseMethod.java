package cn.p2p.p2p.prd.mobile.method.all;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.baseinfo.model.IndexParam;
import cn.p2p.p2p.biz.baseinfo.service.IIndexParamService;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.vfunding.vfunding.biz.system.model.Article;
import cn.vfunding.vfunding.biz.system.model.ScrollpicMobile;
import cn.vfunding.vfunding.biz.system.service.IArticleService;
import cn.vfunding.vfunding.biz.system.service.IScrollpicService;

@Service
public class BaseMethod {

	@Autowired
	private IScrollpicService scrollPicService;

	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IIndexParamService indexParamService;


	/**
	 * 首页banner查询
	 * 
	 * @return
	 */
	public MobileBaseResponse selectScrollpic() {
		List<ScrollpicMobile> list = null;
		// 首页banner
		list = scrollPicService.selectScrollPicByTypeMobile(2); // TODO
		return new MobileBaseResponse(list);
	}

	/**
	 * 首页公告查询
	 * 
	 * @return
	 */
	public MobileBaseResponse selectArticleBulletin() {
		List<Article> list = new ArrayList<Article>();
		// 喇叭旁边的条数
		List<Article> reports = articleService.selectArticleByStatusCount(95, 1, 0, 1);
		// 首页媒体文章2条
		List<Article> meidas = articleService.selectArticleByStatusCount(85, 1, 0, 2);
		list.addAll(reports);
		list.addAll(meidas);
		return new MobileBaseResponse(list);
	}


	public MobileBaseResponse selectParam() {
		IndexParam indexParam = this.indexParamService.selectByPrimaryKey(1);
		return new MobileBaseResponse(indexParam);
	}
}
