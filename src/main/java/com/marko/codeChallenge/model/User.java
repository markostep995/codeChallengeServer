package com.marko.codeChallenge.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "users",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"username"})
)
public class User extends AbstractDataModel implements UserDetails {

    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date lastLoginDate;
    @NotBlank(message = "USER_EMPTY_PASSWORD")
    private String password;
    private boolean isActive;
    @Transient
    private String confirmPassword;
    @NotBlank(message = "{notblank.userEmptyUsername}")
    private String username;
    private boolean isDeleted;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
