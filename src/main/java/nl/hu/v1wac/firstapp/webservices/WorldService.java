package nl.hu.v1wac.firstapp.webservices;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nl.hu.v1wac.firstapp.persistence.CountryDao;
import nl.hu.v1wac.firstapp.persistence.CountryPostgresDaoImpl;

public class WorldService {
	private CountryDao countryDAO = new CountryPostgresDaoImpl();
	
	public List<Country> getAllCountries() {
		return countryDAO.findAll();
	}
	
	public boolean deleteCountry(String code) throws SQLException {
		Country c = countryDAO.findByCode(code);
		
		if (c != null) {
			countryDAO.delete(c);
			return true;
		}
		return false;
	}
	
	public Country createCountry(String code,String land, String hoofdstad, String regio, int oppervlakte, int inwoners) throws SQLException {
		Country c = new Country(code,land,hoofdstad,regio,oppervlakte,inwoners);
		
		
			countryDAO.create(c);
		
		return c;
	}
	
	public Country updateCountry(String code, String land, String hoofdstad, String regio, int oppervlakte, int inwoners) throws SQLException {
		Country c = new Country(code,land,hoofdstad,regio,oppervlakte,inwoners);
		
		countryDAO.update(c);
		
		return c;
	}
	
	public List<Country> get10LargestPopulations() {
		Collections.sort(countryDAO.findAll(), new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return c2.getPopulation() - c1.getPopulation();
			};
		});
		
		return countryDAO.findAll().subList(0, 10);
	}

	public List<Country> get10LargestSurfaces() {
		Collections.sort(countryDAO.findAll(), new Comparator<Country>() {
			public int compare(Country c1, Country c2) {
				return (int)(c2.getSurface() - c1.getSurface());
			};
		});
		
		return countryDAO.findAll().subList(0, 10);
	}
	
	public Country getCountryByCode(String code) {
		Country result = null;
		
		for (Country c : countryDAO.findAll()) {
			if (c.getCode().equals(code)) {
				result = c;
				break;
			}
		}
		
		return result;
	}




}
