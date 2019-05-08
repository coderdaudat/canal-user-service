package com.canalcircle.user.model;

import com.canalcircle.user.config.DateAudit;

import javax.persistence.*;

/**
 * Created by truongnguyen on 5/8/19.
 */
@Entity
@Table(name = "users")
public class User extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public User() {
    }

    private User(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public static class Builder {

        private String name;
        private String description;

        private Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public User build() {

            User build = new User(this);

            return build;
        }
    }
}
