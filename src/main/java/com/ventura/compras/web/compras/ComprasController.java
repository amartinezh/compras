package com.ventura.compras.web.compras;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ventura.compras.domain.compras.Compras;
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
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			ses.setHistorial("");
			model.addAttribute("user_inicio", ses);
			model.addAttribute("listcomp", comprasService.getCompras());
			model.addAttribute("compra", new Compras());
			return "dashboard";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/comprador")
	public String comprador(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute(
					"listcomp",
					comprasService.getCompradores(ses.getCondiciones(),
							ses.getCondicionActual()));
			model.addAttribute("compra", new Compras());
			String hist = ses.getHistorial();
			if (hist.contains("c")) {
				model.addAttribute("c", 1);
			} else {
				model.addAttribute("c", 0);
			}
			if (hist.contains("p")) {
				model.addAttribute("p", 1);
			} else {
				model.addAttribute("p", 0);
			}
			if (hist.contains("i")) {
				model.addAttribute("i", 1);
			} else {
				model.addAttribute("i", 0);
			}
			if (hist.contains("q")) {
				model.addAttribute("q", 1);
			} else {
				model.addAttribute("q", 0);
			}
			if (hist.contains("k")) {
				model.addAttribute("k", 1);
			} else {
				model.addAttribute("k", 0);
			}
			return "reports/comprador";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/proveedor")
	public String proveedor(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute(
					"listcomp",
					comprasService.getProveedores(ses.getCondiciones(),
							ses.getCondicionActual()));
			model.addAttribute("compra", new Compras());
			String hist = ses.getHistorial();
			if (hist.contains("c")) {
				model.addAttribute("c", 1);
			} else {
				model.addAttribute("c", 0);
			}
			if (hist.contains("p")) {
				model.addAttribute("p", 1);
			} else {
				model.addAttribute("p", 0);
			}
			if (hist.contains("i")) {
				model.addAttribute("i", 1);
			} else {
				model.addAttribute("i", 0);
			}
			if (hist.contains("q")) {
				model.addAttribute("q", 1);
			} else {
				model.addAttribute("q", 0);
			}
			if (hist.contains("k")) {
				model.addAttribute("k", 1);
			} else {
				model.addAttribute("k", 0);
			}
			return "reports/proveedor";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/item")
	public String item(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute(
					"listcomp",
					comprasService.getItems(ses.getCondiciones(),
							ses.getCondicionActual()));
			model.addAttribute("compra", new Compras());
			String hist = ses.getHistorial();
			if (hist.contains("c")) {
				model.addAttribute("c", 1);
			} else {
				model.addAttribute("c", 0);
			}
			if (hist.contains("p")) {
				model.addAttribute("p", 1);
			} else {
				model.addAttribute("p", 0);
			}
			if (hist.contains("i")) {
				model.addAttribute("i", 1);
			} else {
				model.addAttribute("i", 0);
			}
			if (hist.contains("q")) {
				model.addAttribute("q", 1);
			} else {
				model.addAttribute("q", 0);
			}
			if (hist.contains("k")) {
				model.addAttribute("k", 1);
			} else {
				model.addAttribute("k", 0);
			}
			return "reports/item";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/clase")
	public String clase(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute(
					"listcomp",
					comprasService.getClases(ses.getCondiciones(),
							ses.getCondicionActual()));
			model.addAttribute("compra", new Compras());
			String hist = ses.getHistorial();
			if (hist.contains("c")) {
				model.addAttribute("c", 1);
			} else {
				model.addAttribute("c", 0);
			}
			if (hist.contains("p")) {
				model.addAttribute("p", 1);
			} else {
				model.addAttribute("p", 0);
			}
			if (hist.contains("i")) {
				model.addAttribute("i", 1);
			} else {
				model.addAttribute("i", 0);
			}
			if (hist.contains("q")) {
				model.addAttribute("q", 1);
			} else {
				model.addAttribute("q", 0);
			}
			if (hist.contains("k")) {
				model.addAttribute("k", 1);
			} else {
				model.addAttribute("k", 0);
			}
			return "reports/clase";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/centro")
	public String centro(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute(
					"listcomp",
					comprasService.getCentros(ses.getCondiciones(),
							ses.getCondicionActual()));
			model.addAttribute("compra", new Compras());
			String hist = ses.getHistorial();
			if (hist.contains("c")) {
				model.addAttribute("c", 1);
			} else {
				model.addAttribute("c", 0);
			}
			if (hist.contains("p")) {
				model.addAttribute("p", 1);
			} else {
				model.addAttribute("p", 0);
			}
			if (hist.contains("i")) {
				model.addAttribute("i", 1);
			} else {
				model.addAttribute("i", 0);
			}
			if (hist.contains("q")) {
				model.addAttribute("q", 1);
			} else {
				model.addAttribute("q", 0);
			}
			if (hist.contains("k")) {
				model.addAttribute("k", 1);
			} else {
				model.addAttribute("k", 0);
			}
			return "reports/centro";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "comp", method = RequestMethod.POST)
	public String comp(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");
			ses.getCondiciones().put("servicio",
					"c.ptype = '" + compra.getPtype() + "'");
			ses.setCondicionActual("servicio");
			String rec = "";
			if (request.getParameter("next").equals("compra")) {
				ret = "redirect:comprador";
				rec = "c";
			} else if (request.getParameter("next").equals("prove")) {
				ret = "redirect:proveedor";
				rec = "p";
			} else if (request.getParameter("next").equals("ite")) {
				ret = "redirect:item";
				rec = "i";
			} else if (request.getParameter("next").equals("clas")) {
				ret = "redirect:clase";
				rec = "q";
			} else if (request.getParameter("next").equals("centr")) {
				ret = "redirect:centro";
				rec = "k";
			}
			if (rec.isEmpty()) {
				ses.setHistorial("");
			} else {
				ses.setHistorial(ses.getHistorial() + rec);
			}
			model.addAttribute("user_inicio", ses);
			return ret;
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "class", method = RequestMethod.POST)
	public String classs(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");
			ses.setCondicionActual("servicio");
			model.addAttribute("user_inicio", ses);
			ses.getCondiciones().put("clase",
					"c.picla = '" + compra.getPicla() + "'");
			ses.setCondicionActual(ses.getCondicionActual() + ",clase");
			String rec = "";
			if (request.getParameter("next").equals("compra")) {
				ret = "redirect:comprador";
				rec = "c";
			} else if (request.getParameter("next").equals("prove")) {
				ret = "redirect:proveedor";
				rec = "p";
			} else if (request.getParameter("next").equals("ite")) {
				ret = "redirect:item";
				rec = "i";
			} else if (request.getParameter("next").equals("centr")) {
				ret = "redirect:centro";
				rec = "k";
			}
			if (rec.isEmpty()) {
				ses.setHistorial("");
			} else {
				ses.setHistorial(ses.getHistorial() + rec);
			}
			model.addAttribute("user_inicio", ses);
			return ret;
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "cent", method = RequestMethod.POST)
	public String cent(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");
			ses.setCondicionActual("servicio");
			model.addAttribute("user_inicio", ses);
			ses.getCondiciones().put("clase",
					"c.pcent = '" + compra.getPcent() + "'");
			ses.setCondicionActual(ses.getCondicionActual() + ",centro");
			String rec = "";
			if (request.getParameter("next").equals("compra")) {
				ret = "redirect:comprador";
				rec = "c";
			} else if (request.getParameter("next").equals("prove")) {
				ret = "redirect:proveedor";
				rec = "p";
			} else if (request.getParameter("next").equals("ite")) {
				ret = "redirect:item";
				rec = "i";
			} else if (request.getParameter("next").equals("clas")) {
				ret = "redirect:clase";
				rec = "q";
			}
			if (rec.isEmpty()) {
				ses.setHistorial("");
			} else {
				ses.setHistorial(ses.getHistorial() + rec);
			}
			model.addAttribute("user_inicio", ses);
			return ret;
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "prov", method = RequestMethod.POST)
	public String prov(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("user_inicio", ses);
			ses.getCondiciones().put("proveedor",
					"c.pprov = " + compra.getPprov());
			ses.setCondicionActual(ses.getCondicionActual() + ",proveedor");
			String rec = "";
			if (request.getParameter("next").equals("compra")) {
				ret = "redirect:comprador";
				rec = "c";
			} else if (request.getParameter("next").equals("ite")) {
				ret = "redirect:item";
				rec = "i";
			} else if (request.getParameter("next").equals("clas")) {
				ret = "redirect:clase";
				rec = "q";
			} else if (request.getParameter("next").equals("centr")) {
				ret = "redirect:centro";
				rec = "k";
			}
			if (rec.isEmpty()) {
				ses.setHistorial("");
			} else {
				ses.setHistorial(ses.getHistorial() + rec);
			}
			model.addAttribute("user_inicio", ses);
			return ret;
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "itemm", method = RequestMethod.POST)
	public String item(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("user_inicio", ses);
			ses.getCondiciones().put("item",
					"c.pipro = '" + compra.getPipro() + "'");
			ses.setCondicionActual(ses.getCondicionActual() + ",item");
			String rec = "";
			if (request.getParameter("next").equals("compra")) {
				ret = "redirect:comprador";
				rec = "c";
			} else if (request.getParameter("next").equals("prove")) {
				ret = "redirect:proveedor";
				rec = "p";
			} else if (request.getParameter("next").equals("clas")) {
				ret = "redirect:clase";
				rec = "q";
			} else if (request.getParameter("next").equals("centr")) {
				ret = "redirect:centro";
				rec = "k";
			}
			if (rec.isEmpty()) {
				ses.setHistorial("");
			} else {
				ses.setHistorial(ses.getHistorial() + rec);
			}
			model.addAttribute("user_inicio", ses);
			return ret;
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "ccompra", method = RequestMethod.POST)
	public String compra(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("user_inicio", ses);
			ses.getCondiciones().put("comprador",
					"c.pcomd = '" + compra.getPcomd() + "'");
			ses.setCondicionActual(ses.getCondicionActual() + ",comprador");
			String rec = "";
			if (request.getParameter("next").equals("prove")) {
				ret = "redirect:proveedor";
				rec = "p";
			} else if (request.getParameter("next").equals("ite")) {
				ret = "redirect:item";
				rec = "i";
			} else if (request.getParameter("next").equals("clas")) {
				ret = "redirect:clase";
				rec = "q";
			} else if (request.getParameter("next").equals("centr")) {
				ret = "redirect:centro";
				rec = "k";
			}
			if (rec.isEmpty()) {
				ses.setHistorial("");
			} else {
				ses.setHistorial(ses.getHistorial() + rec);
			}
			model.addAttribute("user_inicio", ses);
			return ret;
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
