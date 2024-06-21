package com.easybuy.user_details_service.service;

import com.easybuy.user_details_service.dto.UserDTO;
import com.easybuy.user_details_service.repository.UserRepository;
import com.easybuy.user_details_service.utility.DTOEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DTOEntityMapper dtoEntityMapper;
    public UserDTO saveUserDetail(UserDTO userDTO) {
        UserDTO userDTO1=DTOEntityMapper.entityToDTO(userRepository.save(DTOEntityMapper.DTOtoEntity(userDTO)));
        System.out.println(userDTO1);
        return userDTO1;
    }
    public UserDTO getUserByUsername(String username) {
        return DTOEntityMapper.entityToDTO(userRepository.getUserByUsername(username).orElseThrow());
    }
}
