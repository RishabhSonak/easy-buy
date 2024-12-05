package com.easybuy.user_details_service.controller;

import com.easybuy.user_details_service.dto.Request;
import com.easybuy.user_details_service.entity.User;
import com.easybuy.user_details_service.exception.EasyBuyUserDetailsServiceException;
import com.easybuy.user_details_service.service.UserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserDetailsService userDetailsService;
    private final Log logger = LogFactory.getLog(UserController.class);
    private final RestTemplate restTemplate;

    @Value("${server.port}")
    private Integer port;
    @Autowired
    public UserController(UserDetailsService userDetailsService, RestTemplate restTemplate) {
        this.userDetailsService = userDetailsService;
        this.restTemplate=restTemplate;
    }

    @GetMapping("/getserver")
    public Integer getserverport(){
        return this.port;
    }
    @GetMapping("/validate")
    public ResponseEntity<?> validate(@RequestHeader(value = "Authorization") String bearer){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearer);
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = "http://easybuy-auth-service/auth/validate";
        ResponseEntity<String> response = restTemplate.exchange(
                 url,
                 HttpMethod.GET,
                 entity,
                 String.class
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/save")
    public String saveUserDetails(@RequestBody Request request) throws EasyBuyUserDetailsServiceException {
        return userDetailsService.saveUserDetail(request);
    }
    @GetMapping(value = "/user/{username}")
    public Boolean checkUserByUsername(@PathVariable(value="username") String username) throws UsernameNotFoundException {
        return userDetailsService.doesUserAlreadyExists(username);
    }

    @GetMapping(value = "/get_user/{username}")
    public User loadUserByUsername(@PathVariable(value="username") String username) throws UsernameNotFoundException {
        return userDetailsService.loadUserByUsername(username);
    }

    //Completed:  remove a user
    @DeleteMapping("/remove")
    public void removeUser(@RequestParam(value="username") String username,
                           @RequestParam(value="emailId") String emailId) throws EasyBuyUserDetailsServiceException {
        logger.info("request came for deletion of account for username : "+username+"and emailId: "+emailId);
        userDetailsService.removeUser(username,emailId);
    }
    // Completed: update user details
    @PatchMapping("/update")
    public User updateUserDetails(@RequestParam(value = "username",required = true) String username,
                                    @RequestParam(value = "password",required = true) String password,
                                    @RequestParam(value = "newUsername",required = false) String newUsername,
                                    @RequestParam(value = "newPassword",required = false) String newPassword,
                                    @RequestParam(value = "newEmail",required = false) String newEmail) throws EasyBuyUserDetailsServiceException {
        return userDetailsService.updateUserDetails(username,password,newUsername,newPassword,newEmail);
    }
//    @GetMapping("/forgot_password")
//    public String updateUserDetails(@RequestParam(value = "username",required = true) String username,
//                                    @RequestParam(value = "password",required = true) String password,
//                                    @RequestParam(value = "newUsername",required = false) String newUsername,
//                                    @RequestParam(value = "newPassword",required = false) String newPassword,
//                                    @RequestParam(value = "newEmail",required = false) String newEmail) throws Exception {
//        return userDetailsService.updateUserDetails(newUsername,newPassword,newEmail);
//    }

}
