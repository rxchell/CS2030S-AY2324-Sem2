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


<h1>Specifying a path</h1>

**Absolute path**: Independent of the current working directory. Always start with `/` or `~`

**Relative path**: Dependent on the current working directory. Use `..` when going up a tree. Never starts with `/`


<h1>Directory-related Commands</h1>

`pwd`: Print Current Working directory

`ls`: LiSt content in the current working directory

-  To see a complete list of the options, refer to the man pages, `man ls`

`ls -a`: To view all files, including hidden files

- a file or directory with a name that starts with `.` is hidden from `ls`
- eg `.vimrc`, the configuration file for `vim`
- eg `.ssh`, the directory to store your SSH keys and configuration

`ls -F`: for more information

- `/` beside the filename tells you that the file is a directory

`ls -l`: display almost all the file information, include the size of the file and the date of modification

`mkdir (name of file)`: MaKe a subDIRectory with the given name in the current directory.

`cd`: Change the current working directory from one to another

`rmdir (name of file)`: ReMove a subDIRectory in the current directory. Note that a directory must be empty before it can be removed.

# Command History

- `Ctrl+P` (previous)
- `Ctrl+N` (next)


# File Management


## `cp`: CoPy files 

<h3>Copy a file to another name</h3>

1 `$ cp test.txt foo.txt`  (copies the file `test.txt` into `foo.txt`)

2 `$ ls`

3 `test.txt foo.txt`


### `cp -r` Copy the whole directory (recursively) 

1 `$ cp -r ../workshop .`

- In this eg, run `pwd` to double-check that you are in the directory called `copy` that is at the same level as `workshop`
- `cp` takes in two arguments, the first is the source, the second is the destination.
- `.` to copy the whole subtree of `workshop` over the current directory
- `ls` to double-check that the workshop directory exists under `workshop`
> If there is an existing file with the same name, `cp` will **overwrite** the existing file without warning.


## `mv`: MoVe or rename files

- just like `cp`, `mv` takes in two arguments, the first is the source and the second is the destination

1 `$ ls`

2 `foo.txt test.txt`

3 `$ mv foo.txt bar.txt` (**rename** foo.txt into bar.txt)

4 `$ ls`

5 `bar.txt test.txt`


- If the **destination of `mv` is a directory**, however, **instead of renaming**, the mv commands **move** the source to the destination directory.
1 `$ ls`

2 `bar.txt test.txt` (Source)

3 `$ mv ../copy/workshop/foo.txt .` (Destination)

4 `$ ls`

5 `bar.txt foo.txt test.txt` (moved `foo.txt` over to the current directory)

> If there is an existing file with the same name, `mv` will overwrite the existing file without warning. `mv -i` interactively asks you if you are sure if you want to overwrite a file. It is a good idea to always run mv -i. Hit  `Y` to continue overwriting the existing file. You can look up on the Internet on how to make `-i` the default using `alias` command in `.bashrc`.


## `rm`: ReMove files

1 `$ ls`
2 `bar.txt foo.txt test.txt`
3 `$ rm foo.txt`
4 `$ ls`
5 `bar.txt test.txt`

> `rm -i` interactively asks you if you are sure if you want to delete a file. It is a good idea to always run `rm -i`.


## `cat`: CATenate file content to screen. To quickly take a look at the content of the file 

1 `$ cat test.txt`
2 `This is a test file for learning Unix file management commands.`

<h3> `less`: A variant of `cat`. Includes features to read each page leisurely. Useful for long files</h3>

1 `$ less test.txt`

- `<space>` to move down one pageb
- `b` to move Back up one page
- `q` to Quit


## `man`: Online MANual

- `man ls`: information about any Unix command
- `man man`: refer to Man Pages to find out more about the facility
- `q`: exit `man`

