package com.bing.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mainController")
public class MainController {

public void index(){
	system.out.println("Hello to Bing House");
}
