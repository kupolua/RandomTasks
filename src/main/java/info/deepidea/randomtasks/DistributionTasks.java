package info.deepidea.randomtasks;

import java.util.*;

public class DistributionTasks {
    private int avgTasks;
    private int maxTasks;
    private Person person;
    private Map<String, Set<String>> distribution = new HashMap<>();
    private Map<String, Person> persons = new HashMap<>();
    private NavigableMap<Long, String> randomPerson = new TreeMap<>();
    private NavigableMap<Long, String> randomTask = new TreeMap<>();


    public Map<String, Set<String>> distributeTasks(Set<String> tasks, Set<String> listPerson) {
        avgTasks = tasks.size() / listPerson.size();
        setRandomTasks(tasks);
        setRandomPersons(listPerson);
        maxTasks = randomPerson.size() * avgTasks;
        Map<String, Person> persons = createPersons(listPerson);

        while (!randomTask.isEmpty()) {
            String personName = getRandomPerson();
            String taskName = getRandomTask();
            person = persons.get(personName);
            createDistribution(taskName);
        }
        return distribution;
    }

    private String getRandomPerson() {
        boolean isPerson = true;
        String personName = "";

        while (isPerson) {
            Long millis = System.nanoTime() % 1000;
            personName = randomPerson.floorEntry(millis).getValue();
            int personTasksSize = persons.get(personName).getPersonTasks().size();
            if (personTasksSize < avgTasks) {
                --maxTasks;
                return personName;
            }
            if (maxTasks == 0) {
                randomPerson.remove(randomPerson.floorEntry(millis).getKey());
                Long firstElementKey = randomPerson.firstKey();
                if (firstElementKey != Long.valueOf(0)) {
                    Long nextElement = randomPerson.floorEntry(randomPerson.firstKey()).getKey();
                    randomPerson.put(Long.valueOf(0), randomPerson.floorEntry(randomPerson.firstKey()).getValue());
                    randomPerson.remove(nextElement);
                }
                return personName;
            }
        }
        return personName;
    }

    private String getRandomTask() {
        String task;
        Long millis = System.nanoTime() % 1000;
        task = randomTask.floorEntry(millis).getValue();
        randomTask.remove(randomTask.floorEntry(millis).getKey());
        if (randomTask.isEmpty()) {
            return task;
        }
        Long firstElementKey = randomTask.firstKey();
        if (firstElementKey != Long.valueOf(0)) {
            Long nextElement = randomTask.floorEntry(randomTask.firstKey()).getKey();
            randomTask.put(Long.valueOf(0), randomTask.floorEntry(randomTask.firstKey()).getValue());
            randomTask.remove(nextElement);
        }
        return task;
    }

    private Map<String, Person> createPersons(Set<String> listPerson) {
        Iterator<String> listPersonIterator = listPerson.iterator();

        while(listPersonIterator.hasNext()) {
            String personName = listPersonIterator.next();
            Person person = new Person();
            person.setPersonName(personName);
            persons.put(personName, person);
        }
        return persons;
    }

    private void createDistribution(String task) {
        person.setPersonTasks(task);
        distribution.put(person.getPersonName(), person.getPersonTasks());
    }

    private void setRandomTasks(Set<String> tasks) {
        Long point = Long.valueOf(0);
        Long tasksSize = Long.valueOf(tasks.size());
        Long interval = 1000 / tasksSize;
        String task;
        Iterator taskIterator = tasks.iterator();

        while (taskIterator.hasNext()) {
            task = taskIterator.next().toString();
            randomTask.put(point, task);
            point += interval;
        }
    }

    private void setRandomPersons(Set<String> listPerson) {
        Long point = Long.valueOf(0);
        Long listPersonSize = Long.valueOf(listPerson.size());
        Long interval = 1000 / listPersonSize;
        String person;
        Iterator listPersonIterator = listPerson.iterator();

        while (listPersonIterator.hasNext()) {
            person = listPersonIterator.next().toString();
            randomPerson.put(point, person);
            point += interval;
        }
    }
}
