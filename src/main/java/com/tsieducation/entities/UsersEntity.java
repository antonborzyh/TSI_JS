package com.tsieducation.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "mydb")
@NamedQuery(name = "UsersEntity.findAll", query = "SELECT s FROM UsersEntity s")
@NamedQuery(name = "UsersEntity.findByUserName", query = "SELECT s FROM UsersEntity s WHERE LOWER(s.userName) = :userName")
@NamedQuery(name = "UsersEntity.findByFirstNameLastNameAndDoB",
        query = "SELECT s FROM UsersEntity s "
                + "WHERE LOWER(s.firstName) = :firstName "
                + "AND LOWER(s.lastName) = :lastName "
                + "AND DATE(s.dateOfBirth) = :dateOfBirth")
public class UsersEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Integer userId;
    @Basic
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Basic
    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "userByUserId", cascade = CascadeType.MERGE)
    private Collection<TicketsEntity> ticketsById;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "Users_Roles",
            joinColumns = {@JoinColumn(name = "users_user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "roles_role_id", referencedColumnName = "role_id")}
    )
    private List<RolesEntity> roles = new ArrayList<>();

    public UsersEntity(String userName, String firstName, String lastName, Date dateOfBirth, String password,
                       List<RolesEntity> roles) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.roles = roles;
    }

}
