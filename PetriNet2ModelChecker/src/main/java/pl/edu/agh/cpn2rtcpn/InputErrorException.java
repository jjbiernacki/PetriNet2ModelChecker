package pl.edu.agh.cpn2rtcpn;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asia
 */
public class InputErrorException extends Exception
{
    public String message;
    public InputErrorException(String m)
    {
        message=m;
    }
    
    public void printMessage()
    {
        System.out.println("\nInvalid input: "+message+"\n\n");
    }
    
}
