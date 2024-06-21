package com.easybuy.user_details_service.entity;

import jakarta.persistence.*;
@Entity
@Table(name="users")
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "username")
        private  String username;
        @Column(name = "password")
        private  String password;
        @Column(name = "authorities")
        private  String authorities;
        @Column(name = "is_acount_non_expired")
        private  Boolean isAccountNonExpired;
        @Column(name = "is_acount_non_locked")
        private  Boolean isAccountNonLocked;

        public User() {
        }

        public User(Integer id, String username, String password, String authorities, Boolean isAccountNonExpired, Boolean isAccountNonLocked) {
                this.id = id;
                this.username = username;
                this.password = password;
                this.authorities = authorities;
                this.isAccountNonExpired = isAccountNonExpired;
                this.isAccountNonLocked = isAccountNonLocked;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getAuthorities() {
                return authorities;
        }

        public void setAuthorities(String authorities) {
                this.authorities = authorities;
        }

        public Boolean getAccountNonExpired() {
                return isAccountNonExpired;
        }

        public void setAccountNonExpired(Boolean accountNonExpired) {
                isAccountNonExpired = accountNonExpired;
        }

        public Boolean getAccountNonLocked() {
                return isAccountNonLocked;
        }

        public void setAccountNonLocked(Boolean accountNonLocked) {
                isAccountNonLocked = accountNonLocked;
        }

        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", authorities='" + authorities + '\'' +
                        ", isAccountNonExpired=" + isAccountNonExpired +
                        ", isAccountNonLocked=" + isAccountNonLocked +
                        '}';
        }
}
