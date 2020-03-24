package model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendeur obj) {
		// TODO Auto-generated method stub
		
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
