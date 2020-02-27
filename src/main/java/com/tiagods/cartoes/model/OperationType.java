package com.tiagods.cartoes.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "operation_type")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "operationTypeId")
public class OperationType {
    @ApiModelProperty(value = "Codigo da operação")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Operation_Type_ID")
    private Long operationTypeId;
    @ApiModelProperty(value = "Descricao da operação")
    @Column(name = "Description0")
    private String description0;

}
