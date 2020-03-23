package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Vendeur implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String email;
	private Date dateNaissance;
	private Double salaireBase;
	private Departement departement;
	
	public Vendeur() {
	}
	
	public Vendeur(Integer id, String name, String email, Date dateNaissance, Double salaireBase, Departement departement) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.salaireBase = salaireBase;
		this.departement = departement;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Double getSalaireBase() {
		return salaireBase;
	}

	public void setSalaireBase(Double salaireBase) {
		this.salaireBase = salaireBase;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendeur other = (Vendeur) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vendeur [id=" + id + ", name=" + name + ", email=" + email + ", dateNaissance=" + dateNaissance
				+ ", salaireBase=" + salaireBase + ", departement=" + departement + "]";
	}
}
