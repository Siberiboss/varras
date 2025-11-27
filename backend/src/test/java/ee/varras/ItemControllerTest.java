package ee.varras;

import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.HttpClient;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;


@MicronautTest
public class ItemControllerTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void testCreateItem() {
        //TODO
    }

    @Test
    void testDeleteItem() {
        //TODO
    }
}