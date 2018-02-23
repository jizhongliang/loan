package Test;

import java.util.Calendar;
import java.util.Date;

public class DataTime {
	/**
	 * 根据域比较两个时间的该域值。
	 * 
	 * @param field
	 *            域
	 * @param d1
	 *            第一个时间
	 * @param d2
	 *            第二个时间
	 * @return 累计多多少或少多少，d1大为负数
	 */
	public static int compare2Date(int field, Date d1, Date d2) {
		boolean isD1 = true;
		if (d1.compareTo(d2) == 0) {
			return 0;
		} else if (d1.compareTo(d2) > 0) {
			isD1 = false;
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		if (field == Calendar.YEAR || field == Calendar.MONTH || field == Calendar.DAY_OF_MONTH
				|| field == Calendar.DAY_OF_WEEK || field == Calendar.DAY_OF_WEEK_IN_MONTH
				|| field == Calendar.DAY_OF_YEAR || field == Calendar.DATE) {
			if (field == Calendar.YEAR) {
				c1.set(Calendar.MONTH, 1);
				c1.set(Calendar.DAY_OF_MONTH, 1);
				c2.set(Calendar.MONTH, 1);
				c2.set(Calendar.DAY_OF_MONTH, 1);
			} else if (field == Calendar.MONTH) {
				c1.set(Calendar.DAY_OF_MONTH, 1);
				c2.set(Calendar.DAY_OF_MONTH, 1);
			}

			c1.set(Calendar.HOUR_OF_DAY, 0);
			c1.set(Calendar.MINUTE, 0);
			c1.set(Calendar.SECOND, 0);
			c1.set(Calendar.MILLISECOND, 0);
			c2.set(Calendar.HOUR_OF_DAY, 0);
			c2.set(Calendar.MINUTE, 0);
			c2.set(Calendar.SECOND, 0);
			c2.set(Calendar.MILLISECOND, 0);
		}
		int res = 0;
		if (isD1) {
			while (c1.getTime().compareTo(c2.getTime()) < 0) {
				res++;
				c1.add(field, 1);
			}
		} else {
			while (c2.getTime().compareTo(c1.getTime()) < 0) {
				res++;
				c2.add(field, 1);
			}
		}
		if (!isD1) {
			res *= -1;
		}
		return res;
	}
}
