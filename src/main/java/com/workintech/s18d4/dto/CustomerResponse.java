package com.workintech.s18d4.dto;

public record CustomerResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        Double salary,
        Long addressId
) {}
