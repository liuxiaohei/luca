package org.ld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 */
@RestController
public class DemoController {

    @GetMapping(value = "demo")
    public Object demo() {
        Map<String,Object>  a = new HashMap<>();
        Map<String,Object>  b = new HashMap<>();
        b.put("wer", Arrays.asList("234","333","eee"));
        a.put("aaa",b);
        return a;
    }

}
