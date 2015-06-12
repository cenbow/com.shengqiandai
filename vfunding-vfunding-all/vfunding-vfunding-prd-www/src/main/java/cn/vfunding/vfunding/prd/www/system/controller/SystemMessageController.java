package cn.vfunding.vfunding.prd.www.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;
import cn.vfunding.vfunding.biz.message.service.ISystemMessageService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.system.service.IMessageService;

@Controller()
@RequestMapping("/sysMessage")
public class SystemMessageController {
	@Autowired
	private ISystemMessageService systemMessageService;
	
	@Autowired
	private IMessageService messageService;
	@Autowired
	private IAccountService accountService;
	
	@RequestMapping("/sysMessagePage")
	public ModelAndView systemMessagePage(Integer isLook, PageSearch pageSearch) {
		ModelAndView mv = new ModelAndView("message/systemMessage");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	@RequestMapping("/sysMessagePageAjax")
	@ResponseBody
	public Map<String, Object> systemMessagePageAjax(Integer isLook,
			PageSearch pageSearch) {
		Map<String, Object> map = new HashMap<String, Object>();
		PageResult<SystemMessage> result = new PageResult<SystemMessage>();
		SystemMessage sm = new SystemMessage();
		sm.setReceiveUser(UserSession.getLoginUserId());
		if (isLook == null) {
			sm.setIsLook(0);
		} else {
			sm.setIsLook(isLook);
		}
		pageSearch.setEntity(sm);
		List<SystemMessage> smList = systemMessageService
				.selectListPage(pageSearch);
		result.setRows(smList);
		result.setTotal(pageSearch.getTotalResult());
		// 已读数量
		sm.setIsLook(1);
		map.put("isLookCount", systemMessageService.selectIsLookCount(sm));
		// 未读数量
		sm.setIsLook(0);
		map.put("unIsLookCount",
				systemMessageService.selectIsLookCount(sm));
		map.put("isLook", isLook);
		map.put("result", result);
		map.put("messageType", "system");
		return map;
	}

	@RequestMapping("/messagePageAjax")
	@ResponseBody
	public Map<String, Object> messagePageAjax(Integer isLook, PageSearch pageSearch) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 未读站内信
		Integer noReadCount = this.messageService
				.selectNoReadCount((Integer) (UserSession.getLoginUserId()));
		if (EmptyUtil.isNotEmpty(noReadCount)) {
			map.put("unIsLookCount", noReadCount);
		}

		// 所有站内信
		Integer allCount = this.messageService
				.selectAllCount((Integer) (UserSession.getLoginUserId()));
		//已读
		Integer readCount = allCount.intValue() - noReadCount.intValue();
		if (EmptyUtil.isNotEmpty(allCount)) {
			map.put("isLookCount", readCount);
		}

		PageResult<Message> result = new PageResult<Message>();
		Message m = new Message();
		if (isLook == null) {
			m.setStatus(0);
		} else {
			m.setStatus(isLook);
		}
		m.setReceiveUser(UserSession.getLoginUserId());
		pageSearch.setEntity(m);
		List<Message> messages = this.messageService
				.selectMessageByUserListPage(pageSearch);
		result.setRows(messages);
		result.setTotal(pageSearch.getTotalResult());
		map.put("result", result);
		map.put("isLook", isLook);
		map.put("messageType", "ordinary");
		return map;
	}

	@RequestMapping("/lookSysMessage")
	@ResponseBody
	public Map<String, Integer> lookSysMessage(SystemMessage systemMessage) {
		systemMessage.setIsLook(1);
		systemMessage.setReceiveUser(UserSession.getLoginUserId());
		return systemMessageService.updateLookAndSelectCount(systemMessage);
	}
	
	/**
	 * 更新消息为已读状态
	 * 
	 * @param id
	 * @return 
	 */
	@RequestMapping("/lookMessage")
	@ResponseBody
	public Map<String, Integer> readMessage(Integer id) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		Message message = new Message();
		message.setId(id);
		message.setStatus(1);
		if (this.messageService.updateReadPrimaryKey(message) == 1) {
			// 未读站内信
			Integer noReadCount = this.messageService
					.selectNoReadCount((Integer) (UserSession.getLoginUserId()));
			if (EmptyUtil.isNotEmpty(noReadCount)) {
				map.put("unIsLookCount", noReadCount);
			}

			// 所有站内信
			Integer allCount = this.messageService
					.selectAllCount((Integer) (UserSession.getLoginUserId()));
			//已读
			Integer readCount = allCount.intValue() - noReadCount.intValue();
			if (EmptyUtil.isNotEmpty(allCount)) {
				map.put("isLookCount", readCount);
			}
		}
		return map;
	}
	
	@RequestMapping("/deleteSystemMessage")
	@ResponseBody
	public boolean deleteSystemMessage(String ids) {
		return systemMessageService.deleteSystemMessages(ids);
	}
	
	/**
	 * 删除站内信
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteMessage")
	@ResponseBody
	public boolean deleteMessage(String ids){
		this.messageService.deleteMessage(ids);
		return true;
	}
}
