package org.ld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 */
@RestController
public class DemoController {

    @GetMapping(value = "demo")
    public Map<String, String> demo() {
        Map<String,String>  a = new HashMap<>();
        a.put("aaa","bbb");
        return a;
    }

}
