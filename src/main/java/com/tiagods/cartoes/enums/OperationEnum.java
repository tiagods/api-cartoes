package com.tiagods.cartoes.enums;

public enum OperationEnum {
    COMPRA_A_VISTA(1L),COMPRA_PARCELADA(2L),SAQUE(3L),PAGAMENTO(4L);

    private Long value;

    OperationEnum(Long value){
        this.value=value;
    }

    public Long getValue() {
        return value;
    }
}
