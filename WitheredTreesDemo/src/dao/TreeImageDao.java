package dao;

import java.util.List;

import bean.TreeImage;
import jdbc.MysqlQuery;

public class TreeImageDao {

	public static List<TreeImage> getImages(String startDate, String endDate) {
		String sql = null;
		if (startDate.equals(endDate)) {
			sql = "select * from treeImage where record_date = ?";
			return MysqlQuery.queryTreeImages(sql, new Object[] {startDate});
		} else {
			sql = "select * from treeImage where record_date >= ? and record_date <= ?";
			return MysqlQuery.queryTreeImages(sql, new Object[] {startDate,endDate});
		}
	}
	
	public static int addTreeImage(TreeImage treeImage) {
		return MysqlQuery.insertTreeImage(treeImage);
	}
	
}
