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

package com.iwhalecloud.mqtt.instance.server.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 功能描述: <br>
 * 〈消息推送接口〉
 *
 * @author LoadHao
 * @create 2018/12/13
 * @since 1.0.0
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    void sendToMqtt(String data);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);
}

