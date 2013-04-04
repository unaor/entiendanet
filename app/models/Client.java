package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Min;

import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class Client extends Model {
	
	@Id
	public Long clientId;
	@Required
	@MinLength(value=3) @MaxLength(value=15)
	public String firstName;
	@Required
	@MinLength(value=3) @MaxLength(value=15)
	public String lastName;
	@Email
	@Required
	@Column(unique=true)
	public String email;
	@Required
	@MinLength(value=6) @MaxLength(value=15)
	public String password;
	public String country;
	public String business;
	
	public Client(String firstName, String lastName,
			String email, String password, String country, String business) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.country = country;
		this.business = business;
	}
	
	
	public static Finder<Long,Client> finder = new Finder<Long,Client>(Long.class,Client.class);
	
	public static Client createClient(String firstName,String lastName,String email,
			String password,String country,String business){
		Client client = new Client(firstName, lastName, email, password, country, business);
		client.save();
		return client;
	}
	
	public static Long saveClient(Client client){
		client.save();
		return client.clientId;
	}
	public String validate(){
		if(firstName==null ||firstName.isEmpty()){
			return "First Name is required";
		}
		return null;
	}
	
	

}
