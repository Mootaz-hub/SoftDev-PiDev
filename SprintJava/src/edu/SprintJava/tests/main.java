/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.SprintJava.tests;

import edu.SprintJava.entities.Admin;
import edu.SprintJava.entities.Client;
import edu.SprintJava.entities.CustomTimer;
import edu.SprintJava.entities.Livreur;
import edu.SprintJava.entities.Session;
//import edu.SprintJava.entities.User;
import edu.SprintJava.services.AdminCRUD;
import edu.SprintJava.services.ClientCRUD;
import edu.SprintJava.services.LivreurCRUD;
import edu.SprintJava.utils.ControleSaisie;
import edu.SprintJava.utils.MailUser;
//import edu.SprintJava.services.UserService;
import edu.SprintJava.utils.MyConnection;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author moete
 */
public class main {

    public static void main(String[] args) throws Exception {
        //MyConnection mc = new MyConnection();
//        Admin ad = new Admin(1912261, "Moetaz", "Hajji", 65745,"Mootaaz-hub", "Moetaz.hajji@esprit.tn", "oqsjf");
//        Admin ad1 = new Admin(8965, "hamdi", "l3ou9", 44654968, "hamdi@esprit.tn", "sqddqfq","admin produits");
//        Admin ad2 = new Admin(74123698, "loulou", "bounifa", 1456564, "loulou@esprit.tn", "fqsfggqe","admin xxxx");
//        
        AdminCRUD adc = new AdminCRUD();
//        Admin ad3 = new Admin(138, "moumouet", "ben khalifet", 35168, "Mootaaz-hub", "mimouet@esprit.tn","admin xxxx");
        // adc.ajouterAdmin(ad);
        // adc.ajouterAdmin(ad1);
        //adc.ajouterAdmin(ad2);
        //System.out.println(adc.ListerAdmin());
        //adc.modifierAdmin(8965, "oihjlk", "vdfsg",5343, "email", "jhjhuiigu","ezaqsd","kjg");
        //adc.supprimerAdmin(ad);
//        Client c=new Client("ahmed", "fazeni", "17/05/199", "tunis-ariana", 98563214, "ahmed.fazeni@esprit.tn","ahmed hamouda","haouda", "homme");
//        Client c1=new Client("khalil", "barkati", "17/05/2002", "tunis-sidiThebat", 98563214, "khalil.barkati@esprit.tn","khalil khoukhou","khalilllos", "homme");
//        
//        ClientCRUD clc=new ClientCRUD();
//        clc.ajouterClient(c1);
        //System.out.println(clc.ListerClient());
        //clc.modifierClient(435864, "khdjsf", "klqshf", "19/2/946563", "lfhjdnsqf", "qsffqs@fqsfq.com", 56464, "dfsfq");
        //clc.supprimerClient(c2);

//        Livreur l1=new Livreur(5644564, "qsdsq", "qsdfgsd");
//        Livreur l2=new Livreur(426874, "dqsdff", "qstaztaztazdfgsd");
//        LivreurCRUD lc=new LivreurCRUD();
        //lc.ajouterLivreur(l2);
        //System.out.println(lc.ListerLivreur());
        //lc.modifierLivreur(426874, "mootaz", "hajji");
//        lc.supprimerLivreur(l2);
//        User us=new User();
//        UserService uss = new UserService();
//        Session s=new Session();
//        uss.getUser(8965);
       // adc.getAdmin2(8965);
        //System.out.println(adc.rechercherAdmin(8965));
        //adc.affecterAdminRolle(8965, "Master");
        //adc.ajouterAdmin(ad2);
//        System.out.println(adc.rechercherAdmin(8965));
           //adc.Login1("Mootaaz-hub","oqsjf");
            //adc.checkUsername("ygug");
//        ControleSaisie cs = new ControleSaisie();
//        String mail = "19/05/1665";
//        System.out.println(cs.convert(mail));
//          Timer chrono =new Timer();
//          chrono.schedule(new CustomTimer(), 1000, 1000);
        //adc.affecterAdminRolle("soulaymen", "Master");
        MailUser.sendMail("moetaz.hajji@esprit.tn");
    }
    
}
