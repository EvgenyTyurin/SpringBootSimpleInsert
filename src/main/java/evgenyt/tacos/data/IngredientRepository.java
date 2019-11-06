package evgenyt.tacos.data;

import evgenyt.tacos.Ingredient;

/**
 * Work with Ingredients table 
 */

public interface IngredientRepository {

	Iterable<Ingredient> findAll();
	Ingredient findOne(String id);
	Ingredient save(Ingredient ingredient);
	
}
