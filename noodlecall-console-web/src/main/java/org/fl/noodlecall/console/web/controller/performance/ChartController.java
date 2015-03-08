package org.fl.noodlecall.console.web.controller.performance;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.fl.noodle.common.monitor.performance.persistence.redis.RedisPerformancePersistence;
import org.fl.noodle.common.monitor.performance.vo.KeyVo;
import org.fl.noodle.common.mvc.annotation.NoodleRequestParam;
import org.fl.noodle.common.mvc.annotation.NoodleResponseBody;

@Controller
@RequestMapping(value = "monitor/chart")
public class ChartController {
	
	@Autowired
	RedisPerformancePersistence redisPerformancePersistence;
	
	@RequestMapping(value = "/getdatetime")
	@NoodleResponseBody
	public ChartVo getdatetime() throws Exception {
		
		Date now = new Date();
		ChartVo chartVo = new ChartVo();
		chartVo.setTimestamp(now.getTime());
		return chartVo;
	}
	
	@RequestMapping(value = "/querychartsinglenow")
	@NoodleResponseBody
	public List<ChartVo> queryChartSingleNow(@NoodleRequestParam KeyVo keyVo, String region) throws Exception {
		long regionLong = region != null && !region.equals("") ? Long.valueOf(region) : 60;
		long nowTime = System.currentTimeMillis();
		return redisPerformancePersistence.queryList(keyVo.toKeyString(), nowTime - regionLong * 60000, nowTime, ChartVo.class);
	}
	
	@RequestMapping(value = "/querychartbganded")
	@NoodleResponseBody
	public List<ChartVo> queryChartBgAndEd(@NoodleRequestParam KeyVo keyVo, @NoodleRequestParam(type = "date") Date beginTime, @NoodleRequestParam(type = "date") Date endTime) throws Exception {
		return redisPerformancePersistence.queryList(keyVo.toKeyString(), beginTime.getTime(), endTime.getTime(), ChartVo.class);
	}
	
	@RequestMapping(value = "/querychartsinglenowlast")
	@NoodleResponseBody
	public List<ChartVo> queryChartSinglenowlast(@NoodleRequestParam KeyVo keyVo, String intervalLastTime) throws Exception {
		long intervalLastTimeLong = intervalLastTime != null && !intervalLastTime.equals("") ? Long.valueOf(intervalLastTime) : System.currentTimeMillis();
		return redisPerformancePersistence.queryList(keyVo.toKeyString(), intervalLastTimeLong, Long.MAX_VALUE, ChartVo.class);
	}
	
	public static class ChartVo {
		
		private long totalCount;
		private long overtimeCount;
		private long threshold;
		private long averageTime;
		private long successCount;
		private long timestamp;
		
		public long getTotalCount() {
			return totalCount;
		}
		
		public void setTotalCount(long totalCount) {
			this.totalCount = totalCount;
		}

		public long getOvertimeCount() {
			return overtimeCount;
		}

		public void setOvertimeCount(long overtimeCount) {
			this.overtimeCount = overtimeCount;
		}

		public long getThreshold() {
			return threshold;
		}

		public void setThreshold(long threshold) {
			this.threshold = threshold;
		}
		
		public long getAverageTime() {
			return averageTime;
		}

		public void setAverageTime(long averageTime) {
			this.averageTime = averageTime;
		}

		public long getSuccessCount() {
			return successCount;
		}

		public void setSuccessCount(long successCount) {
			this.successCount = successCount;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}
	}
}
