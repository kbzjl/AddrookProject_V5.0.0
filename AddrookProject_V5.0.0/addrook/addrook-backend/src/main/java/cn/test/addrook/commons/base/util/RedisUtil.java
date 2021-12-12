package cn.test.addrook.commons.base.util;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <b></b>
 *
 * @Author Miracle Luna
 * @Date 2021/12/11 18:11
 * @Version 1.0
 */
@Component("redisutil")
public class RedisUtil {
	@Autowired
	private StringRedisTemplate redisTemplate;

	/**
	 * <b>根据key 将对象以JSON格式存入到 Redis中</b>
	 * @param key
	 * @param value
	 * @return
	 */
	//存入Redis的方法 将以object的类型存入
	public boolean saveToRedis(String key,Object value){
		//创建JsonMapper 对象
		JsonMapper jsonMapper = new JsonMapper();
		try {
			//将对象变为String 类型的Json
			String jsonValue = jsonMapper.writeValueAsString(value);
			//将json存入Redis中
			redisTemplate.opsForValue().set(key, jsonValue);
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * <b>根据key从redis中获得对象信息</b>
	 * @param key
	 * @param valueType
	 * @return
	 */
	public Object findFromRedis(String key,Class valueType){
		JsonMapper jsonMapper = new JsonMapper();
		try {
			//根据key从 Readis中获取数据
			String jsonValue = redisTemplate.opsForValue().get(key);
			if(StrUtil.isNotBlank((jsonValue))){
				//在redisTemplate中取到value  将对应的JSON变成对象
				return jsonMapper.readValue(jsonValue, valueType);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
