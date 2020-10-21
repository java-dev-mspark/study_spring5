package com.mspark.tacos.data;

import com.mspark.tacos.domain.Taco;
import org.springframework.data.repository.CrudRepository;

public interface TacoRepository extends CrudRepository<Taco, Long> {

//    Taco save(Taco taco);
}
