package com.ventura.compras.repository.login.imp;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ventura.compras.domain.login.User;
import com.ventura.compras.repository.login.PermisoDao;

@Repository
public class JPAPermisoDao implements PermisoDao {
	
	private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
	@Transactional(readOnly = true)  
    public List<Map<String,String>> getPermisos(User usuario) {    	
    	List<Map<String, String>> permisos = new LinkedList<Map<String,String>>();
    	@SuppressWarnings("unchecked")
		List<Object> resultados = em.createQuery("SELECT P.permiso as permiso FROM Permiso as P Where P.usuario = '" + usuario.getId() + "'").getResultList();
    	Map<String, String> n = new HashMap<String, String>();
    	Map<String, String> comp = new HashMap<String, String>();
    	Map<String, String> mon = new HashMap<String, String>();
    	Map<String, String> nivel = new HashMap<String, String>();
    	Map<String,String> ano = new HashMap<String, String>();
		Map<String,String> mes = new HashMap<String, String>();
		Map<String,String> rano = new HashMap<String, String>();
		Map<String,String> rmes = new HashMap<String, String>();
		Map<String,String> ver = new HashMap<String, String>();
		if(usuario.getLevel().getDescripcion().equalsIgnoreCase("nacional") || usuario.getLevel().getDescripcion().equalsIgnoreCase("todos")) {    		
    		mon.put("nal", "f.cocurr = 'COP'");
    		nivel.put("nal", "NACIONAL");
    		if(!ver.containsKey("nivel")) {
    			ver.put("nivel", "nal");
    		}
    	}
    	if(usuario.getLevel().getDescripcion().equalsIgnoreCase("exportaciones") || usuario.getLevel().getDescripcion().equalsIgnoreCase("todos")) {
    		mon.put("exp", "f.cocurr = 'USD'");
    		nivel.put("exp", "EXPORTACION");
    		if(!ver.containsKey("nivel")) {
    			ver.put("nivel", "exp");
    		}
    	}
    	
    	if(usuario.getComp().getDescripcion().equalsIgnoreCase("panasa") || usuario.getComp().getDescripcion().equalsIgnoreCase("todos")) {
    		n.put("1", "f.cocia = 1");
    		comp.put("1", "PANASA");
    		if(!ver.containsKey("compa")) {
    			ver.put("compa", "1");
    		}
    	}
    	if(usuario.getComp().getDescripcion().equalsIgnoreCase("iaasa") || usuario.getComp().getDescripcion().equalsIgnoreCase("todos")) {
    		n.put("2", "f.cocia = 2");
    		comp.put("2", "IAASA");
    		if(!ver.containsKey("compa")) {
    			ver.put("compa", "2");
    		}
    	}    	
    	Calendar fecha = new GregorianCalendar();
		int anoAct = fecha.get(Calendar.YEAR);
		int mesAct = (fecha.get(Calendar.MONTH)+1)-3;
		ver.put("mesact", (fecha.get(Calendar.MONTH)+1)+"");
		ver.put("messel", (fecha.get(Calendar.MONTH)+1)+"");
		ver.put("anoact", fecha.get(Calendar.YEAR)+"");
		ver.put("anosel", fecha.get(Calendar.YEAR)+"");
		ano.put(anoAct+"", anoAct+"");
		rano.put(anoAct+"", "f.ano = "+anoAct);
		if(mesAct <= 0) {
			mesAct = 12+mesAct;
			anoAct = anoAct-1;
			ano.put(anoAct+"", anoAct+"");
			rano.put(anoAct+"", "f.ano = "+anoAct);
		}
		for(int i = 0; i <= 3; i++) {
			rmes.put(mesAct+"", "f.mes = " + mesAct);
			switch (mesAct) {
				case 1:
					mes.put(mesAct+"", "ENERO");
					break;
				case 2:
					mes.put(mesAct+"", "FEBRERO");
					break;
				case 3:
					mes.put(mesAct+"", "MARZO");
					break;
				case 4:
					mes.put(mesAct+"", "ABRIL");
					break;
				case 5:
					mes.put(mesAct+"", "MAYO");
					break;
				case 6:
					mes.put(mesAct+"", "JUNIO");
					break;
				case 7:
					mes.put(mesAct+"", "JULIO");
					break;
				case 8:
					mes.put(mesAct+"", "AGOSTO");
					break;
				case 9:
					mes.put(mesAct+"", "SEPTIEMBRE");
					break;
				case 10:
					mes.put(mesAct+"", "OCTUBRE");
					break;
				case 11:
					mes.put(mesAct+"", "NOVIEMBRE");
					break;
				case 12:
					mes.put(mesAct+"", "DICIEMBRE");
					break;
			}			
			mesAct++;
			if(mesAct == 13) {
				mesAct = 1;
				anoAct++;
			}
		}		
    	permisos.add(0, comp);
    	permisos.add(1, nivel);
    	permisos.add(2, ano);
    	permisos.add(3, mes);
    	permisos.add(4, mon);
    	permisos.add(5, n);
    	permisos.add(6, rano);
    	permisos.add(7, rmes);
    	permisos.add(8, ver);
    	if(usuario.getTip_usuario().getDescripcion().equalsIgnoreCase("gerente agencia")) {
    		permisos.add(9, generarPermisos(usuario, "cozon", resultados));
    		permisos.get(8).put("per", "permisos");
    	}    	
    	return permisos;
	}    
    
	@SuppressWarnings("unused")
	private Map<String, String> generarPermisos(User usuario, String campo, List<Object> resultados) {		
    	Map<String, String> n = new HashMap<String, String>();
    	String per = "";
    	for(Object rest : resultados) {
    		if(per.isEmpty()) {
    			per = "f." + campo + " = '" + rest+"'";
    		} else {
    			per = per + " or f." + campo + " = '" + rest+"'";
    		}
    	}
    	per = "(" + per + ")";
    	n.put("permisos", per);
    	return n;
    }
	
/*	
	@SuppressWarnings("unused")
	private Map<String, String> generarPermisos(User usuario, String campo, List<Object> resultados) {		
    	Map<String, String> n = new HashMap<String, String>();
    	if(usuario.getLevel().getDescripcion().equalsIgnoreCase("nacional") || usuario.getLevel().getDescripcion().equalsIgnoreCase("todos")) {
    		for(Object rest : resultados) {
    			if(String.valueOf(rest).equalsIgnoreCase("col")) {
    				n.put("nal", "f."+campo+" = 'COP'");
    			}    		
    		}
    	} 
    	if(usuario.getLevel().getDescripcion().equalsIgnoreCase("exportaciones") || usuario.getLevel().getDescripcion().equalsIgnoreCase("todos")) {
    		for(Object rest : resultados) {
    			if(String.valueOf(rest).equalsIgnoreCase("col")) {
    				n.put("exp", "f."+campo+" = 'USD'");    				
    			}
    		}    		
    	}
    	return n;
    }
*/
}
