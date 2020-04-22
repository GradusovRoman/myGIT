package gb.xokyopo.game.core;

import gb.xokyopo.game.entitys.Classes;
import gb.xokyopo.game.entitys.Hero;
import gb.xokyopo.game.entitys.Team;
import gb.xokyopo.game.entitys.Winner;
import gb.xokyopo.game.factorys.HeroFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameCore {
    private int maxHero;
    private boolean onlyUniqueHero;
    private final HeroFactory heroFactory;
    private final CombatReport combatReport;
    private final TeamCombatsAi teamCombatsAi;
    private final Team heroTeam;
    private final Team enemyTeam;
    private boolean running;

    public GameCore() {
        this.heroFactory = HeroFactory.getHeroFactory();
        this.combatReport = new CombatReport();
        this.teamCombatsAi = new TeamCombatsAi();
        this.heroTeam = new Team();
        this.enemyTeam = new Team();
        this.teamCombatsAi.setEnemyTeam(this.enemyTeam);
        this.teamCombatsAi.setHeroTeam(this.heroTeam);
        this.running = false;
    }

    public boolean isOnlyUniqueHero() {
        return onlyUniqueHero;
    }

    public void changeOnlyUniqueHero() throws Exception {
        this.requiredNotRunGame();
        this.onlyUniqueHero = !onlyUniqueHero;
        List<Hero> heroDeletedList = this.applyUniqueHeroFilter(this.heroTeam);
        List<Hero> enemyDeletedList = this.applyUniqueHeroFilter(this.enemyTeam);
        if (heroDeletedList.size() > 0 || enemyDeletedList.size() > 0) {
            throw new Exception(
                    this.combatReport.getApplyFilterMessage(this.heroTeam, heroDeletedList) + "\n" +
                    this.combatReport.getApplyFilterMessage(this.enemyTeam, enemyDeletedList));
        }
    }

    public void requiredNotRunGame() {
        if (this.running) throw new RuntimeException("can't modify while running, end game first");
    }

    public int getMaxHero() {
        return maxHero;
    }

    public void setMaxHero(int maxHero) {
        this.requiredNotRunGame();
        this.maxHero = maxHero;
    }

    public void addHeroToHeroTeam(Classes classes) throws Exception {
        this.addHeroInTeam(classes, this.heroTeam);
    }

    public void addHeroToEnemyTeam(Classes classes) throws Exception {
        this.addHeroInTeam(classes, this.enemyTeam);
    }

    public void addHeroInTeam(Classes classes, Team team) throws Exception {
        this.requiredNotRunGame();
        Hero newHero = this.heroFactory.getHero(classes);
        if (this.isValidHero(team, newHero)) {
            if (team.size() < this.maxHero){
                try {
                    team.addHero(newHero);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                throw new Exception("Вы добавили максимальное количесво герое: " + this.maxHero);
            }
        } else {
            throw new Exception("Можно добавить только уникальных  героев");
        }
    }

    public void setHeroTeamName(String name) {
        this.requiredNotRunGame();
        this.heroTeam.setName(name);
    }

    public void setEnemyTeamName(String name) {
        this.requiredNotRunGame();
        this.enemyTeam.setName(name);
    }

    private boolean haveWinner() {
        if (this.teamCombatsAi.hasTurn()) {
            if (this.isOnlyHealerAlive(this.heroTeam) && this.isOnlyHealerAlive(this.enemyTeam)) {
                this.killingHealer(this.heroTeam);
                this.killingHealer(this.enemyTeam);
                return true;
            }
            return false;
        } else {
            return true;
        }
    }

    private boolean isOnlyHealerAlive(Team team) {
        int[] count = new int[1];
        team.getAll().forEach(hero->{
            if (hero.getType().equals(Classes.HEALER)){
                count[0]++;
            }
        });
        return team.size() == count[0];
    }

    private void killingHealer(Team team) {
        team.applyHeroFilter(hero-> true);
    }

    private Winner getWinner(Team leftTeam, Team rightTeam) {
        Winner result;
        if (leftTeam.isEmpty() && rightTeam.isEmpty()) {
            result = Winner.NOT;
        } else if (rightTeam.isEmpty()) {
            result = Winner.LEFT;
        } else {
            result = Winner.RIGHT;
        }
        return result;
    }

    private String getWinsReport() {
        return this.combatReport.getWinReport(this.heroTeam.getName(), this.getWinner(this.heroTeam, this.enemyTeam), this.enemyTeam.getName());
    }

    private boolean isValidHero(Team team, Hero newHero) {
        boolean[] result = new boolean[]{true};
        if (this.isOnlyUniqueHero()) {
            team.getAll().forEach(hero -> {
                if (hero.getType().equals(newHero.getType())) {
                    result[0] = false;
                    return;
                }
            });
        }
        return result[0];
    }

    private List<Hero> applyUniqueHeroFilter(Team team) {
        Set<Classes> mask = new HashSet<>();
        return team.applyHeroFilter(hero->{
            if (mask.contains(hero.getType())) {
                return true;
            } else {
                mask.add(hero.getType());
                return false;
            }
        });
    }

    public boolean isReady() {
        boolean result = this.maxHero > 0;
        result = result && !this.heroTeam.getName().equals("");
        result = result && !this.enemyTeam.getName().equals("");
        result = result && this.enemyTeam.size() == this.maxHero;
        result = result && this.heroTeam.size() == this.maxHero;
        return result;
    }

    public String runTurn() {
        String logMsg = "игра не готова к запуску";

        if (this.canTurn()) {
            this.running = true;
            if(this.haveWinner()) {
                this.running = false;
               logMsg = this.getWinsReport();
            } else {
                logMsg = this.teamCombatsAi.runTurn();
            }
        }
        return logMsg;
    }

    public boolean canTurn() {
        return this.running || this.isReady();
    }

    public List<Hero> getHeroList() {
        return this.heroTeam.getAll();
    }

    public List<Hero> getEnemyList() {
        return this.enemyTeam.getAll();
    }
}
