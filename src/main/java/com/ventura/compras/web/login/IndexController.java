package com.ventura.compras.web.login;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ventura.compras.domain.login.User;
import com.ventura.compras.domain.session.session;
import com.ventura.compras.service.adm.CompanyService;
import com.ventura.compras.service.adm.TypeUserService;
import com.ventura.compras.service.login.UserManager;

@Controller
@RequestMapping(value = "/index")
@SessionAttributes({ "user_inicio" })
public class IndexController {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private UserManager userManager;

	@Autowired
	private TypeUserService typeUserService;
	
	@Autowired
	private CompanyService companyService;
	
	/*
	 * @Autowired private PermisoManager permisoManager;
	 */
	@RequestMapping(value = "/ingreso", method = RequestMethod.GET)
	public String employee(Map<String, Object> model) {
		model.put("user", new User());
		return "key/index";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Map<String, Object> model) {
		model.put("user", new User());
		return "dashboard";
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/validar", method = RequestMethod.POST)
	public String addEmployee(@Valid @ModelAttribute("user") User user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "key/index";
		} else {
			User uss = userManager.val(user.getId(), user.getPass());
			if (uss != null) {				
				if (uss.getType() != null) {
					session ses = new session(uss.getId());
					String ret = null;
					List<Object> info = null;
					if (uss.getType().getDescripcion().equalsIgnoreCase("administrador")) {						
						info = new LinkedList<Object>();
						info.add(0, typeUserService.listTypeUser());
						info.add(1, companyService.listCompany());
						ret = "redirect:/index/dashboard";
						//ret = "redirect:/admin/listar";
					} else {
						ret = "redirect:/compras/mostrar";
					}
					ses.setInformacion(info);
					model.addAttribute("user_inicio", ses);
					return ret;
				} else {
					return "key/index";
				}
			} else {
				model.addAttribute(
						"msg",
						"<script type=\"text/javascript\">$( window ).load(function() { adv(); }); </script>");
				return "key/index";
			}
		}
	}

	public void setuserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	public UserManager getUserManager() {
		return userManager;
	}

}
