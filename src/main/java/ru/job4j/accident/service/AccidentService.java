package ru.job4j.accident.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.store.AccidentStore;

import java.util.Collection;

@Service
public class AccidentService {

    @Autowired
    @Qualifier("accidentSpringData")
    private AccidentStore accidentStore;

    public void save(Accident accident, String[] ids) {
        if (accident.getId()!=0) {
            accidentStore.save(accident, ids);
        } else {
            accidentStore.create(accident, ids);
        }
    }

    public Collection<Accident> findAllAccidents() {
        return accidentStore.findAllAccidents();
    }

    public Accident findByIdAccident(int id) {
        return accidentStore.findByIdAccident(id);
    }

    public Collection<AccidentType> findAllTypes() {
        return accidentStore.findAllTypes();
    }

    public Collection<Rule> findAllRules() {
        return accidentStore.findAllRules();
    }
}
