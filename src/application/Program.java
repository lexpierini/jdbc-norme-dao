package application;

import java.util.Date;

import model.dao.DaoFabrique;
import model.dao.VendeurDao;
import model.entities.Departement;
import model.entities.Vendeur;

public class Program {

	public static void main(String[] args) {
		
		Departement obj = new Departement(1, "Books");
		Vendeur vendeur = new Vendeur(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
		
		VendeurDao vendeurDao = DaoFabrique.createVendeurDao();
		
		System.out.println(vendeur);

	}

}
