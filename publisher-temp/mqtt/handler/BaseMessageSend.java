/**
 * Copyright (C), 2018-2019
 * FileName: BaseMessageSend
 * Author:   LoadHao
 * Date:     2019/1/2 21:28
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.ztesoft.ziot.dms.service.mqtt.handler;

import com.ztesoft.ziot.dms.service.mqtt.configuration.MqttGateway;
import org.springframework.messaging.Message;
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
public abstract class BaseMessageSend {

    @Resource
    private MqttGateway mqttGateway;

    public void sendToMqtt(byte[] payload) {
        this.mqttGateway.sendToMqtt(payload);
    }

    public void sendToMqtt(String topic, byte[] payload) {
        this.mqttGateway.sendToMqtt(topic, payload);
    }

    public void sendToMqtt(String topic, int qos, byte[] payload) {
        this.mqttGateway.sendToMqtt(topic, qos, payload);
    }

    public void sendToMqtt(Message<?> message) {
        this.mqttGateway.sendToMqtt(message);
    }
}
