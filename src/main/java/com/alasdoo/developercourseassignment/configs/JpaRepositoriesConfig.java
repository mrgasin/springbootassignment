package com.alasdoo.developercourseassignment.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.alasdoo")
public class JpaRepositoriesConfig {
}
