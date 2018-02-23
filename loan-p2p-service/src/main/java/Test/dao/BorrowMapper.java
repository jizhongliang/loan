package Test.dao;

import java.util.List;

import com.hwc.mybatis.core.Mapper;

import Test.Borrow;

public interface BorrowMapper extends Mapper<Borrow> {
    int deleteByPrimaryKey(Long id);

    int insert(Borrow record);

    int insertSelective(Borrow record);

    Borrow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Borrow record);

    int updateByPrimaryKey(Borrow record);

    List<Borrow> selectByState();
}