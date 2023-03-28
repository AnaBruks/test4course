package com.manukhina.test4course.repositories;

import com.manukhina.test4course.entity.Equation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquationRepository extends JpaRepository<Equation, Long> {

    List<Equation> findByRootsIn(List<Double> roots);
    //  знайти  всі  рівняння,  що  мають  один  із
    //  зазначених коренів
    List<Equation> findByRootsContaining(double root);

    List<Equation> findByRootsSize(int size);
    // знайти всі рівняння, які мають рівно size корней

}