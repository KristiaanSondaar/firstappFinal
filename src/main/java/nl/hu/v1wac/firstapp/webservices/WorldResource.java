package nl.hu.v1wac.firstapp.webservices;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;



@Path("/countries")
public class WorldResource {
	WorldService service = ServiceProvider.getWorldService();

	@GET
	@Produces("application/json")
	public String getCountries() {
		WorldService service = ServiceProvider.getWorldService();
		JsonArray countryArray = buildJsonCountryArray(service.getAllCountries());
		
		return countryArray.toString();
		}
	

	

@GET
@Path("{id}")
@Produces("application/json")
public String getCountrieInfo(@PathParam("id") String id) {
	WorldService service = ServiceProvider.getWorldService();
	Country country = service.getCountryByCode(id);
	

	JsonObjectBuilder job = Json.createObjectBuilder();
	job.add("code", country.getCode());
	job.add("name", country.getName());
	job.add("capital", country.getCapital());
	job.add("surface", country.getSurface());
	job.add("government", country.getGovernment());
	job.add("lat", country.getLatitude());
	job.add("iso3", country.getIso3());
	job.add("continent", country.getContinent());
	job.add("region", country.getRegion());
	job.add("population", country.getPopulation());
	job.add("lng", country.getLongitude());
			
	
	return job.build().toString();
	}
	@DELETE
	@RolesAllowed("user")
	@Path("{id}")
	@Produces("application/json")
	public Response deleteCountry(@PathParam("id") String id) throws SQLException {
		if (!service.deleteCountry(id)) {
			return Response.status(404).build();
		}
		return Response.ok().build();

	}
	
	
	@POST
	@RolesAllowed("user")
	@Produces("application/json")
	public Response createCountry(@FormParam("code") String code,
									@FormParam("land") String land,
									@FormParam("hoofdstad") String hoofdstad,
									@FormParam("regio") String regio,
									@FormParam("oppervlakte") int oppervlakte,
									@FormParam("inwoners") int inwoners) throws SQLException {
									
		Country newCountry = service.createCountry(code,land,hoofdstad,regio,oppervlakte,inwoners);
		return Response.ok(newCountry).build();
	}
	
	@PUT
	@RolesAllowed("user")
	@Path("{code}")
	@Produces("application/json")
	public Response updateCountry(@PathParam("code") String code,
									@FormParam("name") String land,
									@FormParam("hoofdstad") String hoofdstad,
									@FormParam("regio") String regio,
									@FormParam("oppervlakte") int oppervlakte,
									@FormParam("inwoners") int inwoners) throws SQLException {
		
		Country country = service.updateCountry(code,land,hoofdstad,regio,oppervlakte,inwoners);
		
		if(country == null) {
			Map<String, String> messages = new HashMap<String, String>();
			messages.put("error", "Country doesnt exist!");
			return Response.status(409).entity(messages).build();
		}
	
	return Response.ok(country).build();
	}


@GET
@Path("/largestsurfaces")
@Produces("application/json")
public String getLargestSurfaces() {
	WorldService service = ServiceProvider.getWorldService();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	
	for (Country w : service.get10LargestSurfaces()) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("name", w.getName());
		job.add("surface", w.getSurface());		
		
		jab.add(job);
	}
	JsonArray array = jab.build();
	return array.toString();
	}


@GET
@Path("/largestpopulations")
@Produces("application/json")
public String getLargestpopulations() {
	WorldService service = ServiceProvider.getWorldService();
	JsonArrayBuilder jab = Json.createArrayBuilder();
	
	for (Country w : service.get10LargestPopulations()) {
		jab.add(convert(w));
	}
	JsonArray array = jab.build();
	return array.toString();
	}

	private JsonObjectBuilder convert(Country c) {
		JsonObjectBuilder job = Json.createObjectBuilder();
		job.add("name", c.getName());
		job.add("population", c.getPopulation());		
		return job;
	}
	private JsonArray buildJsonCountryArray(List<Country> countries) {
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		
		for (Country country : countries) {
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("code", country.getCode());
			job.add("name", country.getName());
			job.add("capital", country.getCapital());
			job.add("surface", country.getSurface());
			job.add("government", country.getGovernment());
			job.add("lat", country.getLatitude());
			job.add("iso3", country.getIso3());
			job.add("continent", country.getContinent());
			job.add("region", country.getRegion());
			job.add("population", country.getPopulation());
			job.add("lng", country.getLongitude());
			
			jsonArrayBuilder.add(job);
		}
		return jsonArrayBuilder.build();
	}
}



