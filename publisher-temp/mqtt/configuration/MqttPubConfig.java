/**
 * Copyright (C), 2018-2018
 * FileName: MqttSenderConfig
 * Author:   LoadHao
 * Date:     2018/12/13 14:30
 * Description: mqtt 发送消息端配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.ztesoft.ziot.dms.service.mqtt.configuration;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

/**
 * 功能描述: <br>
 * 〈mqtt 发送消息端配置〉
 *
 * @author LoadHao
 * @create 2018/12/13
 * @since 1.0.0
 */
@Configuration
@IntegrationComponentScan
public class MqttPubConfig {
    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String hostUrl;

    @Value("${spring.mqtt.service.id}")
    private String serviceId;

    @Value("${spring.mqtt.default.topic}")
    private String defaultTopic;

    @Value("${spring.mqtt.default.qos}")
    private String defaultQos;

    @Value("${spring.mqtt.default.retain}")
    private String defaultRetain;

    @Value("${spring.mqtt.keepAliveInterval}")
    private String keepAliveInterval;

    @Value("${spring.mqtt.completionTimeout}")
    private String completionTimeout;

    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setServerURIs(new String[]{hostUrl});
        //心跳
        mqttConnectOptions.setKeepAliveInterval(Integer.valueOf(keepAliveInterval));
        //连接超时
        mqttConnectOptions.setConnectionTimeout(Integer.valueOf(completionTimeout));
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(serviceId, mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultTopic);
        //消息是否永久保留
        messageHandler.setDefaultRetained(Boolean.valueOf(defaultRetain));
        //消息发布服务质量
        messageHandler.setDefaultQos(Integer.valueOf(defaultQos));
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
}
