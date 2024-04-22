package com.logicea.card.controller;


import com.logicea.card.model.dto.AuthRequestDto;
import com.logicea.card.model.dto.UserRequestDto;
import com.logicea.card.model.dto.UserResponseDto;
import com.logicea.card.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class UserController {

  private final UserServiceImpl userService;

  @PostMapping("/users")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequestDto userRequestDto) {

    var userResponseDto = userService.addNewUser(userRequestDto);
    return ResponseEntity.ok(userResponseDto);
  }

  @PostMapping("/sign-in")
  public ResponseEntity<?> generateToken(@Valid @RequestBody AuthRequestDto authRequestDto) {

    var userResponseDto = userService.generateToken(authRequestDto);
    return ResponseEntity.ok(userResponseDto);
  }

}
