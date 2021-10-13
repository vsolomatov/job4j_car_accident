package ru.job4j.accident.store;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashSet;

@Repository
public class AccidentJdbcTemplate implements AccidentStore {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void create(Accident accident, String[] ids) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                    "insert into accident(name, text_, address, accident_type_id) "
                        + "values (?, ?, ?, ?)",
                            new String[] {"id"});
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getText());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                }, keyHolder
        );
        Integer accidentId = (Integer) keyHolder.getKey();
        if (accidentId != null) {
            for (String id : ids) {
                jdbc.update("insert into accident_rule(accident_id, rules_id) values (?, ?)",
                        accidentId,
                        Integer.parseInt(id)
                );
            }
        }
    }

    @Override
    public void save(Accident accident, String[] ids) {
        jdbc.update("update accident set name = ?, text_ = ?, address = ?, accident_type_id = ? where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId()
        );
        // Удаляем старые записи из таблицы accident_rule
        jdbc.update("delete from accident_rule where accident_id = ?",
                accident.getId()
        );
        // Добавляем новые записи в таблицу accident_rule
        for (String id : ids) {
            jdbc.update("insert into accident_rule(accident_id, rules_id) values (?, ?)",
                    accident.getId(),
                    Integer.parseInt(id)
            );
        }
    }

    @Override
    public Accident findByIdAccident(int id) {
        return jdbc.queryForObject("select id, name, text_, address, accident_type_id from accident where id = ?",
                (resultSet, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(resultSet.getInt("id"));
                    accident.setName(resultSet.getString("name"));
                    accident.setText(resultSet.getString("text_"));
                    accident.setAddress(resultSet.getString("address"));
                    // Находим accident_type в таблице
                    accident.setType(findByIdAccidentType(resultSet.getInt("accident_type_id")));
                    // Находим все rules в таблице
                    accident.setRules(new HashSet<>(findByAccidentIdRules(resultSet.getInt("id"))));
                    return accident;
                },
                id);
    }

    private AccidentType findByIdAccidentType(int id) {
        return jdbc.queryForObject("select id, name from accident_type where id = ?",
                (resultSet, rowNum) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(resultSet.getInt("id"));
                    accidentType.setName(resultSet.getString("name"));
                    return accidentType;
                },
                id);
    }

    private Rule findByIdRule(int id) {
        return jdbc.queryForObject("select id, name from rule where id = ?",
                (resultSet, rowNum) -> {
                    Rule rule = new Rule();
                    rule.setId(resultSet.getInt("id"));
                    rule.setName(resultSet.getString("name"));
                    return rule;
                },
                id);
    }

    private Collection<Rule> findByAccidentIdRules(int id) {
        return jdbc.query("select accident_id, rules_id from accident_rule where accident_id = ?",
                (resultSet, rowNum) -> {
                    Rule rule = findByIdRule(resultSet.getInt("rules_id"));
                    return rule;
                },
                id);
    }

    @Override
    public Collection<Accident> findAllAccidents() {
        return jdbc.query("select a.id, a.name, a.text_, a.address, a.accident_type_id "
                        + "from accident a "
                        + "order by a.id",
                (resultSet, rowNum) -> {
                    Accident accident = new Accident();
                    accident.setId(resultSet.getInt("id"));
                    accident.setName(resultSet.getString("name"));
                    accident.setText(resultSet.getString("text_"));
                    accident.setAddress(resultSet.getString("address"));
                    // Находим accident_type в таблице
                    accident.setType(findByIdAccidentType(resultSet.getInt("accident_type_id")));
                    // Находим все rules в таблице
                    accident.setRules(new HashSet<>(findByAccidentIdRules(resultSet.getInt("id"))));
                    return accident;
                });
    }

    @Override
    public Collection<AccidentType> findAllTypes() {
        return jdbc.query("select id, name from accident_type",
                (resultSet, rowNum) -> {
                    AccidentType accidentType = new AccidentType();
                    accidentType.setId(resultSet.getInt("id"));
                    accidentType.setName(resultSet.getString("name"));
                    return accidentType;
                });
    }

    @Override
    public Collection<Rule> findAllRules() {
        return jdbc.query("select id, name from rule",
                (resultSet, rowNum) -> {
                    Rule rule = new Rule();
                    rule.setId(resultSet.getInt("id"));
                    rule.setName(resultSet.getString("name"));
                    return rule;
                });
    }
}