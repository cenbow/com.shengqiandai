package cn.vfunding.plat.pay.utils.llpay;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class MyTest {

	public static void main(String[] args) {
		
		
		String ori="冻结投资者的投标资金,对标[<a href='/borrow/borrowDetail?id=412>风控测试发标</a>]";
		String fOri="";
		String sOri="";
		String result="";
		String oldText="<a href='";
        if(ori.contains("<a href=")){
        	fOri=ori.substring(ori.indexOf("<a href=")+9);
        	System.out.println(fOri);
        	if(fOri.contains("'")){
        		sOri=fOri.substring(0,fOri.indexOf("'"));
        	}else{
        		sOri=fOri.substring(0,fOri.indexOf(">"));
        	}
        	
        	System.out.println(sOri);
        	oldText+=sOri+"'";
        	if(sOri.startsWith("/invest/a") && sOri.endsWith(".html")){
        		String num=sOri.substring(9,sOri.indexOf(".html"));
        		System.out.println("num:"+num);
        		result+="/borrow/borrowDetail/"+num;
        		
        	}else if(sOri.startsWith("/borrow/")){
        		if(sOri.contains("?id=")){
        			result=sOri.replace("?id=", "/");
        		}
        	}else{
        		System.out.println("errorData:"+sOri);
        	}
        }
        
        if(EmptyUtil.isNotEmpty(result)){
        	System.out.println("oldText"+oldText);
        }
        	
        	
        	
        	
        	
	}

}
