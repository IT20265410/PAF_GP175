//To implement the RESTful API
package com;

import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/*//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
*/
@Path("/Users")
public class UserService {
	
	User userObj = new User();
	
	@POST
	@Path("/")
	// To specify the input type as form data
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)

	// To specify the form elements as the parameters to the register() method
	public String register(@FormParam("firstName") String firstName, 
							@FormParam("lastName") String lastName,
							@FormParam("NIC") String NIC, 
							@FormParam("homeNo") String homeNo,
							@FormParam("street") String street,
							@FormParam("city") String city,
							@FormParam("phone") int phone,
							@FormParam("email") String email)
	
	{
		// pass these values to the register() method, return into a String variable
		// named output, which can be returned to the client as the response.
		String output = userObj.register(firstName, lastName, NIC, homeNo,street,city,phone,email);
		return output;
	}
}
