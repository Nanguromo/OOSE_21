package edu.curtin.comp2003.rover;

public class EarthComm
{
        public String pollCommand(){return "S\nP";}

        public void sendMessage(String s){System.out.println(s);}
}