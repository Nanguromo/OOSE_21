package edu.curtin.comp2003.rover;

public class EarthComm
{
        public String pollCommand()
        {
                String cmd = "S\nP\nD\nD 76.3";

                return cmd;
        }

        public void sendMessage(String s){System.out.println(s);}
}