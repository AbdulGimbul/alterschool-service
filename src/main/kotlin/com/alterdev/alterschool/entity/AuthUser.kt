package com.alterdev.alterschool.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

@Entity
@Table(name = "auth_user")
data class AuthUser(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_unique")
    val id: Int,
    @Column(name = "username")
    val _username: String,
    @Column(name = "password")
    val _password: String,
    @ManyToOne(cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    val role: AuthUserRole,
    @Column(name = "last_login")
    val lastLogin: Date? = null,
    @Column(name = "created_at")
    val createdAt: Date = Date(),
    @Column(name = "modified_at")
    val updatedAt: Date? = null
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role.role))
    }

    override fun getPassword(): String {
        return _password
    }

    override fun getUsername(): String {
        return _username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
