package info.deepidea.randomtasks;

import java.util.HashSet;
import java.util.Set;

public class Person {
    private String personName;
    private Set<String> personTasks = new HashSet<String>();

    public Set<String> getPersonTasks() {
        return personTasks;
    }

    public void setPersonTasks(String tasks) {
        personTasks.add(tasks);
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
