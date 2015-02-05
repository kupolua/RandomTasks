package info.deepidea.randomtasks;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RandomTasks {
    public static void main(String[] args) {
        Set<String> tasks = new HashSet<String>();
            tasks.add("study");
            tasks.add("write");
            tasks.add("read");
            tasks.add("speak");
            tasks.add("call");
            tasks.add("calculate");
            tasks.add("send");
            tasks.add("receive");
            tasks.add("talk");
            tasks.add("fill");
            tasks.add("seek");
            tasks.add("jump");
            tasks.add("walk");

        Set<String> listPerson = new HashSet<String>();
            listPerson.add("Alex");
            listPerson.add("Denis");
            listPerson.add("Vova");
            listPerson.add("Stepa");
            listPerson.add("Pavel");

        DistributionTasks distributionTasks = new DistributionTasks();
        Map<String, Set<String>> personTasks = distributionTasks.distributeTasks(tasks, listPerson);

        System.out.println(personTasks);

    }
}
