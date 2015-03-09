package com.ventura.compras.web.adm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ventura.compras.domain.adm.Company;
import com.ventura.compras.domain.adm.TypeUser;
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
			model.addAttribute("user", new User());
			return "admin/users";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/agregar", method = RequestMethod.GET)
	public String addUser(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("user", new User());
			model.addAttribute("redireccion", "listar");
			model.addAttribute("accion", "insertar");
			Map<Integer, String> comp = new HashMap<Integer, String>();
			Map<Integer, String> typ = new HashMap<Integer, String>();
			List<TypeUser> types = (List<TypeUser>)(ses.getInformacion().get(0));
			List<Company> companys = (List<Company>)(ses.getInformacion().get(1));
			for(TypeUser t: types) {
				typ.put(t.getId(), t.getDescripcion());
			}
			for(Company com: companys) {
				comp.put(com.getId(), com.getDescripcion());
			}
			model.addAttribute("listype", typ);
			model.addAttribute("listcomp", comp);
			return "admin/events/addUser";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/insertar", method = RequestMethod.POST)
	public String insertUser(@ModelAttribute("user") User user, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			if (userManager.addUser(user)) {
				return "redirect:listar";
			} else {
				model.addAttribute("user", user);
				model.addAttribute("redireccion", "listar");
				model.addAttribute("accion", "insertar");
				return "admin/events/addUser";
			}
		} else {
			return "redirect:/index/ingreso";
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteUser(@ModelAttribute("user") User user, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			userManager.deleteUser(user.getId());
			return "redirect:/admin/listar";
		}else {
			return "redirect:/index/ingreso";
		}
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("user") User user, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			return "redirect:/admin/listar";
		}else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/salir", method = RequestMethod.GET)
	public String salir(Model model, SessionStatus status) {
		status.setComplete();
		return "redirect:/index/ingreso";
	}

}
