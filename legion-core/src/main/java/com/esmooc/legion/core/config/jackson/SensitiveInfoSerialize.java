package com.esmooc.legion.core.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

public class SensitiveInfoSerialize extends JsonSerializer<String> implements
        ContextualSerializer {
 
  private SensitiveType type;
 
  public SensitiveInfoSerialize() {
  }
 
  public SensitiveInfoSerialize(final SensitiveType type) {
    this.type = type;
  }
 
  @Override
  public void serialize(final String s, final JsonGenerator jsonGenerator,
      final SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
    switch (this.type) {
      case CHINESE_NAME: {
        jsonGenerator.writeString(SensitiveInfoUtils.chineseName(s));
        break;
      }
      case ID_CARD: {
        jsonGenerator.writeString(SensitiveInfoUtils.idCardNum(s));
        break;
      }
      case FIXED_PHONE: {
        jsonGenerator.writeString(SensitiveInfoUtils.fixedPhone(s));
        break;
      }
      case MOBILE_PHONE: {
        jsonGenerator.writeString(SensitiveInfoUtils.mobilePhone(s));
        break;
      }
      case ADDRESS: {
        jsonGenerator.writeString(SensitiveInfoUtils.address(s, 4));
        break;
      }
      case EMAIL: {
        jsonGenerator.writeString(SensitiveInfoUtils.email(s));
        break;
      }
      case BANK_CARD: {
        jsonGenerator.writeString(SensitiveInfoUtils.bankCard(s));
        break;
      }
      case CNAPS_CODE: {
        jsonGenerator.writeString(SensitiveInfoUtils.cnapsCode(s));
        break;
      }
    }
 
  }
 
  @Override
  public JsonSerializer<?> createContextual(final SerializerProvider serializerProvider,
      final BeanProperty beanProperty) throws JsonMappingException {
    if (beanProperty != null) { // ??????????????????
      if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) { // ??? String ???????????????
        SensitiveData sensitiveData = beanProperty.getAnnotation(SensitiveData.class);
        if (sensitiveData == null) {
          sensitiveData = beanProperty.getContextAnnotation(SensitiveData.class);
        }
        if (sensitiveData != null) { // ??????????????????????????????????????? value ?????? SensitiveInfoSerialize
 
          return new SensitiveInfoSerialize(sensitiveData.value());
        }
      }
      return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
    }
    return serializerProvider.findNullValueSerializer(beanProperty);
  }
}