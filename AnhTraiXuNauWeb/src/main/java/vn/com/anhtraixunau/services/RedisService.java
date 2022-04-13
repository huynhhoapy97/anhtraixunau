package vn.com.anhtraixunau.services;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class RedisService {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private Gson gson;
	
	public void setValue(String key, Object value, long timeMinutes) {
		redisTemplate.opsForValue().set(key, gson.toJson(value));
		redisTemplate.expire(key, timeMinutes, TimeUnit.MINUTES);
	}
	
	public Object getValue(String key) {
		Object value = null;
		value = redisTemplate.opsForValue().get(key);
		
		return value;
	}
	
	public boolean deleteKey(String key) {
		boolean result = false;
		result = redisTemplate.delete(key);
		
		return result;
	}
}
