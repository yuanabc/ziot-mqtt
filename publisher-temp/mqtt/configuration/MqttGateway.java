/**
 * Copyright (C), 2018-2018
 * FileName: MqttGateway
 * Author:   LoadHao
 * Date:     2018/12/13 14:33
 * Description: 消息推送接口类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.ztesoft.ziot.dms.service.mqtt.configuration;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 功能描述: <br>
 * 〈消息推送接口-消息通道〉
 *
 * @author LoadHao
 * @create 2018/12/13
 * @since 1.0.0
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    void sendToMqtt(byte[] data);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, byte[] payload);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, byte[] payload);

    void sendToMqtt(Message<?> message);

}

