package cn.p2p.common.plat.file.service.impl;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.p2p.common.plat.file.dao.SysFileMapper;
import cn.p2p.common.plat.file.model.SysFile;
import cn.p2p.common.plat.file.service.IOperationService;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.FileUtil;
import cn.vfunding.common.framework.utils.beans.ImageUtil;
import cn.vfunding.common.framework.utils.beans.ObjectId;

@Service("operationService")
public class OperationServiceImpl implements IOperationService,
		InitializingBean {
	@Value("${image.dir}")
	private String imageDir;
	@Value("${file.dir}")
	private String fileDir;
	/** 大图的宽高 **/
	@Value("${image.maxWidth}")
	private Integer maxWidth;
	@Value("${image.maxHeight}")
	private Integer maxHeight;
	/** 小图的宽高 **/
	@Value("${image.minWidth}")
	private Integer minWidth;
	@Value("${image.minHeight}")
	private Integer minHeight;
	/** 中图的宽高 **/
	@Value("${image.middleWidth}")
	private Integer middleWidth;
	@Value("${image.middleHeight}")
	private Integer middleHeight;

	/**
	 * 原图地址前缀
	 */
	private static final String ORIGINAL_PATH = "ori";
	/**
	 * 大图地址前缀
	 */
	private static final String MAX_PATH = "max";
	/**
	 * 小图地址前缀
	 */
	private static final String MIN_PATH = "min";
	/**
	 * 中图地址前缀
	 */
	private static final String MIDDLE_PATH = "middle";

	@Autowired
	private SysFileMapper imageMapper;

	@Override
	public String storeFile(InputStream inputStream, String extName, String from)
			throws Exception {
		String picId = ObjectId.get();
		String filePath = "";
		SysFile sysFilePo = new SysFile();
		sysFilePo.setId(picId + "." + extName);
		sysFilePo.setInsertTime(new Date());
		if (ImageUtil.isImage(extName)) {
			filePath = imageDir + "/" + ORIGINAL_PATH + "/" + picId + "."
					+ extName;
			sysFilePo.setType("image");

		} else {
			filePath = fileDir + "/" + picId + "." + extName;
			sysFilePo.setType("file");
		}
		/** 向数据库插入记录 */
		sysFilePo.setFrom(from);
		imageMapper.insert(sysFilePo);
		FileUtil.InputStreamTOFile(inputStream, filePath);
		return picId;
	}

	@Override
	public String storeImage(InputStream inputStream, String extName,
			String from, boolean isConvert) throws Exception {
		String picId = ObjectId.get();
		String filePath = "";
		SysFile sysFilePo = new SysFile();
		sysFilePo.setId(picId + "." + extName);
		sysFilePo.setInsertTime(new Date());
		filePath = imageDir + "/" + ORIGINAL_PATH + "/" + picId + "." + extName;
		sysFilePo.setType("image");

		/** 向数据库插入记录 */
		sysFilePo.setFrom(from);
		imageMapper.insert(sysFilePo);

		FileUtil.InputStreamTOFile(inputStream, filePath);

		/**
		 * 保存大中小图片
		 */
		if (isConvert) {
			String maxFilePath = imageDir + "/" + MAX_PATH + "/" + picId + "."
					+ extName;
			String minFilePath = imageDir + "/" + MIN_PATH + "/" + picId + "."
					+ extName;
			String middleFilePath = imageDir + "/" + MIDDLE_PATH + "/" + picId
					+ "." + extName;
			Image targetImage = ImageIO.read(new File(filePath));
			ImageUtil.resize(maxWidth, maxHeight, maxFilePath, targetImage);
			ImageUtil.resize(minWidth, minHeight, minFilePath, targetImage);
			ImageUtil.resize(middleWidth, middleHeight, middleFilePath,
					targetImage);
		}

		return picId;
	}

	@Override
	public String storeImage(InputStream inputStream, String extName,
			String from, Integer width, Integer heigth) throws Exception {
		String picId = ObjectId.get();
		String filePath = "";
		SysFile sysFilePo = new SysFile();
		sysFilePo.setId(picId + "." + extName);
		sysFilePo.setInsertTime(new Date());
		filePath = imageDir + "/" + ORIGINAL_PATH + "/" + picId + "." + extName;
		sysFilePo.setType("image");

		/**
		 * 向数据库插入记录
		 */
		sysFilePo.setFrom(from);
		imageMapper.insert(sysFilePo);
		// FileUtil.InputStreamTOFile(inputStream, filePath);
		// Image targetImage = ImageIO.read(new File(filePath));
		Image targetImage = ImageIO.read(inputStream);
		ImageUtil.resize(width.intValue(), heigth.intValue(), filePath,
				targetImage);
		return picId;
	}

	@Override
	@CacheEvict(value = "fileDataCache", key = "'file'+#fileName")
	public void deleteFile(String fileName) {
		String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (ImageUtil.isImage(extName)) {// 判断是否是图片
			String oriPath = imageDir + "/" + ORIGINAL_PATH + "/" + fileName;
			this.deleteFileMethod(oriPath);// 删除原图
			String maxPath = imageDir + "/" + MAX_PATH + "/" + fileName;
			this.deleteFileMethod(maxPath);// 删除大图
			String minPath = imageDir + "/" + MIN_PATH + "/" + fileName;
			this.deleteFileMethod(minPath);// 删除小图
			String middlePath = imageDir + "/" + MIDDLE_PATH + "/" + fileName;
			this.deleteFileMethod(middlePath);// 删除中图
		} else {// 文件
			String filePath = fileDir + "/" + fileName;
			this.deleteFileMethod(filePath);
		}

	}

	@Override
	public InputStream getImage(String picName, String type) {
		String filePath = "";
		if (type.equalsIgnoreCase("max")) {
			filePath = imageDir + "/" + MAX_PATH + "/" + picName;
		} else if (type.equalsIgnoreCase("min")) {
			filePath = imageDir + "/" + MIN_PATH + "/" + picName;
		} else if (type.equalsIgnoreCase("middle")) {
			filePath = imageDir + "/" + MIDDLE_PATH + "/" + picName;
		} else {
			filePath = imageDir + "/" + ORIGINAL_PATH + "/" + picName;
		}

		try {
			File file = new File(filePath);
			// 判断文件是否存在如果不存在就返回默认图标
			if (!(file.exists() && file.canRead())) {
				file = new File("WebContent/images/default.jpg");
			}

			FileInputStream inputStream = new FileInputStream(file);
			return inputStream;
		} catch (FileNotFoundException e) {
			throw new BusinessException("5000");
		}
	}

	@Override
	@Cacheable(value = "fileDataCache", key = "'file'+#fileName")
	public String getFileData(String fileName) throws Exception {
		String data = null;
		String filePath = fileDir + "/" + fileName;
		File file = new File(filePath);
		if (file.exists() && file.canRead()) {
			data = FileUtil.readFileToString(filePath, null);
		} else {
			throw new BusinessException("5001", "【" + fileName + "】,文件不存在");
		}

		return data;
	}

	@Override
	@Cacheable(value = "fileDataCache", key = "'file'+#type+#picName")
	public byte[] getImageData(String picName, String type) throws Exception {
		byte[] data = null;
		InputStream inputStream = this.getImage(picName, type);
		data = IOUtils.toByteArray(inputStream);
		IOUtils.closeQuietly(inputStream);
		return data;
	}

	public String getImageDir() {
		return imageDir;
	}

	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}

	public String getFileDir() {
		return fileDir;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	/**
	 * 创建文件保存路径
	 */
	private void createFileDir() {
		String filePath = fileDir;
		File fileDir = new File(filePath);
		if (!fileDir.exists() && !fileDir.isDirectory()) {
			fileDir.mkdir();
		}
	}

	/**
	 * 创建原图保存路径
	 */
	private void createImageDir() {
		String imagePath = imageDir + "/" + ORIGINAL_PATH;
		File imageDir = new File(imagePath);
		if (!imageDir.exists() && !imageDir.isDirectory()) {
			imageDir.mkdir();
		}

	}

	/**
	 * 创建大图保存路径
	 */
	private void createMaxImageDir() {
		String imagePath = imageDir + "/" + MAX_PATH;
		File imageDir = new File(imagePath);
		if (!imageDir.exists() && !imageDir.isDirectory()) {
			imageDir.mkdir();
		}

	}

	/**
	 * 创建小图保存路径
	 */
	private void createMinImageDir() {
		String imagePath = imageDir + "/" + MIN_PATH;
		File imageDir = new File(imagePath);
		if (!imageDir.exists() && !imageDir.isDirectory()) {
			imageDir.mkdir();
		}

	}

	/**
	 * 创建中图保存路径
	 */
	private void createMiddleImageDir() {
		String imagePath = imageDir + "/" + MIDDLE_PATH;
		File imageDir = new File(imagePath);
		if (!imageDir.exists() && !imageDir.isDirectory()) {
			imageDir.mkdir();
		}

	}

	/**
	 * 删除文件方法
	 * 
	 * @param filePath
	 *            文件路径
	 */
	private void deleteFileMethod(String filePath) {
		File file = new File(filePath);
		if (file.exists())
			file.delete();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		createFileDir();
		createImageDir();
		createMaxImageDir();
		createMinImageDir();
		createMiddleImageDir();
	}

}
