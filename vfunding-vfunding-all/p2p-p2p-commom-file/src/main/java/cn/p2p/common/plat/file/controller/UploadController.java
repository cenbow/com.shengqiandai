package cn.p2p.common.plat.file.controller;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.p2p.common.plat.file.service.IOperationService;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.ImageUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;

@Controller
@RequestMapping("")
public class UploadController extends BaseController {
	@Autowired
	private IOperationService operationService;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(
			MultipartHttpServletRequest multipartRequest,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "isConvert", required = false) String isConvert,
			@RequestParam(value = "width", required = false) Integer width,
			@RequestParam(value = "heigth", required = false) Integer heigth)
			throws Exception {
		String fileId = "";
		String extName = "";
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("files");
		// 获得文件名：
		String filename = file.getOriginalFilename();

		if (filename.endsWith(".jsp") || filename.endsWith(".php")
				|| filename.endsWith(".asp") || filename.endsWith(".aspx")) {
			return "error";
		} else {
			// 获得输入流：
			InputStream input = file.getInputStream();
			extName = filename.substring(filename.lastIndexOf(".") + 1);

			if (EmptyUtil.isEmpty(from)) {
				from = "yache_bms";
			}
			if (EmptyUtil.isNotEmpty(isConvert) && isConvert.equals("0")
					&& ImageUtil.isImage(extName)) {// 保存图片且需要额外保存大、中、小三种尺寸
				fileId = this.operationService.storeImage(input, extName, from,
						true);
			} else if (EmptyUtil.isNotEmpty(width)
					&& EmptyUtil.isNotEmpty(heigth)
					&& ImageUtil.isImage(extName)) {// 按照指定的宽高保存图片
				if (width.intValue() > 0 && heigth > 0) {
					fileId = this.operationService.storeImage(input, extName,
							from, width, heigth);
				}
			} else {// 普通方式保存文件，包括保存图片
				fileId = this.operationService.storeFile(input, extName, from);
			}
		}

		return fileId + "." + extName;
	}

}
