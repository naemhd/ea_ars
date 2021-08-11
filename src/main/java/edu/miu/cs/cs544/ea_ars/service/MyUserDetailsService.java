package edu.miu.cs.cs544.ea_ars.service;

import edu.miu.cs.cs544.ea_ars.domain.User;
import edu.miu.cs.cs544.ea_ars.domain.UserPrincipal;
import edu.miu.cs.cs544.ea_ars.repository.UserRepository;
import edu.miu.cs.cs544.ea_ars.utils.ResponseMessage;
import edu.miu.cs.cs544.ea_ars.utils.jwt.JwtResponse;
import edu.miu.cs.cs544.ea_ars.utils.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        UserDetails userDetails;
        if (user == null) {
            userDetails = null;
        } else {
            userDetails = new UserPrincipal(userRepository.findUserByUsername(username));
        }
        return userDetails;
    }

    public Object saveUser(User user) {
//        Encrypt the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

//        Check if the credentials exist
        if (loadUserByUsername(user.getUsername()) != null) {
            return new ResponseMessage("Credentials exist!");
        } else {
            userRepository.save(user);
        }
        return new ResponseMessage("Successfully saved!");
    }

    public JwtResponse login(String username, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtTocken = jwtUtils.generateJwtToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        return new JwtResponse(jwtTocken,userPrincipal.getUsername(),userPrincipal.getAuthorities());
    }
}
