package com.ventura.compras.web.compras;

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
import org.springframework.web.bind.support.SessionStatus;

import com.ventura.compras.domain.login.User;
import com.ventura.compras.domain.session.session;
import com.ventura.compras.service.compras.ComprasService;

@Controller
@RequestMapping(value = "/compras")
@SessionAttributes({ "user_inicio" })
public class ComprasController {

	/** Logger for this class and subclasses */
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private ComprasService comprasService;
	
	@RequestMapping(value = "/mostrar")
	public String inicio(Model model) {
		if(model.containsAttribute("user_inicio") == true) {
			session ses = (session)model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute("listcomp", comprasService.getCompras());
			return "dashboard";
		} else {
			return "redirect:/index/ingreso";
		}
	}
	
	@RequestMapping(value = "/salir", method = RequestMethod.GET)
	public String salir(Model model,  SessionStatus status) {		 
		status.setComplete();
		return "redirect:/index/ingreso";
	}
	
}
