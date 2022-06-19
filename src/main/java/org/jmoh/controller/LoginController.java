package org.jmoh.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jmoh.config.SessionConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpSession session, RedirectAttributes rttr) throws Exception {
        String id = request.getParameter("id");
        if(id != null){
            String userId = SessionConfig.getSessionidCheck("login_id", id);
            System.out.println(id + " : " +userId);
            session.setMaxInactiveInterval(60 * 60);
            session.setAttribute("login_id", id);//login_id type에 id등록
            return "redirect:/home.do";
        }
        return "redirect:/main.do";
    }

    @RequestMapping(value = "/main.do")
    public String index(HttpSession session) throws Exception {
        return "login";
    }

    @RequestMapping(value = "/home.do")
    public String home(HttpSession session) throws Exception {
        return "home";
    }
}