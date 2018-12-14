/**
 * Copyright (C), 2018-2018
 * FileName: SendController
 * Author:   LoadHao
 * Date:     2018/12/13 14:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */

package com.iwhalecloud.mqtt.instance.server.mqtt.controller;

import com.iwhalecloud.mqtt.instance.server.mqtt.MqttGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能描述: <br>
 * 〈测试接口〉
 *
 * @author LoadHao
 * @create 2018/12/13
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/send")
public class SendController {
    @Resource
    private MqttGateway mqttGateway;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String send(@RequestParam String sendData) {
        this.mqttGateway.sendToMqtt(sendData);
        log.info("Message send success! The message is " + sendData + ".");
        return "Message send success! The message is " + sendData + ".";
    }

}
