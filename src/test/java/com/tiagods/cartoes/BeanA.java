package com.tiagods.cartoes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeanA {
    private Long id;
    private BeanB bean;
}
