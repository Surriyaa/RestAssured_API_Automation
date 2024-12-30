package com.apiautomation;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class JSONServer {
    @Test
    public void displayPosts() {

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("http://localhost:3000/posts");

        response.prettyPrint();
    }

    @Test
    public void addPost() {

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("http://localhost:3000/posts");

        response.prettyPrint();
    }

    @Test
    public void editPost() {

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("http://localhost:3000/posts/4");

        response.prettyPrint();
    }

    @Test
    public void deletePost() {

        Response response = given()
                .header("Connection", "keep-alive")
                .when()
                .get("http://localhost:3000/posts/4");

        response.prettyPrint();
    }

    @Test
    public void displayComments() {

        Response response = given()
                .header("Connection", "keep-alive")
                .when()
                .get("http://localhost:3000/comments");

        response.prettyPrint();
    }

    @Test
    public void addComment() {

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("http://localhost:3000/comments");

        response.prettyPrint();
    }

    @Test
    public void editComment() {

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("http://localhost:3000/posts/3");

        response.prettyPrint();
    }

    @Test
    public void deleteComment() {

        Response response = given()
                .header("Connection", "keep-alive")
                .when()
                .get("http://localhost:3000/comments/3");

        response.prettyPrint();
    }

    @Test
    public void displayProfile() {

        Response response = given()
                .header("Connection", "keep-alive")
                .when()
                .get("http://localhost:3000/profile");

        response.prettyPrint();
    }

    @Test
    public void addProfile() {

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("http://localhost:3000/profile");

        response.prettyPrint();
    }

    @Test
    public void editProfile() {

        Response response = given()
                .header("Content-Type", "application/json")
                .when()
                .get("http://localhost:3000/profile");

        response.prettyPrint();
    }
}
