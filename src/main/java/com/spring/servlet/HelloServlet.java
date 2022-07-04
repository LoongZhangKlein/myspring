package com.spring.servlet;

import com.spring.service.impl.HelloServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    private HelloServiceImpl helloService=new HelloServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("Spring");
        resp.getWriter().write("Spring");
        int[] arrays = helloService.getArrays();
        resp.getWriter().write(Arrays.toString(arrays));
    }
}