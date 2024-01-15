<h1> Command-Line Interfaces (CLI) </h1>  

- output printed on a **terminal**
- **shell** (eg `bash` or Bourne Again Shell ) usually works closely with a terminal to get inputs from the users, interpret the meaning of the inputs, execute the tasks (perhaps through the invocation of other programs), and returned the output to the user through the terminal
- **Command Prompt**: customary to use the `$` sign as the final character of the prompt


<h1> Terminal Control Sequence </h1>

- **Ctrl** + **D** : signal the end of input to a program; to exit from a shell
- **Ctrl** + **Z** : pauses the execution of the program (but not terminate it). In the `bash` shell, the most recently suspended program can resume executing in the background with `bg` or be brought back to execution in the foreground with `fg`
- **Ctrl** + **C** : terminate the current running program
>A common mistake for new students is to hit Ctrl+Z frequently if something goes wrong with their program -- this behavior could lead to multiple suspended programs (which still occupy resources such as memory on the computer). The right sequence to use is Ctrl+C -- which terminates the program (and frees up the resources).

Legacy control commands: 
- **Ctrl** + **S** : freeze the terminal; pauses the output printing. You shouldn't need to use this control sequence.
- **Ctrl** + **Q** : resume the terminal; resumes the printing of a teletype machine. You shouldn't need to use this control sequence unless you accidentally hit Ctrl+S


<h1>Directory</h1>

`/`  Root directory

`~`  Home directory

`.`  Current working directory 

`..` Parent directory 


<h1></h1>

