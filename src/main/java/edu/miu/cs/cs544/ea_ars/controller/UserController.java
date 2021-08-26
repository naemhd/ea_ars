package edu.miu.cs.cs544.ea_ars.controller;

import edu.miu.cs.cs544.ea_ars.domain.User;
import edu.miu.cs.cs544.ea_ars.domain.UserPrincipal;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.UserDTO;
import edu.miu.cs.cs544.ea_ars.service.MyUserDetailsService;
import edu.miu.cs.cs544.ea_ars.utils.ResponseMessage;
import edu.miu.cs.cs544.ea_ars.utils.jwt.JwtResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user, BindingResult result) {
        if (!result.hasErrors()) {
            myUserDetailsService.saveUser(user);
            return ResponseEntity.ok().body(user);
        }
        else{
            return ResponseEntity.badRequest()
                    .body(result.getAllErrors());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody User user, BindingResult result) {
        System.out.println(user.getUsername());
        if(!result.hasErrors()){
           JwtResponse response = myUserDetailsService.login(user.getUsername(),user.getPassword());
//            ResponseEntity.ok().header("Authorization",response.getAccessToken());
           return ResponseEntity.ok(response); //).header("Authorization",response.getAccessToken()).body(response);
        }
        return ResponseEntity.internalServerError().body(result.getAllErrors());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping
    public ResponseEntity<?> deactivate(@Valid @RequestBody String disable,
                                        @PathVariable String username,
                                        BindingResult result){
//        Check user if exists
       UserDetails user = myUserDetailsService.loadUserByUsername(username);
       if(user!=null){
           myUserDetailsService.deactivateUser(user);
           return ResponseEntity.ok().body(myUserDetailsService.loadUserByUsername(username));
       }
       return ResponseEntity.badRequest().body(result.getAllErrors());
    }
}
