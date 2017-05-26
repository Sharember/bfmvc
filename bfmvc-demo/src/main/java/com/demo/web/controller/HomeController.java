package com.demo.web.controller;

import cc.cleverfan.web.RequestMethod;
import cc.cleverfan.web.annotation.Autowired;
import cc.cleverfan.web.annotation.Controller;
import cc.cleverfan.web.annotation.RequestMapping;
import cc.cleverfan.web.bean.ModelAndView;
import cc.cleverfan.web.bean.Param;
import com.demo.web.service.PersonService;

import java.util.Map;


@Controller
public class HomeController {

    @Autowired
    private PersonService ps;

    //主界面
    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        return new ModelAndView("home.jsp").setAttribute("message", "成功啦");
    }

    //获取person信息
    @RequestMapping(path = "/persons", method = RequestMethod.GET)
    public ModelAndView getPersons() {
        if (ps == null) {
            return new ModelAndView("home.jsp").setAttribute("message", "成功啦");
        }
        return new ModelAndView("persons.jsp").setAttribute("persons", ps.getPersons());
    }

    //处理用户登录
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(Param param) {
        Map<String, Object> user = param.getFieldMap();
        return new ModelAndView("home.jsp").setAttribute("message", "成功啦");
    }
}
