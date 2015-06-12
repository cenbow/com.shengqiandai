package cn.vfunding.vfunding.biz.all.test.borrow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.dao.MortgageCarPicMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring-context.xml" })
public class BorrowContentConvert {

	@Autowired
	private BorrowMapper mapper;
	@Autowired
	private MortgageCarPicMapper picMapper;

	@Test
	public void convertContent() {
		List<Borrow> borrows = this.mapper.selectByConvertContent();
		if (EmptyUtil.isNotEmpty(borrows)) {
			System.out.println("borrowsSize:" + borrows.size());
			Integer needConvertNum = 0;// 需要处理的数量
			List<Integer> errorIds = new ArrayList<Integer>();

			for (Borrow borrow : borrows) {

				String result = "";// 最终图片地址信息
				String fOri = "";// 第一次转换
				String sOri = "";// 第二次转换

				Integer carId = 0;
				try {
					if (EmptyUtil.isNotEmpty(borrow.getMortgageTypeid())
							&& (borrow.getMortgageTypeid().intValue() == 1 || borrow
									.getMortgageTypeid().intValue() == 2)) {
						if (EmptyUtil.isNotEmpty(borrow.getMortgageId())) {
							needConvertNum++;
							carId = borrow.getMortgageId();
							String ori = borrow.getContent();
							if (EmptyUtil.isNotEmpty(ori)) {
								if (ori.contains("<IMG")
										|| ori.contains("<img")) {
									ori = ori.replace("<IMG", "<img");
									fOri = ori.substring(ori.indexOf("<img"));
									sOri = fOri.replace("<br>", "")
											.replace("</p>", "")
											.replace("<p>", "")
											.replace("&nbsp;", "")
											.replace("&#8203;", "")
											.replace("<strong>", "")
											.replace("</strong>", "")
											.replace("><", ">,<");
									String[] imgs = sOri.split(",");
									int num = 0;
									for (String img : imgs) {
										if (img.startsWith("<img")) {
											String sortImg = img.substring(img
													.indexOf("src=\"") + 5);

											String data = sortImg.substring(0,
													sortImg.indexOf("\""));
											if (EmptyUtil.isNotEmpty(data)
													&& data.length() > 22) {
												if (data.startsWith("http://www.vfunding.cn")) {
													result = data
															.replace(
																	"http://www.vfunding.cn",
																	"http://static.vfunding.cn");
												} else {
													result = "http://static.vfunding.cn"
															+ data;
												}
											} else {
												System.out.println("sortImg:"
														+ sortImg);
												System.out.println("sOri:"
														+ sOri);
												System.out.println("errorData:"
														+ data);
											}

										}

										if (EmptyUtil.isNotEmpty(result)) {
											MortgageCarPic pic = new MortgageCarPic();
											pic.setAddtime(new Date());
											pic.setCarId(carId);
											pic.setType(num > 2 ? "2" : String
													.valueOf(num));
											pic.setPic(result);
											this.picMapper.insert(pic);
											num++;

										}

									}
								}

							}

						}
					}

				} catch (Exception e) {
					System.out.println("errorMsg:" + e.getMessage());
					System.out.println("sOri:" + sOri);
					errorIds.add(borrow.getId());
					System.out.println(e.getMessage());
				}

			}

			System.out.println("errorIds:" + errorIds);

		}
	}
}
