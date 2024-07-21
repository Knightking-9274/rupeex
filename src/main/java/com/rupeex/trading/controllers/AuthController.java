package com.rupeex.trading.controllers;

import com.rupeex.trading.config.JwtProvider;
import com.rupeex.trading.entities.User;
import com.rupeex.trading.repositories.UserRepository;
import com.rupeex.trading.responses.AuthResponse;
import com.rupeex.trading.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@RequestBody User user)throws Exception{

        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if(isEmailExist!=null){
            throw new Exception("Email already registered!");
        }
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFullName(user.getFullName());
        User savedUser = userRepository.save(newUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = JwtProvider.generateToken(auth);
        AuthResponse res= new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("registered sucessfully!");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User user)throws Exception{

        String username = user.getEmail();
        String password = user.getPassword();

        Authentication auth = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = JwtProvider.generateToken(auth);
        AuthResponse res= new AuthResponse();
        res.setJwt(jwt);
        res.setStatus(true);
        res.setMessage("login sucessfull!");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
       UserDetails userDetails = customUserDetailService.loadUserByUsername(username);
       if(userDetails==null){
           throw new BadCredentialsException("invalid username");
       }
       if(!password.equals(userDetails.getPassword())){
           throw new BadCredentialsException("invalid password");
       }
       return new UsernamePasswordAuthenticationToken(username,password,userDetails.getAuthorities());

    }

}
