package org.fl.noodlecall.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.fl.noodlecall.console.dao.MethodDao;
import org.fl.noodlecall.console.domain.MethodMd;
import org.fl.noodle.common.dynamicsql.DynamicSqlTemplate;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.MethodVo;

@Repository("methodDao")
public class MethodDaoImpl implements MethodDao {

	@Autowired
	private DynamicSqlTemplate dynamicSqlTemplate;

	@Override
	public PageVo<MethodVo> queryMethodPage(MethodVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("method_Id", vo.getMethod_Id() != null ? vo.getMethod_Id() : null);
		paramsMap.put("method_Name", vo.getMethod_Name() != null ? (new StringBuilder().append("%").append(vo.getMethod_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("cluster_Type", vo.getCluster_Type() != null ? vo.getCluster_Type() : null);
		paramsMap.put("route_Type", vo.getRoute_Type() != null ? vo.getRoute_Type() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? (new StringBuilder().append("%").append(vo.getService_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("method-query-page", paramsMap, page, rows, MethodVo.class);
	}

	@Override
	public List<MethodVo> queryMethodList(MethodVo vo) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("method_Id", vo.getMethod_Id() != null ? vo.getMethod_Id() : null);
		paramsMap.put("method_Name", vo.getMethod_Name() != null ? vo.getMethod_Name() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("cluster_Type", vo.getCluster_Type() != null ? vo.getCluster_Type() : null);
		paramsMap.put("route_Type", vo.getRoute_Type() != null ? vo.getRoute_Type() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		return dynamicSqlTemplate.queryList("method-query-list", paramsMap, MethodVo.class);
	}

	@Override
	public void insertMethod(MethodVo vo) throws Exception {
		dynamicSqlTemplate.insert(vo, MethodMd.class);
	}

	@Override
	public void insertsMethod(MethodVo[] vos) throws Exception {
		for (MethodVo vo : vos) {
			dynamicSqlTemplate.insert(vo, MethodMd.class);
		}
	}

	@Override
	public void updateMethod(MethodVo vo) throws Exception {
		dynamicSqlTemplate.updateNonull(vo, MethodMd.class);
	}

	@Override
	public void updatesMethod(MethodVo[] vos) throws Exception {
		for (MethodVo vo : vos) {
			dynamicSqlTemplate.updateNonull(vo, MethodMd.class);
		}
	}

	@Override
	public void deleteMethod(MethodVo vo) throws Exception {
		dynamicSqlTemplate.delete(vo, MethodMd.class);
	}

	@Override
	public void deletesMethod(MethodVo[] vos) throws Exception {
		for (MethodVo vo : vos) {
			dynamicSqlTemplate.delete(vo, MethodMd.class);
		}
	}

	@Override
	public PageVo<MethodVo> queryMethodByServicePage(MethodVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		return dynamicSqlTemplate.queryPage("method-query-list", paramsMap, page, rows, MethodVo.class);
	}

	@Override
	public void updateOrInsertsMethod(MethodVo[] vos) throws Exception {
		
		if (vos.length > 0) {
			MethodVo methodVo = new MethodVo();
			methodVo.setService_Name(vos[0].getService_Name());
			List<MethodVo> methodVoList = queryMethodList(methodVo);
			for (MethodVo oldMethodVo : methodVoList) {
				boolean haveSign = false;
				for(MethodVo newMethodVo : vos) {
					if (newMethodVo.getMethod_Name().equals(oldMethodVo.getMethod_Name())) { 
						haveSign = true;
						break;
					}
				}
				if (!haveSign) {
					deleteMethod(oldMethodVo);
				}
			}
		}
		
		for (MethodVo vo : vos) {
			
			int num = dynamicSqlTemplate.updateNonullNoById(vo, MethodMd.class, new String[]{"method_Name", "service_Name"});
			
			if (num <= 0) {			
				
				vo.setIs_Downgrade(ConsoleConstant.IS_DOWNGRADE_NO);
				vo.setDowngrade_Type(ConsoleConstant.DOWNGRADE_TYPE_AVGTIME);
				vo.setReturn_Type(ConsoleConstant.RETURN_TYPE_T_EXCEPTION);
				vo.setAvgTime_Limit_Threshold(200L);
				vo.setOvertime_Threshold(200L);
				vo.setOvertime_Limit_Threshold(10L);
				
				vo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
				dynamicSqlTemplate.insert(vo, MethodMd.class);
			}
		}
	}
}
