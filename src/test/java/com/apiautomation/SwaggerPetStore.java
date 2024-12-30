package com.apiautomation;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SwaggerPetStore {
    @Test
    public void createUser() {
        Response response =
                given()
                        .header("accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body("{\n" +
                                "  \"id\": 111,\n" +
                                "  \"username\": \"surriyaa\",\n" +
                                "  \"firstName\": \"string\",\n" +
                                "  \"lastName\": \"string\",\n" +
                                "  \"email\": \"string\",\n" +
                                "  \"password\": \"password\",\n" +
                                "  \"phone\": \"string\",\n" +
                                "  \"userStatus\": 0\n" +
                                "}")
                        .when()
                        .post("https://petstore.swagger.io/v2/user");

        // Validate the response and print it
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void userCreation() {
        // Sending a POST request to create a new user
        Response response =
                given()
                        .header("accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body("{\n" +
                                "  \"id\": 1,\n" +
                                "  \"username\": \"surriyaa\",\n" +
                                "  \"firstName\": \"string\",\n" +
                                "  \"lastName\": \"string\",\n" +
                                "  \"email\": \"string\",\n" +
                                "  \"password\": \"surriyaa\",\n" +
                                "  \"phone\": \"string\",\n" +
                                "  \"userStatus\": 0\n" +
                                "}")
                        .when()
                        .post("https://petstore.swagger.io/v2/user");
        response.prettyPrint();
        response.then().statusCode(200);

        Assert.assertEquals(response.getStatusCode(), 200, "Status Code Validation Failed");

        //Query parameters
        given()
                .header("accept", "application/json")
                .queryParam("username", "surriyaa")
                .queryParam("pass", "surriyaa")
                .when()
                .get("https://petstore.swagger.io/v2/user/surriyaa")
                .then()
                .statusCode(200);
    }


    @Test
    public void deleteUser() {
        //username to be deleted
        String username = "surriyaa";

        // DELETE request
        Response response =
                given()
                        .header("accept", "application/json") // Accept JSON response
                        .when()
                        .delete("https://petstore.swagger.io/v2/user/" + username); // URL with username
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void updateUser() {
        //username to be updated
        String username = "Vijay";

        //updated user
        String requestBody = "{\n" +
                "  \"id\": 10,\n" +
                "  \"username\": \"vijay\",\n" +
                "  \"firstName\": \"string\",\n" +
                "  \"lastName\": \"string\",\n" +
                "  \"email\": \"string\",\n" +
                "  \"password\": \"string\",\n" +
                "  \"phone\": \"string\",\n" +
                "  \"userStatus\": 0\n" +
                "}";

        Response response =
                given()
                        .header("accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(requestBody) // Attach the request body
                        .when()
                        .put("https://petstore.swagger.io/v2/user/" + username); // URL with username

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void getUserByUsername() {
        // Set the username to fetch
        String username = "surriyaa";

        //GET request
        Response response =
                given()
                        .header("accept", "application/json") // Accept JSON response
                        .when()
                        .get("https://petstore.swagger.io/v2/user/" + username); // URL with username

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void createUsersWithList() {
        String requestBody = "[\n" +
                "  {\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"chandru\",\n" +
                "    \"firstName\": \"string\",\n" +
                "    \"lastName\": \"string\",\n" +
                "    \"email\": \"string\",\n" +
                "    \"password\": \"password\",\n" +
                "    \"phone\": \"string\",\n" +
                "    \"userStatus\": 0\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 0,\n" +
                "    \"username\": \"harith\",\n" +
                "    \"firstName\": \"string\",\n" +
                "    \"lastName\": \"string\",\n" +
                "    \"email\": \"string\",\n" +
                "    \"password\": \"password\",\n" +
                "    \"phone\": \"string\",\n" +
                "    \"userStatus\": 0\n" +
                "  }\n" +
                "]";
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post("https://petstore.swagger.io/v2/user/createWithList");

        response.prettyPrint();

        response.then().statusCode(200);
    }
    @Test
    public void petImageUpload() {
        // POST request to upload an image
        File file = new File("C:\\Users\\ASUS\\Downloads\\download.jpeg");

        Response res =
                given()
                        .header("accept", "application/json")
                        .header("Content-Type", "multipart/form-data")
                        .multiPart(file) // Adding the file for upload
                        .when()
                        .post("https://petstore.swagger.io/v2/pet/uploadImage"); // API endpoint for image upload
        res.prettyPrint();
    }


        @Test
        public void addNewPet() {
            String requestBody = "{\n" +
                    "  \"id\": 0,\n" +
                    "  \"category\": {\n" +
                    "    \"id\": 0,\n" +
                    "    \"name\": \"huskey\"\n" +
                    "  },\n" +
                    "  \"name\": \"doggie\",\n" +
                    "  \"photoUrls\": [\n" +
                    "    \"string\"\n" +
                    "  ],\n" +
                    "  \"tags\": [\n" +
                    "    {\n" +
                    "      \"id\": 0,\n" +
                    "      \"name\": \"string\"\n" +
                    "    }\n" +
                    "  ],\n" +
                    "  \"status\": \"available\"\n" +
                    "}";

            Response response =
                    given()
                            .header("Content-Type", "application/json")
                            .header("accept", "application/json")
                            .body(requestBody) // Attach the JSON body
                            .when()
                            .post("https://petstore.swagger.io/v2/pet"); // POST endpoint to add a new pet

            response.prettyPrint();

            response.then().statusCode(200);
        }
    @Test
    public void updatePetDetails() {
        String requestBody = "{\n" +
                "  \"id\": 10,\n" +
                "  \"category\": {\n" +
                "    \"id\": 20,\n" +
                "    \"name\": \"pet\"\n" +
                "  },\n" +
                "  \"name\": \"Huskey dod\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://www.zigly.com/media/mageplaza/blog/post/siberian_husky_2_.png\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 30,\n" +
                "      \"name\": \"dog\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        Response response =
                given()
                        .header("Content-Type", "application/json") // Set the content type
                        .header("accept", "application/json") // Accept header for the response format
                        .body(requestBody) // Attach the JSON body
                        .when()
                        .put("https://petstore.swagger.io/v2/pet"); // PUT endpoint to update a pet

        response.prettyPrint();

        response.then().statusCode(200); // Expected status code
    }
    @Test
    public void getPetsByStatus() {
        String status = "sold";

        Response response =
                given()
                        .queryParam("status", status) // Add query parameter for status
                        .header("accept", "application/json") // Ensure response is in JSON
                        .when()
                        .get("https://petstore.swagger.io/v2/pet/findByStatus"); // Endpoint to find pets by status

        response.prettyPrint();

        response.then().statusCode(200);

        // Extract specific data from the response (e.g., pet names)
        List<String> petNames = response.jsonPath().getList("name");
        System.out.println("Pets with status '" + status + "': " + petNames);
    }
    @Test
    public void getPetById() {
        int petId = 10; // Pet ID to fetch

        Response response =
                given()
                        .pathParam("petId", petId)
                        .header("accept", "application/json")
                        .when()
                        .get("https://petstore.swagger.io/v2/pet/{petId}");

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void deleteOrderById() {
        int orderId = 5; // Order ID to delete

        Response response =
                given()
                        .pathParam("orderId", orderId)
                        .header("accept", "application/json")
                        .when()
                        .delete("https://petstore.swagger.io/v2/store/order/{orderId}");

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void getOrderById() {
        int orderId = 4; // Order ID to fetch

        Response response =
                given()
                        .pathParam("orderId", orderId)
                        .header("accept", "application/json")
                        .when()
                        .get("https://petstore.swagger.io/v2/store/order/{orderId}");

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void getInventory() {
        Response response =
                given()
                        .header("accept", "application/json")
                        .when()
                        .get("https://petstore.swagger.io/v2/store/inventory");

        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void placeOrder() {
        String requestBody = "{\n" +
                "  \"id\": 9223372016900014000,\n" +
                "  \"petId\": 9223372016900015386,\n" +
                "  \"quantity\": 10,\n" +
                "  \"shipDate\": \"2024-12-24T08:38:22.583+0000\",\n" +
                "  \"status\": \"placed\",\n" +
                "  \"complete\": true\n" +
                "}";

        Response response =
                given()
                        .header("accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post("https://petstore.swagger.io/v2/store/order");

        response.prettyPrint();
        response.then().statusCode(200);
    }

}


