package com.mspark.tacos.web.controller;

import com.mspark.tacos.data.IngredientRepository;
import com.mspark.tacos.data.TacoRepository;
import com.mspark.tacos.domain.Ingredient;
import com.mspark.tacos.domain.Order;
import com.mspark.tacos.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private static final String DESIGN_VIEW = "design";
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository, TacoRepository tacoRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
    }

    @GetMapping
    public String showDesignForm(Model model){

/*        List<Ingredient> ingredients
                = Stream.of(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WARP),
                            new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WARP),
                            new Ingredient("CRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                            new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                            new Ingredient("TMTO", "Decied Tomatos", Ingredient.Type.VEGGIES),
                            new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                            new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                            new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                            new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                            new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
                            ).collect(Collectors.toList());*/

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepository.findAll().forEach(i -> ingredients.add(i));

        final Map<Ingredient.Type, List<Ingredient>> typeListMap
                = ingredients.stream().collect(Collectors.groupingBy(Ingredient::getType));

        typeListMap.forEach((k, v) -> { model.addAttribute(k.toString().toLowerCase(), v);});

        model.addAttribute("taco", new Taco());

        return DESIGN_VIEW;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco design,
                                Errors errors,
                                @ModelAttribute Order order){
        if(errors.hasErrors()){
            return DESIGN_VIEW;
        }

        log.info("Process design : " + design);

        Taco taco = tacoRepository.save(design);
        order.addDesign(taco);

        return "redirect:/orders/current";
    }

}
