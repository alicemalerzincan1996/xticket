package com.xticket.dto.response;

import com.hrproject.repository.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileFindAllResponseDto {

    private String id;

    // private Long userProfileId;

    private Long authId;

    private String username;

    private String email;

    private String phone;

    private String address;

    private String avatar;

    private String about;

    private String name;

    private String surName;

    private LocalDate birthDate;

    private EStatus status;
}