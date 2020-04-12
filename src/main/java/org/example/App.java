package org.example;

import org.example.Servise.Menu;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App 
{
    public static void main( String[] args ) {

        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        System.out.println( "Pildoma duomenų bazė duomenimis" );
        FillInnDb.fill();

        System.out.println("Programa pasiruošusi darbui");
        Menu menu = new Menu();
        menu.run();

    }
}
