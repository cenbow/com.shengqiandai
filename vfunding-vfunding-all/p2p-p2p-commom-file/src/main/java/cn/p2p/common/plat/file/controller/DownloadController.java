package cn.p2p.common.plat.file.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.p2p.common.plat.file.service.IOperationService;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.WorkerPropertiesUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;

@Controller
public class DownloadController extends BaseController {

	@Autowired
	private IOperationService operationService;

	@RequestMapping(value = "/ori/{picName:.*}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> originalImage(
			@PathVariable("picName") String picName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getImageMethod(picName, "ori");
	}

	@RequestMapping(value = "/max/{picName:.*}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> maxImage(
			@PathVariable("picName") String picName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getImageMethod(picName, "max");

	}

	@RequestMapping(value = "/middle/{picName:.*}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> middleImage(
			@PathVariable("picName") String picName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getImageMethod(picName, "middle");

	}

	@RequestMapping(value = "/min/{picName:.*}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> minImage(
			@PathVariable("picName") String picName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return getImageMethod(picName, "min");

	}

	@RequestMapping(value = "/downloadFile/{fileName:.*}", method = RequestMethod.GET)
	public ResponseEntity<String> downloadFile(
			@PathVariable("fileName") String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String data = this.operationService.getFileData(fileName);

		String contentType = "application/x-download";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", contentType);
		headers.add("Content-disposition",
				"attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
		return new ResponseEntity<String>(data, headers, HttpStatus.OK);

	}

	private ResponseEntity<byte[]> getImageMethod(String picName, String type)
			throws Exception {
		byte[] data = this.operationService.getImageData(picName, type);

		String contentType = "image/"
				+ picName.substring(picName.lastIndexOf(".") + 1);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", contentType);
		headers.add("Cache-Control", "max-age=315360000");
		headers.add("Last-Modified", DateUtil.parseDateTime(new Date()));
		return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public ModelAndView downloadFile1(
			@RequestParam("fileName") String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		FileInputStream inputStream = null;

		try {
			String path = WorkerPropertiesUtil.getFileDirValue() + "/"
					+ fileName;
			File file = new File(path);

			if (file.isFile() && file.exists()) {

				response.setContentType("application/x-msdownload;");
				response.setHeader("Content-disposition",
						"attachment; filename=" + file.getName());
				response.setHeader("Content-Length",
						String.valueOf(file.length()));

				// inputStream = new FileInputStream(new File(path));

				bis = new BufferedInputStream(new FileInputStream(path));
				bos = new BufferedOutputStream(response.getOutputStream());

				int length = IOUtils.copy(bis, bos);

				return null;
			}
		} finally {
			// IOUtils.closeQuietly(inputStream);
			IOUtils.closeQuietly(bis);
			IOUtils.closeQuietly(bos);
		}
		return new ModelAndView("error/404");
	}

}
