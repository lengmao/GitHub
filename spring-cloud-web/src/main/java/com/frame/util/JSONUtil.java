package com.frame.util;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import java.util.*;

/**
 * 
 * @author vic
 * @desc json util
 */
public class JSONUtil {

	private static Gson gson = null;

	static {
		gson = new Gson();// todo yyyy-MM-dd HH:mm:ss
	}

	public static synchronized Gson newInstance() {
		if (gson == null) {
			gson = new Gson();
		}

		return gson;
	}

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T toBean(String json, Class<T> clz) {

		return gson.fromJson(json, clz);
	}

	public static <T> Map<String, T> toMap(String json, Class<T> clz) {
		Map<String, JsonObject> map = gson.fromJson(json,
				new TypeToken<Map<String, JsonObject>>() {
				}.getType());
		Map<String, T> result = new HashMap<>();
		for (String key : map.keySet()) {
			result.put(key, gson.fromJson(map.get(key), clz));
		}
		return result;
	}

	public static Map<String, Object> toMap(String json) {
		Map<String, Object> map = gson.fromJson(json,
				new TypeToken<Map<String, Object>>() {
				}.getType());
		return map;
	}

	public static Map<String,String> toMapString(String json){
		Map<String, String> map = new HashMap();
		JSONObject jsonObject = JSONObject.fromObject(json);
		Iterator ite = jsonObject.keys();
		while (ite.hasNext()) {
			String key = ite.next().toString();
			String value = jsonObject.get(key).toString();
			map.put(key, value);
		}
		return map;
	}
	
	public static List<Map<String, Object>> toListMap(String json){
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		com.alibaba.fastjson.JSONArray arry=com.alibaba.fastjson.JSON.parseArray(json);
		for (int i = 0; i < arry.size(); i++) {
			com.alibaba.fastjson.JSONObject jo=arry.getJSONObject(i);
			list.add(toMap(jo.toJSONString()));
		}
		return list;
	}

	public static <T> List<T> toList(String json, Class<T> clz) {
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		List<T> list = new ArrayList<>();
		for (final JsonElement elem : array) {
			list.add(gson.fromJson(elem, clz));
		}
		return list;
	}

	public static <T> T JsonToBean(Class<T> clazz, String JsonString) {
		JSONUtils.getMorpherRegistry().registerMorpher(
				new DateMorpherEx(new String[] { "yyyy-MM-dd HH:mm:ss",
						"yyyy-MM-dd" }, (Date) null));// 调用DateMorpherEx，defaultValue为null
		JSONObject jsonObject = JSONObject.fromObject(JsonString);
		@SuppressWarnings("unchecked")
		T entity = (T) JSONObject.toBean(jsonObject, clazz);
		return entity;
	}
	
	public static String mapToJson(Map<String, Object> map){
		org.json.JSONObject json=new org.json.JSONObject(map);
		return json.toString();
	}

}
