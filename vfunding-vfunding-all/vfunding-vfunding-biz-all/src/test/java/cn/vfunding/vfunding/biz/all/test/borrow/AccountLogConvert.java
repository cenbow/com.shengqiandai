package cn.vfunding.vfunding.biz.all.test.borrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.system.dao.MessageMapper;
import cn.vfunding.vfunding.biz.system.model.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring-context.xml" })
public class AccountLogConvert {

	@Autowired
	private AccountLogMapper logMpper;
	@Autowired
	private MessageMapper messageMapper;

	@Test
	public void convert() {
		List<AccountLog> logs = this.logMpper.selectByConvet();
		System.out.println("size:" + logs.size());
		List<Integer> errorIds = new ArrayList<Integer>();
		if (EmptyUtil.isNotEmpty(logs)) {
			String fOri = "";
			String sOri = "";
			
			for (AccountLog log : logs) {
				String oldText = "<a href='";
				String result = "";
				try {
					String ori = log.getRemark();
					if (ori.contains("<a href=")) {
						fOri = ori.substring(ori.indexOf("<a href=") + 9);
						if(fOri.contains("'")){
							sOri = fOri.substring(0, fOri.indexOf("'"));
							oldText += sOri + "'";
						}else if(fOri.contains(">")){
							sOri = fOri.substring(0, fOri.indexOf(">"));
							oldText += sOri + ">";
						}
						if (sOri.startsWith("/invest/a")
								&& sOri.endsWith(".html")) {
							String num = sOri.substring(9,
									sOri.indexOf(".html"));
							//System.out.println("num:" + num);
							result += "/borrow/borrowDetail/" + num;

						} else if (sOri.startsWith("/borrow/")) {
							if (sOri.contains("?id=")) {
								result = sOri.replace("?id=", "/");
							}
						} else {
							System.out.println("errorData:" + sOri);
						}

						if (EmptyUtil.isNotEmpty(result)) {
							//System.out.println("oldText" + oldText);

							result = "<a href='" + result + "'";
							String newRemark = ori.replace(oldText, result);
							log.setRemark(newRemark);
							this.logMpper.updateByPrimaryKeySelective(log);
						}

					}
				} catch (Exception e) {
					System.out.println("errorMsg:" + e.getMessage());
					System.out.println("errorOriData:" + fOri);
					System.out.println("errorData:" + sOri);
					errorIds.add(log.getId());
				}

			}

			System.out.println("errorIds:" + errorIds);

		}
	}
	
	
	
	@Test
	public void convertMessage() {
		 List<Message> messages = this.messageMapper.selectByConvet();
		System.out.println("size:" + messages.size());
		List<Integer> errorIds = new ArrayList<Integer>();
		if (EmptyUtil.isNotEmpty(messages)) {
			String fOri = "";
			String sOri = "";
			
			for (Message message : messages) {
				String oldText = "<a href='";
				String result = "";
				try {
					String ori = message.getContent();
					if (ori.contains("<a href=")) {
						fOri = ori.substring(ori.indexOf("<a href=") + 9);
						if(fOri.contains("'")){
							sOri = fOri.substring(0, fOri.indexOf("'"));
							oldText += sOri + "'";
						}else if(fOri.contains(">")){
							sOri = fOri.substring(0, fOri.indexOf(">"));
							oldText += sOri + ">";
						}
						if (sOri.startsWith("/invest/a")
								&& sOri.endsWith(".html")) {
							String num = sOri.substring(9,
									sOri.indexOf(".html"));
							//System.out.println("num:" + num);
							result += "/borrow/borrowDetail/" + num;

						} else if (sOri.startsWith("/borrow/")) {
							if (sOri.contains("?id=")) {
								result = sOri.replace("?id=", "/");
							}
						} else if (sOri.startsWith("/index.php?")) {
							
							result = "/friend/financiaDetail";
							
						} else {
							System.out.println("errorData:" + sOri);
						}

						if (EmptyUtil.isNotEmpty(result)) {
							//System.out.println("oldText" + oldText);

							result = "<a href='" + result + "'";
							String newRemark = ori.replace(oldText, result);
							message.setContent(newRemark);
							this.messageMapper.updateByPrimaryKeySelective(message);
						}

					}
				} catch (Exception e) {
					System.out.println("errorMsg:" + e.getMessage());
					System.out.println("errorOriData:" + fOri);
					System.out.println("errorData:" + sOri);
					errorIds.add(message.getId());
				}

			}

			System.out.println("errorIds:" + errorIds);

		}
	}
}
