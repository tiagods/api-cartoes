package com.tiagods.cartoes.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum OperationEnum {
    COMPRA_A_VISTA(1L),COMPRA_PARCELADA(2L),SAQUE(3L),PAGAMENTO(4L);

    private Long value;

    OperationEnum(Long value){
        this.value=value;
    }

    public Long getValue() {
        return value;
    }

    public static Optional<OperationEnum> getInstance(Long operacao) {
        return List.of(OperationEnum.values())
                .stream()
                .filter(c -> c.getValue().equals(operacao))
                .findFirst();
    }
}
