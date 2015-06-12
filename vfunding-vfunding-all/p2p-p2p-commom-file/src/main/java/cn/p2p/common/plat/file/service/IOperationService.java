package cn.p2p.common.plat.file.service;

import java.io.InputStream;

public interface IOperationService {
	/**
	 * 保存文件(包括图片和文件)
	 * @param inputStream
	 * @param extName
	 * @return 图片ID路径
	 * @throws Exception
	 */
	String storeFile(InputStream inputStream,String extName,String from) throws Exception;
	/**
	 * 保存图片
	 * @param inputStream 文件流
	 * @param extName 扩展名
	 * @param from 平台来源
	 * @param isConvert 是否转换成大、中、小图片保存
	 * @return 图片ID
	 * @throws Exception 异常
	 */
	String storeImage(InputStream inputStream,String extName,String from,boolean isConvert) throws Exception;
	/**
	 * 根据指导宽高保存图片
	 * @param inputStream
	 * @param extName
	 * @param from
	 * @param width
	 * @param heigth
	 * @return
	 * @throws Exception
	 */
	String storeImage(InputStream inputStream, String extName,
			String from, Integer width,Integer heigth) throws Exception;
	
	/**
	 * 删除文件（包括文件和图片）
	 * @param fileName
	 */
	void deleteFile(String fileName);
	/**
	 * 获取图片的输入流
	 * @param picName 文件名称
	 * @param type 大、中、小
	 * @return
	 */
	InputStream getImage(String picName,String type);
	
	/**
	 * 获取文件的字节数组
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	String getFileData(String fileName) throws Exception;
		
	/**
	 * 获取图片的字节数组
	 * @param picName
	 * @param type max、min、middle
	 * @return
	 * @throws Exception
	 */
	byte[]  getImageData(String picName,String type) throws Exception;
	

}
