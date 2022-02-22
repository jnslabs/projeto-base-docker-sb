package io.jnslabs.sbbase.controller;

import io.jnslabs.sbbase.model.Greeting;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Autor Jairo Nascimento
 * @Created 22/02/2022 - 09:30
 */
@RestController
@RequestMapping("/hello-world")
public class HelloWordController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    @ResponseBody
    public Greeting sayHello(@RequestParam(name="name", required=false, defaultValue="Stranger") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
