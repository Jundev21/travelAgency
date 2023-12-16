package com.travelAgency.travelAgency.domain.user.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.travelAgency.travelAgency.domain.reservation.entity.Reservations;
import com.travelAgency.travelAgency.domain.review.entity.Reviews;
import com.travelAgency.travelAgency.domain.wishList.entity.WishLists;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String name;
    private int age;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreatedDate
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Reservations> reservations;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<WishLists> wishListsList;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Reviews> reviewsList;

    public Users(String name, int age, String email, String password, Role role){
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
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
