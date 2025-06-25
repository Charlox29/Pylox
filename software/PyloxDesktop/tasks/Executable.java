package tasks;

import java.io.IOException;

/**
 * Task of launching an executable (app, program, etc).
 * The program to launch is stored in a String field.
 *
 * @author Charles A
 *
 * @version 06/21/2025
 *
 * @see Task
 */
public class Executable extends Task
{
    private static final long serialVersionUID = 1L;

    private static final String TYPE = "Executable";

    private String aProgram;

    /**
     * Constructs Executable objects.
     */
    public Executable()
    {
        super(TYPE);

        aProgram = "";
    }

    /**
     * Program accessor
     *
     * @return the program
     */
    public String getProgram(){
        return aProgram;
    }

    /**
     * Program modifier
     *
     * @param pProgram the program
     */
    public void setTime(final String pProgram){
        aProgram = pProgram;
    }

    @Override public Executable clone(){
        Executable vExecutable = new Executable();

        vExecutable.setTime(aProgram);

        return vExecutable;
    }

    @Override public void execute() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(aProgram);
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override public String getDescription() {
        return aProgram;
    }
}