package com.as;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @PostMapping("/listTest")
    public String test(@RequestParam("list") List<String> list, @RequestParam("abc") String abc) {
        System.out.println(list.size());
        return list.toString() + "," + abc;
    }
}
