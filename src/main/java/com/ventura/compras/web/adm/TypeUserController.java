package com.ventura.compras.web.adm;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ventura.compras.domain.adm.TypeUser;
import com.ventura.compras.service.adm.TypeUserService;

@Controller
@RequestMapping(value="/tipousuario")
public class TypeUserController {
	
	@Autowired
	private TypeUserService typeuserService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String listTypeUsers(Map<String, Object> map) {
		map.put("typeuser", new TypeUser());
		map.put("typeuserList", typeuserService.listTypeUser());
		return "/tipousuario/listatipousuario";
	}
	
	@RequestMapping("/get/{Id}")
	public String getTypeUser(@PathVariable int Id, Map<String, Object> map) {
		TypeUser typeUserResult = typeuserService.getTypeUser(Id);
		map.put("typeuser", typeUserResult);
		return "/tipousuario/tuForm";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute("typeuser") TypeUser tu, BindingResult result) {
		typeuserService.saveTypeUser(tu);;
		return "redirect:/tipousuario";
	}

	@RequestMapping("/delete/{Id}")
	public String deleteBook(@PathVariable("Id") int id) {
		typeuserService.deleteTypeUser(id);
		return "redirect:/tipousuario";
	}

}	
