/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjava;

import edu.esprit.tools.MyConnection;

/**
 *
 * @author Ela
 */
public class Pidevjava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MyConnection cnx = new MyConnection();
        cnx.getCnx();
    
    }
    
}
