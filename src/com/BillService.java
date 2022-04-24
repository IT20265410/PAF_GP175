//To implement the RESTful API
package com;

import model.Bill; 

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.*;

@Path("/Bills")
public class BillService {

	Bill billObj = new Bill();

	//view a particular customer details
	@GET
	@Path("/{billId}")
	@Produces(MediaType.TEXT_HTML)
	public String viewBill(@PathParam("billId") String billId) {
		return billObj.viewBill(billId); 
	}

	//Add customer(registration)
	@POST
	@Path("/")
	// To specify the input type as form data
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	// To specify the form elements as the parameters to the register() method
	public String addBill(@FormParam("issueDate") String issueDate, @FormParam("unit") int unit,
			@FormParam("balance") Float balance, @FormParam("amountToPay") Float amountToPay, @FormParam("totalAmount") Float totalAmount,
			@FormParam("customerId") int customerId)

	{
		// pass these values to the register() method, return into a String variable
		// named output, which can be returned to the client as the response.
		String output = billObj.addBill(issueDate, unit, balance, amountToPay, totalAmount, customerId);
		return output;
	}

	//Update customer details
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateBill(String billData) {

		// Convert the input string to a JSON object
		JsonObject BillObject = new JsonParser().parse(billData).getAsJsonObject();

		// Read the values from the JSON object
		String billId = BillObject.get("billId").getAsString();
		String issueDate = BillObject.get("issueDate").getAsString();
		String unit = BillObject.get("unit").getAsString();
		String balance = BillObject.get("balance").getAsString();
		String amountToPay = BillObject.get("amountToPay").getAsString();
		String totalAmount = BillObject.get("totalAmount").getAsString();
		String customerId = BillObject.get("customerId").getAsString();

		String output = billObj.updateBill(issueDate, unit, balance, amountToPay, totalAmount, customerId);
		return output;
	}

	//Admin side view all customers 
	@GET
	@Path("/viewAllBill")
	@Produces(MediaType.TEXT_HTML)
	public String viewAllBill() {
		return billObj.viewAllBill();
	}

	//Delete a customer account
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteBill(String billData) {

		// Convert the input string to an XML document
		Document doc = Jsoup.parse(billData, "", Parser.xmlParser());

		// Read the value from the element <billId>
		String billId = doc.select("billId").text();

		// Pass this billId can call the deleteBill() method in the model
		String output = billObj.deleteBill(billId);
		return output;
	}

}
