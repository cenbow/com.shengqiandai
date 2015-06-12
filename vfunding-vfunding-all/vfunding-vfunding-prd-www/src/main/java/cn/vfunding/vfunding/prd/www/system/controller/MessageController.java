package cn.vfunding.vfunding.prd.www.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.system.service.IMessageService;

@Controller()
@RequestMapping("/message")
public class MessageController extends BaseController {

	@Autowired
	private IMessageService messageService;

	/**
	 * 更新消息为已读状态
	 * 
	 * @param id
	 * @return 2014年5月7日 liuyijun
	 */
	@RequestMapping("/readMessage/{id}")
	@ResponseBody
	public Json readMessage(@PathVariable("id") Integer id) {
		Json j = new Json();
		Message message = new Message();
		message.setId(id);
		message.setStatus(1);
		if (this.messageService.updateReadPrimaryKey(message) == 1) {
			j.setSuccess(true);
		}
		return j;
	}
	
	/**
	 * 删除站内信
	 * @param ids
	 * @return
	 * 2014年8月22日
	 * liuyijun
	 */
	@RequestMapping("/deleteMessage")
	@ResponseBody
	public Json deleteMessage(@RequestParam("ids") String ids){
		Json j=new Json();
		this.messageService.deleteMessage(ids);
		j.setSuccess(true);
		return j;
	}

}
