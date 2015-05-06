package com.ventura.compras.web.login;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import com.ventura.compras.service.adm.CenterService;
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

	@Autowired
	private CenterService centerService;

	/*
	 * @Autowired private PermisoManager permisoManager;
	 */
	@RequestMapping(value = "/ingreso", method = RequestMethod.GET)
	public String employee(Map<String, Object> model) {
		model.put("user", new User());
		return "key/index";
	}

	@RequestMapping(value = "/validar", method = RequestMethod.POST)
	public String addEmployee(@Valid @ModelAttribute("user") User user,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("user", new User());
			return "key/index";
		} else {
			User uss = userManager.val(user.getId(), user.getPass());
			if (uss != null) {
				if (uss.getType() != null) {
					session ses = new session(uss.getId(), uss.getType()
							.getId());
					String ret = null;
					List<Object> info = null;
					if (uss.getType().getDescripcion()
							.equalsIgnoreCase("administrador")) {
						info = new LinkedList<Object>();
						info.add(0, typeUserService.listTypeUser());
						info.add(1, companyService.listCompany());
						info.add(2, centerService.listCenter());
						ret = "redirect:/admin/panel";
					} else {
						Calendar fecha = new GregorianCalendar();
						int anoAct = fecha.get(Calendar.YEAR);
						int mesAct = (fecha.get(Calendar.MONTH) + 1) - 4;
						for (int i = 2015; i <= anoAct; i++) {
							ses.getAnosreporte().put(i + "", i + "");
							ses.getCondiciones().put(i + "anor",
									"c.pano = " + i);
							ses.getValores().put(i + "anor", "Año: " + i);
						}
						String[] v = { "Proveedor", "Producto", "Centro",
								"Bodega", "Clase" };
						String[] vv = { "pprov,ppnov", "pipro,pides",
								"pcent,pcenn", "plocal,plnon", "picla,picln" };
						String[] vvv = { "Valor Ordenado", "Valor Recibido" };
						String[] vvvv = { "pvalbo", "pvalbd" };
						for (int i = 0; i < v.length; i++) {
							ses.getCamposreporte().put("creporte" + i, v[i]);
							ses.getCondiciones().put("creporte" + i, vv[i]);
							ses.getValores().put("creporte" + i, v[i]);
						}
						for (int i = 0; i < vvv.length; i++) {
							ses.getCamposver().put("vereporte" + i, vvv[i]);
							ses.getCondiciones().put("vereporte" + i, vvvv[i]);
							ses.getValores().put("vereporte" + i,
									"Reporte: " + vvv[i]);
						}
						if (mesAct < 1) {
							mesAct = 12 + mesAct;
							anoAct--;
						}
						ses.getCondiciones().put("a" + anoAct,
								"c.pano = " + anoAct + "");
						ses.getValores().put("a" + anoAct, "Año: " + anoAct);
						ses.getAnos().put(anoAct + "", anoAct + "");
						for (int i = 0; i <= 4; i++) {
							ses.getCondiciones().put("mm" + mesAct,
									"c.pmes = " + mesAct);
							switch (mesAct) {
							case 1:
								ses.getValores().put("mm" + mesAct,
										"Mes: Enero");
								ses.getMeses().put(mesAct + "", "ENERO");
								break;
							case 2:
								ses.getValores().put("mm" + mesAct,
										"Mes: Febrero");
								ses.getMeses().put(mesAct + "", "FEBRERO");
								break;
							case 3:
								ses.getValores().put("mm" + mesAct,
										"Mes: Marzo");
								ses.getMeses().put(mesAct + "", "MARZO");
								break;
							case 4:
								ses.getValores().put("mm" + mesAct,
										"Mes: Abril");
								ses.getMeses().put(mesAct + "", "ABRIL");
								break;
							case 5:
								ses.getValores()
										.put("mm" + mesAct, "Mes: Mayo");
								ses.getMeses().put(mesAct + "", "MAYO");
								break;
							case 6:
								ses.getValores().put("mm" + mesAct,
										"Mes: Junio");
								ses.getMeses().put(mesAct + "", "JUNIO");
								break;
							case 7:
								ses.getValores().put("mm" + mesAct,
										"Mes: Julio");
								ses.getMeses().put(mesAct + "", "JULIO");
								break;
							case 8:
								ses.getValores().put("mm" + mesAct,
										"Mes: Agosto");
								ses.getMeses().put(mesAct + "", "AGOSTO");
								break;
							case 9:
								ses.getValores().put("mm" + mesAct,
										"Mes: Septiembre");
								ses.getMeses().put(mesAct + "", "SEPTIEMBRE");
								break;
							case 10:
								ses.getValores().put("mm" + mesAct,
										"Mes: Octubre");
								ses.getMeses().put(mesAct + "", "OCTUBRE");
								break;
							case 11:
								ses.getValores().put("mm" + mesAct,
										"Mes: Noviempre");
								ses.getMeses().put(mesAct + "", "NOVIEMBRE");
								break;
							case 12:
								ses.getValores().put("mm" + mesAct,
										"Mes: Diciembre");
								ses.getMeses().put(mesAct + "", "DICIEMBRE");
								break;
							}
							mesAct++;
							if (mesAct == 13) {
								mesAct = 1;
								anoAct++;
								ses.getCondiciones().put("a" + anoAct,
										"c.pano = " + anoAct);
								ses.getAnos().put(anoAct + "", anoAct + "");
								ses.getValores().put("a" + anoAct,
										"Año: " + anoAct);
							}
						}
						mesAct = mesAct - 1;
						ses.setCondicionUsuario("a" + anoAct + ",mm" + mesAct);
						ses.setFechaActual("a" + anoAct + ",mm" + mesAct);
						ses.setFechaSelec("a" + anoAct + ",mm" + mesAct);
						if (!uss.getCent().getDescripcion()
								.equalsIgnoreCase("n/a")) {
							if (uss.getType().getDescripcion()
									.equalsIgnoreCase("comprador")) {
								ses.getCenters().put("1",
										uss.getCent().getDescripcion());
								ses.getCondiciones()
										.put("k1",
												"c.pcomd = '"
														+ uss.getCent()
																.getDescripcion()
														+ "'");
								ses.getValores().put(
										"k1",
										"COMPRADOR: "
												+ uss.getCent()
														.getDescripcion());
							} else {
								ses.getCenters().put("1",
										uss.getCent().getDescripcion());
								ses.getCondiciones().put(
										"k1",
										"c.pcent = +" + uss.getCent().getId()
												+ "'");
								ses.getValores().put(
										"k1",
										"Centro: "
												+ uss.getCent()
														.getDescripcion());
							}
							ses.setCondicionUsuario(ses
									.getCondicionUsuario() + ",k1");
						}
						if (uss.getComp().getDescripcion()
								.equalsIgnoreCase("panasa")
								|| uss.getComp().getDescripcion()
										.equalsIgnoreCase("todos")) {
							ses.getCondiciones().put("c1", "c.pcia = 1");
							ses.getValores().put("c1", "Compañia: Panasa");
							ses.getCpias().put("1", "Panasa");
						}
						if (uss.getComp().getDescripcion()
								.equalsIgnoreCase("iaasa")
								|| uss.getComp().getDescripcion()
										.equalsIgnoreCase("todos")) {
							ses.getCondiciones().put("c2", "c.pcia = 2");
							ses.getValores().put("c2", "Compañia: IASSA");
							ses.getCpias().put("2", "IAASA");
							if (!ses.getCondicionUsuario().contains("c1")) {
								ses.setCondicionUsuario(ses
										.getCondicionUsuario() + ",c1");
							}
						}
						if (uss.getComp().getDescripcion()
								.equalsIgnoreCase("iaasa")
								|| uss.getComp().getDescripcion()
										.equalsIgnoreCase("todos")) {
							ses.getCondiciones().put("c5", "c.pcia = 5");
							ses.getValores().put("c5", "Compañia: Biopacol");
							ses.getCpias().put("5", "Biopacol");
							if (!ses.getCondicionUsuario().contains("c1")
									&& !ses.getCondicionUsuario()
											.contains("c2")) {
								ses.setCondicionUsuario(ses
										.getCondicionUsuario() + ",c5");
							}
						}
						if (uss.getLevel().getDescripcion()
								.equalsIgnoreCase("nacional")
								|| uss.getLevel().getDescripcion()
										.equalsIgnoreCase("todos")) {
							ses.getCondiciones().put("l1", "c.ppais = 'COL'");
							ses.getValores().put("l1", "Tipo: Nacional");
							ses.getLevels().put("1", "Nacional");
							ses.getCondiciones().put("l3", "");
							ses.getValores().put("l3", "Tipo: Todos");
							ses.getLevels().put("3", "TODOS");
							ses.setCondicionUsuario(ses.getCondicionUsuario()
									+ ",l1");
						}
						if (uss.getLevel().getDescripcion()
								.equalsIgnoreCase("importada")
								|| uss.getLevel().getDescripcion()
										.equalsIgnoreCase("todos")) {
							ses.getCondiciones().put("l2", "c.ppais <> 'COL'");
							ses.getValores().put("l2", "Tipo: Importada");
							ses.getLevels().put("2", "Importada");
							if (!ses.getCondicionUsuario().contains("l1")) {
								ses.setCondicionUsuario(ses
										.getCondicionUsuario() + ",l2");
							}
							if (!ses.getValores().containsKey("l3")) {
								ses.getCondiciones().put("l3", "");
								ses.getValores().put("l3", "Tipo: Todos");
								ses.getLevels().put("3", "TODOS");
							}
						}
						if (uss.getCurr().getDescripcion()
								.equalsIgnoreCase("cop")
								|| uss.getCurr().getDescripcion()
										.equalsIgnoreCase("todos")) {
							ses.getCondiciones().put("m1", "c.pmond = 'COP'");
							ses.getCondiciones().put("m3", "");
							ses.getValores().put("m1", "Moneda: COP");
							ses.getValores().put("m3", "Moneda: TODAS");
							ses.getCurrencys().put("1", "COP");
							ses.getCurrencys().put("3", "TODAS");
							ses.setCondicionUsuario(ses.getCondicionUsuario()
									+ ",m3");
						}
						if (uss.getCurr().getDescripcion()
								.equalsIgnoreCase("usd")
								|| uss.getCurr().getDescripcion()
										.equalsIgnoreCase("todos")) {
							ses.getCondiciones().put("m2", "c.pmond = 'USD'");
							ses.getValores().put("m2", "Moneda: USD");
							ses.getCurrencys().put("2", "USD");
							if (!ses.getCondiciones().containsKey("m3")) {
								ses.getCondiciones().put("m3", "");
								ses.getValores().put("m3", "Moneda: TODAS");
								ses.getCurrencys().put("3", "TODAS");
							}
							if (!ses.getCondicionUsuario().contains("m3")) {
								ses.setCondicionUsuario(ses
										.getCondicionUsuario() + ",m3");
							}
						}
						ret = "redirect:/compras/mostrar";
					}
					ses.setInformacion(info);
					model.addAttribute("user_inicio", ses);
					return ret;
				} else {
					return "key/index";
				}
			} else {
				model.addAttribute("user", new User());
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
