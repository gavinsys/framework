package com.easytonent.common.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jfinal.json.Json;

public class MyJson extends Json{

	@Override
	public String toJson(Object object) {
		 return JSON.toJSONString(object,
	                SerializerFeature.WriteNullListAsEmpty,
	                SerializerFeature.WriteNullStringAsEmpty,
	                SerializerFeature.WriteNullBooleanAsFalse,
	                SerializerFeature.WriteNullNumberAsZero,
	                SerializerFeature.WriteDateUseDateFormat,
	                SerializerFeature.WriteMapNullValue);
	}

	@Override
	public <T> T parse(String jsonString, Class<T> type) {
		return JSON.parseObject(jsonString, type);
	}

}
