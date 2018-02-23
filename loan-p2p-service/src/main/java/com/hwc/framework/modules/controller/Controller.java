package com.hwc.framework.modules.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwc.base.api.Response;

import Test.Borrow;
import Test.service.BorrowService;
import Test.service.BorrowServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/p2p")
@Api(tags = "借款列表")
public class Controller {
	
	@Autowired
	private static BorrowService borrowService;
	
	
	@PostMapping("/list")
	@ApiOperation(value = "借款列表")
	public Response<List<Borrow>> index1(){
		//查询
		List<Borrow> borrowLoanOverdue = borrowService.getBorrowLoanOverdue();
		for (Borrow borrow : borrowLoanOverdue) {
			System.out.println(borrow.toString());
		}
		return Response.success(borrowLoanOverdue);
	}
	
	 public static void main(String[] args) {
		 BorrowServiceImpl a = new BorrowServiceImpl();
			List<Borrow> borrowLoanOverdue = a.getBorrowLoanOverdue();
			for (Borrow borrow : borrowLoanOverdue) {
				System.out.println(borrow.toString());
			}
		}
}
