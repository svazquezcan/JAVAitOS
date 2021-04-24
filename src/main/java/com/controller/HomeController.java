package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Controlador que gestiona el home
 * @author aimry
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {
	
	/**
	 * Devuelve la vista correspondiente
	 * @param model
	 * @return
	 */
	@GetMapping
	public String index(Model model) {
		return "game";
	}
}
