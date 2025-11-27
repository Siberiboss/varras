package ee.varras;

import ee.varras.dto.CategoryDto;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
public class CategoryControllerTest {

    @Inject
    @Client("/api")
    HttpClient client;

    @Test
    void testCreateCategory() {
        System.out.println(client.getClass());
        var categoryReq = Map.of(
                "name", "Tuulelohed",
                "username", "testija"
        );

        CategoryDto category = client
                .toBlocking()
                .retrieve(HttpRequest.POST("/categories", categoryReq), CategoryDto.class);

        assertEquals("Tuulelohed", category.getName());
        assertNotNull(category.getId());
    }
}
