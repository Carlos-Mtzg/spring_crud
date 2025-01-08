package gregdev.spring_crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gregdev.spring_crud.dto.UserDto;
import gregdev.spring_crud.model.UserModel;
import gregdev.spring_crud.service.UserService;
import gregdev.spring_crud.utils.Utilities;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    private static final String RECORD_NOT_FOUND = "Record not found";
    private static final String INTERNAL_SERVER_ERROR = "An internal server error ocurred.";

    UserController(UserService userService) {
        this.userService = userService;
    }

    // Test
    @GetMapping("/test")
    public String test() {
        return "Ok";
    }

    // Get All
    @GetMapping("/user")
    public List<UserModel> getAll() {
        return this.userService.getAll();
    }

    // GetById
    @GetMapping("/user/{id}")
    public UserModel getById(@PathVariable("id") Integer id) {
        return this.userService.findById(id);
    }

    // Create
    @PostMapping("/user")
    public ResponseEntity<Object> createUser(@RequestBody UserModel request) {
        try {
            this.userService.save(request);
            return Utilities.generateResponse(HttpStatus.OK, "Record created successfully");
        } catch (Exception e) {
            return Utilities.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
        }
    }

    // Update
    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Integer id, @RequestBody UserDto request) {
        try {
            UserModel user = this.getById(id);
            if (user == null) {
                return Utilities.generateResponse(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND);
            } else {
                this.userService.update(user, request);
                this.userService.save(user);
                return Utilities.generateResponse(HttpStatus.OK, "Record updated successfully");
            }
        } catch (Exception e) {
            return Utilities.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
        }
    }

    // Delete
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") Integer id) {
        try {
            UserModel user = this.getById(id);
            if (user == null) {
                return Utilities.generateResponse(HttpStatus.NOT_FOUND, RECORD_NOT_FOUND);
            } else {
                this.userService.delete(id);
                return Utilities.generateResponse(HttpStatus.OK, "Record deleted successfully");
            }
        } catch (Exception e) {
            return Utilities.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
        }
    }
}
