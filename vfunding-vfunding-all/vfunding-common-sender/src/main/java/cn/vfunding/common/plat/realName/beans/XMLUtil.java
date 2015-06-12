package cn.vfunding.common.plat.realName.beans;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class XMLUtil {

	public static String readXMLString(String xml) throws Exception {
		String reslut = "fail";
		Document document = DocumentHelper.parseText(xml); // 将字符串转为XML
		if (EmptyUtil.isNotEmpty(document)) {
			// 获取根元素
			Element root = document.getRootElement();
			// 获取root下的message节点
			Element messageChild = root.element("message");
			if (EmptyUtil.isNotEmpty(messageChild)) {
				// 获取root下的message节点下的status节点信息
				Element statusChild = messageChild.element("status");
				if (EmptyUtil.isNotEmpty(statusChild)) {
					if (statusChild.getTextTrim().equals("0")) {// 表示处理成功
						// 获取root下的policeCheckInfos节点
						Element policeCheckInfosChild = root
								.element("policeCheckInfos");
						if (EmptyUtil.isNotEmpty(policeCheckInfosChild)) {
							Element policeCheckInfoChild = policeCheckInfosChild
									.element("policeCheckInfo");
							if (EmptyUtil.isNotEmpty(policeCheckInfoChild)) {
								Element policeCheckInfoMessageChild = policeCheckInfoChild
										.element("message");
								if (EmptyUtil
										.isNotEmpty(policeCheckInfoMessageChild)) {
									Element policeCheckInfoMessageStatusChild = policeCheckInfoMessageChild
											.element("status");
									if (EmptyUtil
											.isNotEmpty(policeCheckInfoMessageStatusChild)) {
										if (policeCheckInfoMessageStatusChild
												.getTextTrim().equals("0")) {// 查询成功
											Element policeCheckInfoCompStatusElement = policeCheckInfoChild
													.element("compStatus");
											if (EmptyUtil
													.isNotEmpty(policeCheckInfoCompStatusElement)) {
												if (policeCheckInfoCompStatusElement
														.getTextTrim()
														.equalsIgnoreCase("3")) {// 认证成功
													reslut = "success";
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return reslut;
	}

}
