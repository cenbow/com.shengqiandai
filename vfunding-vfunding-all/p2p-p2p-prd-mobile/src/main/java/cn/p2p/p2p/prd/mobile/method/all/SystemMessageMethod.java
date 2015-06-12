package cn.p2p.p2p.prd.mobile.method.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.PageUtilVO;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;
import cn.vfunding.vfunding.biz.message.service.ISystemMessageService;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.system.service.IMessageService;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

import com.alibaba.druid.util.StringUtils;

@Service
public class SystemMessageMethod {

	@Autowired
	private IUserTokenService userTokenService;

	@Autowired
	private ISystemMessageService systemMessageService;

	@Autowired
	private IMessageService messageService;

	/**
	 * 加载系统消息列表
	 * 
	 * @param pageSearch
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse systemMessageList(PageUtilVO pageUtil) {
		if (pageUtil.getPage() == null || pageUtil.getPage() < 1)
			return new MobileBaseResponse("page_fail", "页数不可小于1");
		Integer userId = this.userTokenService.selectUserIdByToken(pageUtil.getToken());
		PageSearch ps = new PageSearch();
		SystemMessage sm = new SystemMessage();
		sm.setReceiveUser(userId);
		ps.setEntity(sm);
		ps.setPage(pageUtil.getPage());
		ps.setRows(pageUtil.getRows());
		List<SystemMessage> smList = this.systemMessageService.selectListPage(ps);
		if (smList.isEmpty())
			return new MobileBaseResponse("systemMessageList_null", "没有数据");
		else
			return new MobileBaseResponse(smList);
	}

	/**
	 * 系统消息内容详情
	 * 
	 * @param id
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse systemMessageDetail(GeneralRequestVO generalRequest) {
		if (generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("systemMessageId_fail", "系统消息编号不正确");
		SystemMessage sm = this.systemMessageService.selectByPrimaryKey(generalRequest.getId());
		if (sm == null)
			return new MobileBaseResponse("systemMessageId_fail", "系统消息编号不正确");
		sm.setIsLook(1);
		this.systemMessageService.updateByPrimaryKey(sm);
		return new MobileBaseResponse(sm);
	}

	/**
	 * 删除系统消息
	 * @param ids
	 * @return
	 *
	 * jianwei
	 */
	@CheckToken
	public MobileBaseResponse deleteSystemMessage(GeneralRequestVO generalRequest) {
		if(StringUtils.isEmpty(generalRequest.getSystemMessageIds()))
			return new MobileBaseResponse("systemMessageIds_null_fail","站内信编号不可为空");
		String [] ids = generalRequest.getSystemMessageIds().split(",");
		systemMessageService.deleteSystemMessages(ids);
		return new MobileBaseResponse();
	}
	
	
	/**
	 * 加载站内信数据
	 * 
	 * @param isLook
	 * @param pageSearch
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse messageList(PageUtilVO pageUtil) {
		if (pageUtil.getPage() == null || pageUtil.getPage() < 1)
			return new MobileBaseResponse("page_fail", "页数不可小于1");
		Integer userId = this.userTokenService.selectUserIdByToken(pageUtil.getToken());
		PageSearch ps = new PageSearch();
		Message m = new Message();
		m.setReceiveUser(userId);
		ps.setEntity(m);
		List<Message> messages = this.messageService.selectMessageByUserListPage(ps);
		if (messages.isEmpty())
			return new MobileBaseResponse("messageList_null", "没有数据");
		else
			return new MobileBaseResponse(messages);
	}

	/**
	 * 站内信内容详情
	 * 
	 * @param id
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse messageDetail(GeneralRequestVO generalRequest) {
		if (generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("messageId_fail", "站内信编号不正确");
		Message m = this.messageService.selectByPrimaryKey(generalRequest.getId());
		if (m == null)
			return new MobileBaseResponse("messageId_fail", "站内信编号不正确");
		m.setStatus(1);
		this.messageService.updateByPrimaryKey(m);
		return new MobileBaseResponse(m);
	}

	
	/**
	 * 删除站内信
	 * @param ids
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse deleteMessage(GeneralRequestVO generalRequest){
		if(StringUtils.isEmpty(generalRequest.getMessageIds()))
			return new MobileBaseResponse("messageIds_null_fail","站内信编号不可为空");
		String [] ids = generalRequest.getMessageIds().split(",");
		this.messageService.deleteMessage(ids);
		return new MobileBaseResponse();
	}
}
