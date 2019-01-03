/**
 * Copyright (C), 2018-2019
 * FileName: MqttMessage
 * Author:   LoadHao
 * Date:     2019/1/2 22:16
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.ztesoft.ziot.dms.service.mqtt;

import lombok.Data;

import java.util.Arrays;
import java.util.Objects;

/**
 * 功能描述: <br>
 * 〈Mqtt消息体类〉
 *
 * @author LoadHao
 * @create 2019/1/2
 * @since 1.0.0
 */
@Data
public class MqttMessage {

    private byte[] payload;
    private String topicPrefix;
    private String topic;
    private Integer qos;
    private Boolean retained;

    /**
     * <功能描述>:
     * 清除Broker保留的消息填充类
     *
     * @return MqttMessage
     * @Author LoadHao
     * @Date 2019/1/3 10:47
     * @Param void
     **/
    public static MqttMessage cleanRetainedMsg() {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(new byte[0]);
        mqttMessage.setRetained(true);
        return mqttMessage;
    }

    public MqttMessage() {
    }

    public MqttMessage(byte[] payload) {
        this.payload = payload;
    }

    public MqttMessage(byte[] payload, String topicPrefix) {
        this.payload = payload;
        this.topicPrefix = topicPrefix;
    }

    public MqttMessage(byte[] payload, String topicPrefix, String topic) {
        this.payload = payload;
        this.topicPrefix = topicPrefix;
        this.topic = topic;
    }

    public MqttMessage(byte[] payload, String topicPrefix, String topic, Integer qos) {
        this.payload = payload;
        this.topicPrefix = topicPrefix;
        this.topic = topic;
        this.qos = qos;
    }

    public MqttMessage(byte[] payload, String topicPrefix, String topic, Integer qos, Boolean retained) {
        this.payload = payload;
        this.topicPrefix = topicPrefix;
        this.topic = topic;
        this.qos = qos;
        this.retained = retained;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public String getTopicPrefix() {
        return topicPrefix;
    }

    public void setTopicPrefix(String topicPrefix) {
        this.topicPrefix = topicPrefix;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getQos() {
        return qos;
    }

    public void setQos(Integer qos) {
        this.qos = qos;
    }

    public Boolean isRetained() {
        return retained;
    }

    public void setRetained(Boolean retained) {
        this.retained = retained;
    }

    @Override
    public String toString() {
        return "MqttMessage{" +
                "payload=" + Arrays.toString(payload) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MqttMessage that = (MqttMessage) o;
        return Arrays.equals(payload, that.payload) &&
                Objects.equals(topicPrefix, that.topicPrefix) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(qos, that.qos) &&
                Objects.equals(retained, that.retained);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(topicPrefix, topic, qos, retained);
        result = 31 * result + Arrays.hashCode(payload);
        return result;
    }
}
