package io.swagger.api;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.swagger.model.Individual;
@Repository
public interface IndividualRepository extends JpaRepository<Individual, String> {

}
