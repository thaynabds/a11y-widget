package br.com.thaynabds.a11yapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(
    @NotBlank @Email String email,
    @NotBlank @Size(min = 6, max = 72) String password
) {}
