package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/*
    Spring Data - это отдельный модуль Spring, который максимально упрощает работу с хранилищами
 */
@Repository
public class AccidentSpringData implements AccidentStore {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentSpringData(AccidentRepository accidentRepository
            , AccidentTypeRepository accidentTypeRepository
            , RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    @Override
    public void create(Accident accident, String[] ids) {
        AccidentType accidentType = findByIdAccidentType(accident.getType().getId());
        accident.setType(accidentType);
        updateRules(accident, ids);
        accidentRepository.save(accident);
    }

    @Override
    public void save(Accident accident, String[] ids) {
        AccidentType accidentType = findByIdAccidentType(accident.getType().getId());
        accident.setType(accidentType);
        updateRules(accident, ids);
        accidentRepository.save(accident);
    }

    @Override
    public Accident findByIdAccident(int id) {
        return accidentRepository.findOne(id);
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        Collection<Accident> accidents = new ArrayList<>();
        accidentRepository.findAll().forEach(accidents::add);
        return accidents;
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        Collection<AccidentType> accidentTypes = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(accidentTypes::add);
        return accidentTypes;
    }

    @Override
    public Collection<Rule> findAllRules() {
        Collection<Rule> rules = new ArrayList<>();
        ruleRepository.findAll().forEach(rules::add);
        return rules;
    }

    private AccidentType findByIdAccidentType(int id) {
        return accidentTypeRepository.findById(id).get();
    }

    private Rule findByIdRule(int id) {
        return ruleRepository.findById(id).get();
    }

    private void updateRules(Accident accident, String[] ids) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String id : ids) {
            ruleSet.add(findByIdRule(Integer.parseInt(id)));
        }
        accident.setRules(ruleSet);
    }
}
