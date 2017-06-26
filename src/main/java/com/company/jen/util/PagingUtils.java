package com.company.jen.util;

import com.company.jen.domain.PageVO;

public class PagingUtils {
	
	public PageVO initPage(int pageNo, int pageSize, float listSize) {
		PageVO pv = new PageVO();
		pv.setPageNoCnt((int)Math.ceil(listSize/pageSize));
		pv.setPageSize(pageSize);
		pv.setPageCnt(pageNo);
		if (pageNo == 0 || pageNo == 1) {
			pv.setfCnt(1);
			pv.setlCnt(pv.getPageSize());			
		} else {
			pv.setlCnt(pageNo * pv.getPageSize());
			pv.setfCnt(pv.getlCnt() - (pv.getPageSize()) + 1);
		}
		return pv;
	}
}