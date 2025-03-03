package com.hrproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthUpdateRequestDto {
    private Long id;
    private String username;
    private String email;
    private String password;
}
