package evgenyt.tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import lombok.Data;

/**
 * Client order class with some fields validation
 * @author EUTyrin
 *
 */

@Data // Generates constructor, getters, setters 
public class Order {
	
	private Long id;
	private Date placedAt;
	
	@NotBlank(message="Name is requiried")
	private String name;
	
	@NotBlank(message="Address is requried")
	private String address;
	
	// @CreditCardNumber(message="Not a valid credit number")
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", 
			message="Must be formatted MM/YY")	
	private String ccExpiration;
	
	@Digits(integer=3, fraction=0, message= "Invalid CVV")
	private String ccCVV;
	
	private List<Taco> tacos = new ArrayList<>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}
	
}
