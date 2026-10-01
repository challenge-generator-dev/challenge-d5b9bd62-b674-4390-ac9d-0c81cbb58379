package com.pragma.banca.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "holders")
public class Holder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @Column(name = "identification_number", unique = true, nullable = false)
    @NotBlank(message = "El número de identificación no puede estar vacío")
    private String identificationNumber;

    public Holder() {
    }

    public Holder(String name, String identificationNumber) {
        this.name = name;
        this.identificationNumber = identificationNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        if (identificationNumber == null || identificationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de identificación no puede estar vacío");
        }
        this.identificationNumber = identificationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holder holder = (Holder) o;
        return Objects.equals(id, holder.id) && Objects.equals(identificationNumber, holder.identificationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, identificationNumber);
    }

    @Override
    public String toString() {
        return "Holder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                '}';
    }
}