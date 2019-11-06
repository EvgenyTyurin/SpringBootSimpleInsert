package evgenyt.tacos.data;

import evgenyt.tacos.Taco;

/**
 * Work with Taco table 
 */

public interface TacoRepository {

	Taco save(Taco design);
	
}
