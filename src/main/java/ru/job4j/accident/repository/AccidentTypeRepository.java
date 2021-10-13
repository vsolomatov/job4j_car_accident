package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

public interface AccidentTypeRepository extends CrudRepository<AccidentType, Integer> {
    //А где код? Его нет.
    // Spring Data создает объект из интерфейса AccidentTypeRepository за нас.
    // В нем уже доступны методы создания, редактирования и удаления.
}
