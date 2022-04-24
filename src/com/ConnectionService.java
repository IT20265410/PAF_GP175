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


	//Add connection 
	@POST
	@Path("/")
	// To specify the input type as form data
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	// To specify the form elements as the parameters to the register() method
	public String addConnection(@FormParam("conName") String conName, @FormParam("conType") String conType,
			@FormParam("conDesc") String conDesc, @FormParam("conAdminId") int conAdminId)

	{
		// pass these values to the register() method, return into a String variable
		// named output, which can be returned to the client as the response.
		String output = conObj.addConnection(conName, conName, conType, conAdminId);
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
		String conAdminId = ConObject.get("conAdminId").getAsString();
		

		String output = conObj.updateConnections(conId, conName, conType, conDesc, conAdminId);
		return output;
	}

	//view all connections
	@GET
	@Path("/")
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

		// Pass this userId can call the deleteConnection() method in the model
		String output = conObj.deleteConnection(conId);
		return output;
	}

}