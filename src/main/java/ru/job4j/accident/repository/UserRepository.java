package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("from User u where u.username=?1")
    Optional<User> findByName(String name);

}