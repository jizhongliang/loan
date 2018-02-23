package com.hwc.framework.modules.third.business.zhengxin.service;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserAuthData;
import com.hwc.framework.modules.service.ClUserAuthDataService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.framework.modules.third.business.zhengxin.api.ZhengxinClient;
import com.hwc.framework.modules.third.business.zhengxin.billitem.ZhengxinTask;
import com.hwc.framework.modules.third.business.zhengxin.dto.*;
import com.hwc.framework.modules.third.business.zhengxin.entity.*;
import com.hwc.framework.modules.third.util.DateUtil;
import com.hwc.framework.modules.utils.OSSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * ClassName: ZhengxinService    
 * date: 2017年1月11日 下午7:46:08   
 * @author yuandong
 * @version
 * @since JDK 1.6
 */
@Service
public class ZhengxinService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ZhengxinService.class);

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private ZhengxinClient zhengxinClient;
	@Autowired
	private ClUserService clUserService;
	@Autowired
	private ClUserAuthDataService clUserAuthDataService;

	@Autowired
	private OSSUtils ossUtils;

	public void fetchBill(final ZhengxinTask task) {

		// 这里交给线程池处理，防止下面的业务处理时间太长，导致超时。
		// 超时会导致魔蝎数据进行重试，会收到重复的回调请求

		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Date startTime = DateUtil.getCurrentDate();
					CreditInformation creditInformation = zhengxinClient.getCreditInfo(task.getTaskId(),
							task.getReportNo());
					if (creditInformation != null) {

						DUser dUser = clUserService.getUserByUuid(task.getUserId());
						JSONObject jsonAll = new JSONObject();

						//保存基本信息
						CreditBaseInfo creditBaseInfo = creditInformation.getCreditBaseInfo();
						CreditBaseInfoEntity creditBaseInfoEntity = new CreditBaseInfoEntity();
						BeanUtils.copyProperties(creditBaseInfo, creditBaseInfoEntity);
						creditBaseInfoEntity.setUserId(task.getUserId());
						//
						jsonAll.put("baseInfo",creditBaseInfoEntity);

					   //人行征信报告信贷记录信息概要数组
						if(creditInformation.getCreditRecordSummaries()!=null&&!creditInformation.getCreditRecordSummaries().isEmpty()){
							List list = new ArrayList();
						    for(CreditRecordSummary creditRecordSummary:creditInformation.getCreditRecordSummaries()){
						    	CreditRecordSummaryEntity creditRecordSummaryEntity = new CreditRecordSummaryEntity();
								BeanUtils.copyProperties(creditRecordSummary, creditRecordSummaryEntity);
								creditRecordSummaryEntity.setUserId(task.getUserId());
								list.add(creditRecordSummaryEntity);
						    }
							jsonAll.put("creditRecordSummary",list);
						}

						//信贷记录详细信息数组
						if(creditInformation.getCreditRecordDetails()!=null&&!creditInformation.getCreditRecordDetails().isEmpty()){
							List list = new ArrayList();
						    for(CreditRecordDetail creditRecordDetail:creditInformation.getCreditRecordDetails()){
						    	CreditRecordDetailEntity creditRecordDetailEntity = new CreditRecordDetailEntity();
								BeanUtils.copyProperties(creditRecordDetail, creditRecordDetailEntity);
								creditRecordDetailEntity.setUserId(task.getUserId());
								list.add(creditRecordDetailEntity);
						    }
							jsonAll.put("creditRecordDetail",list);
						}

						//公共记录的结构数组
						if(creditInformation.getPublicInformationDetails()!=null&&!creditInformation.getPublicInformationDetails().isEmpty()){
							List list = new ArrayList();
						    for(PublicInformationDetail publicInformationDetail:creditInformation.getPublicInformationDetails()){
						    	PublicInformationEntity publicInformationEntity = new PublicInformationEntity();
								BeanUtils.copyProperties(publicInformationDetail, publicInformationEntity);
								publicInformationEntity.setUserId(task.getUserId());
								list.add(publicInformationEntity);
						    }
							jsonAll.put("publicInformation",list);
						}

						//查询记录数组, 这部分包含信用报告最近2年内被查询的记录
						if(creditInformation.getQueryInformationDetails()!=null&&!creditInformation.getQueryInformationDetails().isEmpty()){
							List list = new ArrayList();
						    for(QueryInformationDetail queryInformationDetail:creditInformation.getQueryInformationDetails()){
						    	QueryInformationEntity queryInformationEntity = new QueryInformationEntity();
								BeanUtils.copyProperties(queryInformationDetail, queryInformationEntity);
								queryInformationEntity.setUserId(task.getUserId());
								list.add(queryInformationEntity);
						    }
							jsonAll.put("queryInformation",list);
						}

						//信用卡记录详细信息析记录数组
						if(creditInformation.getCreditCardRecordDetailAnalyzes()!=null&&!creditInformation.getCreditCardRecordDetailAnalyzes().isEmpty()){
							List list = new ArrayList();
						    for(CreditCardRecordDetailAnalyze creditCardRecordDetailAnalyze:creditInformation.getCreditCardRecordDetailAnalyzes()){
						    	CreditCardRecordDetailAnalyzeEntity creditCardRecordDetailAnalyzeEntity = new CreditCardRecordDetailAnalyzeEntity();
								BeanUtils.copyProperties(creditCardRecordDetailAnalyze, creditCardRecordDetailAnalyzeEntity);
								creditCardRecordDetailAnalyzeEntity.setUserId(task.getUserId());
								list.add(creditCardRecordDetailAnalyzeEntity);
						    }
							jsonAll.put("creditCardRecordDetailAnalyze",list);
						}

						//购房贷款记录详细信息析记录数组
						if(creditInformation.getHousingLoanRecordDetailAnalyzes()!=null&&!creditInformation.getHousingLoanRecordDetailAnalyzes().isEmpty()){
							List list = new ArrayList();
						    for(HousingLoanRecordDetailAnalyze housingLoanRecordDetailAnalyze:creditInformation.getHousingLoanRecordDetailAnalyzes()){
						    	HousingLoanRecordDetailAnalyzeEntity housingLoanRecordDetailAnalyzeEntity = new HousingLoanRecordDetailAnalyzeEntity();
								BeanUtils.copyProperties(housingLoanRecordDetailAnalyze, housingLoanRecordDetailAnalyzeEntity);
								housingLoanRecordDetailAnalyzeEntity.setUserId(task.getUserId());
								list.add(housingLoanRecordDetailAnalyze);
						    }
							jsonAll.put("housingLoanRecordDetailAnalyze",list);
						}

						//其他贷款记录详细信息析记录数组
						if(creditInformation.getOtherLoanRecordDetailAnalyzes()!=null&&!creditInformation.getOtherLoanRecordDetailAnalyzes().isEmpty()){
							List list = new ArrayList();
						    for(OtherLoanRecordDetailAnalyze otherLoanRecordDetailAnalyze:creditInformation.getOtherLoanRecordDetailAnalyzes()){
						    	OtherLoanRecordDetailAnalyzeEntity otherLoanRecordDetailAnalyzeEntity = new OtherLoanRecordDetailAnalyzeEntity();
								BeanUtils.copyProperties(otherLoanRecordDetailAnalyze, otherLoanRecordDetailAnalyzeEntity);
								otherLoanRecordDetailAnalyzeEntity.setUserId(task.getUserId());
								list.add(otherLoanRecordDetailAnalyzeEntity);
						    }
							jsonAll.put("otherLoanRecordDetailAnalyze",list);
						}

						String reports = saveToOss(jsonAll,dUser.getId());

						DUserAuthData dUserAuthData = new DUserAuthData();
						dUserAuthData.setUserId(dUser.getId());
						dUserAuthData.setZhengxinData(reports);
						clUserAuthDataService.updateUserAuthDataForzhengxin(dUserAuthData);

						Long taskTime = DateUtil.distance(startTime, DateUtil.getCurrentDate());
						LOGGER.info(String.format("征信任务耗时 %s,taskId=%s,reportNo=%s", taskTime.toString(), task.getTaskId(), task.getReportNo()));
					}

				} catch (Exception e) {
					LOGGER.error("zhengxin callback error，mappingId="+task.getMappingId(),e);
				}

			}
		}
		);

	}

	private String saveToOss(JSON json, Long userId){
		String path = "zhengxin/data/";
		return  this.ossUtils.upJason(json.toJSONString(),path,FsUtils.s(userId));
	}


}
