package org.ld.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 */
@Api(tags = {"事例API"})
@RestController
@SuppressWarnings("unused")
public class DemoController {

    @ApiOperation(value = "事例", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "demo")
    public Object demo() {
        Map<String, Object> a = new HashMap<>();
        Map<String, Object> b = new HashMap<>();
        b.put("wer", Arrays.asList("234", "333", "eee"));
        a.put("aaa", b);
        return a;
    }

    @ApiOperation(value = "错误事例", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "errored")
    public Object errorDemo() {
        Map<String, Object> a = new HashMap<>();
        Objects.requireNonNull(null);
        return a;
    }

    @ApiOperation(value = "时间", produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "time")
    public Object time() {
        return (1575021600000L - System.currentTimeMillis())/1000;
    }

}
