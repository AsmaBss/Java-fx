/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import interfaces.IUser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import models.User;
import models.skills_candidat;
import utils.DataSource;
import org.springframework.security.crypto.bcrypt.BCrypt;


import org.controlsfx.control.Notifications;
import utils.UserSession;



import edu.esprit.tools.MyConnection;

/**
 *
 * @author hp
 */
public class CandidatServices implements IUser<User> {
     Connection cnx = MyConnection.getInstance().getCnx();
      public User userInfos;

    @Override
    public void ajouter(User t) {
      String requete = "INSERT INTO User (password,phone,address,town,fb,linkdin,description,img,discr,company,categorie,nom,prenom,date_naissance,niv_etude,type_candidat,roles,nbr_follow)"
                + "VALUES (?,?,?,?,?,?,?,?,?,null,null,?,?,?,?,?,?,0)";
      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
          String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(1, hashedPassword);
            
            pst.setInt(2, t.getPhone());
            pst.setString(3, t.getAddress());
            pst.setString(4, t.getTown());
            pst.setString(5, t.getFb());
            pst.setString(6, t.getLinkdin());
            pst.setString(7, t.getDescription());
            pst.setString(8, t.getImg());
            pst.setString(9, t.getDiscr());
           
            pst.setString(10, t.getNom());
            pst.setString(11, t.getPrenom());
            pst.setDate(12,(Date) t.getDate_naissance());
            pst.setString(13, t.getNiv_etude());
            pst.setString(14, t.getType_candidat());
            pst.setString(15,t.getRole());
          
       
            pst.executeUpdate();
            System.out.println("Candidat ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    

    public void ajouterEmployer(User t) {
      String requete = "INSERT INTO User (password,phone,address,town,fb,linkdin,description,img,discr,company,categorie,nom,prenom,date_naissance,niv_etude,type_candidat,roles,nbr_follow)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,null,null,null,null,null,?,0)";
      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
          
           String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
            pst.setString(1, hashedPassword);
            pst.setInt(2, t.getPhone());
            pst.setString(3, t.getAddress());
            pst.setString(4, t.getTown());
            pst.setString(5, t.getFb());
            pst.setString(6, t.getLinkdin());
            pst.setString(7, t.getDescription());
            pst.setString(8, t.getImg());
            pst.setString(9, t.getDiscr());
           
           pst.setString(10, t.getCompany());
            pst.setString(11, t.getCategorie());
            pst.setString(12,t.getRole());
          
       
            pst.executeUpdate();
            System.out.println("Employer ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(User t) {
        try {
            String requete = "DELETE FROM user WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getId());
            System.out.println(t.getId());
            pst.executeUpdate();
            System.out.println("Membre Supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(User t) {
       try {
                
            String requete = "UPDATE user SET id =? , nom = ?,"
					+ " prenom = ?, address = ?, password = ?,"
					+ " date_naissance = ?, town = ?, fb = ? , linkdin = ? , type_candidat = ? , img = ? , niv_etude = ? , phone = ? , description = ?,"
                                        + " discr= ? , company = ? , categorie = ?, roles = ?, nbr_follow= ? " 
					+ " WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1, t.getId());
                        pst.setString(2, t.getNom());
			pst.setString(3, t.getPrenom());
			pst.setString(4, t.getAddress());
			String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
                        pst.setString(5, hashedPassword);
			pst.setDate(6, t.getDate_naissance());
                        pst.setString(7, t.getTown());
                        pst.setString(8, t.getFb());
                        pst.setString(9, t.getLinkdin());
                        pst.setString(10, t.getType_candidat());
                        pst.setString(11, t.getImg());
                        pst.setString(12, t.getNiv_etude());
                        pst.setInt(13, t.getPhone());
                        pst.setString(14, t.getDescription());
                        pst.setString(15, t.getDiscr());
                         pst.setString(16, t.getCompany());
                        pst.setString(17, t.getCategorie());
                         pst.setString(18, t.getRole());
                        pst.setInt(19, t.getNbr_follow());
                        pst.setInt(20, t.getId());
			
			System.out.println(requete);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public User login(String inputUsername, String inputPassword) {
		User scarra = new User();
		scarra.setId(-1);

		String hashedPassword = "";
		
        try {
            String requete = "SELECT password FROM user where address=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, inputUsername);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
				hashedPassword = rs.getString("password");
            }
			
			if(BCrypt.checkpw(inputPassword, hashedPassword) && scarra.getEtat()==0) {
				System.out.println("It matches");
				requete = "SELECT * FROM user where address=?";
				pst = cnx.prepareStatement(requete);
				pst.setString(1, inputUsername);
				rs = pst.executeQuery();
				while (rs.next()) {
					scarra.setId(rs.getInt("id"));
					scarra.setAddress(rs.getString("address"));
					scarra.setPassword(rs.getString("password"));
					scarra.setPhone(rs.getInt("phone"));
					scarra.setTown(rs.getString("town"));
					scarra.setFb(rs.getString("fb"));
					scarra.setLinkdin(rs.getString("linkdin"));
					scarra.setDescription(rs.getString("description"));
                                        scarra.setImg(rs.getString("img"));
                                        scarra.setDiscr(rs.getString("discr"));
                                        scarra.setCompany(rs.getString("company"));
                                        scarra.setCategorie(rs.getString("categorie"));
                                        scarra.setNom(rs.getString("nom"));
                                        scarra.setPrenom(rs.getString("prenom"));
                                        scarra.setDate_naissance(rs.getDate("date_naissance"));
                                        scarra.setNiv_etude(rs.getString("niv_etude"));
                                        scarra.setType_candidat(rs.getString("type_candidat"));
                                        scarra.setRole(rs.getString("roles"));
                                        scarra.setNbr_follow(rs.getInt("nbr_follow"));
                                        scarra.setEtat(rs.getInt("etat"));
					System.out.println("  aaaa "+scarra);
				}
			}
			else {
				System.out.println("faill"+scarra.getId());
			}

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	
		
		return scarra;
	}
    
     public int loginMembre(String email,String password) throws SQLException{
      User scarra = new User();
		scarra.setId(-1);

		String hashedPassword = "";
		
        try {
            String requete = "SELECT password FROM user where address=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
				hashedPassword = rs.getString("password");
            }
			
			if(BCrypt.checkpw(password, hashedPassword)) {
				System.out.println("It matches");
				requete = "SELECT * FROM user where address=?";
				pst = cnx.prepareStatement(requete);
				pst.setString(1, email);
				rs = pst.executeQuery();
				while (rs.next()) {
					scarra.setId(rs.getInt("id"));
					scarra.setAddress(rs.getString("address"));
					scarra.setPassword(rs.getString("password"));
					scarra.setPhone(rs.getInt("phone"));
					scarra.setTown(rs.getString("town"));
					scarra.setFb(rs.getString("fb"));
					scarra.setLinkdin(rs.getString("linkdin"));
					scarra.setDescription(rs.getString("description"));
                                        scarra.setImg(rs.getString("img"));
                                        scarra.setDiscr(rs.getString("discr"));
                                        scarra.setCompany(rs.getString("company"));
                                        scarra.setCategorie(rs.getString("categorie"));
                                        scarra.setNom(rs.getString("nom"));
                                        scarra.setPrenom(rs.getString("prenom"));
                                        scarra.setDate_naissance(rs.getDate("date_naissance"));
                                        scarra.setNiv_etude(rs.getString("niv_etude"));
                                        scarra.setType_candidat(rs.getString("type_candidat"));
                                        scarra.setRole(rs.getString("roles"));
                                        scarra.setNbr_follow(rs.getInt("nbr_follow"));
                                        scarra.setEtat(rs.getInt("etat"));
                                        scarra.setBlock(rs.getString("block"));
					System.out.println("  aaaa "+scarra);
				}
                                  UserSession.getInstance().setLoggedUser(scarra); 
                                  return 1;
			}
			else {
				System.out.println("faill"+scarra.getId());
			}

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
	
		
		return 0;
    }
     
    
      public User getUserById(String email) {
        User user = null;
        String requete="SELECT * FROM user where address=?";
        ResultSet res;

        try {
            
            PreparedStatement pst =
                 cnx.prepareStatement(requete);
            pst.setString(1, email);
            res = pst.executeQuery();
            if (res.last())//kan il9a il user
            {
                user = new User(res.getInt(1), res.getString(2),res.getInt(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),
                        res.getString(8),res.getString(9),res.getString(10),res.getString(11),res.getString(12),res.getString(13),res.getString(14),res.getDate(15),
                        res.getString(16),res.getString(17),res.getString(18),res.getInt(19),res.getInt(20),res.getString(21));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
    
  public void modifierEmployer(User t) {
       try {
                
            String requete = "UPDATE user SET id =? , nom = ?,"
					+ " prenom = ?, address = ?, password = ?,"
					+ " date_naissance = ?, town = ?, fb = ? , linkdin = ? , type_candidat = ? , img = ? , niv_etude = ? , phone = ? , description = ?,"
                                        + " discr= ? , company = ? , categorie = ?, roles = ?, nbr_follow= ? " 
					+ " WHERE id = ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1, t.getId());
                        pst.setString(2, t.getNom());
			pst.setString(3, t.getPrenom());
			pst.setString(4, t.getAddress());
			String hashedPassword = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt(13));
                        pst.setString(5, hashedPassword);
			pst.setDate(6, t.getDate_naissance());
                        pst.setString(7, t.getTown());
                        pst.setString(8, t.getFb());
                        pst.setString(9, t.getLinkdin());
                        pst.setString(10, t.getType_candidat());
                        pst.setString(11, t.getImg());
                        pst.setString(12, t.getNiv_etude());
                        pst.setInt(13, t.getPhone());
                        pst.setString(14, t.getDescription());
                        pst.setString(15, t.getDiscr());
                         pst.setString(16, t.getCompany());
                        pst.setString(17, t.getCategorie());
                         pst.setString(18, t.getRole());
                        pst.setInt(19, t.getNbr_follow());
                        pst.setInt(20, t.getId());
			
			System.out.println(requete);
            pst.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   @Override
    public ObservableList<User> afficher() {
        ObservableList<User> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM User where discr='candidat'";
        try {
            
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Image image = new Image(rs.getString(9)); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                list.add(new User(rs.getInt(1),rs.getString(13), rs.getString(14),rs.getString(4),rs.getInt(3),rs.getString(6),rs.getString(7),rs.getString(17),rs.getString(16),rs.getDate(15),rs.getString(5),rs.getString(8),rs.getString(2),imgV,rs.getInt(19))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
 
    public ObservableList<User> afficherBack() {
        ObservableList<User> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM User where discr='candidat'";
        try {
            
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Image image = new Image(rs.getString(9)); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                list.add(new User(rs.getInt(1),rs.getString(13), rs.getString(14),rs.getString(4),rs.getInt(3),rs.getString(6),rs.getString(7),rs.getString(17),rs.getString(16),rs.getDate(15),rs.getString(5),rs.getString(8),rs.getString(2),imgV,rs.getInt(19),rs.getString(21))); 
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
   
    public ObservableList<User> afficherI() {
        ObservableList<User> list = FXCollections.observableArrayList();
        
        try {
            String requete = "SELECT * FROM User";
            Statement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                               
                list.add(new User(rs.getInt(1),rs.getString(13), rs.getString(14),rs.getString(4),rs.getInt(3),rs.getString(6),rs.getString(7),rs.getString(17),rs.getString(16),rs.getDate(15),rs.getString(5),rs.getString(8),rs.getString(2)));
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
    
     
    public ObservableList<User> afficherEmployer() {
        ObservableList<User> list = FXCollections.observableArrayList();
        
        String requete = "SELECT * FROM User where discr='employer'";
        try {
            
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Image image = new Image(rs.getString(9)); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                System.out.println(rs.getInt(1));
                list.add(new User(rs.getInt(1),rs.getString(11),rs.getString(12),rs.getString(4),rs.getInt(3),rs.getString(6),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(2),imgV));  
            }
            
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
public ObservableList<User> search(String input) {
		ObservableList <User> ListUsers = FXCollections.observableArrayList();
		
		try {
            String requete = "SELECT *"
					+ "FROM user "
					+ "WHERE (`company` like ? or `categorie` like ? or "
					+ " `address` like ? or `phone` like ? or `fb` like ? or "
					+ " `linkdin` like ?) and (discr = 'employer') ";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, "%"+input+"%");
			pst.setString(2, "%"+input+"%");
			pst.setString(3, "%"+input+"%");
			pst.setString(4, "%"+input+"%");
			pst.setString(5, "%"+input+"%");
			pst.setString(6, "%"+input+"%");
                      
                       
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Image image = new Image(rs.getString("img")); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                ListUsers.add(new User(rs.getInt(1),rs.getString(11),rs.getString(12),rs.getString(4),rs.getInt(3),rs.getString(6),rs.getString(7),rs.getString(5),rs.getString(8),rs.getString(2),imgV
						
				));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
		return ListUsers;
	}
public ObservableList<User> searchCandidat(String input) {
		ObservableList <User> ListUsers = FXCollections.observableArrayList();
		
		try {
            String requete = "SELECT *"
					+ "FROM user "
					+ "WHERE (`nom` like ? or `prenom` like ? or "
					+ " `town` like ? or `phone` like ? or `fb` like ? or "
					+ " `linkdin` like ? ) and (discr = 'candidat') ";
            PreparedStatement pst = cnx.prepareStatement(requete);
			pst.setString(1, "%"+input+"%");
			pst.setString(2, "%"+input+"%");
			pst.setString(3, "%"+input+"%");
			pst.setString(4, "%"+input+"%");
			pst.setString(5, "%"+input+"%");
			pst.setString(6, "%"+input+"%");
                      
                       
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Image image = new Image(rs.getString("img")); //create img
                ImageView imgV = new ImageView(image);
                imgV.setFitHeight(80);
                imgV.setFitWidth(80);
                ListUsers.add(new User(rs.getInt(1),rs.getString(13), rs.getString(14),rs.getString(4),rs.getInt(3),rs.getString(6),rs.getString(7),rs.getString(17),rs.getString(16),rs.getDate(15),rs.getString(5),rs.getString(8),rs.getString(2),imgV,rs.getInt(19)
						
				));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
		return ListUsers;
	}
    @Override
    public List<User> userListe() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> TrieParNom() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
      public void follow(User employer, User candidat) {
        String requete = "INSERT INTO employer_candidat (employer_id,candidat_id)"
                + "VALUES (?,?)";
      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setInt(1, employer.getId());
            pst.setInt(2, candidat.getId());
            try {
            //candidat.setNbr_follow(candidat.getNbr_follow()+1); 
            String requete1 = "UPDATE user SET  nbr_follow= ? " 
					+ " WHERE id = ?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setInt(1,candidat.getNbr_follow()+1);
            pst1.setInt(2, candidat.getId());
             System.out.println(candidat.getNbr_follow());
            System.out.println(employer.getId());
            System.out.println( candidat.getId());
            
          
       
            pst1.executeUpdate();
            pst.executeUpdate();
            System.out.println("follow ajoutee !");
            }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       public void unfollow(User employer, User candidat) {
        String requete = "delete from employer_candidat where id_employer =? and id_candidat=?";
               
      
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
          
            pst.setInt(1, employer.getId());
            pst.setInt(2, candidat.getId());
            try {
            //candidat.setNbr_follow(candidat.getNbr_follow()+1); 
            String requete1 = "UPDATE user SET  nbr_follow= ? " 
					+ " WHERE id = ?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setInt(1,candidat.getNbr_follow()-1);
            pst1.setInt(2, candidat.getId());
             System.out.println(candidat.getNbr_follow());
            System.out.println(employer.getId());
            System.out.println( candidat.getId());
            
          
       
            pst1.executeUpdate();
            pst.executeUpdate();
            System.out.println("follow ajoutee !");
            }catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
       public boolean searchFollow(User t, User candidat) {
          User s = new User();
          s=null;
        
        String requete = "SELECT * FROM employer_candidat where employer_id = ? and candidat_id = ?";
       
        try {
             PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.setInt(2, candidat.getId());
            System.out.println(t.getAddress());
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch(SQLException ex) {
            System.err.println(ex.getMessage());
        }
       return false;
    }
       public void block(User candidat) {
          String requete = "UPDATE user SET etat =?, block=?"
					+ " WHERE id = ?";
            try {
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1,1);
                        pst.setString(2,"blocked");
                        pst.setInt(3,candidat.getId());
                        pst.executeUpdate();
            }
              catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
       }
       
        public void unblock(User candidat) {
          String requete = "UPDATE user SET etat =?, block=?"
					+ " WHERE id = ?";
            try {
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1,0);
                         pst.setString(2,"not blocked");
                        pst.setInt(3,candidat.getId());
                        pst.executeUpdate();
            }
              catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
       }
         public int RecupPwd(String address){
        String requete="SELECT * FROM user where address=? ";
        ResultSet res;
        try {
            PreparedStatement pst =
                    cnx.prepareStatement(requete);
            
            pst.setString(1, address);
            
            
            res= pst.executeQuery();
            if (res.last())
            {
                System.out.println("address Trouvée");
                return 1;
            }
            

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
       
    
//         public boolean follow1 (int idEmployer , int idCandidat){
//        try {
//            String request="SELECT * FROM employer_candidat WHERE employer_id="+idEmployer+" and candidat_id="+idCandidat;
//            Statement ste=cnx.createStatement();
//            ResultSet result = ste.executeQuery(request);
//            if (result.next()) {
//                String req = "DELETE FROM employer_candidat WHERE employer_id="+idEmployer+" and candidat_id="+idCandidat;
//                Statement statement=cnx.createStatement();
//                statement.executeUpdate(req);
//                return false;
//            } else {
//                String req = "INSERT INTO employer_candidat(employer_id, candidat_id) VALUES("+idEmployer+", "+idCandidat+")";
//                Statement statement=cnx.createStatement();
//                statement.executeUpdate(req);
//                return true;
//            }
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
    
}
