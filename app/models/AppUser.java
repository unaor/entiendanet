package models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceException;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import org.apache.commons.codec.DecoderException;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import util.PasswordEncoder;
import util.PrepareModel;
import authtoken.validator.AuthenticityToken;

@Entity 
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
public class AppUser extends Model {
	@Transient
	@AuthenticityToken
	 public String authtoken;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	public Long userId;
	@Required @MinLength(value=2) @MaxLength(value=15)
	public String userFirstName;
	@Required @MinLength(value=2) @MaxLength(value=15)
	public String userSurname;
	@Required @Email @Column(unique=true , updatable=false)
	public String email;
	@Required
	public String hashedPassword;
	public boolean isAdmin=true;
	@OneToOne
	public BusinessData businessData;
	public Date registrationDate;
	
	public AppUser(){};
	
	public static Long createAppUser(AppUser user){
		
		try{
			user.registrationDate= new Date();
			encodePassword(user);
			PrepareModel.prepareAppUser(user);
			user.save();
			return user.userId;
		}catch(PersistenceException ex){
			return -1L;
		}
		
	}
	
	public static Finder<Long,AppUser> finder = new Finder<Long,AppUser>(Long.class,AppUser.class);
	
	public static String validate(AppUser user){
		if(user.userFirstName==null){
			return "A name is required";
		}
		
		return null;
	}
	
	private static void  encodePassword(AppUser user){
		
		PasswordEncoder encoder =	PasswordEncoder.getInstance();
		String maskedPassword;
		
			try {
				maskedPassword = encoder.encode(user.hashedPassword, user.registrationDate.toString());
				user.hashedPassword= maskedPassword;
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (DecoderException e) {
				e.printStackTrace();
			}
		
	}
	
	public static AppUser authenticate(String email , String password){
		AppUser dbUser = finder.where().eq("email", email).findUnique();
		if(dbUser ==null) return null;
		AppUser authUser = new AppUser();
		authUser.registrationDate=dbUser.registrationDate;
		authUser.hashedPassword=password;
		encodePassword(authUser);
		if(dbUser.hashedPassword.equals(authUser.hashedPassword)){
		return dbUser;
		}
		else
			{
			return null;
			}
		}
	
	public static boolean isEmailFree(String email){
		boolean isEmpty=true;
		AppUser result = finder.select("email").where().eq("email", email).findUnique();
		if (result!=null) isEmpty=false;
		return isEmpty;
	}
	
	public static AppUser getUserByEmail(String email){
		return finder.where().eq("email", email).findUnique();
	}
	
	

}
