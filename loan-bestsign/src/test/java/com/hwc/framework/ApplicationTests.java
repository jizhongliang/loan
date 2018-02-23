package com.hwc.framework;

import cn.freesoft.utils.Base64;
import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwc.framework.common.HttpUtils;
import com.hwc.framework.common.NumToCapital;
import com.hwc.framework.modules.third.BestSignService;
import net.dongliu.requests.Requests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	private String COM1 = "CW001";
	private String COM2 = "XH001";
	private String COM3 = "RZ001";
	private String USER_ID = "222333";

	@Test
	public void contextLoads() {

	}

	@Test
	public void testCharge() {
	}

	@Autowired
	private BestSignService signService;

	@Test
	public void register() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", "188");
		jsonObject.put("phone", "15355008306");
		jsonObject.put("realName", "张三分");
		JSONObject jsonObject1 = signService.register(jsonObject);
		System.out.println(jsonObject1.toJSONString());

	}

	@Test
	public void setUserIdno() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", "84");
		jsonObject.put("idNo", "421123197710032578");
		jsonObject.put("realName", "张三分");
		JSONObject jsonObject1 = signService.setUserIdno(jsonObject);
		System.out.println(jsonObject1.toJSONString());
	}

	@Test
	public void getUserInfo() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", "84");
		jsonObject.put("idNo", "421123197710032578");
		jsonObject.put("realName", "张三分");
		JSONObject jsonObject1 = signService.getUserInfo(jsonObject);
		System.out.println(jsonObject1.toJSONString());
	}

	@Test
	public void registerCompany() throws Exception {
		// final String path = "/user/reg";
		//
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", "RZ002");
		jsonObject.put("phone", "13656660307");
		jsonObject.put("realName", "中商（浙江）融资租赁有限公司");
		jsonObject.put("idNo", "91330100MA27YXXJ8L");
		jsonObject.put("text", "");
		signService.registerEnterprise(jsonObject);
		signService.setEnterpriseInfo(jsonObject);
		signService.applycert(jsonObject);
		JSONObject userimage = signService.createSignatureImage(jsonObject);
		System.out.println(userimage.toJSONString());
		////
		jsonObject.put("imageName", "");
		userimage = signService.downUserImage(jsonObject);

		System.out.println(userimage.toJSONString());

		 jsonObject.put("signatureImg",
		 "sign/rzzl.png?x-oss-process=image/resize,w_200,h_100");
		 JSONObject jsonObject1 = signService.upSignatureImage(jsonObject);
		 System.out.println(jsonObject1.toJSONString());
//		 jsonObject.put("file", "C:\\Users\\lxk\\Desktop\\车位分期租金支付计划表-30.pdf");
//		 JSONObject jsonObject2 = signService.upContract(jsonObject);
//		 System.out.println(jsonObject2.toJSONString());
		//
		// JSONObject jsonObject5 = jsonObject2.getJSONObject("data");
		// JSONObject jsonObject6 = signService.getPdfDownLoad(jsonObject5);
		// System.out.println(jsonObject6.toJSONString());
		// //1882280729014960742
		// JSONObject jsonObject3 = jsonObject2.getJSONObject("data");
		//
		// //object.put("userId", "c_C1000");
		// jsonObject.put("fid", jsonObject3.getString("fid"));
		//// object.put("idNo", "330327198801012365");
		//// object.put("realName", "张宝");
		//// object.put("address", "浙江省杭州市西湖区文三路1098号");
		// JSONObject object = new JSONObject();
		//
		//
		// object.put("idNo", "330327198801012365");
		// object.put("realName", "张宝");
		// object.put("address", "浙江省杭州市西湖区文三路1098号");
		// object.put("amount", 10000);
		// object.put("amount_descript", NumToCapital.arabNumToChineseRMB(10000));
		// object.put("begin_year", "2017");
		// object.put("begin_month", "12");
		// object.put("begin_day", "14");
		// object.put("end_year", "2018");
		// object.put("end_month", "12");
		// object.put("end_day", "14");
		// object.put("borrow_day", "365");
		// object.put("first_repay_year", "2018");
		// object.put("first_repay_month", "1");
		// object.put("first_repay_day", "14");
		// object.put("rate", "0.05%");
		// object.put("scene", "旅游");
		// object.put("loan_date", "2017-12-14");
		// object.put("borrow_date", "2017-12-15");
		// object.put("loan3_date", "2017-12-15");
		// object.put("loan4_date", "2017-12-15");
		// jsonObject.put("elements", getJosn(object));
		// JSONObject jsonObject4 = signService.addPDFElements(jsonObject);
		// System.out.println(jsonObject4.toJSONString());
		// JSONObject jsonObject5 = jsonObject4.getJSONObject("data");
		// JSONObject jsonObject6 = signService.getPdfDownLoad(jsonObject5);
		// System.out.println(jsonObject6.toJSONString());

	}

	@Test
	public void applyCert() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", "188");
		JSONObject jsonObject1 = signService.applycert(jsonObject);
		System.out.println(jsonObject1.toJSONString());
	}

	@Test
	public void upimage1() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", "CAIWEI01");
		jsonObject.put("signatureImg",
				"data/image/signatureImg_41140319911215723X_118.jpg?x-oss-process=image/resize,w_200,h_100");
		JSONObject jsonObject1 = signService.upSignatureImage(jsonObject);
		System.out.println(jsonObject1.toJSONString());
	}

	@Test
	public void upimage() throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("userId", "22233");
		jsonObject.put("signatureImg",
				"data/image/1513222018889137signature.png?x-oss-process=image/resize,w_200,h_100");
		JSONObject jsonObject1 = signService.upSignatureImage(jsonObject);
		System.out.println(jsonObject1.toJSONString());
	}

	@Test
	public void upContract() {
		JSONObject object = new JSONObject();
		object.put("userId", "RZ001");
		object.put("file", "E:\\WorkDoc\\外包提供\\信用合同\\1-融资租赁申请书.pdf");
		JSONObject jsonObject1 = signService.upContract(object);
		object.put("file", "E:\\WorkDoc\\外包提供\\信用合同\\2-融资租赁合同-最高额.pdf");
		  jsonObject1 = signService.upContract(object);
		object.put("file", "E:\\WorkDoc\\外包提供\\信用合同\\3-租赁物转让协议.pdf");
		  jsonObject1 = signService.upContract(object);
		object.put("file", "E:\\WorkDoc\\外包提供\\信用合同\\4-划款授权协议书.pdf");
		  jsonObject1 = signService.upContract(object);
		object.put("file", "E:\\WorkDoc\\外包提供\\信用合同\\5-租赁物交付确认书.pdf");
		  jsonObject1 = signService.upContract(object);
		object.put("file", "E:\\WorkDoc\\外包提供\\信用合同\\6-取回标的物同意书.pdf");
		  jsonObject1 = signService.upContract(object);
		object.put("file", "E:\\WorkDoc\\外包提供\\信用合同\\7-应收租赁款债权转让确认书.pdf");
		  jsonObject1 = signService.upContract(object);
		object.put("file", "E:\\WorkDoc\\外包提供\\信用合同\\8-租金支付计划表（二次借款单签此表即可）.pdf");
		  jsonObject1 = signService.upContract(object);
		
		System.out.println(jsonObject1.toJSONString());
	}

	@Test
	public void addPDFElements() {
		JSONObject object = new JSONObject();
		object.put("userId", COM1);
		object.put("fid", "1893235173193941602");
		object.put("idNo", "330327198801012365");
		object.put("num", " XYD771709171229ADK6");
		object.put("realName", "张宝");
		object.put("address", "浙江省杭州市西湖区文三路1098号");
		object.put("amount", 10000);
		object.put("amount_descript", NumToCapital.arabNumToChineseRMB(10000));
		object.put("begin_year", "2017");
		object.put("begin_month", "12");
		object.put("begin_day", "14");
		object.put("end_year", "2018");
		object.put("end_month", "12");
		object.put("end_day", "14");
		object.put("borrow_day", "365");
		object.put("first_repay_year", "2018");
		object.put("first_repay_month", "1");
		object.put("first_repay_day", "14");
		object.put("rate", "0.05%");
		object.put("scene", "旅游");
		object.put("loan_date", "2017-12-14");
		object.put("borrow_date", "2017-12-15");
		object.put("loan3_date", "2017-12-15");
		object.put("loan4_date", "2017-12-15");
		String jdImage = "XINHUAJINDIAN0101_780.png";
		object.put("loan3_iamge", getImage(jdImage));

		object.put("elements", getJosn(object));
		JSONObject jsonObject1 = signService.addPDFElements(object);
		System.out.println(jsonObject1.toJSONString());
		// 1881658200902599272
		if (jsonObject1.getInteger("errno").equals(0)) {
			JSONObject jsonObject = jsonObject1.getJSONObject("data");
			JSONObject jsonObject2 = signService.getPdfDownLoad(jsonObject);
			System.out.println(jsonObject2.toJSONString());
		} else {

		}
	}

	private String getImage(String fileName) {
		try {
			String prefix = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/";
			String key = "contract:sign:image:" + fileName;

			String url = prefix + fileName;
			HttpURLConnection connection = HttpUtils.createHttpURLConnection("GET", url);
			byte[] imageByte = HttpUtils.getResponseBytes(connection);
			String imageData = Base64.encode(imageByte);
			// Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(),
			// 3));
			// cache.set(key, expire, imageData);
			return imageData;
		} catch (IOException e) {
			e.printStackTrace();

		} catch (Exception ex) {
			// logger.error("getimage", ex);
			ex.printStackTrace();
		}
		return "";
	}

	@Test
	public void addPDFElements1() {
		JSONObject object = new JSONObject();
		object.put("userId", COM1);
		object.put("fid", "1893309170917048872");
		object.put("idNo", "330327198801012365");
		object.put("num", " HXYC201602003ZF-1");
		object.put("seq", "HXYC201602003ZF");

		object.put("amount", 1000000);
		object.put("interest1", 1000);
		object.put("total1", 1000);
		object.put("amount1", 2000);

		object.put("interest2", 2000);
		object.put("total2", 2000);
		object.put("amount2", 4000);

		object.put("interest3", 4000);
		object.put("total3", 4000);
		object.put("amount3", 8000);

		object.put("interest4", 4000);
		object.put("total4", 4000);
		object.put("amount4", 8000);
		object.put("rz_image", "");

		object.put("elements", getJosn1(object));
		JSONObject jsonObject1 = signService.addPDFElements(object);
		System.out.println(jsonObject1.toJSONString());
		// 1881658200902599272
		if (jsonObject1.getInteger("errno").equals(0)) {
			JSONObject jsonObject = jsonObject1.getJSONObject("data");
			JSONObject jsonObject2 = signService.getPdfDownLoad(jsonObject);
			System.out.println(jsonObject2.toJSONString());
		} else {

		}
	}

	public JSONArray getJosn1(JSONObject jsonObject) {

		String ret = Requests.get("http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/mortgage.json").send().readToText();

		for (String s : jsonObject.keySet()) {
			if (s.contains("image")) {
				String image = getImage("rzzl.png");
				jsonObject.put(s, image);
			}
			ret = ret.replace("${" + s + "}", jsonObject.getString(s));
		}
		return JSONObject.parseArray(ret);
	}

	public JSONArray getJosn(JSONObject jsonObject) {

		String ret = Requests.get("http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit.json").send().readToText();

		for (String s : jsonObject.keySet()) {
			ret = ret.replace("${" + s + "}", jsonObject.getString(s));
		}
		return JSONObject.parseArray(ret);
	}

	@Test
	public void createUser() {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("userId", "188");
			jsonObject.put("phone", "15355008306");
			jsonObject.put("realName", "张三分");
			JSONObject jsonObject1 = signService.register(jsonObject);
			System.out.println(jsonObject1.toJSONString());

			// JSONObject jsonObject = new JSONObject();
			// jsonObject.put("userId", "22233");
			jsonObject.put("idNo", "421123197710032578");
			// jsonObject.put("realName", "张三分");
			JSONObject jsonObject2 = signService.setUserIdno(jsonObject);
			System.out.println(jsonObject2.toJSONString());
			// JSONObject jsonObject = new JSONObject();
			// jsonObject.put("userId", "22233");
			JSONObject jsonObject3 = signService.applycert(jsonObject);
			System.out.println(jsonObject3.toJSONString());
			//
			// JSONObject jsonObject = new JSONObject();
			// jsonObject.put("userId", "22233");
			jsonObject.put("signatureImg", "data/image/1513144769198137signature.jpg?x-oss-process=image/resize,h_100");
			JSONObject jsonObject4 = signService.upSignatureImage(jsonObject);
			System.out.println(jsonObject4.toJSONString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Test
	public void createContract() {
		JSONObject object = new JSONObject();
		object.put("userId", "c_C1000");
		object.put("fid", "1905597119998198376");
		object.put("title", "20102161");
		object.put("description", "借款合同");

		JSONObject jsonObject = signService.createContract(object);
		System.out.println(jsonObject.toJSONString());
		// "contractId":"1905597119998198376"}

	}

	@Test
	public void createC() {
		JSONObject object = new JSONObject();
		object.put("userId", "CAIWEI01");
		object.put("fid", "1905597119998198376");
		object.put("title", "20102161");
		object.put("description", "借款合同");
		String userId = "205";
		String cid = "CAIWEI01";
		JSONObject jsonObject = signService.createContract(object);
		System.out.println(jsonObject.toJSONString());
		if (jsonObject.getInteger("errno").equals(0)) {
			JSONObject jsonObject3 = jsonObject.getJSONObject("data");
			String contractId = jsonObject3.getString("contractId");
			JSONObject xx = new JSONObject();
			xx.put("contractId", contractId); 
			xx.put("signers", new JSONArray());
			JSONObject jsonObject2 = signService.addSigners(xx);
			System.out.println(jsonObject2.toJSONString());

			JSONObject object2 = new JSONObject();
			object2.put("userId", userId);

			JSONArray array = new JSONArray();
			JSONObject p = new JSONObject();
			p.put("pageNum", "7");
			p.put("x", "0.327");
			p.put("y", "0.536");
			array.add(p);
			object2.put("position", array);
			object2.put("contractId", contractId);
			object2.put("phone", "15355008306");
			JSONObject jsonObject4 = signService.setSignerConfig(object2);

			// JSONObject object22 = new JSONObject();
			// object22.put("userId", cid);
			// JSONArray array2 = new JSONArray();
			// JSONObject p2 = new JSONObject();
			// p2.put("pageNum", "7");
			// p2.put("x", "0.397");
			// p2.put("y", "0.636");
			// array2.add(p2);
			// object22.put("position", array2);
			// object22.put("contractId", contractId);
			// JSONObject jsonObject42 = signService.setSignerConfig(object22);

			JSONObject object21 = new JSONObject();
			object21.put("userId", cid);
			JSONArray array1 = new JSONArray();
			JSONObject p1 = new JSONObject();
			p1.put("pageNum", "7");
			p1.put("x", "0.397");
			p1.put("y", "0.736");
			array1.add(p1);
			object21.put("position", array1);
			object21.put("contractId", contractId);
			JSONObject jsonObject41 = signService.setSignerConfig(object21);

			// JSONObject object5 = new JSONObject();
			// object5.put("userId", userId);
			// object5.put("contractId", contractId);
			// JSONObject jsonObjectc = signService.signCert(object5);
			// System.out.println(jsonObjectc);

			JSONObject object51 = new JSONObject();
			object51.put("userId", cid);
			object51.put("contractId", contractId);
			JSONObject jsonObjectc1 = signService.signCert(object51);
			System.out.println(jsonObjectc1);

			JSONObject object0 = new JSONObject();
			object0.put("contractId", contractId);
			object0.put("userId", userId);
			JSONObject jsonObject11 = signService.getSignURL(object0);
			System.out.println(jsonObject11);
			JSONObject jsonObject1 = signService.getDownload(object0);
			System.out.println(jsonObject1);
		} else {
			System.out.println("error");
		}
	}

	@Test
	public void addSigners() {
		JSONObject object = new JSONObject();
		object.put("userId", "u_2233");
		object.put("contractId", "1905597119998198376");
		object.put("signers", new JSONArray());

		JSONObject jsonObject = signService.addSigners(object);
		System.out.println(jsonObject.toJSONString());
	}

	@Test
	public void setSignerConfig() {
		JSONObject object = new JSONObject();
		object.put("userId", "c_C1000");
		JSONArray array = new JSONArray();
		JSONObject p = new JSONObject();
		p.put("pageNum", "7");
		p.put("x", "0.327");
		p.put("y", "0.566");
		array.add(p);
		object.put("position", array);
		object.put("contractId", "1905597119998198376");
		JSONObject jsonObject = signService.setSignerConfig(object);
		System.out.println(jsonObject.toJSONString());
	}

	@Test
	public void signcert() {

		//已创建合同
		JSONObject xx = new JSONObject();
		String contractId="1905613337945702951";
		xx.put("contractId", contractId); 
		JSONArray signers = new JSONArray();
		signers.add("RZ001");
		
		xx.put("signers", signers); 
		
		JSONObject jsonObject2 = signService.addSigners(xx);

		JSONObject object = new JSONObject();
		JSONArray array = signers;
		JSONObject p = new JSONObject();
		p.put("pageNum", "1");
		p.put("x", "0.327");
		p.put("y", "0.566");
		array.add(p);
		object.put("position", array);

		// object.put("userId", "c_" + "C1000");
		object.put("userId", "RZ001");

		object.put("contractId", "1905613337945702951");
		JSONObject jsonObject = signService.signCert(object);
		signService.finish(object);
		System.out.println(jsonObject.toJSONString());
		// object.put("userId", "u_" + "2233");
		// JSONObject jsonObject1 = signService.signCert(object);
		// System.out.println(jsonObject1.toJSONString());

	}

	@Test
	public void download() {
		JSONObject object = new JSONObject();
		object.put("contractId", "1906216942952251910");
		JSONObject jsonObject1 = signService.getDownload(object);
		System.out.println(jsonObject1.toJSONString());

	}
	@Test
	public void getPdfDownLoad() {
		JSONObject object = new JSONObject();
		object.put("fid", "1893235173193941602");
		JSONObject jsonObject1 = signService.getPdfDownLoad(object);
		System.out.println(jsonObject1.toJSONString());

	}
}
