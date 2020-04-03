package gb.xokyopo.servlets.dao;

public class DAOException extends Exception {

    public DAOException(Object errorIn, String methodName, Throwable cause) {
        super("error in " + errorIn.getClass().getSimpleName() + "." + methodName + " - driver not found", cause);
    }
}
