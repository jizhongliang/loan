package com.hwc.framework.modules.third.business.carrier.sevice;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserAuthData;
import com.hwc.framework.modules.service.ClUserAuthDataService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.framework.modules.third.business.carrier.api.CarrierClient;
import com.hwc.framework.modules.third.business.carrier.billitem.CarrierBillTask;
import com.hwc.framework.modules.third.business.carrier.dto.FamilyMember;
import com.hwc.framework.modules.third.business.carrier.dto.PackageItem;
import com.hwc.framework.modules.third.business.carrier.dto.union.*;
import com.hwc.framework.modules.third.business.carrier.entity.*;
import com.hwc.framework.modules.third.util.DateUtil;
import com.hwc.framework.modules.utils.OSSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 运营商处理类
 * 手机号是基本单位
 * 1、基本信息
 * 2、套餐记录
 * 3、账单记录
 * 4、通话详情
 * 5、短信详情
 * 6、充值记录
 * 7、亲情网记录
 * ClassName: CarrierBillService
 * date: 2016年7月20日 下午5:35:35
 *
 * @author yuandong
 * @since JDK 1.6
 */
@Service
public class CarrierBillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarrierBillService.class);

    @Autowired
    private CarrierClient carrierClient;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ClUserService clUserService;

    @Autowired
    private ClUserAuthDataService clUserAuthDataService;

    @Autowired
    private OSSUtils ossUtils;

    public void fetchBill(final CarrierBillTask task) {
        // 这里交给线程池处理，防止下面的业务处理时间太长，导致超时。
        // 超时会导致魔蝎数据进行重试，会收到重复的回调请求
        taskExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            UnionDataV3 mxData = carrierClient.getMxData(task.getMobile(), task.getTaskId());
                            if (mxData != null) {
                                String userId = task.getMobile();
                                DUser dUser = clUserService.getUserByUuid(task.getUserId());

                                JSONObject jsonAll = new JSONObject();

                                LOGGER.info("开始-处理用户数据: {}", task.getTaskId());
                                //1、处理基本信息
                                jsonAll.put("mobileBasic",saveMobileBasic(task, mxData));
                                //后面的查询最近6个月的数据,这里根据自己的业务来
                                String fromMonth = DateUtil.getStrFromDate(DateUtil.addMonth(DateUtil.getCurrentDate(), -6), "yyyy-MM");
                                String toMonth = DateUtil.getStrFromDate(DateUtil.getCurrentDate(), "yyyy-MM");
                                //2、套餐记录(一般情况下，第一次爬取只会有当月的套餐。如果历史爬取过，会有历史的)
                                jsonAll.put("basic",  savePackageUsage(task, mxData.getPackages()));
                                //6、充值记录
                                jsonAll.put("recharges", saveRecharge(task, mxData.getRecharges()));
                                //7、亲情网
                                jsonAll.put("families",saveFamily(task, mxData.getFamilies()));
                                //3、账单记录
                                jsonAll.put("bill", saveBill(task, mxData.getBills()));
                                //4、通话详情
                                jsonAll.put("calls", saveCall(task, mxData.getCalls()));
                                //5、短信详情
                                jsonAll.put("sms",saveSms(task, mxData.getSmses()));
                                //
                                jsonAll.put("net",saveNet(task, mxData.getNets()));
                                LOGGER.info("结束-处理用户数据: {}", task.getTaskId());
                                if (dUser !=null){
                                    userId = dUser.getId().toString();
                                }
                                String reports = saveToOss(jsonAll, FsUtils.l(userId));

                                DUserAuthData dUserAuthData = new DUserAuthData();
                                dUserAuthData.setPhoneData(reports);
                                dUserAuthData.setUserId(dUser.getId());
                                clUserAuthDataService.updateUserAuthDataForPhone(dUserAuthData);
                            }

                        } catch (Exception e) {
                            LOGGER.error("fetchBill failed. task:{}", task.getTaskId(), e);
                        }
                    }
                }
        );
    }

    private MobileBasicEntity saveMobileBasic(CarrierBillTask task, UnionDataV3 mxData) {
        try {
            MobileBasicEntity mobileBasicEntity = loadMobileBasicByCallApi(task.getUserId(), mxData);
            return mobileBasicEntity;
        } catch (Exception e) {
            LOGGER.error("saveMobileBasic error,task:{}", task.getTaskId(), e);
        }
        return null;
    }


    private MobileBasicEntity loadMobileBasicByCallApi(String userId, UnionDataV3 mxData) throws IOException {
        if (mxData != null) {
            MobileBasicEntity basicEntity = new MobileBasicEntity();
            BeanUtils.copyProperties(mxData, basicEntity);
            basicEntity.setUserId(userId);
            if (mxData.getOpenTime() != null) {
                basicEntity.setOpenTime(DateUtil.getDateFromString(mxData.getOpenTime(), "yyyy-MM-dd"));
            }
            return basicEntity;
        }
        return null;
    }


    private List<PackageUsageEntity> savePackageUsage(CarrierBillTask task, List<UnionPackageUsage> packageUsages) {
        try {

            if (packageUsages != null && !packageUsages.isEmpty()) {
                //删除这个月的此用户此手机号的套餐信息
                List<PackageUsageEntity> packageuseList = new ArrayList<>();
                for (UnionPackageUsage packageUsage : packageUsages) {
                    if (packageUsage.getItems() != null && !packageUsage.getItems().isEmpty()) {

                        //新增这个月的套餐信息
                        PackageUsageEntity packageUsageEntity = new PackageUsageEntity();
                        for (PackageItem packageItem : packageUsage.getItems()) {
                            BeanUtils.copyProperties(packageItem, packageUsageEntity);
                            packageUsageEntity.setMobile(task.getMobile());
                            packageUsageEntity.setUserId(task.getUserId());
//							packageUsageEntity.setBillMonth(packageUsage.getBillMonth());
                            packageUsageEntity.setBillStartDate(packageUsage.getBillStartDate());
                            packageUsageEntity.setBillEndDate(packageUsage.getBillEndDate());
                            packageuseList.add(packageUsageEntity);
                        }
                    }
                }
                return packageuseList;
            }

        } catch (Exception e) {
            LOGGER.error("savePackageUsage failed. taskId:{}", task.getTaskId(), e);
        }
        return null;
    }


    private List<MobileBillEntity> saveBill(CarrierBillTask task, List<UnionBill> bills) {
        List<MobileBillEntity> packageuseList = new ArrayList<>();
        try {
            if (bills != null && !bills.isEmpty()) {
                for (UnionBill mobileBill : bills) {
                    MobileBillEntity mobileBillEntity = new MobileBillEntity();
                    BeanUtils.copyProperties(mobileBill, mobileBillEntity);
                    mobileBillEntity.setUserId(task.getUserId());
                    mobileBillEntity.setMobile(task.getMobile());
                    packageuseList.add(mobileBillEntity);
                }
            }
        } catch (Exception e) {
            LOGGER.error("saveBill failed. taskId:{}", task.getTaskId(), e);
        }
        return packageuseList;
    }


    private List<MobileVoiceCallEntity> saveCall(CarrierBillTask task, List<UnionVoiceCall> calls) {
        List<MobileVoiceCallEntity> mobileVoiceCalls = new ArrayList<>();
        try {
            if (calls != null && !calls.isEmpty()) {
                for (UnionVoiceCall mobileVoiceCallDetail : calls) {
                    if (mobileVoiceCallDetail != null && !mobileVoiceCallDetail.getVoiceCallItems().isEmpty()) {
                        for (UnionVoiceCallItem mobileVoiceCall : mobileVoiceCallDetail.getVoiceCallItems()) {
                            MobileVoiceCallEntity mobileVoiceCallEntity = new MobileVoiceCallEntity();
                            BeanUtils.copyProperties(mobileVoiceCall, mobileVoiceCallEntity);
                            mobileVoiceCallEntity.setUserId(task.getUserId());
                            mobileVoiceCallEntity.setMobile(task.getMobile());
                            mobileVoiceCallEntity.setBillMonth(mobileVoiceCallDetail.getBillMonth());
                            mobileVoiceCalls.add(mobileVoiceCallEntity);
                        }

                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("saveCall failed. taskId:{}", task.getTaskId(), e);
        }
        return mobileVoiceCalls;
    }


    private  List<MobileSmsEntity> saveSms(CarrierBillTask task, List<UnionShortMessage> shortMessages) {
        List<MobileSmsEntity> smsList = new ArrayList<>();
        try {
            if (shortMessages != null && !shortMessages.isEmpty()) {
                for (UnionShortMessage mobileSmsDetail : shortMessages) {
                    if (mobileSmsDetail != null && !mobileSmsDetail.getShortMessageItems().isEmpty()) {
                        for (UnionShortMessageItem mobileSms : mobileSmsDetail.getShortMessageItems()) {
                            MobileSmsEntity mobileSmsEntity = new MobileSmsEntity();
                            BeanUtils.copyProperties(mobileSms, mobileSmsEntity);
                            mobileSmsEntity.setUserId(task.getUserId());
                            mobileSmsEntity.setMobile(task.getMobile());
                            mobileSmsEntity.setBillMonth(mobileSmsDetail.getBillMonth());
                            smsList.add(mobileSmsEntity);
                        }
                    }

                }
            }
        } catch (Exception e) {
            LOGGER.error("saveSms failed. taskId:{}", task.getTaskId(), e);
        }
        return smsList;
    }

    private List<NetFlowEntity> saveNet(CarrierBillTask task, List<UnionNetFlow> nets) {
        List<NetFlowEntity> netflows = new ArrayList<>();
        try {
            if (nets != null && !nets.isEmpty()) {
                for (UnionNetFlow netFlow : nets) {
                    if (netFlow != null && !netFlow.getNetFlowItems().isEmpty()) {
                        for (UnionNetFlowItem netFlowItem : netFlow.getNetFlowItems()) {
                            NetFlowEntity netFlowEntity = new NetFlowEntity();
                            BeanUtils.copyProperties(netFlowItem, netFlowEntity);
                            netFlowEntity.setUserId(task.getUserId());
                            netFlowEntity.setMobile(task.getMobile());
                            netFlowEntity.setBillMonth(netFlow.getBillMonth());
                            netflows.add(netFlowEntity);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("saveNet failed. taskId:{}", task.getTaskId(), e);
        }
        return netflows;
    }

    private List<MobileRechargeEntity> saveRecharge(CarrierBillTask task, List<UnionRecharge> mobileRecharges) {
        List<MobileRechargeEntity> rechargeList = new ArrayList<>();
        try {
            if (mobileRecharges != null && !mobileRecharges.isEmpty()) {
                for (UnionRecharge mobileRecharge : mobileRecharges) {
                    if (mobileRecharge != null) {
                        MobileRechargeEntity mobileRechargeEntity = new MobileRechargeEntity();
                        BeanUtils.copyProperties(mobileRecharge, mobileRechargeEntity);
                        mobileRechargeEntity.setUserId(task.getUserId());
                        mobileRechargeEntity.setMobile(task.getMobile());
                        rechargeList.add(mobileRechargeEntity);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("saveRecharge failed. taskId:{}", task.getTaskId(), e);
        }
        return rechargeList;
    }


    /**
     * saveFamily:保存和此手机号关联的亲情网号码 <br/>
     * TODO(这里描述这个方法适用条件 – 可选).<br/>
     * TODO(这里描述这个方法的执行流程 – 可选).<br/>
     * TODO(这里描述这个方法的使用方法 – 可选).<br/>
     * TODO(这里描述这个方法的注意事项 – 可选).<br/>
     *
     * @param task
     * @author yuandong
     * @since JDK 1.6
     */
    private ArrayList<FamilyMemberEntity> saveFamily(CarrierBillTask task, List<UnionFamilyNet> familyNets) {
        ArrayList<FamilyMemberEntity> list = new ArrayList<>();
        try {
            if (familyNets != null && !familyNets.isEmpty()) {
                for (UnionFamilyNet familyNet : familyNets) {
                    if (familyNet.getFamilyMembers() != null && !familyNet.getFamilyMembers().isEmpty()) {
                        for (FamilyMember familyMember : familyNet.getFamilyMembers()) {
                            if (familyMember.getLongNumber() != null && familyMember.getShortNumber() != null && familyMember.getMemberType() != null) {
                                FamilyMemberEntity familyMemberEntity = new FamilyMemberEntity();
                                BeanUtils.copyProperties(familyMember, familyMemberEntity);
                                familyMemberEntity.setUserId(task.getUserId());
                                familyMemberEntity.setMobile(task.getMobile());
                                familyMemberEntity.setFamilyNetNum(familyNet.getFamilyNetNum());
                                list.add(familyMemberEntity);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("saveRecharge failed. taskId:{}", task.getTaskId(), e);
        }
        return list;
    }

    private String saveToOss(JSON json, Long userId){
        String path = "carrier/data/";
        return this.ossUtils.upJason(json.toJSONString(),path,FsUtils.s(userId));
    }

}
