package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
    //А где код? Его нет.
    // Spring Data создает объект из интерфейса RuleRepository за нас.
    // В нем уже доступны методы создания, редактирования и удаления.
}
