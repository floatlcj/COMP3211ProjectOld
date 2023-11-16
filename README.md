# Personal Information Manager
## **Usage:**

### create {*Note*, *Task*, *Schedule*, *Contact*} *identifier*  
This command creates PIRs. There are 4 types of PIRs: Note, Task, Schedule, Contact. Each PIR needs to be assigned to an identifier. An identifier should start with a letter or '_'. It should not be any of the reserved keywords.  

When inputting date and time, please use the format: "yyyy-MM-dd,HH:mm". For example 2023-11-16,19:00.

#### Note
A note can store your input text
#### Task
A task has a text description and a deadline.
#### Schedule
A schedule has a text description, a start time and an alarm time. The alarm time should not be before the current, and should not be
after the start time. A notification will be printed out at the alarm time.
#### Contact
A contact has a name, an address, and a mobile number.

### print *identifier*  
This command can print out a specific PIR. You can also use command "print *" to print out all the PIRs.  

### modify *identifier*
This command can modify an existed PIR.  

### delete *identifier*  
This command can delete a specific PIR.
### save
This command can save all the existed PIRs into a .pim file. Example filepath: /Users/johnfoo/Save/filename

### load
This command can load a .pim file. Example filepath: /User/johnfoo/Save/filename.pim

### search
This command can search by PIR type, string, time, and identifier. A query string should be surrounded by quotation marks. 
Use "<" to search for PIRs before a specific time. Use "=" to search for PIRs equal to a specific time. Use ">" to search 
for PIRs after a specific time. For example "abc". These conditions can be connected by "&&" and "||" logic operators. "!" 
is used for negation.   
A query example: search >2023-11-15,10:00 && Task && "text"

### exit  
Exit the program.
## Compile
root is src

```zsh
javac -d ../target -sourcepath . PIM.java View/Printer.java Controller/*.java Model/*.java
```
## Run
```zsh
java ../target/PIM
```
