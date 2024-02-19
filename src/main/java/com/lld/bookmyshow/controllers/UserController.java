package com.lld.bookmyshow.controllers;

import com.lld.bookmyshow.dtos.ResponseStatus;
import com.lld.bookmyshow.dtos.SignUpRequestDto;
import com.lld.bookmyshow.dtos.SignUpResponseDto;
import com.lld.bookmyshow.exceptions.PasswordNotMatchException;
import com.lld.bookmyshow.models.User;
import com.lld.bookmyshow.services.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    UserController(UserService userService)
    {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto requestDto)
    {
        SignUpResponseDto responseDto = new SignUpResponseDto();

        try
        {
            User user = userService.singUp(requestDto.getEmail(), requestDto.getPassword());
            responseDto.setUserId(user.getId());
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (PasswordNotMatchException e) {
            responseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDto;
    }
}
