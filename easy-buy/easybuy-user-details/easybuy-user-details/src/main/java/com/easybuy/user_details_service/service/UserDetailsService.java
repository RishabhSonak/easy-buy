package com.easybuy.user_details_service.service;

import com.easybuy.user_details_service.dto.Request;
import com.easybuy.user_details_service.entity.User;
import com.easybuy.user_details_service.exception.EasyBuyUserDetailsServiceException;
import com.easybuy.user_details_service.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsService{
      private final UserRepository userRepository;
      private final Log logger = LogFactory.getLog(UserDetailsService.class);
      @Autowired
      public UserDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
      }

      public String saveUserDetail(Request request) {
        User user=new User();
        user.setUsername(request.getUsername());
        user.setEmailId(request.getEmailId());
        user.setAuthorities(request.getUserRole());
        user.setPassword(request.getPassword());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        userRepository.save(user);
        return "Account created Successfully";
    }
    public Boolean doesUserAlreadyExists(String username) throws UsernameNotFoundException {
          return userRepository.getUserByUsername(username).isPresent();
    }

      public User loadUserByUsername(String username) {
            return userRepository.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));

      }

      public void removeUser(String username, String emailId) throws EasyBuyUserDetailsServiceException {
          int val=userRepository.deleteUserByUsernameAndEmailId(username,emailId);
          if(val==1){
                logger.info("Account with username: "+username+" and emailId: "+ emailId+ "  deleted successfully");
          }
          else throw new EasyBuyUserDetailsServiceException("An Internal Server Error Occured while Deleting User Account");
    }

      public User updateUserDetails(String username,
                                    String password,
                                    String newUsername,
                                    String newPassword,
                                    String newEmail) throws EasyBuyUserDetailsServiceException {
            User existingUser = userRepository.getUserByUsername(username)
                     .orElseThrow(()->new EasyBuyUserDetailsServiceException("username: "+username+" not found"));


            if (existingUser == null) {
                  throw new EasyBuyUserDetailsServiceException("User not found");
            }

            if (newUsername!=null
                     && !newUsername.isEmpty()
                     && !newUsername.isBlank()
                     && !newUsername.equals(existingUser.getUsername())) {
                  existingUser.setUsername(newUsername);
            }
            if (newPassword!=null
                     && !newPassword.isEmpty()
                     && !newPassword.isBlank()
                     && !newPassword.equals(existingUser.getPassword())) {
                  existingUser.setPassword(newPassword);
            }
            if (newEmail!=null
                     && !newEmail.isEmpty()
                     && !newEmail.isBlank()
                     && !newEmail.equals(existingUser.getEmailId())) {
                  existingUser.setEmailId(newEmail);
            }


            return userRepository.save(existingUser);

      }

}
