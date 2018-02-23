package com.hwc.framework.modules.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.dao.ArcBasecodeMapper;
import com.hwc.framework.modules.domain.BaseCode;
import com.hwc.framework.modules.domain.BaseCodeRequest;
import com.hwc.framework.modules.model.ArcBasecode;
import com.hwc.framework.modules.service.ArcBasecodeService;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.DataObjectConverter;
import com.hwc.mybatis.util.PageUtils;

import cn.freesoft.utils.FsUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

/**
 * 2017/11/01.
 */
@Service
public class ArcBasecodeServiceImpl extends AbstractService<ArcBasecodeMapper, ArcBasecode>
                                    implements ArcBasecodeService {
    private static final Logger logger = LoggerFactory.getLogger(ArcBasecodeServiceImpl.class);

    @Autowired
    private IHwcCache           cache;
    
    private final String        key    = "data:basecode:";
    

    public String getDescript(String cat, String code) {
        ArcBasecode arcBasecode = getBaseCode(cat, code, "");
        if (FsUtils.strsNotEmpty(arcBasecode)) {
            return arcBasecode.getDescript();
        } else {
            return "";
        }
    }

    public String getCode(String cat, String descript) {
        ArcBasecode arcBasecode = getBaseCode(cat, "", descript);
        if (FsUtils.strsNotEmpty(arcBasecode)) {
            return arcBasecode.getCode();
        } else {
            return "";
        }
    }

    public String getExtValue(String cat, String code) {
        ArcBasecode arcBasecode = getBaseCode(cat, code, "");
        if (FsUtils.strsNotEmpty(arcBasecode)) {
            return arcBasecode.getExts1();
        } else {
            return "";
        }
    }

    public String getExtValue(String cat, String code, String ext) {
        ArcBasecode arcBasecode = getBaseCode(cat, code, "");
        if (FsUtils.strsNotEmpty(arcBasecode)) {
            return arcBasecode.getExts1();
        } else {
            return "";
        }
    }

    private ArcBasecode getBaseCode(String cat, String code, String descript) {

        //        if (FsUtils.strsEmpty(code, descript))
        //            return null;

        String nkey = key + "cat:" + cat;
        String ncode = FsUtils.strsEmpty(code) ? descript : code;
        if (cache.hexists(nkey, ncode)) {
            return JSON.parseObject(FsUtils.s(cache.hget(nkey, ncode)), ArcBasecode.class);
        } else {
            ArcBasecode arcBasecode = null;
            if (FsUtils.strsNotEmpty(cat)) {
                if (FsUtils.strsNotEmpty(code)) {
                    ArcBasecode basecode = new ArcBasecode();
                    basecode.setCat(cat);
                    basecode.setCode(code);
                    arcBasecode = this.mapper.selectOne(basecode);
                }
            }
            if (FsUtils.strsEmpty(arcBasecode)) {
                if (FsUtils.strsNotEmpty(descript)) {
                    List<ArcBasecode> arcBasecodes = getCodeList(cat, "", descript);
                    if (FsUtils.strsNotEmpty(arcBasecodes) && !arcBasecodes.isEmpty()) {
                        arcBasecode = arcBasecodes.get(0);
                    }

                }
            }
            if (FsUtils.strsNotEmpty(arcBasecode)) {
                cache.hset(nkey, ncode, arcBasecode);
            }
            return arcBasecode;
        }

    }

    private List<ArcBasecode> getCodeList(String cat, String code, String descript) {
        String nkey = key;
        if (FsUtils.strsNotEmpty(cat)) {
            nkey = nkey + ":cat:" + cat;
        }
        if (FsUtils.strsNotEmpty(code)) {
            nkey = nkey + ":code:" + code;
        }
        if (FsUtils.strsNotEmpty(descript)) {
            nkey = nkey + ":descript:" + descript;
        }
        List<ArcBasecode> arcBasecodes = null;
        if (cache.exists(nkey)) {
            arcBasecodes = JSON.parseArray(cache.get(nkey), ArcBasecode.class);
        }
        if (arcBasecodes != null && !arcBasecodes.isEmpty()) {
            return arcBasecodes;
        }
        Condition condition = new Condition(ArcBasecode.class);
        Example.Criteria criteria = condition.createCriteria();
        if (FsUtils.strsNotEmpty(cat))
            criteria.andEqualTo("cat", cat);
        if (FsUtils.strsNotEmpty(code)) {
            criteria.andEqualTo("code", code);
        }
        if (FsUtils.strsNotEmpty(descript)) {
            criteria.andLike("descript", "%" + descript + "%");
        }
        criteria.andEqualTo("halt", "F");
        condition.setOrderByClause("seq");
        arcBasecodes = mapper.selectByCondition(condition);
        Long expire = FsUtils.getDaySpan(new Date());
        cache.set(nkey, expire, arcBasecodes);
        return arcBasecodes;
    }

    public List<BaseCode> getBasecodeList(String cat) {
    	 if (FsUtils.strsEmpty(cat)) {
             return new ArrayList<BaseCode>();
         }

         String nkey = key + ":list:cat:" + cat;
         if (cache.exists(nkey)) {
             return JSON.parseArray(cache.get(nkey), BaseCode.class);
         } else {
             List<ArcBasecode> arcBasecodes = getCodeList(cat, "", "");
             List<BaseCode> list = new ArrayList<>();
             if (FsUtils.strsNotEmpty(arcBasecodes)) {
                 for (ArcBasecode basecode : arcBasecodes) {
                     BaseCode code = new BaseCode();
                     code.setCode(basecode.getCode());
                     code.setDescript(basecode.getDescript());
                     code.setExts(basecode.getExts2());
                     list.add(code);
                 }
             }
             if (!list.isEmpty()) {
                 Long expire = FsUtils.getDaySpan(new Date());
                 cache.set(nkey, expire, list);
             }
             return list;
         }
    }

    @Override
    public Response getBasecodeList(BaseCodeRequest request) {
        List<BaseCode> list = new ArrayList<>();
        Condition condition = new Condition(ArcBasecode.class);
        Example.Criteria c = condition.createCriteria();
        if (FsUtils.strsNotEmpty(request.getCat())) {
            c.andEqualTo("cat", request.getCat());
        }
        if (FsUtils.strsNotEmpty(request.getCode())) {
            c.andEqualTo("code", request.getCode());
        }
        if (FsUtils.strsNotEmpty(request.getDescript())) {
            c.andLike("descript", request.getDescript() + "%");
        }
        if (FsUtils.strsEmpty(request.getHalt())) {
            c.andEqualTo("halt", "F");
        } else {
            c.andEqualTo("halt", request.getHalt());
        }
        condition.setOrderByClause("seq ");
        PageHelper.startPage(request.getPage(), request.getPageSize());
        List<ArcBasecode> basecodes = mapper.selectByCondition(condition);
        List<BaseCode> baseCodes = PageUtils.convert(basecodes,
            new DataObjectConverter<ArcBasecode, BaseCode>() {
                @Override
                public BaseCode convert(ArcBasecode basecode) {
                    BaseCode basecode1 = new BaseCode();
                    basecode1.setDescript(basecode.getDescript());
                    basecode1.setCode(basecode.getCode());
                    basecode1.setCat(basecode.getCat());
                    basecode1.setSys(basecode.getSys());
                    basecode1.setHalt(basecode.getHalt());
                    basecode1.setSeq(basecode.getSeq());
                    return basecode1;
                }
            });
        return Response.success(baseCodes);
    }

    @Override
    public Response add(BaseCode code) {
        ArcBasecode arcBasecode = getBaseCode(code.getCat(), code.getCode(), "");
        if (FsUtils.strsEmpty(arcBasecode)) {
            arcBasecode = new ArcBasecode();
            arcBasecode.setHalt("F");
            arcBasecode.setCat(code.getCat());
            arcBasecode.setCode(code.getCode());
            arcBasecode.setDescript(code.getDescript());
            arcBasecode.setChanged(new Date());
            arcBasecode.setSys("F");
            arcBasecode.setSeq(code.getSeq() == null ? 100 : code.getSeq());
            insert(arcBasecode);
            expireCache(key + code.getCat());
            return Response.success(arcBasecode);
        } else {
            return Response.fail("该代码已经存在，请勿重复插入");
        }
    }

    private void expireCache(String key) {
        Set<String> keys = cache.keys(key + "*");
        if (FsUtils.strsNotEmpty(keys)) {
            for (String s : keys) {
                cache.del(s);
            }
        }
    }

    @Override
    public Response del(BaseCode code) {
        ArcBasecode arcBasecode = getBaseCode(code.getCat(), code.getCode(), "");
        if (FsUtils.strsNotEmpty(arcBasecode)) {
            mapper.delete(arcBasecode);
            expireCache(key + code.getCat());
            return Response.success(arcBasecode);
        } else {
            return Response.fail("该代码不存在");
        }
    }

    @Override
    public Response update(BaseCode code) {
        ArcBasecode arcBasecode = getBaseCode(code.getCat(), code.getCode(), "");
        if (FsUtils.strsNotEmpty(arcBasecode)) {
            arcBasecode.setHalt(code.getHalt());
            arcBasecode.setCat(code.getCat());
            arcBasecode.setCode(code.getCode());
            arcBasecode.setSys(FsUtils.strsEmpty(code.getSys()) ? "F" : code.getSys());
            arcBasecode.setDescript(code.getDescript());
            arcBasecode.setChanged(new Date());
            arcBasecode.setSeq(code.getSeq() == null ? 100 : code.getSeq());
            update(arcBasecode);
            expireCache(key + code.getCat());
            return Response.success(arcBasecode);
        } else {
            return Response.fail("该代码不存在");
        }
    }

    /** 
     * @see com.hwc.framework.modules.service.ArcBasecodeService#getExtValue(java.lang.String)
     */
    @Override
    public BaseCode getBaseCode(String cat, String code) {
        ArcBasecode basecode = new ArcBasecode();
        basecode.setCat(cat);
        basecode.setCode(code);
        ArcBasecode arcBasecode = this.mapper.selectOne(basecode);
        BaseCode baseCode = new BaseCode();
        if (arcBasecode != null) {
            baseCode.setCode(arcBasecode.getCode());
            basecode.setCat(arcBasecode.getCat());
            basecode.setDescript(arcBasecode.getDescript());
            basecode.setCby(arcBasecode.getCby());
            basecode.setExts2(arcBasecode.getExts2());
            basecode.setExts1(arcBasecode.getExts1());
        }
        return baseCode;
    }

	@Override
	public Response getBankList(String bankCode) {
		ArcBasecode record=new ArcBasecode();
		record.setCat("bank_code");
		List<ArcBasecode> list =this.mapper.select(record);
		ArcBasecode arcBasecode=null;
		for (int i = 0; i <list.size(); i++) {
			if(list.get(i).getExts2() !=null &&list.get(i).getExts2().contains(bankCode)){
				arcBasecode=list.get(i);
				break;
			}
		}
		if(arcBasecode!=null){
			return Response.success(arcBasecode);
		}
        return Response.fail("银行卡暂不支持，请更换银行卡");
    }

	@Override
	public Response getBank(String bankNo) {
		String bankNos=bankNo.substring(0,6);
		ArcBasecode record=new ArcBasecode();
		record.setCat("bank_code");
		List<ArcBasecode> list =this.mapper.select(record);
		ArcBasecode arcBasecode=null;
		for (int i = 0; i <list.size(); i++) {
			if(list.get(i).getExts2() !=null &&list.get(i).getExts2().contains(bankNos)){
				arcBasecode=list.get(i);
				break;
			}
		}
		if(arcBasecode!=null){
			logger.info("arcBasecode--------1----------->"+arcBasecode.getCode());
			logger.info("arcBasecode--------2----------->"+arcBasecode.getDescript());
			return Response.success(arcBasecode);
		}
        return Response.fail("银行卡暂不支持，请更换银行卡");
	}

	@Override
	public List<BaseCode> getBankcodeList(String cat) {
		 if (FsUtils.strsEmpty(cat)) {
	            return new ArrayList<BaseCode>();
	        }
	           ArcBasecode record=new ArcBasecode();
			   record.setCat("bank_code");
			  List<ArcBasecode> arcBasecodes =this.mapper.select(record);
	            List<BaseCode> list = new ArrayList<>();
	            if (FsUtils.strsNotEmpty(arcBasecodes)) {
	                for (ArcBasecode basecode : arcBasecodes) {
	                    BaseCode code = new BaseCode();
	                    code.setCode(basecode.getCode());
	                    code.setDescript(basecode.getDescript());
	                    code.setExts(basecode.getExts2());
	                    list.add(code);
	                }
	           
	        }
				return list;
	}

	@Override
	public Response Bank(String bankCode, String bankNo) {
		String bankNos=bankNo.substring(0,6);
		ArcBasecode record=new ArcBasecode();
		record.setCat("bank_code");
		List<ArcBasecode> list =this.mapper.select(record);
		ArcBasecode arcBasecode=null;
		for (int i = 0; i <list.size(); i++) {
			if(list.get(i).getExts2() !=null &&list.get(i).getExts2().contains(bankNos)){
				arcBasecode=list.get(i);
				break;
			}
		}
		if(arcBasecode!=null){
			if(null!=arcBasecode.getCode()){
				if(bankCode.equals(arcBasecode.getCode())){
					arcBasecode.setCat("yizhi");
				}else{
					arcBasecode.setCat("buyizhi");
				}
				return Response.success(arcBasecode);
			}
		}else{
			ArcBasecode arcBasecodes=new ArcBasecode();
			arcBasecodes.setCat("yizhi");
			return Response.success(arcBasecodes);
		}
		return Response.fail("银行卡暂不支持，请更换银行卡");
	}
	
}
