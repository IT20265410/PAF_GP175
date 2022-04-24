package com;

import model.Payment;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.*;

@Path("/Payments")
public class PaymentService {

	Payment payObj = new Payment();


	//Add payment
	@POST
	@Path("/")
	// To specify the input type as form data
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	// To specify the form elements as the parameters to the payment() method
		public String payment(@FormParam("cardNumber") String cardNumber, @FormParam("CVV") String CVV,
				@FormParam("expDate") String expDate, @FormParam("amount") float amount, @FormParam("payCustomerId") int payCustomerId)

		{
		String output = payObj.payment(cardNumber, CVV, expDate, amount, payCustomerId);
		return output;
	}

	//Update payment details
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData) {

		// Convert the input string to a JSON object
		JsonObject PayObject = new JsonParser().parse(paymentData).getAsJsonObject();

		// Read the values from the JSON object
		String paymentId = PayObject.get("paymentId").getAsString();
		String cardNumber = PayObject.get("cardNumber").getAsString();
		String CVV = PayObject.get("CVV").getAsString();
		String expDate = PayObject.get("expDate").getAsString();
		String amount = PayObject.get("amount").getAsString();
		String payCustomerId = PayObject.get("payCustomerId").getAsString();
		

		String output = payObj.updatePayment(paymentId, cardNumber, CVV, expDate, amount, payCustomerId);
		return output;
	}

	//view all payments
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String viewAllPayments() {
		return payObj.viewAllPayments();
	}

	//Delete a payment
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData) {

		// Convert the input string to an XML document
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

		// Read the value from the element <conId>
		String paymentId = doc.select("paymentId").text();

		// Pass this paymentId can call the deletePayments() method in the model
		String output = payObj.deletePayment(paymentId);
		return output;
	}

}