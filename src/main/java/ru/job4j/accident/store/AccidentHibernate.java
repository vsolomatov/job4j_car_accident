package ru.job4j.accident.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AccidentHibernate implements AccidentStore {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void create(Accident accident, String[] ids) {
        Session session = sf.openSession();
        AccidentType accidentType = findByIdAccidentType(accident.getType().getId());
        accident.setType(accidentType);
        updateRules(accident, ids);
        session.beginTransaction();
        session.save(accident);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void save(Accident accident, String[] ids) {
        Session session = sf.openSession();
        AccidentType accidentType = findByIdAccidentType(accident.getType().getId());
        accident.setType(accidentType);
        updateRules(accident, ids);
        session.beginTransaction();
        session.update(accident);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Accident findByIdAccident(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct a from Accident a join fetch a.rules where a.id = :id", Accident.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    private AccidentType findByIdAccidentType(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType a where a.id = :id", AccidentType.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    private Rule findByIdRule(int id) {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule a where a.id = :id", Rule.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
    }

    private void updateRules(Accident accident, String[] ids) {
        Set<Rule> ruleSet = new HashSet<>();
        for (String id : ids) {
            ruleSet.add(findByIdRule(Integer.parseInt(id)));
        }
        accident.setRules(ruleSet);
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("select distinct a from Accident a join fetch a.rules order by a.id", Accident.class)
                    .list();
        }
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from AccidentType", AccidentType.class)
                    .list();
        }
    }

    @Override
    public Collection<Rule> findAllRules() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Rule", Rule.class)
                    .list();
        }
    }
}
