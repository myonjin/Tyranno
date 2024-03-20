package com.tyranno.ssg.users.presentation;

import com.tyranno.ssg.security.JwtTokenProvider;
import com.tyranno.ssg.users.application.UsersService;
import com.tyranno.ssg.users.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.constant.Constable;

//new ResponseEntity<>(message, headers, HttpStatus.OK);
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UsersController {

    private final UsersService usersService;
    private final JwtTokenProvider jwtTokenProvider;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
<<<<<<< Updated upstream
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpDto) {

        usersService.createUsers(signUpDto);

        return new ResponseEntity<>("메세지",HttpStatus.OK);
    }
=======
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
//    @PostMapping
//    public ResponseEntity<> signUp(@RequestBody SignUpDto signUpDto) {
//        usersService.createUsers(signUpDto);
//
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes


}
