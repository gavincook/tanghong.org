package org.antstudio.weixin.lottery;

import org.moon.rest.annotation.Get;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * 大转盘
 * @author:Gavin
 * @date 9/4/2014
 */
@Controller
public class BigTurnplate {

    @Get("/weixin/bigTurnplate")
    public ModelAndView showBigTurnplate(){
        return new ModelAndView("pages/weixin/bigTurnplate");
    }

}
