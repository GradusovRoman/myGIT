package xokyopo.test_task;

//7 раз пришлось запускать что бы найти все ошибки.
// затрачено на все было 2 часа 8 минут

public class FillingMap {
    private int[][] map;
    private final FillingFormula formula;
    private int nextNumber;
    private Rect noFilling;
    private boolean clockwise = true;

    public FillingMap(int[][] map, FillingFormula formula, int startNumber, boolean clockwise) {
        this.map = map;
        this.formula = formula;
        this.nextNumber = startNumber;
        this.noFilling = new Rect(
                new Pos(0,0, map[0].length -1, map.length - 1),
                new Pos(map[0].length - 1,map.length - 1, map[0].length - 1, map.length - 1)
        );

        this.clockwise = clockwise;
    }

    public FillingMap(int[][] map, FillingFormula formula, int startNumber) {
        this.map = map;
        this.formula = formula;
        this.nextNumber = startNumber;
        this.noFilling = new Rect(
                new Pos(0,0, map[0].length -1, map.length - 1),
                new Pos(map[0].length - 1,map.length - 1, map[0].length - 1, map.length - 1)
        );

    }

    public void fill() {
        for (int count = 0; !this.noFilling.rightBot.equals(this.noFilling.leftTop); count++) {
            int commandInt = count % 4; // 4 количество сторон объекта;
            Pos[] line = {this.noFilling.leftTop};

            if (commandInt == 0) {
                line = this.noFilling.getTopLine();
                this.noFilling.leftTop.incrementY();
            } else if (commandInt == 1) {
                line = this.noFilling.getRightLine();
                this.noFilling.rightBot.decrementX();
            } else if (commandInt == 2) {
                line = this.noFilling.getBotLine();
                this.noFilling.rightBot.decrementY();
            } else if (commandInt == 3) {
                line = this.noFilling.getLeftLine();
                this.noFilling.leftTop.incrementX();
            }

            this.fillingLine((commandInt >= 2)? this.revertLine(line): line);
        }
        this.fillingLine(this.noFilling.leftTop);
        if (!this.clockwise) this.map = this.mirroringMapByY(map);
    }

    public int[][] mirroringMapByY(int[][] map) {
        int[][] newMap = new int[map.length][map[0].length];
        for (int y = 0; y < map.length; y++) {
            for (int x =0; x < map.length; x++) {
                newMap[y][map[0].length - x - 1] = map[y][x];
            }
        }
        return newMap;
    }


    private Pos[] revertLine(Pos... line) {
        Pos[] arr = new Pos[line.length];
        for (int i = 0; i < line.length; i++) {
            arr[i] = line[line.length - i - 1];
        }
        return arr;
    }

    public void printMap() {
        System.out.println("печатаю сообщение");
        for (int y = 0; y < this.map.length; y++) {
            for (int x = 0; x < this.map[0].length; x++) {
                System.out.print(this.map[y][x] + "\t");
            }
            System.out.println("");
        }
    }

    private void  fillingLine(Pos... arr) {
        for (int i = 0; i < arr.length; i++) {
            this.fillingCell(arr[i], this.nextNumber);
            this.nextNumber = this.formula.fill(this.nextNumber);
        }
    }

    private void fillingCell(Pos pset, int number) {
        this.map[pset.y][pset.x] = number;
    }



    protected class Rect {
        private Pos leftTop;
        private Pos rightBot;

        public Rect(Pos leftTop, Pos rightBot) {
            this.leftTop = leftTop;
            this.rightBot = rightBot;
        }


        private   Pos[] getHorizontalLine(boolean top) {
            Pos[] line = new Pos[this.rightBot.x + 1 - this.leftTop.x];
            int count = 0;
            for (int x = this.leftTop.x; x <= this.rightBot.x ; x++) {
                line[count] = new Pos(x, (top)? this.leftTop.y : this.rightBot.y);
                count++;
            }
            return line;
        }

        private   Pos[] getVerticalLine(boolean left) {
            Pos[] line = new Pos[this.rightBot.y + 1 -this.leftTop.y];
            int count = 0;
            for (int y = this.leftTop.y; y <= this.rightBot.y ; y++) {
                line[count] = new Pos((left) ? this.leftTop.x: this.rightBot.x, y);
                count++;
            }
            return line;
        }

        public Pos[] getTopLine() {
            return this.getHorizontalLine(true);
        }

        public Pos[] getBotLine() {
            return this.getHorizontalLine(false);
        }

        public Pos[] getLeftLine() {
            return this.getVerticalLine(true);
        }

        public  Pos[] getRightLine() {
            return this.getVerticalLine(false);
        }
    }

    protected class Pos {
        public int x;
        public int y;

        private int minX = 0;
        private int minY = 0;

        private int maxX = Integer.MAX_VALUE;
        private int maxY = Integer.MAX_VALUE;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int maxX, int maxY) {
            this.x = x;
            this.y = y;
            this.maxX = maxX;
            this.maxY = maxY;
        }

        public Pos(int x, int y, int maxX, int maxY, int minX, int minY) {
            this.x = x;
            this.y = y;
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }

        public void decrementX() {
            if (this.x > this.minX) this.x--;
        }

        public void decrementY() {
            if (this.y > this.minY) this.y--;
        }

        public void incrementX() {
            if (this.x < this.maxX) this.x++;
        }

        public void incrementY() {
            if (this.y < this.maxY) this.y++;
        }

        public void decrementXY() {
            this.decrementX();
            this.decrementY();
        }

        public void incrementXY() {
            this.incrementX();
            this.incrementY();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj.getClass() == this.getClass()) {
                Pos equalsObj = (Pos)obj;
                return (this.x == equalsObj.x) && (this.y == equalsObj.y);
            }
            return false;
        }
    }
}
