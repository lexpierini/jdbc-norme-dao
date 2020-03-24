package application;

import java.util.List;

import model.dao.DaoFabrique;
import model.dao.VendeurDao;
import model.entities.Departement;
import model.entities.Vendeur;

public class Program {

	public static void main(String[] args) {
		
		VendeurDao vendeurDao = DaoFabrique.createVendeurDao();
		
		System.out.println("=== Teste 1: Vendeur findById ===");
		Vendeur vendeur = vendeurDao.findById(3);
		System.out.println(vendeur);
		
		System.out.println("\n=== Teste 2: Vendeur findByDepartment ===");
		Departement departement = new Departement(2, null);
		List<Vendeur> list = vendeurDao.findByDepartement(departement);
		for (Vendeur obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\n=== Teste 3: Vendeur findByAll ===");
		list = vendeurDao.findAll();
		for (Vendeur obj : list) {
			System.out.println(obj);
		}
	}

}
