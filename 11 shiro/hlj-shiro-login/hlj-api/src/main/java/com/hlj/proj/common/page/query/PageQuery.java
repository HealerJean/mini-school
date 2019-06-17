package com.hlj.proj.common.page.query;

import lombok.Data;


/**
 *
 *
 */
@Data
public  class PageQuery {

	private Integer pageNo = 1;
	private Integer pageSize = 20;
	//是否分页，默认false不分页
	private Boolean page = false;

}
