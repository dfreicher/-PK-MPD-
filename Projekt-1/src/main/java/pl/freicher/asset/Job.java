package pl.freicher.asset;

/**
 * @author Freicher
 * @version 1.0
 */
public class Job implements Comparable<Job> {

    private String name;
    private int value;

    public Job(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Job o) {
        return value - o.getValue();
    }

}
