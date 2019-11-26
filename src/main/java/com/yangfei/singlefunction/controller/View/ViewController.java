package com.yangfei.singlefunction.controller.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>
 *
 * </p>
 *
 * @author yangfei
 * @since 2019-05-18
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/invitationPage", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView invitationPage(@RequestParam("oldUserId") long oldUserId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("oldUserId", oldUserId);
        mv.setViewName("welcome");
        return mv;
    }
}
