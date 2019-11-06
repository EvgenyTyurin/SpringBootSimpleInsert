package evgenyt.tacos.data;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import evgenyt.tacos.Ingredient;
import evgenyt.tacos.Taco;

/**
 * Work with Taco table via jdbc template 
 */

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbc;
		
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Taco save(Taco taco) {
		// Save taco to Taco table and get Taco.id
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		// Save taco ingredients to Taco_Ingredients table
		for (Ingredient ingredient : taco.getIngredients() ) {
			saveIngredientToTaco(ingredient, tacoId);
		}
		return taco;
	}
	
	// Create new Taco record and return an id
	private long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory preparedStatementCreatorFactory =  
				new PreparedStatementCreatorFactory(
						"insert into Taco(name, createdAt) values (?, ?)",
						Types.VARCHAR, Types.TIMESTAMP
					);
		// For key generation
		preparedStatementCreatorFactory.setReturnGeneratedKeys(true);		
		PreparedStatementCreator psc = preparedStatementCreatorFactory
				.newPreparedStatementCreator(
						Arrays.asList(taco.getName(), taco.getCreatedAt())
				);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		// Insert taco and get a id in keyHolder
		jdbc.update(psc, keyHolder);
		return keyHolder.getKey().longValue();
	}
	
	// Create Taco_Ingredients table row
	private void saveIngredientToTaco (Ingredient ingredient, long tacoId) {
		jdbc.update("insert into Taco_Ingredients(taco, ingredient) " + 
				"values (?, ?)", tacoId, ingredient.getId());
	}

}
