package Test.service;

import java.util.List;

import com.hwc.mybatis.core.Service;

import Test.Borrow;

public interface BorrowService extends Service<Borrow> {
	
	/**
	 * 查询逾期订单
	 * @return
	 */
	public List<Borrow> getBorrowLoanOverdue ();
}
