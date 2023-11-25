package com.felipesouls.client.dto;

import com.felipesouls.client.entities.Client;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;

    @NotNull(message = "This value not be null")
    @NotBlank(message = "This value not be empty")
    private String name;

    @NotNull(message = "This value not be null")
    @NotBlank(message = "This value not be empty")
    @CPF()
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$",message = "The default value for cpf is XXX.XXX.XXX-XX")
    private String cpf;

    @Positive(message = "This has positive value")
    private Double income;

    @PastOrPresent(message = "Incorrect date, you need insert old date or present")
    private LocalDate birthDate;

    @Positive(message = "This value not has be negative.")
    private Integer children;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client entity) {
        BeanUtils.copyProperties(entity, this);
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
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getChildren() {
        return children;
    }

    public void setChildren(Integer children) {
        this.children = children;
    }
}
