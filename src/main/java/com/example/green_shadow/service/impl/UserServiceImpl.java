package com.example.green_shadow.service.impl;

import com.example.green_shadow.dao.UserDAO;
import com.example.green_shadow.dto.impl.UserDTO;
import com.example.green_shadow.entity.Role;
import com.example.green_shadow.entity.impl.User;
import com.example.green_shadow.exception.InvalidUserRoleException;
import com.example.green_shadow.secure.JWTAuthResponse;
import com.example.green_shadow.service.UserService;
import com.example.green_shadow.util.Mapping;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private Mapping mapping;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    @Value("${administrator.clarificationCode}")
    private String adminClarificationCode;
    @Value("${manager.clarificationCode}")
    private String managerClarificationCode;
    @Value("${scientist.clarificationCode}")
    private String scientistClarificationCode;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    @Override
    public void register(UserDTO userDTO) {
        System.out.println("hello");
        Role role = userDTO.getRole();
        String roleCode = userDTO.getRoleClarificationCode();
        if ((role == Role.ADMINISTRATIVE && roleCode.equals(adminClarificationCode)) ||
                (role == Role.MANAGER && roleCode.equals(managerClarificationCode)) ||
                (role == Role.SCIENTIST && roleCode.equals(scientistClarificationCode))
        ) {
            userDTO.setPassword(encoder.encode(userDTO.getPassword()));
            userDAO.save(mapping.mapToUser(userDTO));
            log.info("User Registered :)" + userDTO.getEmail());
        } else {
            throw new InvalidUserRoleException("Invalid Role Or Clarification Code :(");
        }

    }

    @Override
    public JWTAuthResponse login(UserDTO userDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(), userDTO.getPassword()
                )
        );
        if (auth.isAuthenticated()) {
            log.info("User Login Successfully :) " + userDTO.getEmail());
            return JWTAuthResponse.builder()
                    .token(jwtService.generateToken(userDTO.getEmail()))
                    .build();
        }
        return null;
    }

    @Override
    public JWTAuthResponse refresh(String accessToken) {
        String userEmail = jwtService.extractUserEmail(accessToken);
        User fetchedUser = userDAO.findByEmail(userEmail);
        if(fetchedUser == null){
            throw new UsernameNotFoundException("User Not Found :(");
        }
        log.info("Token Refreshed "+userEmail);
        return  JWTAuthResponse.builder()
                .token(jwtService.generateToken(userEmail))
                .build();
    }

    @Override
    public void update(UserDTO userDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(), userDTO.getPassword()
                )
        );
        if(auth.isAuthenticated()){
            User fetchedToUser = userDAO.findByEmail(userDTO.getEmail());
            if(fetchedToUser == null){
                throw new UsernameNotFoundException("User not Found");
            }
            fetchedToUser.setPassword(encoder.encode(userDTO.getPassword()));
            userDAO.save(fetchedToUser);
        }
    }

    @Override
    public void delete(UserDTO userDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(), userDTO.getPassword()
                )
        );
        if(auth.isAuthenticated()){
            userDAO.deleteById(userDTO.getEmail());
            log.info("User Deleted :)" + userDTO.getEmail());
        }

    }

    @Override
    public void updatePassword(UserDTO userDTO, String newPassword) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(), userDTO.getPassword()
                )
        );
        if (auth.isAuthenticated()){
            User fetchedUser = userDAO.findByEmail(userDTO.getEmail());
            if(fetchedUser == null){
                throw new UsernameNotFoundException("User Not Found :(");
            }
            fetchedUser.setPassword(encoder.encode(newPassword));
            userDAO.save(fetchedUser);
        }
    }

    @Override
    public void updateUserRole(UserDTO userDTO) {
        Role role = userDTO.getRole();
        String roleCode = userDTO.getRoleClarificationCode();
        if ((role == Role.ADMINISTRATIVE && roleCode.equals(adminClarificationCode)) ||
                (role == Role.MANAGER && roleCode.equals(managerClarificationCode)) ||
                (role == Role.SCIENTIST && roleCode.equals(scientistClarificationCode))
        ) {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getEmail(), userDTO.getPassword()
                    )
            );
            if(auth.isAuthenticated()){
                User fetchedUser = userDAO.findByEmail(userDTO.getEmail());
                if(fetchedUser == null){
                    throw new UsernameNotFoundException("User Not Found :(");
                }
                fetchedUser.setRole(role);
                userDAO.save(fetchedUser);
            }
        }else{
            throw new InvalidUserRoleException("Invalid Role Or Clarification Code :(");
        }
    }

    @Override
    public String getUserRole(UserDTO userDTO) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(), userDTO.getPassword()
                )
        );
        if(auth.isAuthenticated()){
            User fetchedUser = userDAO.findByEmail(userDTO.getEmail());
            if(fetchedUser == null){
                throw new UsernameNotFoundException("User Not Found :(");
            }
            return fetchedUser.getRole().toString();
        }
        return null;
    }
}
