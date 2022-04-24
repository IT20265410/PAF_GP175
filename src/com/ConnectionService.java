//To implement the RESTful API
package com;

import model.Connections;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.*;

@Path("/Connections")
public class ConnectionService {

	Connections conObj = new Connections();

	//view a particular connection details
	@GET
	@Path("/{conId}")
	@Produces(MediaType.TEXT_HTML)
	public String viewConnection(@PathParam("conId") String conId) {
		return conObj.viewConnection(conId); 
	}

	//Add connection 
	@POST
	@Path("/")
	// To specify the input type as form data
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	// To specify the form elements as the parameters to the register() method
	public String register(@FormParam("conName") String conName, @FormParam("conType") String conType,
			@FormParam("conDesc") String conDesc)

	{
		// pass these values to the register() method, return into a String variable
		// named output, which can be returned to the client as the response.
		String output = conObj.register(conName, conName, conType);
		return output;
	}

	//Update connection details
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateConnections(String connectionData) {

		// Convert the input string to a JSON object
		JsonObject ConObject = new JsonParser().parse(connectionData).getAsJsonObject();

		// Read the values from the JSON object
		String conId = ConObject.get("conId").getAsString();
		String conName = ConObject.get("conName").getAsString();
		String conType = ConObject.get("conType").getAsString();
		String conDesc = ConObject.get("conDesc").getAsString();
		

		String output = conObj.updateConnections(conId, conName, conType, conDesc);
		return output;
	}

	//view all connections
	@GET
	@Path("/viewAllCustomers")
	@Produces(MediaType.TEXT_HTML)
	public String viewAllConnections() {
		return conObj.viewAllConnections();
	}

	//Delete a connection
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteConnection(String connectionData) {

		// Convert the input string to an XML document
		Document doc = Jsoup.parse(connectionData, "", Parser.xmlParser());

		// Read the value from the element <conId>
		String conId = doc.select("conId").text();

		// Pass this userId can call the deleteCustomer() method in the model
		String output = conObj.deleteConnection(conId);
		return output;
	}

}