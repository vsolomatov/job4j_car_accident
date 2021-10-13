package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query(value = "SELECT distinct a FROM Accident a LEFT JOIN FETCH a.rules ORDER BY a.id")
    Iterable<Accident> findAll();

    @Query(value="SELECT distinct a FROM Accident a LEFT JOIN FETCH a.rules WHERE a.id = ?1")
    Accident findOne(int id);

}
