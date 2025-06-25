package utils;

import tasks.TaskList;

import java.io.Serializable;

public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    private final TaskList[][] aTaskLists;

    public Profile(TaskList[][] aTaskList){
        this.aTaskLists = aTaskList;
    }
    public Profile(){

        this(new TaskList[3][5]);

        for (int r = 0; r < 3; r++) for (int c = 0; c < 5; c++) aTaskLists[r][c] = (new TaskList());
    }

    public TaskList[][] getTaskLists() {
        return aTaskLists;
    }
}
