package cn.vfunding.common.plat.sender.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.plat.realName.beans.DesUtil;
import cn.vfunding.common.plat.realName.beans.QueryValidatorServices;
import cn.vfunding.common.plat.realName.beans.QueryValidatorServicesProxy;
import cn.vfunding.common.plat.realName.beans.XMLUtil;
import cn.vfunding.common.plat.sender.dao.RealnameRecordMapper;
import cn.vfunding.common.plat.sender.model.RealnameRecord;
import cn.vfunding.common.plat.sender.service.IRealNameService;
import cn.vfunding.common.plat.sender.vo.RealNameVO;

@Service("realNameService")
public class RealNameServiceImpl implements IRealNameService {

	@Autowired
	private RealnameRecordMapper mapper;
	@Value("${gboss.username}")
	private String gbossUserName;

	@Value("${gboss.passowrd}")
	private String gbossPassword;

	@Override
	public boolean realName(RealNameVO realName) throws Exception {
		boolean result = false;
		QueryValidatorServicesProxy proxy = new QueryValidatorServicesProxy();
		proxy.setEndpoint("http://gboss.id5.cn/services/QueryValidatorServices");
		QueryValidatorServices service = proxy.getQueryValidatorServices();
		String key = "12345678";
		String userName = DesUtil.encode(key, gbossUserName);
		String password = DesUtil.encode(key, gbossPassword); 
		System.setProperty("javax.net.ssl.trustStore", "keystore");
		String resultXML = "";
		String datasource = DesUtil.encode(key, "1A020201");
		String param = realName.getName() + "," + realName.getCardId();
		resultXML = service.querySingle(userName, password, datasource,
				DesUtil.encode(key, param));
		resultXML = DesUtil.decodeValue(key, resultXML);
		if (EmptyUtil.isNotEmpty(resultXML)) {
			RealnameRecord record=new RealnameRecord(realName.getName(),realName.getCardId());
			String res = XMLUtil.readXMLString(resultXML);
			if (res.equalsIgnoreCase("success")) {
				result = true;
				record.setStatus("success");

			}else{
				record.setStatus("faild"); 
			}
			record.setAddTime(new Date());
			mapper.insert(record);
		}
		return result;

	}

}
