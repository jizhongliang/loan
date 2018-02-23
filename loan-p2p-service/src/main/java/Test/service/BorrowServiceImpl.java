package Test.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hwc.mybatis.core.AbstractService;

import Test.Borrow;
import Test.dao.BorrowMapper;

public class BorrowServiceImpl extends AbstractService<BorrowMapper, Borrow> implements BorrowService {
	
	
	/**
	 * 查询逾期订单 
	 * 
	 */
	 public List<Borrow> getBorrowLoanOverdue () {
		List<Borrow> selectByState = this.mapper.selectByState();
		return selectByState;
	}
	 
}
