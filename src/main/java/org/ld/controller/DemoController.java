package org.ld.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 */
@Api(tags = {"事例API"})
@RestController
public class DemoController {

    @ApiOperation(value = "事例", produces = "application/json")
    @GetMapping(value = "demo")
    public Object demo() {
        Map<String, Object> a = new HashMap<>();
        Map<String, Object> b = new HashMap<>();
        b.put("wer", Arrays.asList("234", "333", "eee"));
        a.put("aaa", b);
        return a;
    }

}
