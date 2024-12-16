package org.example.est_team_project2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.example.est_team_project2.dao")
public class EstTeamProject2Application {

    public static void main(String[] args) {
        SpringApplication.run(EstTeamProject2Application.class, args);
    }

}
