package gb.xokyopo.game.core;

import gb.xokyopo.game.entitys.Hero;
import gb.xokyopo.game.entitys.Team;
import gb.xokyopo.game.entitys.Winner;

import java.util.List;

public class CombatReport {
    public String getInfoDamage(String attackerHeroName, String attackerTeamName, int damage, boolean isCritical, String defenderHeroName, String DefenderTeamName) {
        return String.format("Герой %s из команды %s нанес герою %s из команды %s %s ед. %sурона",
                attackerHeroName, attackerTeamName, defenderHeroName, DefenderTeamName, damage, (isCritical) ? "критического " : "");
    }

    public String getInfoHealing(String healingHeroName, String healingTeamName, int healing, String healedHeroName) {
        return String.format("Герой %s из команды %s вылечил %s из команды %s на %s здоровья",
                healingHeroName, healingTeamName, healedHeroName, healingTeamName, healing);
    }

    public String getHealthReport(String heroName, String teamName, int health) {
        return String.format("Теперь у Героя %s из команды %s %s здоровья %s", heroName, teamName, health, (health <= 0) ? " и он мерт" : "");
    }

    public String getWinReport(String firstTeamName, Winner winner, String secondTeamName) {
        if (winner.equals(Winner.NOT)) {
            return "Победила дружба";
        } else {
            return String.format("Команда %s победила команду %s",
                    winner.equals(Winner.LEFT)? firstTeamName : secondTeamName, (winner.equals(Winner.RIGHT) ? firstTeamName : secondTeamName));
        }
    }

    public String getTeamReports(String teamName, int teamSize) {
        return String.format("В команде %s живых: %s герой(ев)", teamName, teamSize);
    }

    public String getApplyFilterMessage(Team team, List<Hero> heroes) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean moreThenOne = heroes.size() != 1;
        stringBuilder.append("Из команды ").append(team.getName()).append(" был").append(moreThenOne ? "о" : "")
                .append(" удален").append(moreThenOne ? "о " : " ").append(heroes.size()).append(" герой") . append(moreThenOne ? "ев " : " ");
        heroes.forEach(hero -> {
            stringBuilder.append("\n").append(hero.getName());
        });
        return stringBuilder.toString();
    }
}
