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

```Java
1 $ cp test.txt foo.txt  // copies the file `test.txt` into `foo.txt`
2 $ ls
3 test.txt foo.txt
```

### `cp -r` Copy the whole directory (recursively) 

```Java
1 $ cp -r ../workshop .
```

- In this eg, run `pwd` to double-check that you are in the directory called `copy` that is at the same level as `workshop`
- `cp` takes in two arguments, the first is the source, the second is the destination.
- `.` to copy the whole subtree of `workshop` over the current directory
- `ls` to double-check that the workshop directory exists under `workshop`
> If there is an existing file with the same name, `cp` will **overwrite** the existing file without warning.


## `mv`: MoVe or rename files

- just like `cp`, `mv` takes in two arguments, the first is the source and the second is the destination

``` Java
1 $ ls
2 foo.txt test.txt
3 $ mv foo.txt bar.txt  //rename foo.txt into bar.txt
4 $ ls
5 bar.txt test.txt
```

- If the **destination of `mv` is a directory**, however, **instead of renaming**, the mv commands **move** the source to the destination directory.

```Java
1 $ ls
2 bar.txt test.txt // Source
3 $ mv ../copy/workshop/foo.txt . // Destination
4 $ ls
5 bar.txt foo.txt test.txt // moved `foo.txt` over to the current directory
```

> If there is an existing file with the same name, `mv` will overwrite the existing file without warning. `mv -i` interactively asks you if you are sure if you want to overwrite a file. It is a good idea to always run mv -i. Hit  `Y` to continue overwriting the existing file. You can look up on the Internet on how to make `-i` the default using `alias` command in `.bashrc`.


## `rm`: ReMove files

``` Java
1 $ ls
2 bar.txt foo.txt test.txt
3 $ rm foo.txt
4 $ ls
5 bar.txt test.txt
```

> `rm -i` interactively asks you if you are sure if you want to delete a file. It is a good idea to always run `rm -i`.


## `cat (filename)`: CATenate file content to screen. To quickly take a look at the content of the file 

```Java
1 $ cat test.txt
2 This is a test file for learning Unix file management commands.
```

### `less`: A variant of `cat`. Includes features to read each page leisurely. Useful for long files

``` Java
1 $ less test.txt
```

- `<space>` to move down one page
- `b` to move Back up one page
- `q` to Quit


## `man`: Online MANual

- `man ls`: information about any Unix command
- `man man`: refer to Man Pages to find out more about the facility
- `q`: exit `man`


# File Permission Management
## WHAT you can do to a file
|permission|effect on file|effect on directory|
| --- | --- | --- |
| `r` read | reading the content of a file | read the names of the files in the directory
|`w` write | writing into a file | create/delete/rename files in the directory
| `x` execute |	executing a file| access contents and meta-info (size, creation time) of files in the directory

### Symbolic notation
`rwx` , `r-x`, `-wx` , where a `-` means that the corresponding permission is not given (in the order of `r`, `w`, `x`)

### Numerical notation
Uses a digit between 0 and 7, computed as a sum of the individual digit representing the permissions.
| |numerical representation|
| --- | --- | 
|`r`|4
| `w`| 2
|`x`| 1
|`r-x`| 5
|`-wx`|3

## WHO of file permissions

Order:

- `u`: user who owns the file
- `g`: users in the same group as the user
- `o`: all the other users
 
E.g. permission of 644, or `rw-r--r--`, on a file means:

- owner can read and write
- group users can only read
- all other users can only read


## `ls -l (file name)`: Checking file permission

## `chmod`: Change the permissions of a file / directory

``` Java
1 $ chmod 666 test.txt // add the permission w to both group and other users
2 $ ls -l test.txt
3 -rw-rw-rw-@ 1 ooiwt  staff  64 Jul 28 09:52 test.txt
```

**OR**

Specify the changes
```Java
1 $ chmod o-w test.txt // remove the permission to write from others
2 $ ls -l test.txt
3 -rw-rw-r--@ 1 ooiwt  staff  64 Jul 28 09:52 test.txt
```

### Common Scenarios for `chmod`

-  Homework directory inSoC Unix server: Permission of `700` to prevent the directory that stores your homework from being accessible by other users
-  `u+r` to give yourself the read permission to read a file (eg downloaded from the internet) that you do not have permission to read
-  `u+x` execution permission to run a script or executable file


# Standard Input/Output
`wc (filename)`  counts the number of lines, words, characters in the given file 
```Java
1 $ wc test.txt
2       1      11      64 test.txt
```

## Output Redirection
`>` and `>>` to redirect the standard output to a file

- `>` will overwrite the given file
- `>>` will concatenate into the given file

```Java
1 $ wc test.txt > test.count // redirects the output from `wc` to a file named `test.count`
2 $ cat test.count`  // check by running `cat` on the new file `test.count`
3     1      11      64 // test.txt (original output from `wc` is now stored in the file `test.count`
```
 
If we repeat the command `wc test.txt > test.count` again, the file has been overwritten with the output from `wc` again. But if we replace `>` with `>>`, a new line is concatenated into  `test.count`. So the file now has two lines.

```Java
1 $ wc test.txt > test.count 
2 $ cat test.count
3        1      11      64 test.txt
4 $ wc test.txt >> test.count
5 $ cat test.count
6        1      11      64 test.txt
```

## Input Redirection: `<` is used to redirect a file into the standard input

- So, instead of reading from the keyboard, we can now read from a file.
- Commands such as `cat` and `wc` already support reading from a file directly, so there is no difference in terms of functionality to run the commands by passing in the file name, or by using the `<` operator.

```Java
1 $ wc test.txt
2        1      11      64 test.txt
3 $ wc < test.txt
4        1      11      64
5 $ cat test.txt
6 This is a test file for learning Unix file management commands.
7 $ cat < test.txt
8 This is a test file for learning Unix file management commands.`
```

Note the slight difference in the output format of the second `wc` above -- it no longer prints the file name since from `wc` points of view, it is read from the standard input and not from a file, so it is not aware of the file named `test.txt`

> In most CS programming assignments, however, to keep things simple, you will be asked to read from the standard input only, so the `<` is a great time-saver -- you do not have to repeatedly type in the same input data over and over from the keyboard. You can just save the input data in a file, then redirect it to standard input with the `<` operator.





