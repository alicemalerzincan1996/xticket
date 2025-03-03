package com.hrproject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterResponseDto {
    private String token;
    private Long id;
    private String activationCode;
    private String username;
}
