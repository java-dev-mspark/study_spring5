package com.mspark.tacos;

import com.mspark.tacos.data.IngredientRepository;
import com.mspark.tacos.domain.Ingredient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.mspark.tacos.data", "com.mspark.tacos.web.config"})
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repository){
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				repository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WARP));
				repository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WARP));
				repository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
				repository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
				repository.save(new Ingredient("TMTO", "Diced Tomatos", Ingredient.Type.VEGGIES));
				repository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
				repository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
				repository.save(new Ingredient("JACK", "Montererey Jack", Ingredient.Type.CHEESE));
				repository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
				repository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
			}
		};
	}

}
