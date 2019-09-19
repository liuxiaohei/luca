package org.ld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alen.c on 2018/4/23.
 */
@RestController
public class DemoController {

    @GetMapping(value = "demo")
    public Object demo() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("msg", "成功！");
        return map;
    }

}
