package com.hwc.framework.modules.model;

import com.hwc.framework.modules.domain.DomianBase;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BankModel extends  DomianBase {
   
	  @ApiModelProperty(value = "银行卡号", required = false)
	  private String bankNo;
	  @ApiModelProperty(value = "银行编码", required = false)
	  private String bankCode;
	


	
	  

}
