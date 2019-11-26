package com.yangfei.singlefunction.controller;

import com.alibaba.fastjson.JSON;
import com.yangfei.singlefunction.utils.JsonUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@RestController
@RequestMapping("/json")
public class JsonController {
    @RequestMapping("/readFile")
    public Object readFile() throws IOException {
        Object o = JsonUtils.readJsonFromClassPath("json/middleware.json");

        //System.out.println(o);
        String json = JSON.toJSONString(o);
        System.out.println(json);
        return o;
    }
}
