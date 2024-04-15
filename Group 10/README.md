Wildlife Rescue Scheduler
Description:
This application automates the scheduling process for volunteers 
at wildlife rescue centers. It generates daily task lists based 
on animal care needs, ensuring efficient use of time while 
prioritizing animal welfare.

Features:

Automated Scheduling: Generates daily schedules based on animal 
care requirements and volunteer availability.

Database Integration: Imports data from a database to identify 
medical tasks for each animal.

Flexible Task Management: Accommodates fixed-time medical tasks 
and flexible tasks like feeding and cage cleaning.

Backup Volunteer Notification: Alerts the scheduler if backup 
volunteers are needed and waits for confirmation before finalizing 
the schedule.

Error Handling: Reports scheduling issues and allows for adjustments 
based on veterinarian recommendations.

Usage:
Run the application daily in the evening for the following day.

Login in with the correct credentials:
    url
    username
    password
Failure to initialize a connection to DB will display a dialog message
with the associated error.
Clicking "cancel" or closing the dialog will terminate the Application
Else you'll be prompted to enter credentials over and over until you are 
connected


You can check the current animals that are kept by the shealter by clicking 
the "Display Animals". This will lauch another window listing the animal, 
species and name.

You can review all medical treatment tasks buy click the "Move a Treatment" button.
A window will appear displaying all the medical treatment tasks that currently in the
EWR database in order of starting hour. 

If needed you can select one of these treatments and will be given an option to change
the start time of the Treatment using the spinner.
To confirm change follow the prompts. If there is an error with changing the database
values a dialog box will appear containing a message relating to the cause and return you
to the main fram.

Once ready you can click the Blue "Print Schedule" button to generate the daily task
schedule.

The app works but first placing all the treatments that day in the hour blocks of the day
starting at midnight.
Then it fills in the feedings of species where it is applicable to it's sleeping habit.
Finally, it adds the needed daily cage cleanings wherever there is space left

At any point the Scheduler cannot find a viable location for the task it is currently attempting
to schedule it will alert the user with a dialog message containg information on the task it
failed to schedule. The user will be prompted if they wish to add a volunteer, be allowed to
pick an hour inside that particular tasks window to add the backup volunteer and has a final
confirmation to ensure that a backup volunteer has been called and can come in at that time.

Once confirmed the schedular will reapeat all of the above steps until a valid schedule is found.

The schedule will be displayed in a window and be writen to a text file. A dialog message will tell
the user where the file is located.

If the user decieds not to call a backup volunteer and clicks the cancel at any point a schedule will
not be made.


Run GUIforEWR to start the application.