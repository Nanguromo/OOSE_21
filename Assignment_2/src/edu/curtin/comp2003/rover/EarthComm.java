package edu.curtin.comp2003.rover;

public class EarthComm
{
        public String pollCommand()
        {
                String cmd = "S\nP\nD\nD 23.1\nT 75.5\nE";

                return cmd;
        }

        public void sendMessage(String s){System.out.println(s);}
}