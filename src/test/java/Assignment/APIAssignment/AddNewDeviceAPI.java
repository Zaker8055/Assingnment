package Assignment.APIAssignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewDeviceAPI {
	
	@Test
	public void AddDevice() {
				
		RestAssured.baseURI="https://api.restful-api.dev";
		Response response=given().log().all().contentType(ContentType.JSON).body("{\r\n"
				+ "\"name\": \"Apple Max Pro 1TB\",\r\n"
				+ "\"data\": {\r\n"
				+ "\"year\": 2023,\r\n"
				+ "\"price\": 7999.99,\r\n"
				+ "\"CPU model\": \"Apple ARM A7\",\r\n"
				+ "\"Hard disk size\": \"1 TB\"\r\n"
				+ "}\r\n"
				+ "}").when().post("objects").then().log().all().assertThat().statusCode(200).extract().response();
		
		String Jsonresponce=response.asString();
		System.out.println("Responce of Add Device API"+Jsonresponce);
		
		String ID=response.jsonPath().getString("id");
		String NAME=response.jsonPath().getString("name");
		String CREATE_DATE=response.jsonPath().getString("createdAt");
		String DATA_YEAR=response.jsonPath().getString("data.year");
		String DATA_PRICE=response.jsonPath().getString("data.price");
		String CPU_MODEL=response.jsonPath().getString("data.'CPU model'");
		String HARD_DISK=response.jsonPath().getString("data.'Hard disk size'");
	
		
		//Validate the Id and Created Date Should not be Null
		Assert.assertNotNull(ID,"Id Should not be Null");
		Assert.assertNotNull(CREATE_DATE,"Created Date Should not be Null");

		//Validate the Field of Request Body and Responce
		Assert.assertEquals(NAME,"Apple Max Pro 1TB","Name Should Be equal");
		Assert.assertEquals(DATA_YEAR,"2023","Year Should Be equal");
		Assert.assertEquals(DATA_PRICE,"7999.99","Price Should Be equal");
		Assert.assertEquals(CPU_MODEL,"Apple ARM A7","CPU Model Should Be equal");
		Assert.assertEquals(HARD_DISK,"1 TB","Hard Disk Should Be equal");
		
	}

}
