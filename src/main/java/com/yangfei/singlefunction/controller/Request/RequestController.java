package com.yangfei.singlefunction.controller.Request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@RestController
@RequestMapping("/request")
public class RequestController {
    @RequestMapping("/requestParams")
    public Object requestParams(HttpServletRequest request) {
        String queryString = request.getQueryString();
        System.out.println(queryString);
        return queryString;
    }
}
