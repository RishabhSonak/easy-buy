package com.easybuy.user_details_service.controller;


import com.easybuy.user_details_service.dto.Authorities;
import com.easybuy.user_details_service.dto.UserDTO;
import com.easybuy.user_details_service.repository.UserRepository;
import com.easybuy.user_details_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/easybuy/users/")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getauser")
    public UserDTO userDTO(){
        UserDTO userDTO=new UserDTO();
        userDTO.setUsername("ramram");
        userDTO.setPassword("ramram");
        userDTO.setAuthorities(Authorities.USER.toString());
        return userDTO;
    }

    @PostMapping("sign_up")
    public UserDTO saveUserDetails(@RequestBody UserDTO userDTO) throws Exception {
        if(!userRepository.getUserByUsername(userDTO.getUsername()).isPresent()) {
            userDTO.setAccountNonLocked(true);
            userDTO.setAccountNonExpired(true);
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            System.out.println(userDTO.getAuthorities());
            return userService.saveUserDetail(userDTO);
        }
        else
            throw new Exception("Username already exists, try again using another username");
    }
    @GetMapping("getUserDetails/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        UserDTO userDTO=userService.getUserByUsername(username);
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userDTO;
    }

}
