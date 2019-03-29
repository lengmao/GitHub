package com.springboot.demo.commom.util;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import java.io.IOException;
import java.io.StringWriter;

public class JsonUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	public static String beanToJson(Object obj) {

		StringWriter writer = new StringWriter();

		try {
			JsonGenerator gen = new JsonFactory().createJsonGenerator(writer);
			objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
			objectMapper.writeValue(gen, obj);

			gen.close();

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return writer.toString();
	}
	public static <T> T jsonToBean(String json, Class<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static <T> T jsonToBean(String json, TypeReference<T> clazz) {
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		
	}
}
