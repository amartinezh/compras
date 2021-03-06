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

import com.ventura.compras.domain.adm.Center;
import com.ventura.compras.domain.adm.Company;
import com.ventura.compras.domain.adm.Currency;
import com.ventura.compras.domain.adm.Level;
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

	@RequestMapping(value = "/panel", method = RequestMethod.GET)
	public String panel(Map<String, Object> model) {
		model.put("user", new User());
		return "panel";
	}
	
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
			Map<Integer, String> cent = new HashMap<Integer, String>();
			Map<Integer, String> curr = new HashMap<Integer, String>();
			Map<Integer, String> lev = new HashMap<Integer, String>();
			List<TypeUser> types = (List<TypeUser>)(ses.getInformacion().get(0));
			List<Company> companys = (List<Company>)(ses.getInformacion().get(1));
			List<Center> centers = (List<Center>)(ses.getInformacion().get(2));
			List<Currency> currencys = (List<Currency>)(ses.getInformacion().get(3));
			List<Level> levels = (List<Level>)(ses.getInformacion().get(4));
			for(TypeUser t: types) {
				typ.put(t.getId(), t.getDescripcion());
			}
			for(Company com: companys) {
				comp.put(com.getId(), com.getDescripcion());
			}
			for(Center cen : centers) {
				cent.put(cen.getId(), cen.getDescripcion());
			}
			for(Currency cur: currencys) {
				curr.put(cur.getId(), cur.getDescripcion());
			}
			for(Level levv: levels) {
				lev.put(levv.getId(), levv.getDescripcion());
			}
			model.addAttribute("listype", typ);
			model.addAttribute("listcomp", comp);
			model.addAttribute("listcent", cent);
			model.addAttribute("listcur", curr);
			model.addAttribute("listlev", lev);
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
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("useredit", user);
			model.addAttribute("redireccion", "listar");
			model.addAttribute("accion", "editar");
			Map<Integer, String> comp = new HashMap<Integer, String>();
			Map<Integer, String> typ = new HashMap<Integer, String>();
			Map<Integer, String> cent = new HashMap<Integer, String>();
			Map<Integer, String> curr = new HashMap<Integer, String>();
			Map<Integer, String> lev = new HashMap<Integer, String>();
			List<TypeUser> types = (List<TypeUser>)(ses.getInformacion().get(0));
			List<Company> companys = (List<Company>)(ses.getInformacion().get(1));
			List<Center> centers = (List<Center>)(ses.getInformacion().get(2));
			List<Currency> currencys = (List<Currency>)(ses.getInformacion().get(3));
			List<Level> levels = (List<Level>)(ses.getInformacion().get(4));
			for(TypeUser t: types) {
				typ.put(t.getId(), t.getDescripcion());
			}
			for(Company com: companys) {
				comp.put(com.getId(), com.getDescripcion());
			}
			for(Center cen : centers) {
				cent.put(cen.getId(), cen.getDescripcion());
			}
			for(Currency cur: currencys) {
				curr.put(cur.getId(), cur.getDescripcion());
			}
			for(Level levv: levels) {
				lev.put(levv.getId(), levv.getDescripcion());
			}
			model.addAttribute("listype", typ);
			model.addAttribute("listcomp", comp);
			model.addAttribute("listcent", cent);
			model.addAttribute("listcur", curr);
			model.addAttribute("listlev", lev);
			return "admin/events/editUser";
		}else {
			return "redirect:/index/ingreso";
		}
	}
	
	@RequestMapping(value = "/editar", method = RequestMethod.POST)
	public String editarUser(@ModelAttribute("user") User user, Model model) {		
		if (model.containsAttribute("user_inicio") == true) {
			User u = userManager.getUser(user);
			u.setCent(user.getCent());
			u.setComp(user.getComp());
			u.setCurr(user.getCurr());
			u.setLevel(user.getLevel());
			u.setType(user.getType());
			if (userManager.editUser(u)) {
				return "redirect:listar";
			} else {
				session ses = (session) model.asMap().get("user_inicio");
				model.addAttribute("user", user);
				model.addAttribute("redireccion", "listar");
				model.addAttribute("accion", "editar");
				Map<Integer, String> comp = new HashMap<Integer, String>();
				Map<Integer, String> typ = new HashMap<Integer, String>();
				Map<Integer, String> cent = new HashMap<Integer, String>();
				Map<Integer, String> curr = new HashMap<Integer, String>();
				Map<Integer, String> lev = new HashMap<Integer, String>();
				List<TypeUser> types = (List<TypeUser>)(ses.getInformacion().get(0));
				List<Company> companys = (List<Company>)(ses.getInformacion().get(1));
				List<Center> centers = (List<Center>)(ses.getInformacion().get(2));
				List<Currency> currencys = (List<Currency>)(ses.getInformacion().get(3));
				List<Level> levels = (List<Level>)(ses.getInformacion().get(4));
				for(TypeUser t: types) {
					typ.put(t.getId(), t.getDescripcion());
				}
				for(Company com: companys) {
					comp.put(com.getId(), com.getDescripcion());
				}
				for(Center cen : centers) {
					cent.put(cen.getId(), cen.getDescripcion());
				}
				for(Currency cur: currencys) {
					curr.put(cur.getId(), cur.getDescripcion());
				}
				for(Level levv: levels) {
					lev.put(levv.getId(), levv.getDescripcion());
				}
				model.addAttribute("listype", typ);
				model.addAttribute("listcomp", comp);
				model.addAttribute("listcent", cent);
				model.addAttribute("listcur", curr);
				model.addAttribute("listlev", lev);
				return "admin/events/addUser";
			}
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/pass", method = RequestMethod.POST)
	public String passUser(@ModelAttribute("user") User user, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("useredit", user);
			model.addAttribute("redireccion", "listar");
			model.addAttribute("accion", "editpass");			
			return "admin/events/passUser";
		}else {
			return "redirect:/index/ingreso";
		}
	}
	
	@RequestMapping(value = "/editpass", method = RequestMethod.POST)
	public String editPass(@ModelAttribute("user") User user, Model model) {		
		if (model.containsAttribute("user_inicio") == true) {
			User u = userManager.getUser(user);
			user.setCent(u.getCent());
			user.setComp(u.getComp());
			user.setCurr(u.getCurr());
			user.setLevel(u.getLevel());
			user.setType(u.getType());
			if (userManager.editUser(user)) {
				return "redirect:listar";
			} else {
				session ses = (session) model.asMap().get("user_inicio");
				model.addAttribute("user", user);
				model.addAttribute("redireccion", "listar");
				model.addAttribute("accion", "editpass");
				return "admin/events/passUser";
			}
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
