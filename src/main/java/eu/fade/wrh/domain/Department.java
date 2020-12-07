package eu.fade.wrh.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Department {

    private String name;
    private String manager;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(final String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }
}
