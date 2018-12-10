package ar.edu.unaj.api.exception;

public class StudentException extends Exception{

    private int codeError;

    public StudentException (int codeError) {

        super ();
        this.codeError = codeError;
    }

    @Override
    public String getMessage() {

        String message = "";

        switch (codeError) {

            case 100111:
                message = "Error, the object student already exist in the database";
                break;
            case 100222:
                message = "Error, the object student no exist in the database";
                break;
        }

        return message;
    }
}
