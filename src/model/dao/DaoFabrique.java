package model.dao;

import model.dao.implementation.VendeurDaoJDBC;

public class DaoFabrique {

	public static VendeurDao createVendeurDao() {
		return new VendeurDaoJDBC();
	}
}
