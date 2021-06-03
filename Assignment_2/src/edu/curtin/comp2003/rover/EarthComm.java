package edu.curtin.comp2003.rover;

public class EarthComm
{
        public String pollCommand()
        {
                /*Add different commands to demonstrate the rover receiving polled commands.
                * The program will detect incorrect errors and will also ensure commands are only performed
                * given the rover is in the correct state. Furthermore, if errors were to happen, the rover will
                * send an error back to Earth in the format "! ...", where "..." is the error described.*/
                
                /*comment out different cmd or enter your own to perform different comment. For polling commands,
                * ensure commands are separated by "\n"*/


                //STUB CODDE...
                String cmd;
                //cmd = "S\nP\nD\nD 23.1\nT 75.5\nE";
                cmd = "S\nS\nP\nE\nT 23.5\nT56.6\n\nsss12131\nerr eerror\nD\nD 22.1\nS";
                //cmd = "D 12.55\nT 123.3\nerror\nS 65.6\nD 31.3\nT 15.2";
                return cmd;
        }

        public void sendMessage(String s){System.out.println(s);}
}