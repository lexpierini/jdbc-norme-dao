package application;

import model.dao.DaoFabrique;
import model.dao.VendeurDao;
import model.entities.Vendeur;

public class Program {

	public static void main(String[] args) {
		
		VendeurDao vendeurDao = DaoFabrique.createVendeurDao();
		
		Vendeur vendeur = vendeurDao.findById(3);
		
		System.out.println(vendeur);

	}

}
