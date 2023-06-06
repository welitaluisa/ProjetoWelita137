package apiTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPetStore {

    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    static String ct = "application/json";
    private static String petId;
    private static String orderId;

    public static String lerArquivoJson(String arquivoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(arquivoJson)));
    }

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @Order(1)
    public void testarCadastrarGato() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/pet.json");

        Response response = given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post("/pet");

        response.then()
                .statusCode(200)
                .body("name", is("Bichento"))
                .body("status", is("available"))
                .body("category.name", is("cat"));

        petId = response.path("id").toString();
    }

    @Test
    @Order(2)
    public void testarCadastrarUsuario() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/user.json");
        String userId = "1370399388645";
        Response response = given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post("/user");

        response.then()
                .statusCode(200)
                .body("code", is(200))
                .body("type", is("unknown"))
                .body("message", is(userId));

    }

    @Test
    @Order(3)
    public void testarVenderPetParaUsuario() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/orderPet.json");
        Response response = given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .post("/store/order");

        response.then()
                .log().all()
                .statusCode(200)
                .body("id", is(100))
                .body("petId", is(Integer.parseInt(petId)))
                .body("quantity", is(1))
                .body("status", is("sold"));

        orderId = response.path("id").toString();
    }

    @Test
    @Order(4)
    public void testarAlterarStatusPet() throws IOException {
        String jsonBody = lerArquivoJson("src/test/resources/json/petVendida.json");

        given()
                .contentType(ct)
                .log().all()
                .body(jsonBody)
        .when()
                .put("/pet")
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Bichento"))
                .body("category.name", is("cat"))
                .body("status", is("sold"));
    }

    @Test
    @Order(5)
    public void testarConsultarStatusDoAnimalAposVenda() {
        given()
                .contentType(ct)
                .log().all()
       .when()
                .get("/pet/" + petId)
       .then()
                .log().all()
                .statusCode(200)
                .body("category.name", is("cat"))
                .body("name", is("Bichento"))
                .body("status", is("sold"));
    }

    @Test
    @Order(6)
    public void testarConsultarOrdemDeVendaDoAnimal() {
        given()
                .contentType(ct)
                .log().all()
        .when()
                .get("/store/order/" + orderId)
        .then()
                .log().all()
                .statusCode(200)
                .body("petId", is(Integer.parseInt(petId)))
                .body("status", is("sold"))
                .body("quantity", is(1));
    }

}
