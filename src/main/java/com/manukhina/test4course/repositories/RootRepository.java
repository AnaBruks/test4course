package com.manukhina.test4course.repositories;

import com.manukhina.test4course.entity.Root;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RootRepository extends JpaRepository<Root, Long> {

    ArrayList<Root> findByValue(Double value);

    ArrayList<Root> findByEquationId(Long equationId);

    ArrayList<Root> findByEquationIdAndValueIn(Long equationId, List<Double> values);

    long countByEquationId(Long equationId);
}

