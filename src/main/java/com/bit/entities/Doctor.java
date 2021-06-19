package com.bit.entities;

import com.bit.enums.DoctorType;
import com.bit.enums.Gender;
import com.bit.models.AuditModel;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "doctors")
public class Doctor extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phone;

    @Enumerated(EnumType.STRING)
    private DoctorType type;

    @Column(name = "user_name")
    private String userName;

    private String password;
}