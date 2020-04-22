package gb.xokyopo.game.core;

import gb.xokyopo.game.entitys.Classes;
import gb.xokyopo.game.entitys.Hero;
import gb.xokyopo.game.entitys.Team;

//TODO если будет несколько команд?
//TODO стоит ли расширять или нет вообще для этого?
public class TeamCombatsAi {
    private Team heroTeam;
    private Team enemyTeam;
    private boolean isEnemyTurn;
    private final CombatReport combatReport;

    public TeamCombatsAi() {
        this.combatReport = new CombatReport();
    }

    private Team getAttackerTeam() {
        return (this.isEnemyTurn) ? this.enemyTeam : this.heroTeam;
    }

    private Team getDefenderTeam() {
        return (this.isEnemyTurn) ? this.heroTeam : this.enemyTeam;
    }

    public void setHeroTeam(Team heroTeam) {
        this.heroTeam = heroTeam;
    }

    public void setEnemyTeam(Team enemyTeam) {
        this.enemyTeam = enemyTeam;
    }

    private void requiredNotNullTeam() {
        if (this.heroTeam == null || this.enemyTeam == null) throw new RuntimeException("Team can't be null");
    }

    public boolean hasTurn() {
        return this.hasTurnPrivate();
    }

    private boolean hasTurnPrivate() {
        this.requiredNotNullTeam();
        return !this.heroTeam.isEmpty() && !this.enemyTeam.isEmpty();
    }

    public String runTurn() {
        String report = "Ходов больше не осталось";
        if (this.hasTurnPrivate()) {
            this.isEnemyTurn = !this.isEnemyTurn;
            Team attackerTeam = this.getAttackerTeam();
            Hero attackerHero = attackerTeam.getRandomHero();
            if (attackerHero.getType().equals(Classes.HEALER)) {
                report = this.applyHealing(attackerHero, attackerTeam.getName(), attackerTeam.getRandomHero());
            } else {
                Team defenderTeam = this.getDefenderTeam();
                report = this.applyDamage(attackerHero, attackerTeam.getName(), defenderTeam.getRandomHero(), defenderTeam.getName());
            }
        }
        return report;
    }

    private String applyDamage(Hero attackerHero, String attackerTeamName, Hero defenderHero, String defenderTeamName) {
        int damage = attackerHero.getDamage();
        boolean isCritical = attackerHero.isCriticalDamage(damage);
        defenderHero.takeDamage(damage);
        return this.combatReport.getInfoDamage(attackerHero.getName(), attackerTeamName, damage, isCritical, defenderHero.getName(), defenderTeamName) +
                "\n" + this.combatReport.getHealthReport(defenderHero.getName(), defenderTeamName, defenderHero.getHealth());
    }

    private String applyHealing(Hero healingHero, String healingTeam, Hero healedHero) {
        int healing = healingHero.getHealing();
        healedHero.takeHealing(healing);
        return this.combatReport.getInfoHealing(healingHero.getName(), healingTeam, healing, healedHero.getName()) +
                "\n" + this.combatReport.getHealthReport(healedHero.getName(), healingTeam, healedHero.getHealth());
    }
}
