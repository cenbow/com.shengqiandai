package cn.vfunding.common.framework.utils.http;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.FileBody;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.utils.beans.CalendarFormatUtil;
import cn.vfunding.common.framework.utils.beans.CalendarUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.WorkerPropertiesUtil;

public class MultipartEntityBuilderUtil {

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @author liuyijun
	 */
	public static MultipartEntityBuilder createMultipartEntityBuilderByMultipartFile(
			MultipartFile file) throws IOException {
		MultipartEntityBuilder multipartEntity = null;
		try {

			Assert.notNull(file);
			multipartEntity = MultipartEntityBuilder.create();
			// input = file.getInputStream();
			// file.getBytes();

			String filename = file.getOriginalFilename();
			// multipartEntity.addPart("files", new InputStreamBody(input,
			// filename));
			//new FileBody(file,ContentType.APPLICATION_JSON,filename);
			multipartEntity.addPart("files", new ByteArrayBody(file.getBytes(),
					filename));
		} catch (IOException e) {
			throw e;
		}
		return multipartEntity;

	}

	public static String uploandFile(MultipartFile file) throws IOException {
		String org = "";
		EmployeeSession session = EmployeeSession.getUserSession();
		if (EmptyUtil.isNotEmpty(session)) {
			org = String.valueOf(session.getCurrentOrgId());
		}

		String name = CalendarFormatUtil.format(CalendarUtil.getDate(),
				CalendarFormatUtil.YYYYMMDD) + "/" + org;

		String realPath = WorkerPropertiesUtil.getFileDirValue() + "/";
		// String realPath = "d:/files/";

		File pathFile = new File(realPath + name);

		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}

		name = name + "/" + UUID.randomUUID().toString().replace("-", "")
				+ file.getOriginalFilename();

		OutputStream output = null;
		InputStream inputStream = null;
		try {
			output = new FileOutputStream(new File(realPath + name));
			inputStream = file.getInputStream();
			IOUtils.copy(inputStream, output);
			output.flush();
		} finally {
			IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(output);
		}
		System.out.println();
		return name;
	}
}
