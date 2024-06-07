package org.zmz.c.utils;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonUtil {

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String toJson(Object target) {
        return toJson(target, null, false, null, null, true);
    }

    public static String toJson(Object target, Type targetType, boolean isSerializeNulls,
                                Double version, String datePattern, boolean excludesFieldsWithoutExpose) {
        if (target == null) {
            return "{}";
        }
        GsonBuilder builder = new GsonBuilder();
        if (isSerializeNulls) {
            builder.serializeNulls();
        }
        if (version != null) {
            builder.setVersion(version.doubleValue());
        }
        if (StrUtil.isBlank(datePattern)) {
            datePattern = DEFAULT_DATE_PATTERN;
        }
        builder.setDateFormat(datePattern);
        if (!excludesFieldsWithoutExpose) {
            builder.excludeFieldsWithoutExposeAnnotation();
        }

        final String pattern = datePattern;
        builder.registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
            DateFormat df = new SimpleDateFormat(pattern);

            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                if (src == null) {
                    return null;
                } else {
                    return new JsonPrimitive(df.format(src));
                }
            }
        });

        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            DateFormat df = new SimpleDateFormat(pattern);

            @Override
            public Date deserialize(final JsonElement json, final Type typeOfT,
                                    final JsonDeserializationContext context) throws JsonParseException {
                try {
                    if (json == null || StrUtil.isEmpty(json.getAsString())) {
                        return null;
                    } else {
                        return df.parse(json.getAsString());
                    }
                } catch (ParseException e) {
                    throw new JsonParseException(e);
                }
            }
        });
        return toJson(target, targetType, builder);
    }

    public static String toJson(Object target, Type targetType, GsonBuilder builder) {
        if (target == null) {
            return "{}";
        }
        Gson gson = null;
        if (builder == null) {
            gson = new Gson();
        } else {
            gson = builder.create();
        }
        String result = "{}";
        try {
            if (targetType == null) {
                result = gson.toJson(target);
            } else {
                result = gson.toJson(target, targetType);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return result;
    }
}
