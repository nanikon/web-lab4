package ru.nanikon.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Natalia Nikonova
 */
@RestController
public class TestController {

   @GetMapping("/echo")
   @ResponseBody
   public String echo(@RequestParam(name = "s") String s) {
      return s;
   }
}

