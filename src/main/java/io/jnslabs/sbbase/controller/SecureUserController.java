package io.jnslabs.sbbase.controller;

import io.jnslabs.sbbase.model.entity.Usuario;
import io.jnslabs.sbbase.model.reposnse.springSecurity.LoginResponse;
import io.jnslabs.sbbase.model.reposnse.springSecurity.UserResponse;
import io.jnslabs.sbbase.model.request.springSecurity.LoginRequest;
import io.jnslabs.sbbase.model.request.springSecurity.SignUpRequest;
import io.jnslabs.sbbase.service.ISecureUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Autor Jairo Nascimento
 * @Created 03/03/2022 - 17:18
 */
@RestController
@RequestMapping("/secureUsers")
@SecurityRequirement(name = "sbbase")
@AllArgsConstructor
public class SecureUserController {

    private final ISecureUserService userService;

    @Operation(summary = "Login User", description = "", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "405", description = "Invalid input"),
            @ApiResponse(responseCode = "200", description = "User logging sucesses", content = {@Content(mediaType = "application/json")})
    })
    @PostMapping(value = "/public/login")
    public ResponseEntity<LoginResponse> login(HttpServletRequest requestHeader, @RequestBody LoginRequest request) throws RuntimeException {

        LoginResponse loginResponse = userService.login(request.getUserName(), request.getPassword());

        if (loginResponse == null) {
            throw new RuntimeException("Login failed. Possible cause : incorrect username/password");
        } else {
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
    }

    @Operation(summary = "Add a new User to the store", description = "", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "405", description = "Invalid input", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "201", description = "User created sucesses")
    })
    @PostMapping(value = "/public/signUp")
    public ResponseEntity<Usuario> signUp(HttpServletRequest requestHeader, @RequestBody SignUpRequest request) throws RuntimeException {

        Usuario user;
        try {
            user = userService.signUp(request);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @Operation(summary = "Deletes a user", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),

            @ApiResponse(responseCode = "404", description = "User not found")})
    @DeleteMapping(value = "deleteUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@RequestParam String userName) throws RuntimeException {
        try {
            userService.removeUser(userName);
        } catch (Exception e) {
            throw e;
        }
        return new ResponseEntity<>(userName, HttpStatus.OK);
    }

    @Operation(summary = "Get All Users", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "getAllUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Usuario>> getAllUser() throws RuntimeException {
        try {
            return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }

    }

    @Operation(summary = "Search a User", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping(value = "searchUser")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public ResponseEntity<UserResponse> searchUser(@RequestParam String userName) throws RuntimeException {

        UserResponse userResponse = userService.searchUser(userName);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Refresh token", tags = {"user"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping("/refreshToken")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refreshToken(HttpServletRequest req) {
        return userService.refreshToken(req.getRemoteUser());
    }
}
