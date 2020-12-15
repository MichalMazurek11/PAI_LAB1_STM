package stm.demo.model.enums;

public enum Type {


    TASK("TASK"),
    BUG("BUG"),
    FEATURE("FEATURE");

    private final String enumTask;


    Type(String task) {
        this.enumTask = task;
    }
}
