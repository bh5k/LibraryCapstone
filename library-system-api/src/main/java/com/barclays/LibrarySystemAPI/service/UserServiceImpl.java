package com.barclays.LibrarySystemAPI.service;


import com.barclays.LibrarySystemAPI.dto.UserRequestDTO;
import com.barclays.LibrarySystemAPI.exception.IdNotFoundException;
import com.barclays.LibrarySystemAPI.model.Address;
import com.barclays.LibrarySystemAPI.model.User;
import com.barclays.LibrarySystemAPI.repository.AddressRepository;
import com.barclays.LibrarySystemAPI.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private AddressRepository addressRepository;


   @Override
   public List<User> findAllUsers(){
      List<User> users = new ArrayList<>();
      userRepository.findAll().forEach(users ::add);
      return users;
   }

   @Override
   public User findUserById(Long id){
      Optional<User> user= userRepository.findById(id);
      return user.orElseThrow(()->new IdNotFoundException("Id not found "));
   }

   @Override
   public User save(UserRequestDTO userDTO){
       Address address= new Address();
       address.setLineOne(userDTO.getAddress().getLineOne());
       address.setCity(userDTO.getAddress().getCity());
       address.setCountry(userDTO.getAddress().getCountry());
       address.setPostCode(userDTO.getAddress().getPostCode());

       Address savedAddress = addressRepository.save(address);

       User user = new User();
       user.setName(userDTO.getName());
       user.setAddress(savedAddress);
       user.setEmail(userDTO.getEmail());
       user.setPhoneNumber(userDTO.getPhoneNumber());

       return userRepository.save(user);
   }

   @Override
   public void deleteUser(Long id){

     User user = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id not found"));
       userRepository.deleteById(user.getId());

   }


   @Override
   public List<User> searchByName(String name){
      return userRepository.searchByName(name);
   }



   @Override
   public User updateUser(User user){

       return userRepository.save(user);
   }


   @Autowired
    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }
}
