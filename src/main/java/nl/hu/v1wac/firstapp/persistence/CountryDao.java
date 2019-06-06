package nl.hu.v1wac.firstapp.persistence;

import java.sql.SQLException;
import java.util.List;

import nl.hu.v1wac.firstapp.webservices.Country;

public interface CountryDao {
	public List<Country> findAll();
	public Country findByCode(String countryCode) throws SQLException;
	public void delete(Country c) throws SQLException;
	public void create(Country c) throws SQLException;
	public void update(Country c) throws SQLException;
}
