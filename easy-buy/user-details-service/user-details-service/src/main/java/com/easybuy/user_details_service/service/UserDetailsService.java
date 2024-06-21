package com.easybuy.user_details_service.service;

import com.easybuy.user_details_service.dto.UserDTO;
import com.easybuy.user_details_service.repository.UserRepository;
import com.easybuy.user_details_service.utility.DTOEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        return DTOEntityMapper.entityToDTO(userRepository.getUserByUsername(username).get());
    }
}
