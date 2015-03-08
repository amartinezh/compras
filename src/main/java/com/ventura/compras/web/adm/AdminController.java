package com.ventura.compras.web.adm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ventura.compras.domain.login.User;
import com.ventura.compras.domain.session.session;
import com.ventura.compras.service.login.UserManager;

@Controller
@RequestMapping("/admin")
@SessionAttributes({ "user_inicio" })
public class AdminController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String openPage(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute("listuser", userManager.getUsers());
			return "admin/users";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/agregar", method = RequestMethod.GET)
	public String addUser(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			model.addAttribute("user", new User());
			model.addAttribute("redireccion", "listar");
			model.addAttribute("accion", "insertar");
			return "admin/events/addUser";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/salir", method = RequestMethod.GET)
	public String salir(Model model, SessionStatus status) {
		status.setComplete();
		return "redirect:/index/ingreso";
	}

}
