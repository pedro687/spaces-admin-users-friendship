package com.spaces.user.svc.infrastructure.users.persistence;

import com.spaces.user.svc.domain.users.Gender;
import com.spaces.user.svc.domain.users.Tellphone;
import com.spaces.user.svc.domain.users.User;
import com.spaces.user.svc.domain.users.UserID;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Table(name = "tb_users_data")
@Entity
public class UserEntityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "birthday_date")
    private LocalDate birthdayDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // Pode ser EnumType.ORDINAL para usar a posição numérica do enum
    private Gender gender;

    @Column(nullable = false)
    private String tellphone;

    @Column(nullable = false)
    private boolean active;

    @Column(name = "created_at", columnDefinition = "DATETIME(6)")
    private Instant createdAt;

    @Column(name = "updated_at", columnDefinition = "DATETIME(6)")
    private Instant updatedAt;

    @Column(name = "deleted_at", columnDefinition = "DATETIME(6)")
    private Instant deletedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Instant.now();
    }

    public UserEntityJpa() {
    }

    private UserEntityJpa(String uuid, String username, String password, String email, LocalDate birthdayDate, Gender gender, String tellphone,
                          boolean active) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthdayDate = birthdayDate;
        this.gender = gender;
        this.tellphone = tellphone;
        this.active = active;
    }

    public static UserEntityJpa from(final User user) {
        return new UserEntityJpa(
                user.getIdentifier().getValue(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getBirthdayDate(),
                user.getGender(),
                user.getTellphone().toString(),
                user.isActive()
        );
    }

    public User toAggregate() {
        return User.from(
                UserID.from(uuid),
                username,
                email,
                birthdayDate,
                gender,
                Tellphone.fromString(tellphone)
        );
    }

    public void delete() {
        this.deletedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getTellphone() {
        return tellphone;
    }

    public void setTellphone(String tellphone) {
        this.tellphone = tellphone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
