package Xokyopo.hw_1;

import Xokyopo.hw_1.arena.Arena;
import Xokyopo.hw_1.arena.obstacles.template.Obstacle;
import Xokyopo.hw_1.team.Team;
import Xokyopo.hw_1.team.players.template.Athlete;

public class Contest {
    private Arena arena;
    private Team team;
    private boolean[] benchs;
    private String msg = "Наш %s %s %s смог преодолеть  %s в %s м \n";
    private String msgInfo = "Наш %s %s смог преодолеть все испытания \n";
    private boolean withLose = true;

    public Contest(boolean _withLose) {
        this.withLose = _withLose;
        this.arena = new Arena(Arena.ObstaclesType.MARATHON, Arena.ObstaclesType.RIVER , Arena.ObstaclesType.WALL);
        this.team = new Team(Team.Type.HUMAN, Team.Type.CAT, Team.Type.DOG);
        this.enablingBench();
    }

    private void  enablingBench() {
        this.benchs = new boolean[this.team.getAthlete().length];
        for (int i = 0 ; i < this.benchs.length ; i++) {
            this.benchs[i] = true;
        }
    }

    public void starContest() {
        this.printingStartInfo();
        for (Obstacle obstacle : this.arena.getObstacles()) {
            for (int i = 0 ; i < this.team.getAthlete().length ; i++) {
                if (this.withLose || this.benchs[i]) {
                    this.benchs[i] = obstacle.isOvercoming(this.team.getAthlete()[i]) && this.benchs[i];
                    this.showingMsg(this.benchs[i], this.team.getAthlete()[i], obstacle);
                }
            }
        }
        this.printingEndInfo();
    }

    private void showingMsg(boolean _isOvercome, Athlete _athlete, Obstacle _obstacle) {
        System.out.printf(this.msg, _athlete.getType(), _athlete.getName() , (_isOvercome) ? "" : "НЕ", _obstacle.getName(), Double.toString(_obstacle.getCap()));
    }

    private void printingStartInfo() {
        System.out.println("Начинаются соревнования:");
        System.out.println("");
        this.team.printingInfo();
        System.out.println("");
        this.arena.printingInfo();
        System.out.println("");
    }

    private void printingEndInfo() {
        System.out.println("");
        System.out.println("Итоги соревнований: ");
        for (int i = 0 ; i < this.team.getAthlete().length ; i++) {
            if (this.benchs[i]) {
                System.out.printf(this.msgInfo, this.team.getAthlete()[i].getType(), this.team.getAthlete()[i].getName());
            }
        }
    }

}
