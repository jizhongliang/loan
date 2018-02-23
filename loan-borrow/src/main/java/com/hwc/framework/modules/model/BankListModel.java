package com.hwc.framework.modules.model;

import com.hwc.framework.modules.domain.DomianBase;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BankListModel extends  DomianBase {
   
	  @ApiModelProperty(value = "银行编号", required = true)
	  private String bankCode;

	
	  

}
