package api;

import models.AddListOfBooksRequest;
import models.AddListOfBooksResponse;
import models.ListOfIsbns;

import java.util.ArrayList;

import static data.ApiEndpoints.BOOKS;
import static io.restassured.RestAssured.given;
import static specs.Specification.request;
import static specs.Specification.successAddBooksResponse201;

public class BooksApi {
    public static AddListOfBooksResponse addBooks (String token, String userId) {
        ArrayList books = new ArrayList<>();
        books.add(new ListOfIsbns("9781449325862"));
        AddListOfBooksRequest dataBook = new AddListOfBooksRequest();
        dataBook.setCollectionOfIsbns(books);
        dataBook.setUserId(userId);

        return given(request)
                .header("Authorization", "Bearer " + token)
                .body(dataBook)
                .when()
                .post(BOOKS)
                .then()
                .log().all()
                .spec(successAddBooksResponse201)
                .extract().as(AddListOfBooksResponse.class);
    }

}
