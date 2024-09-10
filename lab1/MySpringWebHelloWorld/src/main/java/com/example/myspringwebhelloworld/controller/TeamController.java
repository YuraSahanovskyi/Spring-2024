package com.example.myspringwebhelloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeamController {

    @GetMapping("/team")
    @ResponseBody
    public String teamPage() {
        return "<h1> IA-21 </h1>" +
                "Our team has 4 members<br>" +
                "<a href='/Mariia'> Mariia </a><br>" +
                "<a href='/Yurii'> Yurii </a><br>" +
                "<a href='/Andrii'> Andrii </a><br>" +
                "<a href='/Danilo'> Danilo </a><br>";
    }

    @GetMapping("/Mariia")
    @ResponseBody
    public String mariiaPage() {
        return "<h1> Mariia Lapusta </h1>" +
                "Student in KPI";
    }

    @GetMapping("/Yurii")
    @ResponseBody
    public String yuriiPage() {
        return "<h1> Yurii Sahanovskyi</h1>" +
                "Student in KPI";
    }

    @GetMapping("/Andrii")
    @ResponseBody
    public String andriiPage() {
        return "<h1> Andrii Nevmerzhytskyi </h1>" +
                "Student in KPI";
    }

    @GetMapping("/Danilo")
    @ResponseBody
    public String daniloPage() {
        return "<h1> Danilo Litosh </h1>" +
                "Student in KPI";
    }
}
