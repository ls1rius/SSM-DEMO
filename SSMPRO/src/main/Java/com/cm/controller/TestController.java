package com.cm.controller;

import com.cm.entity.User;
import com.cm.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huaban.analysis.jieba.JiebaSegmenter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
@RequestMapping(value = "/test")
@ResponseBody
public class TestController {

    @Resource(name="userService")
    private IUserService userService;


//    两种不同的参数请求方式

    @RequestMapping(value = "/showUser",method = RequestMethod.GET)
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        long userId = Long.parseLong(request.getParameter("id"));
        User user = this.userService.selectUser(userId);

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(user));
        response.getWriter().close();
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public User getUser(@RequestParam("id") int id) {
        return this.userService.selectUser(id);
    }

//    止


//    两种不同的参数请求方式

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public Object test(@RequestParam("id") String id){
        HashMap<String,Object> mp = new HashMap<String, Object>();
        mp.put("res","TestGET");
        return mp;
    }

    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        HashMap<String,Object>mp = new HashMap<String, Object>();
        mp.put("res","CheckTest");

        ObjectMapper mapper = new ObjectMapper();
        response.getWriter().write(mapper.writeValueAsString(mp));
        response.getWriter().close();
    }

//    止



//    POST传参
    @RequestMapping(value = "/testPost",method = RequestMethod.POST)
    public Object testPost(@RequestBody HashMap<String, Object> mp){
        String content = (String) mp.get("content");
        HashMap<String,Object> ans = new HashMap<String, Object>();
        ans.put("res","TestPost");
        return ans;
    }
//    止

}
