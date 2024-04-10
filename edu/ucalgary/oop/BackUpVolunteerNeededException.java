package edu.ucalgary.oop;
/*
@author Group 10
@author Max McEvoy <a href="mailto:max.mcevoy@ucalgary.ca"> max.mcevoy@ucalgary.ca</a>
@author Chukwudiebube Anachebe
@author Daniel Lee
@author Michael Attia>
@version 1.1
@since 1.0
*/

/**
 * The BackUpVolunteerNeededException class represents an exception that is thrown
 * when a backup volunteer is needed for a specific task.
 * It extends the Exception class.
 */
public class BackUpVolunteerNeededException extends Exception {

    // The DailyTasks object associated with the exception
    private DailyTasks task;

    /**
     * Constructs a BackUpVolunteerNeededException with the specified error message
     * and the associated DailyTasks object.
     *
     * @param error the error message
     * @param task  the DailyTasks object associated with the exception
     */
    BackUpVolunteerNeededException(String error, DailyTasks task) {
        super(error);
        this.task = task;
    }

    /**
     * Constructs a BackUpVolunteerNeededException with the specified error message.
     *
     * @param error the error message
     */
    BackUpVolunteerNeededException(String error) {
        super(error);
    }

    /**
     * Returns the DailyTasks object associated with the exception.
     *
     * @return the DailyTasks object
     */
    public DailyTasks getTask() {
        return task;
    }
}
