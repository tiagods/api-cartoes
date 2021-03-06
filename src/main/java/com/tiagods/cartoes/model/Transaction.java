package com.tiagods.cartoes.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tiagods.cartoes.dto.TransactionDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Objects;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "transactionId")
public class Transaction {
    @ApiModelProperty(value = "Codigo da transação")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Transaction_ID")
    private Long transactionId;

    @ApiModelProperty(value = "Conta")
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Account_ID")
    private Account account;

    @ApiModelProperty(value = "Operação")
    @ManyToOne
    @JoinColumn(name = "operation_type_id")
    private OperationType operationType;

    @ApiModelProperty(value = "Valor da transação")
    @Column(name = "Amount")
    @JsonFormat(shape=JsonFormat.Shape.NUMBER_FLOAT)
    private BigDecimal amount;

    @ApiModelProperty(value = "Data da transação dd/MM/yyyy HH:mm:ss")
    @Column(name = "Event_Date")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Calendar eventDate;

    public Transaction(Account account, OperationType operationType, BigDecimal amount, Calendar eventDate){
        this.account=account;
        this.operationType=operationType;
        this.amount=amount;
        this.eventDate=eventDate;
    }

    public Transaction(TransactionDTO transactionDto) {
        this.amount=transactionDto.getAmount();
        this.eventDate=transactionDto.getEventDate();
    }
}
