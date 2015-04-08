package com.ventura.compras.web.compras;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ventura.compras.domain.compras.Compras;
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
			ses.setCondicionActual("");
			ses.setHistorial("");
			ses.setCondicionActual(ses.getCondicionUsuario());
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
			model.addAttribute("user_inicio", ses);
			model.addAttribute(
					"listcomp",
					comprasService.getCompras(ses.getCondiciones(),
							ses.getCondicionActual(), ses.getFechaActual(), ses.getFechaSelec()));
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
							ses.getCondicionActual(), ses.getFechaActual(), ses.getFechaSelec()));
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
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
			if (hist.contains("r")) {
				model.addAttribute("r", 1);
			} else {
				model.addAttribute("r", 0);
			}
			if (hist.contains("o")) {
				model.addAttribute("o", 1);
			} else {
				model.addAttribute("o", 0);
			}
			if (hist.contains("b")) {
				model.addAttribute("b", 1);
			} else {
				model.addAttribute("b", 0);
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
							ses.getCondicionActual(), ses.getFechaActual(), ses.getFechaSelec()));
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
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
			if (hist.contains("r")) {
				model.addAttribute("r", 1);
			} else {
				model.addAttribute("r", 0);
			}
			if (hist.contains("o")) {
				model.addAttribute("o", 1);
			} else {
				model.addAttribute("o", 0);
			}
			if (hist.contains("b")) {
				model.addAttribute("b", 1);
			} else {
				model.addAttribute("b", 0);
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
							ses.getCondicionActual(), ses.getFechaActual(), ses.getFechaSelec()));
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
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
			if (hist.contains("r")) {
				model.addAttribute("r", 1);
			} else {
				model.addAttribute("r", 0);
			}
			if (hist.contains("o")) {
				model.addAttribute("o", 1);
			} else {
				model.addAttribute("o", 0);
			}
			if (hist.contains("b")) {
				model.addAttribute("b", 1);
			} else {
				model.addAttribute("b", 0);
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
							ses.getCondicionActual(), ses.getFechaActual(), ses.getFechaSelec()));
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
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
			if (hist.contains("r")) {
				model.addAttribute("r", 1);
			} else {
				model.addAttribute("r", 0);
			}
			if (hist.contains("o")) {
				model.addAttribute("o", 1);
			} else {
				model.addAttribute("o", 0);
			}
			if (hist.contains("b")) {
				model.addAttribute("b", 1);
			} else {
				model.addAttribute("b", 0);
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
							ses.getCondicionActual(), ses.getFechaActual(), ses.getFechaSelec()));
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
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
			if (hist.contains("r")) {
				model.addAttribute("r", 1);
			} else {
				model.addAttribute("r", 0);
			}
			if (hist.contains("o")) {
				model.addAttribute("o", 1);
			} else {
				model.addAttribute("o", 0);
			}
			if (hist.contains("b")) {
				model.addAttribute("b", 1);
			} else {
				model.addAttribute("b", 0);
			}
			return "reports/centro";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/requisicion")
	public String requisicion(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute(
					"listcomp",
					comprasService.getRequisiciones(ses.getCondiciones(),
							ses.getCondicionActual(), ses.getFechaActual(), ses.getFechaSelec()));
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
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
			if (hist.contains("r")) {
				model.addAttribute("r", 1);
			} else {
				model.addAttribute("r", 0);
			}
			if (hist.contains("o")) {
				model.addAttribute("o", 1);
			} else {
				model.addAttribute("o", 0);
			}
			if (hist.contains("b")) {
				model.addAttribute("b", 1);
			} else {
				model.addAttribute("b", 0);
			}
			return "reports/requesicion";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/orden")
	public String orden(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");			
			model.addAttribute("usuarioactuall", ses.getUsuario());			
			List<Compras> ll = comprasService.getOrdenes(ses.getCondiciones(), ses.getCondicionActual(), ses.getFechaActual(), ses.getFechaSelec());
			ses.setAutocomplete(ll.get(ll.size()-1).getNroor().split("-")[1].replaceAll("'", String.valueOf('"')));
			model.addAttribute("autocompletar", ses.getAutocomplete());
			ll.get(ll.size()-1).setNroor(ll.get(ll.size()-1).getNroor().split("-")[0]);
			model.addAttribute("listcomp", ll);
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
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
			if (hist.contains("r")) {
				model.addAttribute("r", 1);
			} else {
				model.addAttribute("r", 0);
			}
			if (hist.contains("o")) {
				model.addAttribute("o", 1);
			} else {
				model.addAttribute("o", 0);
			}
			if (hist.contains("b")) {
				model.addAttribute("b", 1);
			} else {
				model.addAttribute("b", 0);
			}
			return "reports/orden";
		} else {
			return "redirect:/index/ingreso";
		}
	}
	
	@RequestMapping(value = "/filtro", method = RequestMethod.POST)
	public String filtro(@ModelAttribute("compra") Compras compra, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute("listcomp", comprasService.getOrdenesFiltro(ses.getCondiciones(), ses.getCondicionActual(), compra, ses.getFechaActual(), ses.getFechaSelec()));
			model.addAttribute("autocompletar", ses.getAutocomplete());
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
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
			if (hist.contains("r")) {
				model.addAttribute("r", 1);
			} else {
				model.addAttribute("r", 0);
			}
			if (hist.contains("o")) {
				model.addAttribute("o", 1);
			} else {
				model.addAttribute("o", 0);
			}
			if (hist.contains("b")) {
				model.addAttribute("b", 1);
			} else {
				model.addAttribute("b", 0);
			}
			return "reports/orden";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/bodega")
	public String bodega(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute(
					"listcomp",
					comprasService.getBodegas(ses.getCondiciones(),
							ses.getCondicionActual(), ses.getFechaActual(), ses.getFechaSelec()));
			String[] cond = ses.getCondicionActual().split(",");
			String mens = "";
			for (String cc : cond) {
				if (mens.isEmpty()) {
					mens = ses.getValores().get(cc);
				} else {
					mens = mens + " " + ses.getValores().get(cc);
				}
			}
			model.addAttribute("mensaje", mens.toUpperCase());
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
			if (hist.contains("r")) {
				model.addAttribute("r", 1);
			} else {
				model.addAttribute("r", 0);
			}
			if (hist.contains("o")) {
				model.addAttribute("o", 1);
			} else {
				model.addAttribute("o", 0);
			}
			if (hist.contains("b")) {
				model.addAttribute("b", 1);
			} else {
				model.addAttribute("b", 0);
			}
			return "reports/bodega";
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
			ses.setCondicionActual(ses.getCondicionActual() + ",Tipo Proveedor");
			if (compra.getPtype().equalsIgnoreCase("@@@@@")) {
				ses.getValores().put("Tipo Proveedor", "Tipo Proveedor: Todos");
				ses.getCondiciones().put("Tipo Proveedor", "");
			} else {
				ses.getValores().put("Tipo Proveedor",
						"Tipo Proveedor: " + compra.getPtyno());
				ses.getCondiciones().put("Tipo Proveedor",
						"c.ptype = '" + compra.getPtype() + "'");
			}

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
			} else if (request.getParameter("next").equals("oc")) {
				ret = "redirect:orden";
				rec = "o";
			} else if (request.getParameter("next").equals("rq")) {
				ret = "redirect:requisicion";
				rec = "r";
			} else if (request.getParameter("next").equals("bod")) {
				ret = "redirect:bodega";
				rec = "b";
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

	@RequestMapping(value = "ord", method = RequestMethod.POST)
	public String ord(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");

			ses.setCondicionActual(ses.getCondicionActual() + ",Ord");

			if (compra.getNroor().equalsIgnoreCase("@@@@@")) {
				ses.getValores().put("Ord", "O/C: Todos");
				ses.getCondiciones().put("Ord", "");
			} else {
				ses.getValores().put("Ord", "O/C: " + compra.getNroor());
				ses.getCondiciones().put("Ord",
						"c.nroor = '" + compra.getNroor() + "'");
			}
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
			} else if (request.getParameter("next").equals("rq")) {
				ret = "redirect:requisicion";
				rec = "r";
			} else if (request.getParameter("next").equals("bod")) {
				ret = "redirect:bodega";
				rec = "b";
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

	@RequestMapping(value = "req", method = RequestMethod.POST)
	public String req(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");

			ses.setCondicionActual(ses.getCondicionActual() + ",Req");

			if (compra.getNroor().equalsIgnoreCase("@@@@@")) {
				ses.getValores().put("Req", "R/Q: Todos");
				ses.getCondiciones().put("Req", "");
			} else {
				ses.getValores().put("Req", "R/Q: " + compra.getNroor());
				
				ses.getCondiciones().put("Req",
						"c.nroor = '" + compra.getNroor() + "'");
			}
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
			} else if (request.getParameter("next").equals("oc")) {
				ret = "redirect:orden";
				rec = "o";
			} else if (request.getParameter("next").equals("bod")) {
				ret = "redirect:bodega";
				rec = "b";
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

			ses.setCondicionActual(ses.getCondicionActual() + ",Clase");

			if (compra.getPicla().equalsIgnoreCase("@@@@@")) {
				ses.getValores().put("Clase", "Clase: Todos");
				ses.getCondiciones().put("Clase", "");
			} else {
				ses.getValores().put("Clase", "Clase: " + compra.getPicln());
				ses.getCondiciones().put("Clase",
						"c.picla = '" + compra.getPicla() + "'");
			}
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
			} else if (request.getParameter("next").equals("oc")) {
				ret = "redirect:orden";
				rec = "o";
			} else if (request.getParameter("next").equals("rq")) {
				ret = "redirect:requisicion";
				rec = "r";
			} else if (request.getParameter("next").equals("bod")) {
				ret = "redirect:bodega";
				rec = "b";
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
			ses.setCondicionActual(ses.getCondicionActual() + ",Centro");
			if (compra.getPcent().equalsIgnoreCase("@@@@@")) {
				ses.getCondiciones().put("Centro", "");
				ses.getValores().put("Centro", "Centro: Todos");
			} else {
				ses.getCondiciones().put("Centro",
						"c.pcent = '" + compra.getPcent() + "'");
				ses.getValores().put("Centro", "Centro: " + compra.getPcenn());
			}
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
			} else if (request.getParameter("next").equals("oc")) {
				ret = "redirect:orden";
				rec = "o";
			} else if (request.getParameter("next").equals("rq")) {
				ret = "redirect:requisicion";
				rec = "r";
			} else if (request.getParameter("next").equals("bod")) {
				ret = "redirect:bodega";
				rec = "b";
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
			ses.setCondicionActual(ses.getCondicionActual() + ",Proveedor");
			if (compra.getPprov() == -1000) {
				ses.getCondiciones().put("Proveedor", "");
				ses.getValores().put("Proveedor", "Proveedor: Todos");
			} else {
				ses.getCondiciones().put("Proveedor",
						"c.pprov = " + compra.getPprov());
				ses.getValores().put("Proveedor",
						"Proveedor: " + compra.getPpnov());
			}
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
			} else if (request.getParameter("next").equals("oc")) {
				ret = "redirect:orden";
				rec = "o";
			} else if (request.getParameter("next").equals("rq")) {
				ret = "redirect:requisicion";
				rec = "r";
			} else if (request.getParameter("next").equals("bod")) {
				ret = "redirect:bodega";
				rec = "b";
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
			ses.setCondicionActual(ses.getCondicionActual() + ",Item");
			if (compra.getPipro().equalsIgnoreCase("@@@@@")) {
				ses.getCondiciones().put("Item", "");
				ses.getValores().put("Item", "Item: Todos");
			} else {
				ses.getCondiciones().put("Item",
						"c.pipro = '" + compra.getPipro() + "'");
				ses.getValores().put("Item", "Item: " + compra.getPides());
			}
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
			} else if (request.getParameter("next").equals("oc")) {
				ret = "redirect:orden";
				rec = "o";
			} else if (request.getParameter("next").equals("rq")) {
				ret = "redirect:requisicion";
				rec = "r";
			} else if (request.getParameter("next").equals("bod")) {
				ret = "redirect:bodega";
				rec = "b";
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
			ses.setCondicionActual(ses.getCondicionActual() + ",Comprador");
			if (compra.getPcomd().equalsIgnoreCase("@@@@@")) {
				ses.getCondiciones().put("Comprador", "");
				ses.getValores().put("Comprador", "Comprador: Todos");
			} else {
				ses.getCondiciones().put("Comprador",
						"c.pcomd = '" + compra.getPcomd() + "'");
				ses.getValores().put("Comprador",
						"Comprador: " + compra.getPnomd());
			}
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
			} else if (request.getParameter("next").equals("oc")) {
				ret = "redirect:orden";
				rec = "o";
			} else if (request.getParameter("next").equals("rq")) {
				ret = "redirect:requisicion";
				rec = "r";
			} else if (request.getParameter("next").equals("bod")) {
				ret = "redirect:bodega";
				rec = "b";
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
	

	@RequestMapping(value = "bod", method = RequestMethod.POST)
	public String bod(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");

			ses.setCondicionActual(ses.getCondicionActual() + ",Bod");

			if (compra.getPlocal().equalsIgnoreCase("@@@@@")) {
				ses.getValores().put("Bod", "Bodegas: Todos");
				ses.getCondiciones().put("Bod", "");
			} else {
				ses.getValores().put("Bod", "Bodega: " + compra.getPlnon());
				ses.getCondiciones().put("Bod",
						"c.plocal = '" + compra.getPlocal() + "'");
			}
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
			} else if (request.getParameter("next").equals("rq")) {
				ret = "redirect:requisicion";
				rec = "r";
			} else if (request.getParameter("next").equals("oc")) {
				ret = "redirect:orden";
				rec = "o";
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

	@RequestMapping(value = "/retornar", method = RequestMethod.GET)
	public String retornar(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");
			String[] cond = ses.getCondicionActual().split(",");
			if (ses.getHistorial().length() > 1) {
				String ncond = "";
				String hist = "";
				for (int i = ses.getCondicionUsuario().split(",").length; i < cond.length - 1; i++) {
					if (ncond.isEmpty()) {
						ncond = cond[i];
					} else {
						ncond = ncond + "," + cond[i];
					}
					hist = hist + ses.getHistorial().charAt(i - ses.getCondicionUsuario().split(",").length);
				}
				if (hist.charAt(hist.length() - 1) == 'p') {
					ret = "redirect:proveedor";
				} else if (hist.charAt(hist.length() - 1) == 'i') {
					ret = "redirect:item";
				} else if (hist.charAt(hist.length() - 1) == 'c') {
					ret = "redirect:comprador";
				} else if (hist.charAt(hist.length() - 1) == 'q') {
					ret = "redirect:clase";
				} else if (hist.charAt(hist.length() - 1) == 'k') {
					ret = "redirect:centro";
				} else if (hist.charAt(hist.length() - 1) == 'r') {
					ret = "redirect:requisicion";
				} else if (hist.charAt(hist.length() - 1) == 'o') {
					ret = "redirect:orden";
				} else if (hist.charAt(hist.length() - 1) == 'b') {
					ret = "redirect:bodega";
				}
				ses.setCondicionActual(ses.getCondicionUsuario() + "," + ncond);
				ses.setHistorial(hist);
			} else {
				ses.setHistorial("");
				ses.setCondicionActual("");
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

	@RequestMapping(value = "/actualizar", method = RequestMethod.GET)
	public String actualizar(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			model.addAttribute("redireccion", "mostrar");
			model.addAttribute("accion", "generar");
			model.addAttribute("compra", new Compras());
			session ses = (session) (model.asMap().get("user_inicio"));
			if (ses.getCenters().isEmpty()) {
				model.addAttribute("cennnn", 0);
			} else {
				model.addAttribute("cennnn", 1);
			}
			return "actualizar";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/generar", method = RequestMethod.POST)
	public String generaar(@ModelAttribute("compra") Compras compra, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) (model.asMap().get("user_inicio"));
			if (ses.getCenters().isEmpty()) {
				ses.setFechaSelec("a" + compra.getPano() + ",mm"+ compra.getPmes());
				ses.setCondicionUsuario("a" + compra.getPano() + ",mm"
						+ compra.getPmes() + ",c" + compra.getPcia() + ",l"
						+ compra.getPpais() + ",m" + compra.getPmond());
			} else {
				ses.setFechaSelec("a" + compra.getPano() + ",mm"+ compra.getPmes());
				ses.setCondicionUsuario("a" + compra.getPano() + ",mm"
						+ compra.getPmes() + ",c" + compra.getPcia() + ",l"
						+ compra.getPpais() + ",m" + compra.getPmond() + ",k"
						+ compra.getPcent());
			}
			model.addAttribute("user_inicio", ses);
			return "redirect:mostrar";
		} else {
			return "redirect:/index/ingreso";
		}
	}

}
