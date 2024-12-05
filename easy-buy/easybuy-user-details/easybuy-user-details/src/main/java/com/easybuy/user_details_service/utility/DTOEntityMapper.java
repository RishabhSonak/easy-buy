//package com.easybuy.user_details_service.utility;
//
//
//import com.easybuy.user_details_service.entity.User;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class DTOEntityMapper {
//
//    public  User DTOtoEntity(UserDTO userDetails){
//        User user =new User();
//        user.setUsername(userDetails.getUsername());
//        user.setPassword(userDetails.getPassword());
//        user.setAuthorities(userDetails.getAuthoritiesString());
//        user.setAccountNonLocked(userDetails.isAccountNonLocked());
//        user.setAccountNonExpired(userDetails.isAccountNonExpired());
//        return user;
//    }
//    public  UserDTO entityToDTO(User user){
//        UserDTO userDetails =new UserDTO();
//        userDetails.setUsername(user.getUsername());
//        userDetails.setPassword(user.getPassword());
//        userDetails.setAccountNonLocked(user.getAccountNonLocked());
//        userDetails.setAccountNonExpired(user.getAccountNonExpired());
//        userDetails.setAuthorities(user.getAuthorities());
//        return userDetails;
//    }
//
//}
