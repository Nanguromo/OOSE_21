Created: 18/04/2021
Last Edited: 22/04/2021 10:29PM AWST
Author: Wayne Nanguromo
Student ID: 19480060

Purpose: To gain confidence with using different containers and gain experience with various software design patterns, such as the Strategy, Composite and MVC pattern.


How to compile and run: 
    - Ensure you are in the Assignment directory (you should be able to see the build.xml file when typing "ls")
    - type "ant" to compile
    - change to the "dist" directory with "cd dist/"
    - Within "dist" should be a simple example file "read.txt" that you can use later to read, or supply your own
    - To run, within "dist" directory, type "java -jar ElectricityUsage [args...]
        - argument 1 can be "-r" to read from a file provided as an argument following OR "-g" to generate a data. You can only enter one option
        - argument 2 can be "-w" to write to a file provided as an argument folllowing OR "-d" to display data that was either generated or read, to the terminal
            - You cannot enter the same argument twice, it will be an error!
            - If you chose to read and write, the files entered must be unique!

    - When running read or write, two example files have been provided, "read.txt" and "write.txt". 
        - Note: You can simply make a writefile by entering a name. If it is not found in the directory, the file will be created. Furthermore, if the found exists, it will APPEND to it!

Examples:
    - "java -jar ElectricityUsage -r myreadfile.txt -w mywritefile.csv"
        - reads from myreadfile.txt and creates it tree internally, before writing the tree to mywritefile.csv 
    - "java -jar ElectricityUsage -g -w mywritefile.txt"
        - generates a tree internally and writes it to mywritefile.txt
    - "java -jar ElectricityUsage -g -d"
        - gennerates a tree interally and displays it to the terminal

    Bad example:
        - "java -jar ElectricityUsage -g -r mywritefile.txt"
            - you cannot read AND generate. Similarly you cannot write AND display. Doing this will always result in an error! 

    

