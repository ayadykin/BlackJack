package com.ayadykin.blackjack.security;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ayadykin.blackjack.model.Role;
import com.ayadykin.blackjack.model.User;

/**
 * Created by Yadykin Andrii Sep 9, 2016
 *
 */

@Named
public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDao.class.getName());

    @Override
    @Transactional
    public User findByEmail(String email) {
        logger.debug(" findByEmail ");
        String sql = "select * from users where email = ?";
        User user = null;
        try {
            user = new JdbcTemplate(configureDataSource()).queryForObject(sql, new Object[] { email }, new ActorMapper());

        } catch (Exception e) {
            logger.error(" findByEmail error : " + e);
        }
        return user;
    }

    private DataSource configureDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/black_jack");
        dataSource.setUsername("postgres");
        dataSource.setPassword("pgpass");
        return dataSource;
    }

    private class ActorMapper implements RowMapper<User> {

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRole(new Role("user"));
            return user;
        }
    }
}
