package com.ventura.compras.repository.login;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ventura.compras.domain.adm.TypeUser;
import com.ventura.compras.domain.login.User;

@Repository
public class JPAUserDao implements UserDao {

	private EntityManager em = null;

	/*
	 * Sets the entity manager.
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<User> getUserList() {
		return em.createQuery("select p from User p").getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public User val(String k, String p) {
		List<User> results = em.createQuery(
				"select u from User u where u.id = '" + k + "' and u.pass = '"
						+ p + "'").getResultList();
		if (results.isEmpty())
			return null;
		else
			return results.get(0);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addUser(User user) {
		if (em.find(User.class, user.getId()) == null) {
			em.persist(user);
			return true;
		} else {
			return false;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(String id) {
		User user = em.find(User.class, id);
		if (user != null) {
			em.remove(user);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User getUser(User user) {
		return (User) (em.find(User.class, user.getId()));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean editUser(User user) {
		try {
			em.merge(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String, String> obtenerDatos() {
		Map<String, String> datos = new HashMap();
		StringBuilder cProd = new StringBuilder();
		StringBuilder dProd = new StringBuilder();
		StringBuilder cProv = new StringBuilder();
		StringBuilder dProv = new StringBuilder();
		List<Object[]> result1 = em.createQuery(
				"SELECT c.pipro as pipro, c.pides as pides"
						+ " FROM Compras_h as c"
						+ " GROUP BY c.pipro, c.pides " + "ORDER BY pipro asc")
				.getResultList();
		List<Object[]> result2 = em.createQuery(
				"SELECT c.pprov as pprov, c.ppnov as ppnov"
						+ " FROM Compras_h as c"
						+ " GROUP BY c.pprov, c.ppnov " + "ORDER BY pprov asc")
				.getResultList();
		int cant;
		if (result1.size() > result2.size()) {
			cant = result1.size();
		} else {
			cant = result2.size();
		}
		cProd.append("[");
		dProd.append("[");
		cProv.append("[");
		dProv.append("[");
		for (int i = 0; i < cant; i++) {
			if (i < result1.size()) {
				if(cProd.length() > 1) {
					cProd.append(", ");
					dProd.append(", ");
				}
				cProd.append("\""+result1.get(i)[0]+"\"");
				if(result1.get(i)[1].toString().contains("\"")) {
					dProd.append("\""+result1.get(i)[1].toString().replaceAll("\"", "'")+"\"");
				} else {
					dProd.append("\""+result1.get(i)[1]+"\"");
				}
			}
			if (i < result2.size()) {
				if(cProv.length() > 1) {
					cProv.append(", ");
					dProv.append(", ");
				}
				cProv.append("\""+result2.get(i)[0]+"\"");
				dProv.append("\""+result2.get(i)[1]+"\"");
			}
		}
		cProd.append("]");
		dProd.append("]");
		cProv.append("]");
		dProv.append("]");
		datos.put("codProd", cProd.toString());
		datos.put("desProd", dProd.toString());
		datos.put("codProv", cProv.toString());
		datos.put("desProv", dProv.toString());
		return datos;
	}

}
