package com.xticket.dto.authdto.request;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequest_ManagerDto {
    @NotBlank(message = "Email adı boş geçilemez!!!")
    @Email
    @UniqueElements
    @Column(unique = true)
    private String email;
    @NotBlank(message = "Kullanıcı adı boş geçilemez!!!")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "Şifre boş geçilemez!!!")
    @Size(min = 8,max=32, message = "Şifre uzunlugu en az 8 karakter en fazla 32 karakter olabilir !!!")
    private String password;
    @NotBlank(message = "Şifre boş geçilemez!!!")
    private String rePassword;
    private String taxNumber;
    private String companyName;


}