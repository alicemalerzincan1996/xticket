package com.xticket.dto.authdto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RequestUpdateemployee {
    private String email;
    private String phone;
    private String surName;
    private String name;
}
