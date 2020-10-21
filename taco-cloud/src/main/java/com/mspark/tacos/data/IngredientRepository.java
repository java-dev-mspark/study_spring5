package com.mspark.tacos.data;

import com.mspark.tacos.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
//    Iterable<Ingredient> findAll();
//    Ingredient findById(String id);
//    Ingredient save(Ingredient ingredient);
}
