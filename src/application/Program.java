package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFabrique;
import model.dao.DepartementDao;
import model.dao.VendeurDao;
import model.entities.Departement;
import model.entities.Vendeur;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		VendeurDao vendeurDao = DaoFabrique.createVendeurDao();
		DepartementDao departementDao = DaoFabrique.createDepartementDao();
		
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
		
		System.out.println("\n=== Teste 4: Vendeur insert ===");
		Vendeur nouveauVendeur = new Vendeur(null, "Greg", "greg@gmail.com", new Date(), 4000.0, departement);
		vendeurDao.insert(nouveauVendeur);
		System.out.println("Inserted! New id = " + nouveauVendeur.getId());
		
		System.out.println("\n=== Teste 5: Vendeur update ===");
		vendeur = vendeurDao.findById(1);
		vendeur.setNom("Martha Wayne");
		vendeurDao.update(vendeur);
		System.out.println("Update completed");
		
		System.out.println("\n=== Teste 6: Vendeur delete ===");
		System.out.print("Entrez avec l'id pour le test de suppression: ");
		int id = sc.nextInt();
		vendeurDao.deleteById(id);
		System.out.println("Suppression terminée");
		
		System.out.println("\n=== Teste 7: Departement insert ===");
		Departement nouveauDepartement = new Departement(null, "teste7");
		departementDao.insert(nouveauDepartement);
		System.out.println("Inserted! New id = " + nouveauDepartement.getId());
		
		System.out.println("\n=== Teste 8: Departement update ===");
		departement = departementDao.findById(5);
		departement.setNom("teste8");
		departementDao.update(departement);
		System.out.println("Update completed");
		
		System.out.println("\n=== Teste 9: Departement deleteById ===");
		System.out.print("Entrez avec l'id pour le test de suppression: ");
		id = sc.nextInt();
		departementDao.deleteById(id);
		System.out.println("Suppression terminée");
		
		System.out.println("\n=== Teste 10: Departement findById ===");
		departement = departementDao.findById(3);
		System.out.println(departement);
		
		System.out.println("\n=== Teste 11: Departement findAll ===");
		List<Departement> listDep = departementDao.findAll();
		for (Departement obj : listDep) {
			System.out.println(obj);
		}
		
		sc.close();
	}
}
