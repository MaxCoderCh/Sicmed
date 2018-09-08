package com.prostate.common.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prostate.common.domain.LogDO;
import com.prostate.common.domain.PageDO;
import com.prostate.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
