package com.tiagods.cartoes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "accountId")
public class Account {
    @ApiModelProperty(value = "Codigo da conta")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Account_ID")
    private Long accountId;
    @ApiModelProperty(value = "Numero da conta")
    @Column(name = "Document_Number")
    @NotNull(message = "Numero do Documento obrigatorio")
    private Long documentNumber;
    @JsonIgnore
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public Account(Long accountId, Long documentNumber){
        this.accountId=accountId;
        this.documentNumber=documentNumber;
    }

}
