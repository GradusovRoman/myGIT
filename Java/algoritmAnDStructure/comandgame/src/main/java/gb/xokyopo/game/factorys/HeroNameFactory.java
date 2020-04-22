package gb.xokyopo.game.factorys;

import java.util.Random;

public class HeroNameFactory {
    private static HeroNameFactory heroNameFactory;
    private final Random random;
    private final String[] names = {"Abbadon","Alchemist","Ancient Apparition","Anti-Mage","Axe","Bane","Batrider",
            "Beastmaster","Bloodseeker","Bounty Hunter","Brewmaster","Bristleback","Broodmother","Centaur Warrunner",
            "Chaos Knight","Chen","Clinkz","Clockwerk","Crystal Maiden","Dark Seer","Dazzle","Disruptor","Doom",
            "Drow Ranger","Earth Spirit","Earthshaker","Elder Titan","Ember Spirit","Enchantress","Enigma",
            "Faceless Void","Gyrocopter","Huskar","Invoker","Jakiro","Juggernaut","Keeper of the Light","Kunkka",
            "Legion Commander","Leshrac","Lich","Lifestealer","Lina","Lion","Lone Druid","Luna","Lycan","Magnus","Meepo",
            "Mirana","Morphling","Naga Siren","Nature’s Prophet","Necrophos","Night Stalker","Ogre Magi","Omniknight",
            "Oracle","Outworld Devourer","Phantom Assassin","Phantom Lancer","Phoenix","Puck","Pudge","Pugna",
            "Queen of Pain","Razor","Rubick","Riki","Sand King","Shadow Demon","Shadow Fiend","Shadow Shaman","Silencer",
            "Skywrath Mage","Slardar","Slark","Sniper","Spectre","Spirit Breaker","Storm Spirit","Sven","Techies",
            "Templar Assassin","Terrorblade","Tidehunter","Timbersaw","Tinker","Tiny","Treant Protector","Troll Warlord",
            "Касательно Tusk","Undying","Ursa","Vengeful Spirit","Venomancer","Viper","Visage","Warlock","Weaver",
            "Winter Wyvern","Zeus"};

    public HeroNameFactory() {
        this.random = new Random();
    }

    public static HeroNameFactory getHeroNameFactory(){
        if (HeroNameFactory.heroNameFactory == null)
            HeroNameFactory.heroNameFactory = new HeroNameFactory();
        return HeroNameFactory.heroNameFactory;
    }

    public String getRandomName() {
        return this.names[this.getRandomInt(this.names.length)];
    }

    public int getRandomInt(int seed) {
        return this.random.nextInt(seed);
    }
}
