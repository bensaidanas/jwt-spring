package com.example.jwt.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.jwt.user.Permission.*;

@RequiredArgsConstructor
public enum Role {

    ROLE_ADMIN,
    ROLE_USER

//    USER(Collections.emptySet()),
//    ADMIN(
//            Set.of(
//                    ADMIN_READ,
//                    ADMIN_UPDATE,
//                    ADMIN_CREATE,
//                    ADMIN_DELETE
//            )
//    ),
//
//    ;
//
//    @Getter
//    private final Set<Permission> permissions;
//
//    public List<SimpleGrantedAuthority> getAuthorities() {
//        var authorities = getPermissions()
//                .stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toList());
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return authorities;
//    }



}
