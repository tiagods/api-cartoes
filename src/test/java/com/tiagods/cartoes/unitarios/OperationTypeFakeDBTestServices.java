package com.tiagods.cartoes.unitarios;

import com.tiagods.cartoes.CartoesApplication;
import com.tiagods.cartoes.config.H2ConfigTest;
import com.tiagods.cartoes.service.OperationTypeService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {CartoesApplication.class,H2ConfigTest.class})
public class OperationTypeFakeDBTestServices {

    @Autowired
    OperationTypeService operationTypeService;

    @Test
    public void testeQualquer() {
        System.out.println("heloo");
        Assertions.assertNotNull(operationTypeService);
        Assertions.assertEquals(1,1);
    }

}
