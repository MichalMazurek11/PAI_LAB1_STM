package stm.demo.model.enums;

public enum Status {


    NEW("NEW"),
    IN_PROGRESS("IN PROGRESS"),
    DONE("DONE");

    private final String enumStatus;


    Status(String status) {
        this.enumStatus = status;
    }


}


