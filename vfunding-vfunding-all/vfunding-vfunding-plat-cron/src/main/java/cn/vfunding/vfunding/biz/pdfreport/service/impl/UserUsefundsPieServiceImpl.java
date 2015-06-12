package cn.vfunding.vfunding.biz.pdfreport.service.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.pdfreport.dao.UserTenderLineMapper;
import cn.vfunding.vfunding.biz.pdfreport.dao.UserUsefundsPieMapper;
import cn.vfunding.vfunding.biz.pdfreport.model.UserTenderLine;
import cn.vfunding.vfunding.biz.pdfreport.model.UserUsefundsPie;
import cn.vfunding.vfunding.biz.pdfreport.service.IUserUsefundsPieService;

@Service("userUsefundsPieService")
public class UserUsefundsPieServiceImpl implements IUserUsefundsPieService {

	@Autowired
	private UserUsefundsPieMapper pieMapper;

	@Autowired
	private UserTenderLineMapper lineMapper;

	@Value("${report.image.path}")
	private String imagePath;

	/**
	 * 取生成饼状图的数据
	 */
	@Override
	public List<UserUsefundsPie> selectUserUsefundsPie() {
		// TODO Auto-generated method stub
		List<UserUsefundsPie> list = this.pieMapper.selectUserUsefundsPie();
		List<UserTenderLine> lineList = this.lineMapper.selectAllTenderLine();
		DefaultPieDataset dpd = null;
		File file = null;
		for (UserUsefundsPie pie : list) {
			Integer id = pie.getUserId();
			if (pie.getTenderUserId() != 0) {
				file = new File(imagePath + id + "pie.jpg");
				dpd = new DefaultPieDataset(); // 建立一个默认的饼图
				dpd.setValue("提现手续费", pie.getCashFeeMoney()); // 输入数据
				dpd.setValue("总收益", pie.getUserHavaInterestMoney());
				dpd.setValue("好友返利", pie.getUserSonMoney());
				this.imagePie(dpd, file);
				this.selectUserTenderLine(id);
			} else {
				this.selectNoTenderLine(id, lineList);
			}

		}
		return null;
	}

	/**
	 * 生成饼状图
	 * 
	 * @param dataset
	 * @param file
	 * @return
	 */
	private String imagePie(DefaultPieDataset dataset, File file) {
		JFreeChart chart = ChartFactory.createPieChart3D("资金使用情况", dataset,
				true, true, false);
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 12));
		chart.setBackgroundPaint(Color.white);
		// 获得3D的水晶饼图对象
		PiePlot3D pieplot3d = (PiePlot3D) chart.getPlot();
		// 设置开始角度
		pieplot3d.setLabelFont(new Font("黑体", Font.BOLD, 15));
		pieplot3d.setStartAngle(20D);
		// 设置方向为”顺时针方向“
		pieplot3d.setDirection(Rotation.CLOCKWISE);
		// 设置透明度，0.5F为半透明，1为不透明，0为全透明
		pieplot3d.setForegroundAlpha(1F);
		pieplot3d.setNoDataMessage("无数据显示");
		try {
			ChartUtilities.saveChartAsJPEG(file, 1f, chart, 520, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 取生成投资者线状图的数据
	 * 
	 * @param dataset
	 * @param file
	 * @return
	 */
	public void selectUserTenderLine(Integer userId) {
		// TODO Auto-generated method stub
		List<UserTenderLine> list = this.lineMapper
				.selectUserTenderLine(userId);
		File file = new File(imagePath + userId + "line.jpg");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<String> yearMonth = this.getYearMonth();
		String series1 = "投资走势";

		for (int i = 0; i < yearMonth.size(); i++) {

			if (list.size() > 0
					&& yearMonth.get(i).endsWith(list.get(0).getYearMonth())) {
				dataset.addValue(list.get(0).getTenderMoney(), series1,
						yearMonth.get(i));
				list.remove(0);
			} else {
				dataset.addValue(0d, series1, yearMonth.get(i));
			}
		}
		this.imageLine(dataset, file);
	}

	/**
	 * 取生成未投资者线状图的数据
	 * 
	 * @param dataset
	 * @param file
	 * @return
	 */
	public void selectNoTenderLine(Integer userId, List<UserTenderLine> lineList) {
		List<UserTenderLine> list = this.lineMapper
				.selectUserTenderLine(userId);
		File file = new File(imagePath + userId + "line.jpg");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		List<String> yearMonth = this.getYearMonth();
		String series1 = "您的投资走势";
		String series2 = "其他用户平均投资走势";
		Integer j = 0;
		for (int i = 0; i < yearMonth.size(); i++) {
			if (list.size() > 0
					&& yearMonth.get(i).equals(list.get(0).getYearMonth())) {
				dataset.addValue(list.get(0).getTenderMoney(), series1,
						yearMonth.get(i));
				list.remove(0);
			} else {
				dataset.addValue(0d, series1, yearMonth.get(i));
			}
			if (lineList.size() > 0
					&& yearMonth.get(i)
							.equals(lineList.get(j).getYearMonth())) {
				dataset.addValue(lineList.get(j).getTenderMoney(), series2,
						yearMonth.get(i));
				j++;
			} else {
				dataset.addValue(0d, series2, yearMonth.get(i));
			}

		}
		this.imageLine(dataset, file);
	}

	private List<String> getYearMonth() {
		Date date = new Date();
		int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date)) - 1;
		int month = Integer.parseInt(new SimpleDateFormat("MM").format(date));
		if (month == 0) {
			year -= 1;
			month = 12;
		}
		String y = year + "";
		String m = "";
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			if (month < 10) {
				m = "0" + month;
			} else if (month >= 10 && month <= 12) {
				m = "" + month;
			} else if (month > 12) {
				month = month - 12;
				year += 1;
				y = year + "";
				m = "0" + month;
			}
			list.add(y + "." + m);
			month++;
		}
		return list;
	}

	/**
	 * 生成线状图
	 * 
	 * @param series
	 * @param file
	 * @return
	 */
	private String imageLine(CategoryDataset series, File file) {
		JFreeChart chart = ChartFactory.createLineChart(null, null, null,
				series, PlotOrientation.VERTICAL, true, true, false);
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 16));
		// 设置真个图片的背景颜色
		chart.setBackgroundPaint(Color.white);
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		categoryplot.setBackgroundPaint(Color.white);
		categoryplot.setRangeGridlinePaint(Color.black);
		// 设置横轴的字体，防止中文乱码
		CategoryAxis categoryaxis = categoryplot.getDomainAxis();
		categoryaxis.setTickLabelFont(new Font("黑体", Font.BOLD, 12));
		categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		// 设置竖轴的字体，防止中文乱码
		categoryplot.getRangeAxis().setTickLabelFont(
				new Font("黑体", Font.BOLD, 12));

		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setAutoRangeIncludesZero(true);
		LineAndShapeRenderer lasp = (LineAndShapeRenderer) categoryplot
				.getRenderer();
		lasp.setShapesVisible(true); // series 点（即数据点）可见
		lasp.setSeriesStroke(0, new BasicStroke(3F));
		lasp.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
		try {
			// // 保存图片到指定文件夹1
			ChartUtilities.saveChartAsJPEG(file, 1f, chart, 520, 300);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "	";
	}

}
