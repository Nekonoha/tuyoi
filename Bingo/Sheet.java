

import java.util.*;

public class Sheet {

    public int[][] field = new int[5][5];
    public int num[] = new int[25];
    public List<Integer> list = new ArrayList<Integer>();

    public int castnum[] = new int[25];
    public List<Integer> castlist = new ArrayList<Integer>();
    public int turn;
    private boolean game;
    //本来は引かれるボールは全員共通なので、クラス変素とメソッドで処理するのが正しい
    //あとで直す

    public Sheet() {
        turn = 0;
        game = true;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = 0;
            }
        }

    }

    public void makeSheet() {
        for (int i = 1; i <= num.length; i++) {
            list.add(i);
            castlist.add(i);
        }

        Collections.shuffle(list);
        Collections.shuffle(castlist);

        for (int i = 0; i < num.length; i++) {
            num[i] = list.get(i);
            castnum[i] = castlist.get(i);
        }

        int count = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = num[count];
                count++;
            }
        }
        
        field[2][2] = 0;

    }

    public void cast() {

        turn++;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (castnum[turn - 1] == field[i][j]) {
                    field[i][j] = 0;
                }
            }
            System.out.println("");
        }
    }

    public void calcBingo() {
        int bingoCount = 0;
        //列判定

        for (int i = 0; i < field.length; i++) {
            bingoCount = 0;
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] == 0) {
                    bingoCount++;
                    continue;
                }
                break;
            }
            if (bingoCount >= 5) {
                System.out.println("Bingo");
                game = false;
            }
        }

        //行判定
        for (int i = 0; i < field[0].length; i++) {
            bingoCount = 0;
            for (int j = 0; j < field.length; j++) {
                if (field[j][i] == 0) {
                    bingoCount++;
                    continue;
                }
                break;
            }
            if (bingoCount >= 5) {
                System.out.println("Bingo");
                game = false;
            }
        }

        //斜め判定
        if (field[0][0] == 0 && field[1][1] == 0 && field[2][2] == 0
                && field[3][3] == 0 && field[4][4] == 0) {
            System.out.println("Bingo");
            game = false;
        }
        
        if (field[0][4] == 0 && field[1][3] == 0 && field[2][2] == 0
                && field[3][1] == 0 && field[4][0] == 0) {
            System.out.println("Bingo");
            game = false;
        }

    }

    public boolean playGame() {
        if (turn < 25) {
            return game;
        } else {
            return false;
        }
    }

    public void dispSheet() {

        System.out.println("turn :" + turn);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j] != 0) {
                    System.out.print(field[i][j] + "\t");
                } else {
                    System.out.print("X" + "\t");
                }
            }
            System.out.println("");
        }

    }

    //end
}