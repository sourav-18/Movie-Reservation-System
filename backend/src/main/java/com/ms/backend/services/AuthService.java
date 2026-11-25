package com.ms.backend.services;

import com.ms.backend.dtos.request.AdminLoginDto;
import com.ms.backend.dtos.request.TheaterAdminLoginDto;
import com.ms.backend.dtos.response.AdminDto;
import com.ms.backend.dtos.response.TheaterAdminDto;
import com.ms.backend.entities.Admin;
import com.ms.backend.entities.TheaterAdmin;
import com.ms.backend.entities.types.AdminStatus;
import com.ms.backend.entities.types.TheaterAdminStatus;
import com.ms.backend.exceptions.DataNotFoundException;
import com.ms.backend.exceptions.InActiveStatusException;
import com.ms.backend.mapper.AuthMapper;
import com.ms.backend.repositories.AdminRepository;
import com.ms.backend.repositories.TheaterAdminRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AdminRepository adminRepository;
    private final TheaterAdminRepository theaterAdminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthMapper authMapper;


    public AdminDto adminLogin(AdminLoginDto body) {
        Admin admin = adminRepository.findByEmail(body.getEmail()).orElse(null);
        if (admin == null || !passwordEncoder.matches(body.getPassword(), admin.getPassword())) {
            throw new DataNotFoundException("Admin not found");
        }
        if (admin.getStatus() != AdminStatus.ACTIVE) {
            throw new InActiveStatusException("Your account is inactive. Please contact support.", HttpStatus.FORBIDDEN);
        }

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("auth-id", admin.getId());
        claims.put("Role", "admin");
        String token = jwtService.generateToken(admin.getId().toString(), claims);
        AdminDto adminDto = authMapper.toDto(admin);
        adminDto.setToken(token);
        return adminDto;
    }

    public TheaterAdminDto theaterAdminLogin(TheaterAdminLoginDto body) {
        TheaterAdmin theaterAdmin = theaterAdminRepository.findByEmail(body.getEmail()).orElse(null);
        if (theaterAdmin == null || !passwordEncoder.matches(body.getPassword(), theaterAdmin.getPassword())) {
            throw new DataNotFoundException("Admin not found");
        }
        if (theaterAdmin.getStatus() != TheaterAdminStatus.ACTIVE) {
            throw new InActiveStatusException("Your account is inactive. Please contact support.", HttpStatus.FORBIDDEN);
        }
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("theater-id", theaterAdmin.getTheater().getId());
        claims.put("auth-id", theaterAdmin.getId());
        claims.put("Role", "theater-admin");
        String token = jwtService.generateToken(theaterAdmin.getId().toString(), claims);
        TheaterAdminDto theaterAdminDto = authMapper.toDto(theaterAdmin);
        theaterAdminDto.setToken(token);
        return theaterAdminDto;
    }


}
