package com.hwc.framework.modules.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.bo.request.MjRequest;
import com.hwc.framework.modules.dao.CLChannelAppMapper;
import com.hwc.framework.modules.dao.CLOpinionMapper;
import com.hwc.framework.modules.dao.MjParkingInfoMapper;
import com.hwc.framework.modules.dao.MjParkingInfoUserMapper;
import com.hwc.framework.modules.domain.CLChannelApp;
import com.hwc.framework.modules.domain.CLOpinion;
import com.hwc.framework.modules.domain.MjParkingInfo;
import com.hwc.framework.modules.domain.MjParkingInfoUser;

import io.swagger.annotations.ApiOperation;

/**马甲包接口
 */
@RestController
@RequestMapping("/api/p2p/vestWrap")
public class VestWrapController {
	
	@Autowired
	private MjParkingInfoMapper mjParkingInfoMapper;
	
	@Autowired
	private CLChannelAppMapper cLChannelAppMapper;
	
	@Autowired
	private CLOpinionMapper cLOpinionMapper;
	
	@Autowired
	private MjParkingInfoUserMapper mjParkingInfoUserMapper;
	
	
	//首页
	//跑马灯 姓氏
	@RequestMapping("/surnameInfos")
    @ApiOperation(value = "姓氏显示")
    public Response<List<String>> getSurnameInfos() {
		String info = "恭喜高**已租到公元里的车位";
		List<String> list = new ArrayList<String>();
		String[] surname = {"李","王","张","刘","陈","杨","赵","黄","周","吴",
							"徐","孙","胡","朱","高","林","何","郭","马","罗",
							"梁","宋","郑","谢","韩","唐","冯","于","董","萧",
							"程","曹","袁","邓","许","傅","沈","曾","彭","吕",
							"苏","卢","蒋","蔡","贾","丁","魏","薛","叶","阎",
							"余","潘","杜","戴","夏","钟","汪","田","任","姜",
							"范","方","石","姚","谭","廖","邹","熊","金","陆",
							"郝","孔","白","崔","康","毛","邱","秦","江","史",
							"顾","侯","邵","孟","龙","万","段","漕","钱","汤",
							"尹","黎","易","常","武","乔","贺","赖","龚","文"};
		for (int i = 0; i < 100; i++) {
			String name = surname[i];
			String mSg = info.replace("高",name);
			list.add(mSg);
		}
        return Response.success(list);
    }
	
	//热门 和 附近 车位出租信息 parkingPosition
	@RequestMapping("/parkingPositionInfos")
    @ApiOperation(value = "车位信息")
    public Response<List<MjParkingInfo>> getParkingPositionInfos(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getPage()||mjRequest.getPage().equals("")
				||null == mjRequest.getPageSize()||mjRequest.getPage().equals("")
				||null == mjRequest.getDemandIsHot()||mjRequest.getDemandIsHot().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		PageHelper.startPage(Integer.valueOf(mjRequest.getPage()), Integer.valueOf(mjRequest.getPageSize()));
		Map<String,Object> map = new HashMap<>();
		map.put("isDemand", "2");//出租的  
		map.put("demandIsHot", mjRequest.getDemandIsHot());//热门 还是附近
		List<MjParkingInfo> findInfos = mjParkingInfoMapper.findInfos(map);
		//查信息
		return Response.success(findInfos);
	}   
	
	//车位的详细信息    永琪
	@RequestMapping("/detailedParkingPositionInfos")
    @ApiOperation(value = "详细的车位信息")
    public Response<MjParkingInfo> detailedParkingPositionInfos(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getId()||mjRequest.getId().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		MjParkingInfo selectByPrimaryKey = mjParkingInfoMapper.selectByPrimaryKey(Long.valueOf(mjRequest.getId()));
		//查信息
		return Response.success(selectByPrimaryKey);
	}   
	
