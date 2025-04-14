package com.example.pokedex.loader;

import com.example.pokedex.model.Pokemon;
import com.example.pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Override
    public void run(String... args) throws Exception {

        Pokemon[] pokemons = new Pokemon[]{
                new Pokemon("001", "Bulbasaur", "A Grass/Poison type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png", "Grass", "Seed", "Forests", 0.7, 6.9),
                new Pokemon("004", "Charmander", "A Fire type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/004.png", "Fire", "Lizard", "Mountains", 0.6, 8.5),
                new Pokemon("007", "Squirtle", "A Water type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png", "Water", "Tiny Turtle", "Lakes", 0.5, 9.0),
                new Pokemon("025", "Pikachu", "An Electric type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png", "Electric", "Mouse", "Urban areas", 0.4, 6.0),
                new Pokemon("132", "Ditto", "A Normal type Pokémon capable of transforming.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/132.png", "Normal", "Transform", "Anywhere", 0.3, 4.0),
                new Pokemon("150", "Mewtwo", "A Psychic type Legendary Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/150.png", "Psychic", "Genetic", "Cerulean Cave", 2.0, 122.0),
                new Pokemon("003", "Venusaur", "A Grass/Poison type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/003.png", "Grass", "Seed", "Forests", 2.0, 100.0),
                new Pokemon("004", "Charizard", "A Fire/Flying type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/006.png", "Fire", "Flame", "Volcanoes", 1.7, 90.5),
                new Pokemon("009", "Blastoise", "A Water type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/009.png", "Water", "Shellfish", "Swamps", 1.6, 85.5),
                new Pokemon("025", "Pikachu", "An Electric type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png", "Electric", "Mouse", "Urban areas", 0.4, 6.0),
                new Pokemon("151", "Mew", "A Psychic type Mythical Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/151.png", "Psychic", "New Species", "Mew's Island", 0.4, 4.0),
                new Pokemon("144", "Articuno", "A Legendary Ice/Flying type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/144.png", "Ice", "Freezer", "Seafoam Islands", 1.7, 55.4),
                new Pokemon("145", "Zapdos", "A Legendary Electric/Flying type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/145.png", "Electric", "Thunder", "Power Plants", 1.6, 52.6),
                new Pokemon("146", "Moltres", "A Legendary Fire/Flying type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/146.png", "Fire", "Flame", "Victory Road", 1.8, 60.0),
                new Pokemon("199", "Slowking", "A Water/Psychic type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/199.png", "Water", "Royal", "Ruins", 2.0, 79.5),
                new Pokemon("134", "Vaporeon", "A Water type Evolution Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/134.png", "Water", "Bubble Jet", "Lakes", 1.0, 29.0),
                new Pokemon("135", "Jolteon", "An Electric type Evolution Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/135.png", "Electric", "Lightning", "Urban areas", 0.8, 24.5),
                new Pokemon("136", "Flareon", "A Fire type Evolution Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/136.png", "Fire", "Flame", "Volcanoes", 1.0, 25.0),
                new Pokemon("132", "Ditto", "A Normal type Pokémon capable of transforming.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/132.png", "Normal", "Transform", "Anywhere", 0.3, 4.0),
                new Pokemon("214", "Heracross", "A Bug/Fighting type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/214.png", "Bug", "Single Horn", "Forests", 1.5, 54.0),
                new Pokemon("392", "Infernape", "A Fire/Fighting type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/392.png", "Fire", "Flame", "Volcanoes", 1.2, 55.0),
                new Pokemon("448", "Lucario", "A Fighting/Steel type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/448.png", "Fighting", "Aura", "Ancient Ruins", 1.2, 54.0),
                new Pokemon("493", "Arceus", "A Normal type Legendary Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/493.png", "Normal", "Alpha", "Sinnoh", 3.2, 320.0),
                new Pokemon("597", "Emolga", "An Electric/Flying type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/597.png", "Electric", "Thunderbolt", "Forest", 0.4, 5.0),
                new Pokemon("559", "Scraggy", "A Dark/Fighting type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/559.png", "Dark", "Hoodlum", "Deserts", 0.6, 11.8),
                new Pokemon("658", "Froakie", "A Water type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/658.png", "Water", "Bubble", "Rivers", 0.6, 7.0),
                new Pokemon("722", "Rowlet", "A Grass/Flying type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/722.png", "Grass", "Grass Quill", "Forests", 0.3, 1.5),
                new Pokemon("777", "Torracat", "A Fire type Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/777.png", "Fire", "Fire Cat", "Mountains", 0.7, 25.0),
                new Pokemon("892", "Zarude", "A Dark/Grass type Legendary Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/892.png", "Dark", "Savagery", "Jungle", 1.8, 70.0),
                new Pokemon("899", "Regieleki", "An Electric type Legendary Pokémon.", "https://assets.pokemon.com/assets/cms2/img/pokedex/full/899.png", "Electric", "Volt", "Energy Field", 1.1, 145.0)
        };

        for (Pokemon pokemon : pokemons) {
            pokemonRepository.save(pokemon);
        }
    }
}
