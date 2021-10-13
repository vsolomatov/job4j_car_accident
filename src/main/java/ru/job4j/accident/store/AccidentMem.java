package ru.job4j.accident.store;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentStore {

    private final HashMap<Integer, Accident> accidents = new HashMap<>();
    private final HashMap<Integer, AccidentType> types = new HashMap<>();
    private final HashMap<Integer, Rule> rules = new HashMap<>();

    private final AtomicInteger atomicInteger = new AtomicInteger(2);

    private AccidentMem() {
        AccidentType type1 = AccidentType.of(0, "Две машины");
        types.put(0, type1);
        AccidentType type2 = AccidentType.of(1, "Машина и человек");
        types.put(1, type2);
        AccidentType type3 = AccidentType.of(2, "Машина и велосипед");
        types.put(2, type3);

        Rule rule1 = Rule.of(0, "Статья. 1");
        rules.put(0, rule1);
        Rule rule2 = Rule.of(1, "Статья. 2");
        rules.put(1, rule2);
        Rule rule3 = Rule.of(2, "Статья. 3");
        rules.put(2, rule3);

        Set<Rule> ruleHashSet = new HashSet<>();
        ruleHashSet.add(rule1);
        Accident accident1 = new Accident(1, "Правонарушение 1", "Описание 1", "Адрес 1");
        accident1.setType(type1);
        accident1.setRules(ruleHashSet);
        accidents.put(1, accident1);

        Set<Rule> ruleHashSet1 = new HashSet<>();
        ruleHashSet1.add(rule1);
        ruleHashSet1.add(rule2);
        Accident accident2 = new Accident(2, "Правонарушение 2", "Описание 2", "Адрес 2");
        accident2.setType(type2);
        accident2.setRules(ruleHashSet1);
        accidents.put(2, accident2);
    }

    public void create(Accident accident, String[] ids) {
        int next = atomicInteger.addAndGet(1);
        accident.setId(next);
        AccidentType type = types.get(accident.getType().getId());
        accident.setType(type);
        changeRules(accident, ids);
        accidents.put(next, accident);
    }

    public void save(Accident accident, String[] ids) {
        AccidentType type = types.get(accident.getType().getId());
        accident.setType(type);
        changeRules(accident, ids);
        accidents.put(accident.getId(), accident);
    }

    private void changeRules(Accident accident, String[] ids) {
        Set<Rule> rules = new HashSet<>();
        for (String id : ids) {
            rules.add(this.rules.get(Integer.parseInt(id)));
        }
        accident.setRules(rules);
    }

    public Accident findByIdAccident(int id) {
        return accidents.get(id);
    }

    public Collection<Accident> findAllAccidents() {
        return accidents.values();
    }

    public Collection<AccidentType> findAllTypes() {
        return types.values();
    }

    public Collection<Rule> findAllRules() {
        return rules.values();
    }

}