	//发布
	//热门发布需求
	@RequestMapping("/selectDemandInfos")
    @ApiOperation(value = "车位需求信息")
    public Response<List<MjParkingInfo>> getDemandInfos(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getPage()||mjRequest.getPage().equals("")
				||null == mjRequest.getPageSize()||mjRequest.getPage().equals("")
				||null == mjRequest.getUserId()
				) {
			return Response.fail("缺少必要参数！");
		}
		PageHelper.startPage(Integer.valueOf(mjRequest.getPage()), Integer.valueOf(mjRequest.getPageSize()));
		Map<String,Object> map = new HashMap<>();
		//查信息需求信息
		map.put("isDemand", "1");
		List<MjParkingInfo> findInfos = mjParkingInfoMapper.findInfos(map);
		Map<String,Object> map1 = new HashMap<>();
		//查用户收藏信息
		map1.put("userId", mjRequest.getUserId());
		List<MjParkingInfoUser> findInfos2 = mjParkingInfoUserMapper.findInfos(map1);
		if(findInfos2 != null && findInfos != null && findInfos.size()>0){
			for (int i = 0; i <findInfos.size() ; i++) {
				for (int j = 0; j < findInfos2.size(); j++) {
					if(findInfos.get(i).getId().equals(findInfos2.get(j).getMjparkinginfoid())){
						findInfos.get(i).setIsCollect("1");
						findInfos.get(i).setCollectId(String.valueOf(findInfos2.get(j).getId()));
						break;
					}
				}
				if(null == findInfos.get(i).getIsCollect()){
					findInfos.get(i).setIsCollect("2");
				}
			}
			return Response.success(findInfos);
		}
		Map<String,Object> map2 = new HashMap<>();
		map2.put("items", "");
		return Response.success(map2);
	}   
	//保存发布需求   （用户id）     永琪
	@RequestMapping("/insertDemandInfos")
    @ApiOperation(value = "保存车位需求信息")
    public Response insertDemandInfos(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getUserId()||mjRequest.getUserId().equals("")
				||null == mjRequest.getParkingPosition()||mjRequest.getParkingPosition().equals("")
				||null == mjRequest.getPrice()||mjRequest.getPrice().equals("")
				||null == mjRequest.getPhone()||mjRequest.getPhone().equals("")
				||null == mjRequest.getName()||mjRequest.getName().equals("")
				||null == mjRequest.getDemandLabel()||mjRequest.getDemandLabel().equals("")
				||null == mjRequest.getVillageName()||mjRequest.getVillageName().equals("")
				) {
			return Response.fail("缺少必要参数！");
		}
		MjParkingInfo mjParkingInfo = new MjParkingInfo();
		mjParkingInfo.setUserId(Long.valueOf(mjRequest.getUserId()));
		mjParkingInfo.setParkingPosition(mjRequest.getParkingPosition());
		mjParkingInfo.setPrice(Long.valueOf(mjRequest.getPrice()));
		mjParkingInfo.setPhone(mjRequest.getPhone());
		mjParkingInfo.setName(mjRequest.getName());
		mjParkingInfo.setDemandLabel(mjRequest.getDemandLabel());
		mjParkingInfo.setRemark(mjRequest.getRemark());
		mjParkingInfo.setVillageName(mjRequest.getVillageName());
		mjParkingInfo.setIsDemand("1");//需求
		mjParkingInfo.setCreateTime(new Date());
		//保存需求信息
		int insertSelective = mjParkingInfoMapper.insertSelective(mjParkingInfo);
		if(insertSelective == 0){
			return Response.fail("保存失败！");
		}
		return Response.success("保存成功!");
	}   
	
	//我的
	//我的发布  根据userId 查发布    永琪
	@RequestMapping("/selectDemandInfosByUserId")
    @ApiOperation(value = "查发布的车位需求信息")
    public Response<List<MjParkingInfo>> selectDemandInfosByUserId(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getPage()||mjRequest.getPage().equals("")
				||null == mjRequest.getPageSize()||mjRequest.getPage().equals("")
				||null == mjRequest.getUserId()||mjRequest.getUserId().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		PageHelper.startPage(Integer.valueOf(mjRequest.getPage()), Integer.valueOf(mjRequest.getPageSize()));
		Map<String,Object> map = new HashMap<>();
		//根据userId 查询
		map.put("userId", mjRequest.getUserId());
		List<MjParkingInfo> findInfos = mjParkingInfoMapper.findInfos(map);
		if(findInfos != null && findInfos.size()>0){
			for (MjParkingInfo mjParkingInfo : findInfos) {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
				String str=sdf.format(mjParkingInfo.getCreateTime()); 
				mjParkingInfo.setCreateTime1(str);
			}
			return Response.success(findInfos);
		}
		return Response.success("用户没有发布信息");
	}   
	
	//发布详情
	@RequestMapping("/selectDemandInfosById")
    @ApiOperation(value = "查发布的车位需求详细信息")
    public Response selectDemandInfosById(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getId()||mjRequest.getId().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		MjParkingInfo selectByPrimaryKey = mjParkingInfoMapper.selectByPrimaryKey(Long.valueOf(mjRequest.getId()));
		if (null == selectByPrimaryKey) {
			return Response.fail("没有对应的信息！");
		}
		return Response.success(selectByPrimaryKey);
	}   
	
	
	//撤销发布  根据id 删除      永琪
	@RequestMapping("/deleteDemandInfosByUserId")
    @ApiOperation(value = "删除发布的车位需求信息")
    public Response deleteDemandInfosByUserId(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getId()||mjRequest.getId().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		int deleteByPrimaryKey = mjParkingInfoMapper.deleteByPrimaryKey(Long.valueOf(mjRequest.getId()));
			if(deleteByPrimaryKey == 0){
				return Response.fail("没有对应的车位信息");
			}else{
				return Response.success();
			}
	}   
	//意见反馈
	@RequestMapping("/insertFeedback")
    @ApiOperation(value = "意见反馈")
    public Response insertFeedback(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getContent()||mjRequest.getContent().equals("")
				||null == mjRequest.getUserId()||mjRequest.getUserId().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		CLOpinion cLOpinion = new CLOpinion();
		cLOpinion.setOpinion(mjRequest.getContent());
		cLOpinion.setUserId(Long.valueOf(mjRequest.getUserId()));
		cLOpinion.setCreateTime(new Date());
		int insert = cLOpinionMapper.insert(cLOpinion);
		return Response.success(insert);
	}   
	
	/**
	 * 收藏
	 * @param response 
	 * @param mjParkingInfoId   马甲车位信息 id
	 * @param userId			用户id 
	 * @return
	 */
	@RequestMapping("/collect")
    @ApiOperation(value = "收藏")
    public Response collect(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getMjParkingInfoId()||mjRequest.getMjParkingInfoId().equals("")
				||null == mjRequest.getUserId()||mjRequest.getUserId().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mjParkingInfoId", mjRequest.getMjParkingInfoId());
		map.put("userId", mjRequest.getUserId());
		map.put("type", "1");
		List<MjParkingInfoUser> findInfos = mjParkingInfoUserMapper.findInfos(map);
		if (findInfos != null&&findInfos.size()>0) {
			return Response.fail("已经收藏过了");
		}
		if(null == mjParkingInfoMapper.selectByPrimaryKey(Long.valueOf(mjRequest.getMjParkingInfoId()))){
			return Response.fail("没有对应的车位信息！");
		}
		MjParkingInfoUser mjParkingInfoUser = new MjParkingInfoUser();
		mjParkingInfoUser.setMjparkinginfoid(Long.valueOf(mjRequest.getMjParkingInfoId()));
		mjParkingInfoUser.setUserid(Long.valueOf(mjRequest.getUserId()));
		mjParkingInfoUser.setType("1");
		mjParkingInfoUser.setCreatetime(new Date());
		int insert = mjParkingInfoUserMapper.insert(mjParkingInfoUser);
		if (insert>0) {
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("mjParkingInfoId", mjRequest.getMjParkingInfoId());
			map1.put("userId", mjRequest.getUserId());
			map1.put("type", "1");
			List<MjParkingInfoUser> findInfos2 = mjParkingInfoUserMapper.findInfos(map1);
			Map<String,Object> map2 = new HashMap<String,Object>();
			map2.put("collectId", String.valueOf(findInfos2.get(0).getId()));
			return Response.success(map2);
		}
		return Response.fail("保存失败");
	} 
	
	// 删除收藏   
	@RequestMapping("/deleteCollect")
    @ApiOperation(value = "删除收藏的车位需求信息")
    public Response deleteCollect(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getCollectId()||mjRequest.getCollectId().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		int deleteByPrimaryKey = mjParkingInfoUserMapper.deleteByPrimaryKey(Long.valueOf(mjRequest.getCollectId()));
			if(deleteByPrimaryKey == 0){
				return Response.fail("没有对应的收藏信息");
			}else{
				return Response.success("删除成功");
			}
	}   
	
	
	/**
	 * 我想要       永琪
	 * @param response 
	 * @param mjParkingInfoId   马甲车位信息 id
	 * @param userId			用户id 
	 * @return
	 */
	@RequestMapping("/purchase")
    @ApiOperation(value = "购买")
    public Response purchase(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getMjParkingInfoId()||mjRequest.getMjParkingInfoId().equals("")
				||null == mjRequest.getUserId()||mjRequest.getUserId().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mjParkingInfoId", mjRequest.getMjParkingInfoId());
		map.put("userId", mjRequest.getUserId());
		map.put("type", "2");
		List<MjParkingInfoUser> findInfos = mjParkingInfoUserMapper.findInfos(map);
		if (findInfos != null&&findInfos.size()>0) {
			return Response.fail("您已提交过申请，请耐心等待工作人员与您联系！");
		}
		MjParkingInfoUser mjParkingInfoUser = new MjParkingInfoUser();
		mjParkingInfoUser.setMjparkinginfoid(Long.valueOf(mjRequest.getMjParkingInfoId()));
		mjParkingInfoUser.setUserid(Long.valueOf(mjRequest.getUserId()));
		mjParkingInfoUser.setType("2");
		mjParkingInfoUser.setCreatetime(new Date());
		int insert = mjParkingInfoUserMapper.insert(mjParkingInfoUser);
		if(insert == 0){
			return Response.fail("购买失败！");
		}else{
			return Response.success("购买成功");
		}
	} 
	
	//     永琪
	@RequestMapping("/myCollect")
    @ApiOperation(value = "我的收藏")
    public Response<List<MjParkingInfo>> myCollect(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getUserId()||mjRequest.getUserId().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		Map<String,Object> map = new HashMap<>();
		//根据userId 查询
		map.put("userId", mjRequest.getUserId());
		map.put("type", "1");
		List<MjParkingInfoUser> findInfos = mjParkingInfoUserMapper.findInfos(map);
		List<MjParkingInfo> list = new ArrayList<MjParkingInfo>();
		if (findInfos != null&&findInfos.size()==0) {
			return Response.success("没有收藏信息！");
		}
		if(findInfos != null && findInfos.size()>0){
			for (int i = 0; i < findInfos.size(); i++) {
				MjParkingInfo selectByPrimaryKey = mjParkingInfoMapper.selectByPrimaryKey(findInfos.get(i).getMjparkinginfoid());
				if(null == selectByPrimaryKey){
					return Response.fail("没有找到对应的车位信息！");
				}
				selectByPrimaryKey.setCollectId(String.valueOf(findInfos.get(i).getId()));
				list.add(selectByPrimaryKey);
			}
			return Response.success(list);
		}else{
			return Response.success("没有收藏信息！");
		}
	}   
	
	//审核状态
	/**
	 *  ios
	 * @param response
	 * @param version   版本号
	 * @param type      类型  ios20  安卓30   40 iso审核版本    50 安卓审核版本
	 * @return
	 */
	@RequestMapping("/approvalStatus")
    @ApiOperation(value = "app审核状态")
    public Response approvalStatus(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getType()||mjRequest.getType().equals("")
				||null == mjRequest.getVersion()||mjRequest.getVersion().equals("")) {
			return Response.fail("缺少必要参数！");
		}
		Map<String,Object> map = new HashMap<>();
		Map<String, Object> returnMap = new HashMap<>();
		map.put("appType", mjRequest.getType());
		List<CLChannelApp> findInfos = cLChannelAppMapper.findInfos(map);
		if(findInfos != null && findInfos.size()>0){
			//当传进来的版本等于库中审核版本     进马甲包
			String latesVersion = findInfos.get(0).getLatestVersion();
			if(latesVersion.compareTo(mjRequest.getVersion())==0){
				returnMap.put("state", "1");
				return Response.success(returnMap);
			}
		}
		returnMap.put("state", "2");
		return Response.success(returnMap);
	} 
	
	
	/**
	 *  Android
	 * @param response
	 * @param version   版本号
	 * @param type      类型  40 iso审核版本    50 安卓审核版本
	 * @return
	 */
	@RequestMapping("/approvalStatusAndroid")
    @ApiOperation(value = "app审核状态")
    public Response approvalStatusAndroid(	
    		HttpServletResponse response,
    		@RequestBody MjRequest mjRequest
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if (null == mjRequest.getType()||mjRequest.getType().equals("")
				||null == mjRequest.getVersion()||mjRequest.getVersion().equals("")
				||null == mjRequest.getChannelId()||mjRequest.getChannelId().equals("")
				) {
			return Response.fail("缺少必要参数！");
		}
		Map<String,Object> map = new HashMap<>();
		Map<String, Object> returnMap = new HashMap<>();
		map.put("appType", mjRequest.getType());
		map.put("channelId", mjRequest.getChannelId());
		List<CLChannelApp> findInfos = cLChannelAppMapper.findInfos(map);
		if(findInfos != null && findInfos.size()>0){
			//当传进来的版本等于库中审核版本     进马甲包
			String latesVersion = findInfos.get(0).getLatestVersion();
			if(latesVersion.compareTo(mjRequest.getVersion())==0){
				returnMap.put("state", "1");
				return Response.success(returnMap);
			}
		}
		returnMap.put("state", "2");
		return Response.success(returnMap);
	} 
	
	
	public static void main(String[] args) {
//		String a = "2.0"; 
//		String b = "2.0";
//		int compareTo = a.compareTo(b);
//		System.out.println(compareTo);
		
//		List list = new ArrayList<>();
		List list = null;
		System.out.println(list.size());
		
	}
	
	
}
