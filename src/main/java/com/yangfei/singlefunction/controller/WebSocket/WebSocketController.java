package com.yangfei.singlefunction.controller.WebSocket;

import com.yangfei.singlefunction.webSocket.MessageSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * <p>
 * websocket
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@RestController
@RequestMapping("/message")
public class WebSocketController {
    @Autowired
    private MessageSocket messageSocket;
    @RequestMapping("/pushAll")
    public Object pushAll(String data) throws IOException {
        messageSocket.sendInfo(data);
        return data;
    }

    @RequestMapping("/pushOne")
    public Object pushOne(String data) throws IOException {
        messageSocket.sendMessage(data);
        return data;
    }
}
