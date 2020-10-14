package com.mspark.tacos.web.controller;

import com.mspark.tacos.domain.Ingredient;
import com.mspark.tacos.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model){

        List<Ingredient> ingredients
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
                            ).collect(Collectors.toList());

        final Map<Ingredient.Type, List<Ingredient>> typeListMap
                = ingredients.stream().collect(Collectors.groupingBy(Ingredient::getType));

        typeListMap.forEach((k, v) -> { model.addAttribute(k.toString().toLowerCase(), v);});

        model.addAttribute("taco", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(Taco design){
        log.info("Process design : " + design);

        return "redirect:/orders/current";
    }

}
