package com.pbl5.PBL5_Elearning.controller;

import com.pbl5.PBL5_Elearning.entity.Users;
import com.pbl5.PBL5_Elearning.helper.JwtProvider;
import com.pbl5.PBL5_Elearning.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me")
public class RememberAccessController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserServiceImp userServiceImp;

    @PostMapping("")
    @CrossOrigin
    public ResponseEntity<?> rememberTokenCheck(@RequestBody Access access){
        if(jwtProvider.validationToken(access.getAccess_token())){
            String username = jwtProvider.decodeToken(access.getAccess_token());
            Users users = userServiceImp.findUserByUsername(username);
            return new ResponseEntity<Users>(users, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Not found", HttpStatus.BAD_REQUEST);
        }
    }

    private static class Access{
        private String access_token;

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }
    }
}
