package com.telerik.extension_repository.entities;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {
    private Long id;

    private String authority;

    private Set<User> users;

    Authority() {
        this.users = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    public Long getId() {
        return id;
    }

//    @Transient
//    public String getSimpleName(){
//        return StringUtils.capitalize(this.getName().substring(5).toLowerCase());
//    }
    @Override
    public String getAuthority() {
        return authority;
    }

    @ManyToMany(mappedBy = "authorities", cascade = CascadeType.ALL)
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
