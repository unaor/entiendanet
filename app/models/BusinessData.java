package models;

import java.util.Date;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class BusinessData extends Model {
	
	@Id
	@OneToOne
	public Long businessId;
	@Required
	@Nonnull
	public AppUser appUser;
	@Required @MinLength(value=2) @MaxLength(value=15)
	public String businessName;
	@Required @MinLength(value=5) @MaxLength(value=30)
	public String businessAddress;
	@Required @MinLength(value=7) @MaxLength(value=30)
	public Long businessPhone;
	public Long businessFax;
	public Long businessZipCode;
	public String businessState;
	@Required @MinLength(value=4) @MaxLength(value=30)
	public String businessCountry;
	
	protected BusinessData(){};
	
	public BusinessData(AppUser user){
		this.appUser=user;
	}
	

}
