package com.barclays.LibrarySystemAPI.controller;

import com.barclays.LibrarySystemAPI.dto.UserRequestDTO;
import com.barclays.LibrarySystemAPI.model.User;
import com.barclays.LibrarySystemAPI.repository.UserRepository;
import com.barclays.LibrarySystemAPI.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class UserController {

    private UserService userService;

    @GetMapping(value = "/user") //no working
    public List<User> findAllUsers(){
       return userService.findAllUsers();
    }

    @GetMapping( "/user/{id}")
    public User findUserById(@PathVariable  Long id){
       return userService.findUserById(id);
    }

    @PostMapping("user/create") // not working
    public User createUser(@RequestBody UserRequestDTO userDTO){
       return userService.save( userDTO);
    }

    @GetMapping(value = "user/search")
    public List<User> getUser(@PathParam("name") String name) {
        List<User> user = Collections.emptyList();
        if(StringUtils.isNotBlank(name)) {
            user = userService.searchByName(name);

        }
        else {
            user = userService.findAllUsers();
        }

        return user;
    }

    @PutMapping ("user/update") //not tested
    public User updateUser(@RequestBody User user) {
        log.debug(String.valueOf(user));
        return userService.updateUser(user);
    }



    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;

    }







}
