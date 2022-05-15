package service;

import com.Model;

import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;

@Path("/Service")
public class service {
	
	Model serviceObj = new Model();
	
	@POST
	@Path("/insert") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertService
	(@FormParam("month") String month, 
	 @FormParam("pastUnits") String pastUnits, 
	 @FormParam("currentUnits") String currentUnits, 
	 @FormParam("consumeUnits") String consumeUnits
	)
	{ 
	 String output = serviceObj.insertService(month, pastUnits, currentUnits, consumeUnits ); 
	return output; 
	}
	
	@GET
	@Path("/read") 
	@Produces(MediaType.TEXT_HTML) 
	public String readService() 
	 { 
	 return serviceObj.readService(); 
	}
	
	@PUT
	@Path("/update") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateService(String serviceData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject serviceObject = new JsonParser().parse(serviceData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String id = serviceObject.get("id").getAsString(); 
	 String month = serviceObject.get("month").getAsString(); 
	 String pastUnits = serviceObject.get("pastUnits").getAsString(); 
	 String currentUnits = serviceObject.get("currentUnits").getAsString(); 
	 String consumeUnits = serviceObject.get("consumeUnits").getAsString();
	 String output = serviceObj.updateService(id, month, pastUnits, currentUnits, consumeUnits ); 
	return output; 
	}

	@DELETE
	@Path("/delete") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteService(String serviceData) 
	{ 
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(serviceData, "", Parser.xmlParser()); 
	 
	//Read the value from the element <itemID>
	 String id = doc.select("id").text(); 
	 String output = serviceObj.deleteService(id); 
	return output; 
	}

}
