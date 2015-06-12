package cn.vfunding.vfunding.prd.bms.sinadeposit.syn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendActionLogService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.User2Sinamember;
import cn.vfunding.vfunding.biz.user.service.IUserService;

public class MemberCheck implements Callable<Result>  {
	static Logger log = LoggerFactory.getLogger("sinaSendActionLog");
	//private static String outputFile = "MemberCheck.csv";
	private static int rows = 200;
	private static int index = 0;
	public synchronized static int getIndex() {

		return ++index;
	}
	ISinaMemberManagerService sinaMemberManagerService;

	IUserService userService;
	
	IQuerySinaService querySinaService;
	
	ISinaSendActionLogService sinaSendActionLogService;

	private List<User2Sinamember> users;

	private MemberCheck() {
	}

	public MemberCheck(ISinaMemberManagerService sinaMemberManagerService,
			IUserService userService, IQuerySinaService querySinaService) {
		super();
		this.sinaMemberManagerService = sinaMemberManagerService;
		this.userService = userService;
		this.querySinaService = querySinaService;
	}
	
	public MemberCheck(ISinaMemberManagerService sinaMemberManagerService,
			IUserService userService, IQuerySinaService querySinaService,
			ISinaSendActionLogService sinaSendActionLogService) {
		super();
		this.sinaMemberManagerService = sinaMemberManagerService;
		this.userService = userService;
		this.querySinaService = querySinaService;
		this.sinaSendActionLogService = sinaSendActionLogService;
	}

	public Result call() {
		queryUser();
		LinkedHashMap<String, HashMap<String, SynResult>> map = new LinkedHashMap<String,HashMap<String,SynResult>>();
		HashMap<String, SynResult> propertyMap  = null;
		List<String> titlelist = null;
		String userId = null;
		for (User user : users) {
			propertyMap = new HashMap<String,SynResult>();
			titlelist = new ArrayList<String>();
			userId = user.getUserId() + "";

			titlelist.add("user_id");
			propertyMap.put("user_id", new SynResult( true , userId));
			
			
			//创建激活会员
			BaseSyn createActivateMemberSyn = new CreateActivateMemberSyn(sinaMemberManagerService, user ,sinaSendActionLogService);
			titlelist.add(createActivateMemberSyn.operationName());
			
			BaseSyn setRealNameSyn = new SetRealNameSyn(sinaMemberManagerService, user ,sinaSendActionLogService);
			titlelist.add(setRealNameSyn.operationName());
			
			BaseSyn queryVerifySyn = new QueryVerifySyn(sinaMemberManagerService, user ,sinaSendActionLogService);
			titlelist.add(queryVerifySyn.operationName());
			
			createActivateMemberSyn.setSuccessor(setRealNameSyn , queryVerifySyn);
			
			BaseSyn querySinaMoney = new QuerySinaMoneySyn(user, sinaMemberManagerService, querySinaService ,sinaSendActionLogService);
			setRealNameSyn.setSuccessor(querySinaMoney);
			
			
			propertyMap.putAll(createActivateMemberSyn.doHandler());
			
			
			map.put(user.getUserId().toString(), propertyMap);
		}
		//map2csv(titlelist , map);
		return new Result( !(users.size() == rows) , Thread.currentThread().getName());
	}

	private void queryUser() {
		PageSearch pageSearch = new PageSearch();
		pageSearch.setPage(getIndex());
		pageSearch.setRows(rows);
		pageSearch.setOrder("user_id");
		pageSearch.setSort("asc");
		users = userService.selectAllUsersByBalanceOrRedListPage(pageSearch);
	}
	
	public static int  sheetIndex = 0;
	public static String delimiter=",";
	/*private synchronized static void map2csv(List<String> titlelist , LinkedHashMap<String, HashMap<String, SynResult>> map){
		File csv = new File(outputFile);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(csv, true));
			//bw.write(StringUtils.join(titlelist, delimiter));
			//bw.newLine();
			
			Iterator<String> it=map.keySet().iterator();
			
			while(it.hasNext()){
				String userId = it.next();
				HashMap<String, SynResult> propertyMap = map.get(userId);
				List<String> list = new ArrayList<String>();
				for (String key : titlelist) {
					SynResult syn = propertyMap.get(key);
					if(syn == null){
						list.add("#####");
					}else{
						if(syn.isOk()){
							if(BaseSyn.SET_REALNAME_STATUS_EXCEPTION.equals(syn.getResult())){
								list.add("#####"+syn.getResult());
							}else{
								list.add(syn.getResult());
							}
						}else{
							list.add("#####"+syn.getResult());
						}
					}
				}
				bw.write(StringUtils.join(list, delimiter));
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			if(bw != null){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}*/
}
