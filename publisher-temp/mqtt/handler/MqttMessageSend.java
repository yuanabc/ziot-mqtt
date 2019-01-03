/**
 * Copyright (C), 2018-2019
 * FileName: MessageSend
 * Author:   LoadHao
 * Date:     2019/1/2 21:40
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.ztesoft.ziot.dms.service.mqtt.handler;

import com.ztesoft.ziot.dms.service.mqtt.MqttMessage;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 功能描述: <br>
 * 〈MQTT消息发送类〉
 *
 * @author LoadHao
 * @create 2019/1/2
 * @since 1.0.0
 */
@Component
public class MqttMessageSend extends BaseMessageSend {

    @Resource
    private BaseMessageSend baseMessageSend;

    /**
     * <功能描述>:
     * 清除Broker保留的消息
     *
     * @return void
     * @Author LoadHao
     * @Date 2019/1/3 10:48
     * @Param void
     **/
    public void cleanRetainedMsg() {
        MqttMessage mqttMessage = MqttMessage.cleanRetainedMsg();
        Message message = populateEntity(mqttMessage);
        if (null != message) {
            this.baseMessageSend.sendToMqtt(message);
        }
    }

    public void sendToMqtt(String topicPrefix, String topic, int qos, byte[] payload) {
        MessageBuilder<byte[]> stringMessageBuilder = MessageBuilder.withPayload(payload);
        stringMessageBuilder.setHeader(MqttHeaders.PREFIX, topicPrefix);
        stringMessageBuilder.setHeader(MqttHeaders.TOPIC, topic);
        stringMessageBuilder.setHeader(MqttHeaders.QOS, qos);
        Message<?> message = stringMessageBuilder.build();
        this.baseMessageSend.sendToMqtt(message);
    }

    public void sendToMqtt(String topicPrefix, String topic, int qos, boolean retained, byte[] payload) {
        MessageBuilder<byte[]> stringMessageBuilder = MessageBuilder.withPayload(payload);
        stringMessageBuilder.setHeader(MqttHeaders.PREFIX, topicPrefix);
        stringMessageBuilder.setHeader(MqttHeaders.TOPIC, topic);
        stringMessageBuilder.setHeader(MqttHeaders.QOS, qos);
        stringMessageBuilder.setHeader(MqttHeaders.RETAINED, retained);
        Message<?> message = stringMessageBuilder.build();
        this.baseMessageSend.sendToMqtt(message);
    }

    public Message populateEntity(MqttMessage mqttMessage) {
        MessageBuilder<byte[]> stringMessageBuilder;

        byte[] payload = mqttMessage.getPayload();
        Integer qos = mqttMessage.getQos();
        String topicPrefix = mqttMessage.getTopicPrefix();
        String topic = mqttMessage.getTopic();
        Boolean retained = mqttMessage.isRetained();

        if (null != payload) {
            stringMessageBuilder = MessageBuilder.withPayload(payload);
            if (null != topicPrefix) {
                stringMessageBuilder.setHeader(MqttHeaders.PREFIX, topicPrefix);
            }
            if (null != topic) {
                stringMessageBuilder.setHeader(MqttHeaders.TOPIC, topic);
            }
            if (null != qos) {
                stringMessageBuilder.setHeader(MqttHeaders.QOS, qos);
            }
            if (null != retained) {
                stringMessageBuilder.setHeader(MqttHeaders.RETAINED, retained);
            }
            return stringMessageBuilder.build();
        }
        return null;
    }
}
