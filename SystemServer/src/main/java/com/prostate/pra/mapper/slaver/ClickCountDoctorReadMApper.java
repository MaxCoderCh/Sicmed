package com.prostate.pra.mapper.slaver;


import com.prostate.pra.entity.ClickCountDoctorDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 医生之点击统计
 * @author ykbian
 * @email 1992lcg@163.com
 * @date 2018-08-10 15:50:11
 */
@Repository
public interface ClickCountDoctorReadMApper {

	ClickCountDoctorDO get(String id);
	
	List<ClickCountDoctorDO> list(Map<String, Object> map);

	/**
	 * 根据医生id统计数据
	 */
	int countByDoctorId(String doctorId);



	/**
	 * 统计最近一周数据======近7天和近30天的数据都是按照天数进行统计，因此使用同一个mapper
	 */
	int countEverDay(@Param("doctorId") String doctorId, @Param("days") int days);


	/**一年数据====按照月份进行统计
	 */
	int countThisYear(@Param("doctorId") String doctorId, @Param("mooths") int mooths);





	int count(Map<String, Object> map);
	
	int save(ClickCountDoctorDO countDoctor);
	
	int update(ClickCountDoctorDO countDoctor);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}
