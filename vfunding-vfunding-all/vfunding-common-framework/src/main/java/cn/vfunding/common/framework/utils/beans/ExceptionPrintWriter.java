package cn.vfunding.common.framework.utils.beans;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;


public class ExceptionPrintWriter  {
	PrintWriter printWriter;
	ByteArrayOutputStream outputStream;
	public ExceptionPrintWriter(Exception e){
		outputStream = new ByteArrayOutputStream();
		printWriter = new PrintWriter(outputStream);
		e.printStackTrace(printWriter);
		printWriter.flush();
	}
	
	public String getContent(){
		return outputStream.toString();
	}
}
