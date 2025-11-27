package ee.varras;

import ee.varras.dto.AttributeDto;
import ee.varras.dto.CategoryDto;
import ee.varras.model.AttributeType;
import ee.varras.service.CategoryService;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class AttributeControllerTest {

    @Inject
    @Client("/api")
    HttpClient client;

    Long createCategory(String name) {
        CategoryDto resp = client.toBlocking().retrieve(
                HttpRequest.POST(
                        "/categories",
                        Map.of(
                                "name", name,
                                "username", "testija"
                        )
                ),
                CategoryDto.class
        );
        return resp.getId();
    }

    @Test
    void testCreateAttribute() {
        Long catId = createCategory("Hoidised");

        var attrReq = Map.of(
                "name", "saadus",
                "type", "STRING"
        );

        AttributeDto attr = client
                .toBlocking()
                .retrieve(HttpRequest.POST("/categories/" + catId + "/attributes", attrReq), AttributeDto.class);

        assertEquals("saadus", attr.getName());
        assertEquals(AttributeType.STRING, attr.getType());
        assertNotNull(attr.getId());
    }

    @Test
    void testListAttributes() {
        Long catId = createCategory("Raamatud");

        client.toBlocking().retrieve(
                HttpRequest.POST("/categories/" + catId + "/attributes",
                        Map.of("name", "autor", "type", "STRING")),
                AttributeDto.class
        );

        List<AttributeDto> attributes = client.toBlocking().retrieve(
                HttpRequest.GET("/categories/" + catId + "/attributes"),
                Argument.listOf(AttributeDto.class)
        );

        assertEquals(1, attributes.size());
        assertEquals("autor", attributes.getFirst().getName());
    }

    @Test
    void testUpdateAttribute() {
        Long catId = createCategory("Tööriistad");

        AttributeDto attr = client.toBlocking().retrieve(
                HttpRequest.POST("/categories/" + catId + "/attributes",
                        Map.of("name", "kaal", "type", "NUMBER")),
                AttributeDto.class
        );

        AttributeDto updated = client.toBlocking().retrieve(
                HttpRequest.PUT("/categories/" + catId + "/attributes/" + attr.getId(),
                        Map.of("name", "kaal (kg)", "type", "NUMBER")),
                AttributeDto.class
        );

        assertEquals("kaal (kg)", updated.getName());
    }

    @Test
    void testDeleteAttribute() {
        Long catId = createCategory("Sõidukid");

        AttributeDto attr = client.toBlocking().retrieve(
                HttpRequest.POST("/categories/" + catId + "/attributes",
                        Map.of("name", "mootor", "type", "STRING")),
                AttributeDto.class
        );

        HttpResponse<?> resp = client.toBlocking().exchange(
                HttpRequest.DELETE("/categories/" + catId + "/attributes/" + attr.getId())
        );

        assertEquals(HttpStatus.NO_CONTENT, resp.getStatus());
    }
}
