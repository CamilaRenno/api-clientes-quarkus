package br.com.exemplo.cliente;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ClienteResourceTest {

    @Test
	@Disabled("Corrigir")
    public void testCrudFlow() {
        // cria
        long id = 
        given()
            .contentType("application/json")
            .body("{nome:Alice, email:alice@example.com, telefone:1199999-0000}")
        .when()
            .post("/clientes")
        .then()
            .statusCode(201)
            .extract().path("id");

        // lista
        given()
        .when()
            .get("/clientes")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));

        // busca por id
        given()
        .when()
            .get("/clientes/" + id)
        .then()
            .statusCode(200)
            .body("nome", is("Alice"));

        // atualiza
        given()
            .contentType("application/json")
            .body("{nome:Alice Silva, email:alice.silva@example.com, telefone:1199999-1111}")
        .when()
            .put("/clientes/" + id)
        .then()
            .statusCode(200)
            .body("nome", is("Alice Silva"));

        // deleta
        given()
        .when()
            .delete("/clientes/" + id)
        .then()
            .statusCode(204);
    }
}