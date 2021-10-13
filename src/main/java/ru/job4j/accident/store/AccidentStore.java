package ru.job4j.accident.store;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

public interface AccidentStore {

    void create(Accident accident, String[] ids);

    void save(Accident accident, String[] ids);

    Accident findByIdAccident(int id);

    Collection<Accident> findAllAccidents();

    Collection<AccidentType> findAllTypes();

    Collection<Rule> findAllRules();
}
