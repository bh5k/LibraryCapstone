package com.barclays.LibrarySystemAPI.service;

import com.barclays.LibrarySystemAPI.dto.AddressDTO;
import com.barclays.LibrarySystemAPI.dto.UserRequestDTO;
import com.barclays.LibrarySystemAPI.model.*;
import com.barclays.LibrarySystemAPI.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceUnitImplTest {

    private User user;
    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private UserRequestDTO userRequestDTO;

    @InjectMocks
    private AddressDTO addressDTO;

    @MockBean
    private UserRepository userRepository;
    List<User> userList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        Address address = new Address(1L,"43 Jenson street", "Nottingham", "NG6 5TY","UK");
        Address address2 = new Address(2L,"65 Broadson street", "Nottingham", "NG8 9TY","UK");
        Address address3 = new Address(3L,"21 Wilkinson street", "Nottingham", "NG3 9YY","UK");

        userList=  new ArrayList<>();
        userList.add(new User(1L, "Tolu Adetomiwa",address,"07347284950", "toluadetomiwa@gmail.com" ));
        userList.add(new User(2L, "Tise Oludayomi",address2,"07347583840", "tiseoludayomi@gmail.com" ));
        userList.add(new User(3L, "Victor Thomsom",address2,"07347583840", "victorthomson@gmail.com" ));
    }



    @Test
    void findAllUsers() {
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        List<User> userList1= userService.findAllUsers();
        assertEquals(userList1.size(), userList.size());
    }

    @Test
    void findUserById() {
        String expectedUserName = "Tolu Adetomiwa";
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(userList.get(0)));
        User  actualUser = userService.findUserById(1L);
        assertEquals(expectedUserName, actualUser.getName());

    }

//   @Test
//    void save() {
//        AddressDTO addressDTO1=  new AddressDTO("34 Mascara street", "Nottingham", "NG4 8BE","UK")
//
//        UserRequestDTO expectedUser5= new UserRequestDTO("Dara Gbenle", addressDTO1,"07852946702","daragbenle@gmail.com");
//
//        User expectedUser= new User();
//        expectedUser.setName(expectedUser5.getName());
//        expectedUser.setPhoneNumber(expectedUser5.getPhoneNumber());
//        expectedUser.setAddress(expectedUser5.getAddress());
//        expectedUser.setEmail(expectedUser5.getEmail());
//
//        Mockito.when(userRepository.save(expectedUser5)).thenReturn(expectedUser5);
//        User actualSavedUser3= userService.save(expectedUser5);
//        assertEquals(expectedUser5.getName(), actualSavedUser3.getName());
//
//
//
//    }

    @Test
    void deleteUser() {
        int expectedUserListSize = 2;
        User user1= userList.get(2);
        when(userRepository.findById(user1.getId())).thenReturn(Optional.of(user1));
        doNothing().when(userRepository).deleteById(user1.getId());
        userList.remove(user1);
        userService.deleteUser(user1.getId());

        int actualUserList = userList.size();

        assertEquals(expectedUserListSize, actualUserList);

    }

    @Test
    void searchByName() {

        String userName= "Tise Oludayomi";
       // Genre genre = Genre.valueOf(genreString);
        List<User> expectedName= userList.stream()
                .filter(user -> "Tise".equals(user.getName()))
                .collect(Collectors.toList());


        when(userRepository.searchByName(userName)).thenReturn( expectedName );
        List<User> actualName= userService.searchByName(userName);
        assertEquals(expectedName, actualName);
    }


    @Test
    void updateUser() {
        User user1= userList.get(1);
        String emailAddress = "tiseoluwaoludayomi@gmail.com";
        user1.setEmail(emailAddress);
        when(userRepository.save(user1)).thenReturn(user1 );
        User actualUser = userService.updateUser(user1);
        assertEquals(user1.getEmail(), actualUser.getEmail());
    }
}