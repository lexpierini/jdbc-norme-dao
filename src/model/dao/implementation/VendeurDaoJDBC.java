package model.dao.implementation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.VendeurDao;
import model.entities.Departement;
import model.entities.Vendeur;

public class VendeurDaoJDBC implements VendeurDao {

	private Connection conn;
	
	public VendeurDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Vendeur obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
				"INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) "
				+ "VALUES (?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS
			);
			
			st.setString(1, obj.getNom());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDateNaissance().getTime()));
			st.setDouble(4, obj.getSalaireBase());
			st.setInt(5, obj.getDepartement().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
				
			} else {
				throw new DbException("Erreur inattendue! Aucune ligne affectée!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Vendeur obj) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement(
				"UPDATE seller "
				+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
				+ "WHERE Id = ?"
			);
			
			st.setString(1, obj.getNom());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDateNaissance().getTime()));
			st.setDouble(4, obj.getSalaireBase());
			st.setInt(5, obj.getDepartement().getId());
			st.setInt(6, obj.getId());
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendeur findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
				"SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "WHERE seller.Id = ?"
			);
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Departement dep = instantiateDepartement(rs);
				Vendeur obj = instantiateVendeur(rs, dep);
				return obj;
			}
			
			return null;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Vendeur instantiateVendeur(ResultSet rs, Departement dep) throws SQLException {
		Vendeur obj = new Vendeur();
		obj.setId(rs.getInt("Id"));
		obj.setNom(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setDateNaissance(rs.getDate("BirthDate"));
		obj.setSalaireBase(rs.getDouble("BaseSalary"));
		obj.setDepartement(dep);
		
		return obj;
	}

	private Departement instantiateDepartement(ResultSet rs) throws SQLException {
		Departement dep = new Departement();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setNom(rs.getString("DepName"));
		
		return dep;
	}

	@Override
	public List<Vendeur> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
				"SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "ORDER BY Name"
			);
			
			rs = st.executeQuery();
			
			List<Vendeur> list = new ArrayList<>();
			Map<Integer, Departement> map = new HashMap<>();
			
			while (rs.next()) {
				Departement dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = instantiateDepartement(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Vendeur obj = instantiateVendeur(rs, dep);
				list.add(obj);
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Vendeur> findByDepartement(Departement departement) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
				"SELECT seller.*,department.Name as DepName "
				+ "FROM seller INNER JOIN department "
				+ "ON seller.DepartmentId = department.Id "
				+ "WHERE DepartmentId = ? "
				+ "ORDER BY Name"
			);
			
			st.setInt(1, departement.getId());
			rs = st.executeQuery();
			
			List<Vendeur> list = new ArrayList<>();
			Map<Integer, Departement> map = new HashMap<>();
			
			while (rs.next()) {
				Departement dep = map.get(rs.getInt("DepartmentId"));
				
				if (dep == null) {
					dep = instantiateDepartement(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				Vendeur obj = instantiateVendeur(rs, dep);
				list.add(obj);
			}
			
			return list;
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
