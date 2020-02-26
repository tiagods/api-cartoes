package com.tiagods.cartoes.repository;

import com.tiagods.cartoes.model.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;

@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType,Long> {
}
