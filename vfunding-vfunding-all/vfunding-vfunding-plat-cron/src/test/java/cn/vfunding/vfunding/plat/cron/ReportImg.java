package cn.vfunding.vfunding.plat.cron;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
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

import cn.vfunding.vfunding.biz.pdfreport.model.UserTenderLine;

public class ReportImg {

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
			ChartUtilities.saveChartAsJPEG(file, 0f, chart, 520, 300);
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
		File file = new File("c:\\line.jpg");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		String series1 = "投资走势";

		this.imageLine(dataset, file);
	}

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

	public static void main(String[] args) {
		ReportImg ri = new ReportImg();
		File file = new File("C://tmp/b1.jpg");
		File file1 = new File("C://tmp/a.jpg");
		String series1 = "投资走势";
		String type1 = "一月份";
		String type2 = "二月份";
		String type3 = "三月份";
		String type4 = "四月份";
		String type5 = "五月份";
		String type6 = "六月份";
		String type7 = "七月份";
		String type8 = "八月份";
		String type9 = "九月份";
		String type10 = "十月份";
		String type11 = "十一月份";
		String type12 = "十二月份";
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(0, series1, type1);
		dataset.addValue(0, series1, type2);
		dataset.addValue(0, series1, type3);
		dataset.addValue(0, series1, type4);
		dataset.addValue(0, series1, type5);
		dataset.addValue(0, series1, type6);
		dataset.addValue(0, series1, type7);
		dataset.addValue(0, series1, type8);
		dataset.addValue(0, series1, type9);
		dataset.addValue(0, series1, type10);
		dataset.addValue(0, series1, type11);
		dataset.addValue(0, series1, type12);
		ri.imageLine(dataset, file1);
		DefaultPieDataset dpd = new DefaultPieDataset(); // 建立一个默认的饼图
		dpd.setValue("好友返利", 0); // 输入数据
		dpd.setValue("已赚利息", 110.75); // 输入数据
		dpd.setValue("提现手续费", 0); // 输入数据
		ri.imagePie(dpd, file);
	}

}
