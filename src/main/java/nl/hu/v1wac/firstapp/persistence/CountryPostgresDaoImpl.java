package nl.hu.v1wac.firstapp.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.hu.v1wac.firstapp.webservices.Country;

public class CountryPostgresDaoImpl extends PostgresBaseDao implements CountryDao {

	
	private List<Country> selectCountries(String query) {
		List<Country> results = new ArrayList<Country>();
		try (Connection con = super.getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet dbResultSet = pstmt.executeQuery();
			while (dbResultSet.next()) {
				String code = dbResultSet.getString("code");
				String name = dbResultSet.getString("name");
				String capital = dbResultSet.getString("capital");
				float surface = dbResultSet.getFloat("surfacearea");
				String government = dbResultSet.getString("governmentform");
				float lat = dbResultSet.getFloat("latitude");
				String iso3 = dbResultSet.getString("iso3");
				String continent = dbResultSet.getString("continent");
				String region = dbResultSet.getString("region");
				int population = dbResultSet.getInt("population");
				float lng = dbResultSet.getFloat("longitude");
				Country newCountry = new Country(code,iso3,name,capital,continent,region,surface,population,government,lat,lng);
				
				
				results.add(newCountry);
			}
		} catch (SQLException sqle) {sqle.printStackTrace();}
		return results;
	}

	public List<Country> findAll(){
		return selectCountries("SELECT * FROM COUNTRY");
	}
	public Country findByCode(String countryCode) throws SQLException {
		Connection con = super.getConnection();
		String strQuery = ("SELECT * FROM COUNTRY WHERE CODE = ?");
		PreparedStatement pstmt = con.prepareStatement(strQuery);
		pstmt.setString(1,countryCode);
		ResultSet dbResultSet = pstmt.executeQuery();
		dbResultSet.next();
			String code = dbResultSet.getString("code");
			String name = dbResultSet.getString("name");
			String capital = dbResultSet.getString("capital");
			float surface = dbResultSet.getFloat("surfacearea");
			String government = dbResultSet.getString("governmentform");
			float lat = dbResultSet.getFloat("latitude");
			String iso3 = dbResultSet.getString("iso3");
			String continent = dbResultSet.getString("continent");
			String region = dbResultSet.getString("region");
			int population = dbResultSet.getInt("population");
			float lng = dbResultSet.getFloat("longitude");
			Country newCountry = new Country(code,iso3,name,capital,continent,region,surface,population,government,lat,lng);
			con.close();

			return newCountry;
		

	}
	public void delete(Country c) throws SQLException {
		Connection conn = super.getConnection();
		String strQuery = "DELETE FROM COUNTRY where CODE = ?";
		PreparedStatement ps = conn.prepareStatement(strQuery);
		ps.setString(1,c.getCode());
		ps.executeUpdate();
		conn.close();
	}
	public void create(Country c) throws SQLException {
		Connection conn = super.getConnection();
		String strQuery = "insert into country (code,name,capital,region,surfacearea,population) values (?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(strQuery);
		ps.setString(1,c.getCode());
		ps.setString(2, c.getName());
		ps.setString(3, c.getCapital());
		ps.setString(4, c.getRegion());
		ps.setDouble(5, c.getSurface());
		ps.setInt(6, c.getPopulation());
		ps.executeUpdate();
		conn.close();

	}
	public void update(Country c) throws SQLException {
		Connection conn = super.getConnection();
		String strQuery = "update country set name = ?,capital = ?,region = ?,surfacearea = ?,population = ? where code = ?";
		PreparedStatement ps = conn.prepareStatement(strQuery);
		ps.setString(1,c.getName());
		ps.setString(2,c.getCapital());
		ps.setString(3,c.getRegion());
		ps.setDouble(4,c.getSurface());
		ps.setInt(5,c.getPopulation());
		ps.setString(6,c.getCode());
		ps.executeUpdate();
		conn.close();

	
	}
	
	
}
