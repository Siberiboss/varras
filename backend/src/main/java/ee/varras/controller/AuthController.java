package ee.varras.controller;

import io.micronaut.http.annotation.*;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

import java.util.Base64;


@Controller("/auth")
public class AuthController {

    @Post("/login")
    public LoginResponse login(@Body LoginRequest req) {
        System.out.println(req.getUsername());
        if (req.getPassword() == null || req.getPassword().isBlank()) {
            System.out.println("ERROR ERROR ERROR");
            System.out.println(req.getPassword());
            return null;
        }

        String tokenJson = """
        {
            "username": "%s",
            "expires": "2099-01-01T00:00:00Z",
            "roles": ["USER"]
        }""".formatted(req.getUsername());
        String fakeToken = Base64.getEncoder().encodeToString(tokenJson.getBytes());

        return new LoginResponse(fakeToken);
    }

    @Introspected
    @Serdeable
    public static class LoginRequest {
        private String username;
        private String password;

        public String getUsername() { return username; }
        public String getPassword() { return password; }

        public void setUsername(String username) { this.username = username; }
        public void setPassword(String password) { this.password = password; }
    }

    @Introspected
    @Serdeable
    public static class LoginResponse {
        private final String token;

        public LoginResponse(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }


}
