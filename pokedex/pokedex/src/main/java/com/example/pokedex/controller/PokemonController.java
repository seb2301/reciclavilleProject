package com.example.pokedex.controller;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    @PostMapping
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(pokemonService.savePokemon(pokemon));
    }

    @PutMapping("/{numero}")
    public ResponseEntity<Pokemon> updatePokemon(@PathVariable String numero, @RequestBody Pokemon pokemon) {
        pokemon.setNumero(numero);
        return ResponseEntity.ok(pokemonService.savePokemon(pokemon));
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> deletePokemon(@PathVariable String numero) {
        pokemonService.deletePokemon(numero);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{numero}")
    public ResponseEntity<Pokemon> getPokemonByNumero(@PathVariable String numero) {
        Optional<Pokemon> pokemon = pokemonService.getPokemonByNumero(numero);
        return pokemon.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Pokemon> getAllPokemons() {
        return pokemonService.getAllPokemons();
    }
}
