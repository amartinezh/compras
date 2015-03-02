package com.ventura.compras.repository.adm;

import java.util.List;

import com.ventura.compras.domain.adm.TypeUser;

public interface TypeUserDao {
	
	public List<TypeUser> listTypeUser();
	public void saveTypeUser(TypeUser typeuser);
	public void deleteTypeUser(int id);
	public TypeUser getTypeUser(int id);
	
}
