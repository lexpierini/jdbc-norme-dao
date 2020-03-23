package model.dao;

import db.DB;
import model.dao.implementation.VendeurDaoJDBC;

public class DaoFabrique {

	public static VendeurDao createVendeurDao() {
		return new VendeurDaoJDBC(DB.getConnection());
	}
}
