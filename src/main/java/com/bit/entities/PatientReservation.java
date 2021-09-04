package com.bit.entities;

import com.bit.enums.Gender;
import com.bit.models.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
@Table(name = "patients_reservations")
public class PatientReservation extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    private String email;

    private String phone;

    @Lob
    private String feeling;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "receptionist_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Receptionist receptionist;
}