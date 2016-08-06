package com.ventura.compras.web.compras;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder.Case;
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

	private String obtNav(String hist) {
		StringBuilder nav = new StringBuilder();
		nav.append("> Inicio");
		for (int i = 0; i < hist.length(); i++) {
			switch (hist.charAt(i)) {
			case 'p':
				nav.append(" > Proveedores");
				break;
			case 'i':
				nav.append(" > Items");
				break;
			case 'q':
				nav.append(" > Clases");
				break;
			case 'k':
				nav.append(" > Centros");
				break;
			case 'r':
				nav.append(" > Requisiciones");
				break;
			case 'o':
				nav.append(" > Ordenes");
				break;
			case 'b':
				nav.append(" > Bodegas");
				break;
			case 'e':
				nav.append(" > Estados");
				break;
			default:
				nav.append(" > Compradores");
				break;
			}
		}
		return nav.toString();
	}

	private String obtMen(String[] cond, Map<String, String> valores,
			String campover) {
		StringBuilder mens = new StringBuilder();
		if (campover.equals("ord")) {
			mens.append("Filtro: Ordenado");
		} else {
			mens.append("Filtro: Recibido");
		}
		for (String cc : cond) {
			if (mens.length() == 0) {
				mens.append(valores.get(cc));
			} else {
				mens.append(" " + valores.get(cc));
			}
		}
		return mens.toString();
	}

	@RequestMapping(value = "/mostrar")
	public String inicio(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			ses.setCondicionActual("");
			ses.setHistorial("");
			ses.setFiltro(null);
			ses.setAutocomplete("");
			ses.setCondicionActual(ses.getCondicionUsuario());
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
			model.addAttribute("user_inicio", ses);
			model.addAttribute(
					"listcomp",
					comprasService.getCompras(ses.getCondiciones(),
							ses.getCondicionActual(), ses.getFechaActual(),
							ses.getFechaSelec(), ses.getCampover()));
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
							ses.getCondicionActual(), ses.getFechaActual(),
							ses.getFechaSelec(), ses.getCampover()));
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
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
			if (hist.contains("e")) {
				model.addAttribute("e", 1);
			} else {
				model.addAttribute("e", 0);
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
			List<Compras> ll = comprasService.getProveedores(
					ses.getCondiciones(), ses.getCondicionActual(),
					ses.getFechaActual(), ses.getFechaSelec(), ses.getFiltro(),
					ses.getCampover());
			if (ses.getFiltro() == null) {
				ses.setAutocomplete(ll.get(ll.size() - 1).getNroor()
						.replaceAll("'", String.valueOf('"')));
				ll.get(ll.size() - 1).setNroor("");
			}
			ses.setFiltro(null);
			model.addAttribute("autocompletar", ses.getAutocomplete());
			model.addAttribute("listcomp", ll);
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
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
			if (hist.contains("e")) {
				model.addAttribute("e", 1);
			} else {
				model.addAttribute("e", 0);
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
			List<Compras> ll = comprasService.getItems(ses.getCondiciones(),
					ses.getCondicionActual(), ses.getFechaActual(),
					ses.getFechaSelec(), ses.getFiltro(), ses.getCampover());
			if (ses.getFiltro() == null) {
				ses.setAutocomplete(ll.get(ll.size() - 1).getNroor()
						.replaceAll("'", String.valueOf('"')));
				ll.get(ll.size() - 1).setNroor("");
			}
			ses.setFiltro(null);
			model.addAttribute("autocompletar", ses.getAutocomplete());
			model.addAttribute("listcomp", ll);
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
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
			if (hist.contains("e")) {
				model.addAttribute("e", 1);
			} else {
				model.addAttribute("e", 0);
			}
			return "reports/item";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/estado")
	public String estado(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute(
					"listcomp",
					comprasService.getEstados(ses.getCondiciones(),
							ses.getCondicionActual(), ses.getFechaActual(),
							ses.getFechaSelec(), ses.getCampover()));
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
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
			return "reports/estado";
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
							ses.getCondicionActual(), ses.getFechaActual(),
							ses.getFechaSelec(), ses.getCampover()));
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
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
			if (hist.contains("e")) {
				model.addAttribute("e", 1);
			} else {
				model.addAttribute("e", 0);
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
							ses.getCondicionActual(), ses.getFechaActual(),
							ses.getFechaSelec(), ses.getCampover()));
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
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
			if (hist.contains("e")) {
				model.addAttribute("e", 1);
			} else {
				model.addAttribute("e", 0);
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
			List<Compras> ll = comprasService.getRequisiciones(
					ses.getCondiciones(), ses.getCondicionActual(),
					ses.getFechaActual(), ses.getFechaSelec(), ses.getFiltro(),
					ses.getCampover());
			if (ses.getFiltro() == null) {
				ses.setAutocomplete(ll.get(ll.size() - 1).getNroor().split("-")[1]
						.replaceAll("'", String.valueOf('"')));
				ll.get(ll.size() - 1).setNroor(
						ll.get(ll.size() - 1).getNroor().split("-")[0]);
			}
			ses.setFiltro(null);
			model.addAttribute("autocompletar", ses.getAutocomplete());
			model.addAttribute("listcomp", ll);
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
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
			List<Compras> ll = comprasService.getOrdenes(ses.getCondiciones(),
					ses.getCondicionActual(), ses.getFechaActual(),
					ses.getFechaSelec(), ses.getFiltro(), ses.getCampover());
			if (ses.getFiltro() == null) {
				ses.setAutocomplete(ll.get(ll.size() - 1).getNroor().split("-")[1]
						.replaceAll("'", String.valueOf('"')));
				ll.get(ll.size() - 1).setNroor(
						ll.get(ll.size() - 1).getNroor().split("-")[0]);
			}
			ses.setFiltro(null);
			model.addAttribute("autocompletar", ses.getAutocomplete());
			model.addAttribute("listcomp", ll);
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
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
			/*
			 * if (hist.contains("e")) { model.addAttribute("e", 1); } else {
			 * model.addAttribute("e", 0); }
			 */
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
							ses.getCondicionActual(), ses.getFechaActual(),
							ses.getFechaSelec(), ses.getCampover()));
			model.addAttribute("navegacion", obtNav(ses.getHistorial()));
			model.addAttribute(
					"mensaje",
					obtMen(ses.getCondicionActual().split(","),
							ses.getValores(), ses.getCampover()).toUpperCase());
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
			if (hist.contains("e")) {
				model.addAttribute("e", 1);
			} else {
				model.addAttribute("e", 0);
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
			} else if (request.getParameter("next").equals("est")) {
				ret = "redirect:estado";
				rec = "e";
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
			ses.setFiltro(null);
			ses.setAutocomplete("");
			ses.setCondicionActual(ses.getCondicionActual() + ",Ord");
			if (compra.getNroor().equalsIgnoreCase("@@@@@")) {
				ses.getValores().put("Ord", "O/C: Todos");
				ses.getCondiciones().put("Ord", "c.tipoc = 'O'");
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
			} else if (request.getParameter("next").equals("est")) {
				ret = "redirect:estado";
				rec = "e";
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
			ses.setFiltro(null);
			ses.setAutocomplete("");
			ses.setCondicionActual(ses.getCondicionActual() + ",Req");
			if (compra.getNroor().equalsIgnoreCase("@@@@@")) {
				ses.getValores().put("Req", "R/Q: Todos");
				ses.getCondiciones().put("Req", "c.tipoc = 'R'");
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
			} else if (request.getParameter("next").equals("est")) {
				ret = "redirect:estado";
				rec = "e";
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
			} else if (request.getParameter("next").equals("est")) {
				ret = "redirect:estado";
				rec = "e";
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
			} else if (request.getParameter("next").equals("est")) {
				ret = "redirect:estado";
				rec = "e";
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
			} else if (request.getParameter("next").equals("est")) {
				ret = "redirect:estado";
				rec = "e";
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
			ses.setFiltro(null);
			ses.setAutocomplete("");
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
			} else if (request.getParameter("next").equals("est")) {
				ret = "redirect:estado";
				rec = "e";
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
			} else if (request.getParameter("next").equals("est")) {
				ret = "redirect:estado";
				rec = "e";
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
			} else if (request.getParameter("next").equals("est")) {
				ret = "redirect:estado";
				rec = "e";
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

	@RequestMapping(value = "est", method = RequestMethod.POST)
	public String est(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");
			ses.setFiltro(null);
			ses.setAutocomplete("");
			ses.setCondicionActual(ses.getCondicionActual() + ",Est");
			if (compra.getNroor().equalsIgnoreCase("total")) {
				ses.getValores().put("Est", "Estados: Todos");
				ses.getCondiciones().put("Est", "");
			} else {
				ses.getValores().put("Est", "Estado: " + compra.getNroor());
				ses.getCondiciones().put("Est",
						"c.pcstp = '" + compra.getPcstp() + "'");
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

	@RequestMapping(value = "/retornar", method = RequestMethod.GET)
	public String retornar(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:mostrar";
			session ses = (session) model.asMap().get("user_inicio");
			String[] cond = ses.getCondicionActual().split(",");
			ses.setFiltro(null);
			ses.setAutocomplete("");
			if (ses.getHistorial().length() > 1) {
				String ncond = "";
				String hist = "";
				for (int i = ses.getCondicionUsuario().split(",").length; i < cond.length - 1; i++) {
					if (ncond.isEmpty()) {
						ncond = cond[i];
					} else {
						ncond = ncond + "," + cond[i];
					}
					hist = hist
							+ ses.getHistorial().charAt(
									i
											- ses.getCondicionUsuario().split(
													",").length);
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
				} else if (hist.charAt(hist.length() - 1) == 'e') {
					ret = "redirect:estado";
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

	@RequestMapping(value = "/filtro", method = RequestMethod.POST)
	public String filtro(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			String ret = "redirect:mostrar";
			ses.setFiltro(compra);
			if (request.getParameter("busca").equals("ite")) {
				ret = "redirect:item";
			} else if (request.getParameter("busca").equals("requ")) {
				ret = "redirect:requisicion";
			} else if (request.getParameter("busca").equals("orde")) {
				ret = "redirect:orden";
			} else if (request.getParameter("busca").equals("prov")) {
				ret = "redirect:proveedor";
			} else {
				ses.setFiltro(null);
				ses.setAutocomplete("");
			}
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
			model.addAttribute("usuarioactuall", ses.getUsuario());
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

	@RequestMapping(value = "/refrescar", method = RequestMethod.GET)
	public String refrescar(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			model.addAttribute("redireccion", "anual");
			model.addAttribute("accion", "tomar");
			model.addAttribute("compra", new Compras());
			session ses = (session) (model.asMap().get("user_inicio"));
			model.addAttribute("usuarioactuall", ses.getUsuario());
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

	@RequestMapping(value = "/anual", method = RequestMethod.GET)
	public String reporte(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			model.addAttribute("redireccion", "mostrar");
			model.addAttribute("accion", "visualizar");
			model.addAttribute("compra", new Compras());
			session ses = (session) (model.asMap().get("user_inicio"));
			model.addAttribute("usuarioactuall", ses.getUsuario());
			StringBuilder mens = new StringBuilder();
			for (int i = 2; i < ses.getCondicionUsuario().split(",").length; i++) {
				if (mens.length() > 0) {
					mens.append(" ");
				}
				mens.append(ses.getValores().get(
						ses.getCondicionUsuario().split(",")[i]));
			}
			model.addAttribute("mensaje", mens.toString().toUpperCase());
			return "reporte";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/historico", method = RequestMethod.GET)
	public String historico(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			model.addAttribute("accion", "histcodprod");
			model.addAttribute("redireccion", "mostrar");
			model.addAttribute("compra", new Compras());
			session ses = (session) (model.asMap().get("user_inicio"));
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute("mensaje1", "CONSULTA POR CODIGO DE PRODUCTO");
			model.addAttribute("mensaje2", "CONSULTA POR NOMBRE DE PRODUCTO");
			model.addAttribute("mensaje3", "CONSULTA POR CODIGO DE PROVEEDOR");
			model.addAttribute("mensaje4", "CONSULTA POR NOMBRE DE PROVEEDOR");
			model.addAttribute("accion1", "cprod");
			model.addAttribute("accion2", "dprod");
			model.addAttribute("accion3", "cprov");
			model.addAttribute("accion4", "dprov");
			model.addAttribute("codproducto", ses.getDatos().get("codProd"));
			model.addAttribute("nombproducto", ses.getDatos().get("desProd"));
			model.addAttribute("codproveedor", ses.getDatos().get("codProv"));
			model.addAttribute("nombproveedor", ses.getDatos().get("desProv"));
			return "historico";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/cprod", method = RequestMethod.POST)
	public String cprod(@ModelAttribute("compra") Compras compra, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) (model.asMap().get("user_inicio"));
			ses.setCondicionesHistorico(new LinkedList<String>());
			String mensaje = "";
			if (!compra.getFecep1().isEmpty()
					&& compra.getFecep1().split("/").length == 2
					&& !compra.getFecep2().isEmpty()
					&& compra.getFecep2().split("/").length == 2) {
				ses.getCondicionesHistorico().add(
						0,
						"c.pipro LIKE '%"
								+ compra.getPipro().toUpperCase()
								+ "%' and c.pano >= "
								+ compra.getFecep1().split("/")[1]
								+ " and c.pmes >= "
								+ Integer.parseInt(compra.getFecep1()
										.split("/")[0])
								+ " and c.pano <= "
								+ compra.getFecep2().split("/")[1]
								+ " and c.pmes <= "
								+ Integer.parseInt(compra.getFecep2()
										.split("/")[0]));
				mensaje = " ENTRE [" + compra.getFecep1() + " - "
						+ compra.getFecep2() + "]";
			} else {
				ses.getCondicionesHistorico().add(
						0,
						"c.pipro LIKE '%" + compra.getPipro().toUpperCase()
								+ "%'");
			}
			ses.getCondicionesHistorico().add(1,
					"c.pipro as pipro, c.pides as pides");
			ses.getCondicionesHistorico().add(2, "c.pipro, c.pides");
			ses.getCondicionesHistorico().add(
					3,
					"CODIGO DE PRODUCTO QUE CONTENGA ["
							+ compra.getPipro().toUpperCase() + "]" + mensaje);
			ses.getCondicionesHistorico().add(4, "Codigo producto");
			ses.getCondicionesHistorico().add(5, "Nombre producto");
			ses.getCondicionesHistorico().add(6, "Historico producto");
			ses.getCondicionesHistorico().add(7, " > Producto");
			model.addAttribute("user_inicio", ses);
			return "redirect:ver";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/dprod", method = RequestMethod.POST)
	public String dprod(@ModelAttribute("compra") Compras compra, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) (model.asMap().get("user_inicio"));
			ses.setCondicionesHistorico(new LinkedList<String>());
			String mensaje = "";
			if (!compra.getFecep1().isEmpty()
					&& compra.getFecep1().split("/").length == 2
					&& !compra.getFecep2().isEmpty()
					&& compra.getFecep2().split("/").length == 2) {
				ses.getCondicionesHistorico().add(
						0,
						"c.pides LIKE '%"
								+ compra.getPides().toUpperCase()
										.replaceAll("'", "\"")
								+ "%' and c.pano >= "
								+ compra.getFecep1().split("/")[1]
								+ " and c.pmes >= "
								+ Integer.parseInt(compra.getFecep1()
										.split("/")[0])
								+ " and c.pano <= "
								+ compra.getFecep2().split("/")[1]
								+ " and c.pmes <= "
								+ Integer.parseInt(compra.getFecep2()
										.split("/")[0]));
				mensaje = " ENTRE [" + compra.getFecep1() + " - "
						+ compra.getFecep2() + "]";
			} else {
				ses.getCondicionesHistorico().add(
						0,
						"c.pides LIKE '%"
								+ compra.getPides().toUpperCase()
										.replaceAll("'", "\"") + "%'");
			}

			ses.getCondicionesHistorico().add(1,
					"c.pipro as pipro, c.pides as pides");
			ses.getCondicionesHistorico().add(2, "c.pipro, c.pides");
			ses.getCondicionesHistorico().add(
					3,
					"NOMBRE DE PRODUCTO QUE CONTENGA ["
							+ compra.getPides().toUpperCase()
									.replaceAll("'", "\"") + "]" + mensaje);
			ses.getCondicionesHistorico().add(4, "Codigo producto");
			ses.getCondicionesHistorico().add(5, "Nombre producto");
			ses.getCondicionesHistorico().add(6, "Historico producto");
			ses.getCondicionesHistorico().add(7, " > Producto");
			model.addAttribute("user_inicio", ses);
			return "redirect:ver";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/cprov", method = RequestMethod.POST)
	public String cprov(@ModelAttribute("compra") Compras compra, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			try {
				Integer.parseInt(String.valueOf(compra.getPpnov()));
				session ses = (session) (model.asMap().get("user_inicio"));
				ses.setCondicionesHistorico(new LinkedList<String>());
				String mensaje = "";
				if (!compra.getFecep1().isEmpty()
						&& compra.getFecep1().split("/").length == 2
						&& !compra.getFecep2().isEmpty()
						&& compra.getFecep2().split("/").length == 2) {
					ses.getCondicionesHistorico().add(
							0,
							"c.pprov ="
									+ compra.getPpnov()
									+ " and c.pano >= "
									+ compra.getFecep1().split("/")[1]
									+ " and c.pmes >= "
									+ Integer.parseInt(compra.getFecep1()
											.split("/")[0])
									+ " and c.pano <= "
									+ compra.getFecep2().split("/")[1]
									+ " and c.pmes <= "
									+ Integer.parseInt(compra.getFecep2()
											.split("/")[0]));
					mensaje = " ENTRE [" + compra.getFecep1() + " - "
							+ compra.getFecep2() + "]";
				} else {
					ses.getCondicionesHistorico().add(0,
							"c.pprov =" + compra.getPpnov());
				}
				ses.getCondicionesHistorico().add(1,
						"c.pprov as pprov, c.ppnov as ppnov");
				ses.getCondicionesHistorico().add(2, "c.pprov, c.ppnov");
				ses.getCondicionesHistorico().add(
						3,
						"CODIGO DE PROVEEDOR QUE CONTENGA ["
								+ compra.getPpnov() + "]" + mensaje);
				ses.getCondicionesHistorico().add(4, "Codigo proveedor");
				ses.getCondicionesHistorico().add(5, "Nombre proveedor");
				ses.getCondicionesHistorico().add(6, "Historico proveedor");
				ses.getCondicionesHistorico().add(7, " > Proveedor");
				model.addAttribute("user_inicio", ses);
				return "redirect:ver";
			} catch (Exception e) {
				return "redirect:historico";
			}
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/dprov", method = RequestMethod.POST)
	public String dprov(@ModelAttribute("compra") Compras compra, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) (model.asMap().get("user_inicio"));
			ses.setCondicionesHistorico(new LinkedList<String>());
			String mensaje = "";
			if (!compra.getFecep1().isEmpty()
					&& compra.getFecep1().split("/").length == 2
					&& !compra.getFecep2().isEmpty()
					&& compra.getFecep2().split("/").length == 2) {
				ses.getCondicionesHistorico().add(
						0,
						"c.ppnov LIKE '%"
								+ compra.getPpnov().toUpperCase()
										.replaceAll("'", "\"") + "%' and c.pano >= "
									+ compra.getFecep1().split("/")[1]
									+ " and c.pmes >= "
									+ Integer.parseInt(compra.getFecep1()
											.split("/")[0])
									+ " and c.pano <= "
									+ compra.getFecep2().split("/")[1]
									+ " and c.pmes <= "
									+ Integer.parseInt(compra.getFecep2()
											.split("/")[0]));
					mensaje = " ENTRE [" + compra.getFecep1() + " - "
							+ compra.getFecep2() + "]";
			} else {
			ses.getCondicionesHistorico().add(
					0,
					"c.ppnov LIKE '%"
							+ compra.getPpnov().toUpperCase()
									.replaceAll("'", "\"") + "%'");
			}
			ses.getCondicionesHistorico().add(1,
					"c.pprov as pprov, c.ppnov as ppnov");
			ses.getCondicionesHistorico().add(2, "c.pprov, c.ppnov");
			ses.getCondicionesHistorico().add(
					3,
					"NOMBRE DE PROVEEDOR QUE CONTENGA ["
							+ compra.getPpnov().toUpperCase()
									.replaceAll("'", "\"") + "]" + mensaje);
			ses.getCondicionesHistorico().add(4, "Codigo proveedor");
			ses.getCondicionesHistorico().add(5, "Nombre proveedor");
			ses.getCondicionesHistorico().add(6, "Historico proveedor");
			ses.getCondicionesHistorico().add(7, " > Proveedor");
			model.addAttribute("user_inicio", ses);
			return "redirect:ver";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/ver")
	public String ver(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			boolean ind = false;
			if (ses.getCondicionesHistorico().get(7).contains("Producto")) {
				ind = true;
			}
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute("listcomp", comprasService.getHistorico(
					ses.getCondicionesHistorico(), ind));
			model.addAttribute("navegacion", "> Historico"
					+ ses.getCondicionesHistorico().get(7));
			model.addAttribute("mensaje", ses.getCondicionesHistorico().get(3));
			model.addAttribute("regresar", "historico");
			model.addAttribute("titulo", ses.getCondicionesHistorico().get(6));
			if (ind) {
				model.addAttribute("mostrar", 1);
				model.addAttribute("fila", "Producto");
				model.addAttribute("fila1", "Proveedor");
			} else {
				model.addAttribute("mostrar", 0);
				model.addAttribute("fila", "Proveedor");
				model.addAttribute("fila1", "Producto");
			}
			model.addAttribute("compra", new Compras());
			return "reports/historial";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "retroceder")
	public String retroceder(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			ses.getCondicionesHistorico().remove(10);
			ses.getCondicionesHistorico().remove(9);
			ses.getCondicionesHistorico().remove(8);
			model.addAttribute("compra", new Compras());
			return "redirect:ver";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "observar")
	public String observar(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			ses.getCondicionesHistorico().remove(10);
			ses.getCondicionesHistorico().remove(9);
			ses.getCondicionesHistorico().remove(8);
			return "redirect:ver";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "ordenado")
	public String ordenado(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute("listcomp", comprasService
					.getOrdenesHistoricos(ses.getCondicionesHistorico().get(8)));
			model.addAttribute("navegacion", "> Historico"
					+ ses.getCondicionesHistorico().get(7)
					+ ses.getCondicionesHistorico().get(9));
			model.addAttribute("mensaje", ses.getCondicionesHistorico().get(10));
			model.addAttribute("compra", new Compras());
			return "reports/ordenHistorial";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "compradores")
	public String compradores(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute("listcomp", comprasService
					.getCompradoresHistoricos(ses.getCondicionesHistorico()
							.get(8)));
			model.addAttribute("navegacion", "> Historico"
					+ ses.getCondicionesHistorico().get(7)
					+ ses.getCondicionesHistorico().get(9));
			model.addAttribute("mensaje", ses.getCondicionesHistorico().get(10));
			model.addAttribute("compra", new Compras());
			return "reports/compradorHistorial";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "centroscostos")
	public String centroscostos(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute("listcomp", comprasService
					.getCentrosHistoricos(ses.getCondicionesHistorico().get(8)));
			model.addAttribute("navegacion", "> Historico"
					+ ses.getCondicionesHistorico().get(7)
					+ ses.getCondicionesHistorico().get(9));
			model.addAttribute("mensaje", ses.getCondicionesHistorico().get(10));
			model.addAttribute("compra", new Compras());
			return "reports/centroHistorial";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "estados")
	public String estados(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) model.asMap().get("user_inicio");
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute("listcomp", comprasService
					.getEstadosHistoricos(ses.getCondicionesHistorico().get(8)));
			model.addAttribute("navegacion", "> Historico"
					+ ses.getCondicionesHistorico().get(7)
					+ ses.getCondicionesHistorico().get(9));
			model.addAttribute("mensaje", ses.getCondicionesHistorico().get(10));
			model.addAttribute("compra", new Compras());
			return "reports/estadoHistorial";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "ordenh", method = RequestMethod.POST)
	public String ordenh(@ModelAttribute("compra") Compras compra,
			HttpServletRequest request, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			String ret = "redirect:ver";
			session ses = (session) model.asMap().get("user_inicio");
			String enc = "";
			String condicion = "";
			String mensaje = "";
			if(ses.getCondicionesHistorico().get(0).contains("c.pano")) {
				condicion = "c.pano" +ses.getCondicionesHistorico().get(0).split("c.pano")[1] + "c.pano" +  ses.getCondicionesHistorico().get(0).split("c.pano")[2] + " and ";
				mensaje = " ENTRE " + ses.getCondicionesHistorico().get(3).split("ENTRE")[1];
			}
			if (!ses.getCondicionesHistorico().contains(" > Proveedor")) {
				ses.getCondicionesHistorico().add(
						8,
						condicion + "c.pipro = '" + compra.getPipro() + "' and c.pprov ="
								+ compra.getPprov());
				enc = "Código producto: " + compra.getPipro()
						+ " Descripción del producto: " + compra.getPides()
						+ " Código Proveedor: " + compra.getPprov()
						+ " Nombre del producto: " + compra.getPpnov() + mensaje;
			} else {
				ses.getCondicionesHistorico().add(8, condicion +
						"c.pprov = '" + compra.getPipro() + "'");
				enc = "Código proveedor: " + compra.getPipro()
						+ " Nombre del proveedor: " + compra.getPides() + mensaje;
			}
			if (request.getParameter("next").equals("ordenado")) {
				ses.getCondicionesHistorico().add(9, " > Ordenes");
				ret = "redirect:ordenado";
			} else if (request.getParameter("next").equals("compradores")) {
				ses.getCondicionesHistorico().add(9, " > Compradores");
				ret = "redirect:compradores";
			} else if (request.getParameter("next").equals("centrocosto")) {
				ses.getCondicionesHistorico().add(9, " > Centros costos");
				ret = "redirect:centroscostos";
			} else if (request.getParameter("next").equals("estados")) {
				ses.getCondicionesHistorico().add(9, " > Estados");
				ret = "redirect:estados";
			}
			model.addAttribute("user_inicio", ses);
			ses.getCondicionesHistorico().add(10, enc);
			return ret;
		} else {
			return "redirect:/index/ingreso";
		}
	}

	// reports/

	@RequestMapping(value = "/tomar", method = RequestMethod.POST)
	public String tomar(@ModelAttribute("compra") Compras compra, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) (model.asMap().get("user_inicio"));
			if (ses.getCenters().isEmpty()) {
				ses.setFechaSelec("a" + compra.getPano() + ",mm"
						+ compra.getPmes());
				ses.setCondicionUsuario("a" + compra.getPano() + ",mm"
						+ compra.getPmes() + ",c" + compra.getPcia() + ",l"
						+ compra.getPpais() + ",m" + compra.getPmond()
						+ ",ordeCond");
			} else {
				ses.setFechaSelec("a" + compra.getPano() + ",mm"
						+ compra.getPmes());
				ses.setCondicionUsuario("a" + compra.getPano() + ",mm"
						+ compra.getPmes() + ",c" + compra.getPcia() + ",l"
						+ compra.getPpais() + ",m" + compra.getPmond() + ",k"
						+ compra.getPcent() + ",ordeCond");
			}
			ses.setCampover(compra.getPunin());
			model.addAttribute("user_inicio", ses);
			return "redirect:anual";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/generar", method = RequestMethod.POST)
	public String generaar(@ModelAttribute("compra") Compras compra, Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) (model.asMap().get("user_inicio"));
			if (ses.getCenters().isEmpty()) {
				ses.setFechaSelec("a" + compra.getPano() + ",mm"
						+ compra.getPmes());
				ses.setCondicionUsuario("a" + compra.getPano() + ",mm"
						+ compra.getPmes() + ",c" + compra.getPcia() + ",l"
						+ compra.getPpais() + ",m" + compra.getPmond()
						+ ",ordeCond");
			} else {
				ses.setFechaSelec("a" + compra.getPano() + ",mm"
						+ compra.getPmes());
				ses.setCondicionUsuario("a" + compra.getPano() + ",mm"
						+ compra.getPmes() + ",c" + compra.getPcia() + ",l"
						+ compra.getPpais() + ",m" + compra.getPmond() + ",k"
						+ compra.getPcent() + ",ordeCond");
			}
			ses.setCampover(compra.getPunin());
			model.addAttribute("user_inicio", ses);
			return "redirect:mostrar";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/visualizar", method = RequestMethod.POST)
	public String visualizar(@ModelAttribute("compra") Compras compra,
			Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) (model.asMap().get("user_inicio"));
			ses.setCondicionReporte(compra.getPano() + "anor,"
					+ compra.getTipoc() + "," + compra.getPnpas());
			return "redirect:reporteanual";
		} else {
			return "redirect:/index/ingreso";
		}
	}

	@RequestMapping(value = "/reporteanual", method = RequestMethod.GET)
	public String reporteanual(Model model) {
		if (model.containsAttribute("user_inicio") == true) {
			session ses = (session) (model.asMap().get("user_inicio"));
			model.addAttribute("usuarioactuall", ses.getUsuario());
			model.addAttribute(
					"listcomp",
					comprasService.getReporte(ses.getCondiciones(),
							ses.getCondicionUsuario(),
							ses.getCondicionReporte()));
			List<String> mes = new LinkedList<String>();
			mes.add("Código");
			mes.add(ses.getValores().get(
					ses.getCondicionReporte().split(",")[1]));
			mes.add("Enero");
			mes.add("Febrero");
			mes.add("Marzo");
			mes.add("Abril");
			mes.add("Mayo");
			mes.add("Junio");
			mes.add("Julio");
			mes.add("Agosto");
			mes.add("Septiembre");
			mes.add("Octubre");
			mes.add("Noviembre");
			mes.add("Diciembre");
			mes.add("Total");
			model.addAttribute("listmeses", mes);
			if (ses.getValores().get(ses.getCondicionReporte().split(",")[2])
					.contains("Valor")) {
				model.addAttribute("mostpeso", 1);
			} else {
				model.addAttribute("mostpeso", 0);
			}
			StringBuilder mens = new StringBuilder();
			for (int i = 2; i < ses.getCondicionUsuario().split(",").length; i++) {
				if (mens.length() > 0) {
					mens.append(" ");
				}
				mens.append(ses.getValores().get(
						ses.getCondicionUsuario().split(",")[i]));
			}
			if (mens.length() > 0) {
				mens.append(" ");
			}
			mens.append(ses.getValores().get(
					ses.getCondicionReporte().split(",")[0]));
			mens.append(" "
					+ ses.getValores().get(
							ses.getCondicionReporte().split(",")[2]));
			model.addAttribute("mensaje", mens.toString().toUpperCase());
			return "reports/matricial";
		} else {
			return "redirect:/index/ingreso";
		}
	}

}
