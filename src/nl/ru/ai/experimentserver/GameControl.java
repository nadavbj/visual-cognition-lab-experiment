package nl.ru.ai.experimentserver;

import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for a game controller
 *
 * @author Tessa Beinema
 */
public class GameControl {
    private BufferedWriter writer;
    private GameModel model;
    int player1x,player1y,player2x,player2y;
    public static final int SLEEP_TIME = 4000;

    /**
     * Constructor for a new gamecontroller
     *  @param model
     * @param writer
     * @param player1x
     * @param player1y
     * @param player2x
     * @param player2y
     */
    public GameControl(GameModel model, BufferedWriter writer, int player1x, int player1y, int player2x, int player2y) {
        this.writer = writer;
        this.model = model;
        this.player1x=player1x;
        this.player1y=player1y;
        this.player2x=player2x;
        this.player2y=player2y;
    }

    /**
     * Function that updates the totalscores and the roundscores
     */
    public void updateScore() {
        if (this.model.getPlayer1Chose() && this.model.getPlayer2Chose()) {
            showRoundSummery(model.getPlayer1TotalScore(),model.getPlayer2TotalScore(),model.getPlayer1LastChoice(),model.getPlayer2LastChoice());
new Timer().schedule(new TimerTask() {
    @Override
    public void run() {


            if (model.getPlayer1LastChoice() == model.getPlayer2LastChoice()) {

                if (model.getPlayer1LastChoice() == "A") {
                    model.setPlayer1RoundScore(3);
                    model.setPlayer1TotalScore(model.getPlayer1TotalScore() + 3);
                    model.setPlayer2RoundScore(2);
                    model.setPlayer2TotalScore(model.getPlayer2TotalScore() + 2);
                    Date d = new Date();
                    try {
                        writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER1" + "\tChoice: " + model.getPlayer1LastChoice() + "\tRound: " + model.getRound() + "\tRoundscore: " + model.getPlayer1RoundScore() + "\tTotalscore: " + model.getPlayer1TotalScore() + "\n");
                        writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER2" + "\tChoice: " + model.getPlayer2LastChoice() + "\tRound: " + model.getRound() + "\tRoundscore: " + model.getPlayer2RoundScore() + "\tTotalscore: " + model.getPlayer2TotalScore() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (model.getPlayer1LastChoice() == "B") {
                    model.setPlayer1RoundScore(2);
                    model.setPlayer1TotalScore(model.getPlayer1TotalScore() + 2);
                    model.setPlayer2RoundScore(3);
                    model.setPlayer2TotalScore(model.getPlayer2TotalScore() + 3);
                    Date d = new Date();
                    try {
                        writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER1" + "\tChoice: " + model.getPlayer1LastChoice() + "\tRound: " + model.getRound() + "\tRoundscore: " + model.getPlayer1RoundScore() + "\tTotalscore: " + model.getPlayer1TotalScore() + "\n");
                        writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER2" + "\tChoice: " + model.getPlayer2LastChoice() + "\tRound: " + model.getRound() + "\tRoundscore: " + model.getPlayer2RoundScore() + "\tTotalscore: " + model.getPlayer2TotalScore() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                model.setPlayer1RoundScore(0);
                model.setPlayer1TotalScore(model.getPlayer1TotalScore() + 0);
                model.setPlayer2RoundScore(0);
                model.setPlayer2TotalScore(model.getPlayer2TotalScore() + 0);
                Date d = new Date();
                try {
                    writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER1" + "\tChoice: " + model.getPlayer1LastChoice() + "\tRound: " + model.getRound() + "\tRoundscore: " + model.getPlayer1RoundScore() + "\tTotalscore: " + model.getPlayer1TotalScore() + "\n");
                    writer.write(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + ":" + d.getTime() + "\tDISPLAY\t" + "\tPLAYER2" + "\tChoice: " + model.getPlayer2LastChoice() + "\tRound: " + model.getRound() + "\tRoundscore: " + model.getPlayer2RoundScore() + "\tTotalscore: " + model.getPlayer2TotalScore() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //showRoundSummery(model.getPlayer1RoundScore(), model.getPlayer2RoundScore(), model.getPlayer1LastChoice().charAt(0), model.getPlayer2LastChoice().charAt(0));
            //System.out.println("Player 1: " + model.getPlayer1LastChoice() + " " + model.getPlayer1RoundScore() + " " + model.getPlayer1TotalScore());
            //System.out.println("Player 2: " + model.getPlayer2LastChoice() + " " + model.getPlayer2RoundScore() + " " + model.getPlayer2TotalScore());
            model.increaseRound(1);
            try {
                writer.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            setPlayer1Chose(false);
            setPlayer2Chose(false);
    }
}, SLEEP_TIME);
        }
    }

    /**
     * Display this round summary and suspens the game for 2 seconds
     *
     * @param player1RoundScore
     * @param player2RoundScore
     */
    private void showRoundSummery(int player1RoundScore, int player2RoundScore, String player1Choice, String player2Choice) {
        String message1, message2;
        if (player1Choice.equals(player2Choice)) {
            message1 = "שניכם בחרתם " + player1Choice;
            message2 = message1;
        } else {
            message1 = "בחרת " + player1Choice + "והשחקן השני בחר" + player2Choice;
            message2 = "בחרת " + player2Choice + "והשחקן השני בחר" + player1Choice;
        }
        message1 += "\n" + "זכית ב-" + player1RoundScore + " נקודת, השחקן השני זכה ב" + player2RoundScore + " נקודות";
        message2 += "\n" + "זכית ב-" + player2RoundScore + " נקודת, השחקן השני זכה ב" + player1RoundScore + " נקודות";
        final ShowRoundSummaryFrame showRoundSummaryFrame1 =new ShowRoundSummaryFrame(message1,player1x,player1y+200);
        final ShowRoundSummaryFrame showRoundSummaryFrame2 =new ShowRoundSummaryFrame(message2,player2x,player2y+200);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                showRoundSummaryFrame1.dispose();
                showRoundSummaryFrame2.dispose();
            }
        },SLEEP_TIME);
    }


    /**
     * Getter for the player 1 chose boolean
     *
     * @return player 1 chose
     */
    public boolean getPlayer1Chose() {
        return this.model.getPlayer1Chose();
    }

    /**
     * Setter for the player 1 chose boolean, and if player 2 chose the scores are updated
     *
     * @param bool
     */
    public void setPlayer1Chose(boolean bool) {
        this.model.setPlayer1Chose(bool);
    }

    /**
     * Getter for the player 2 chose boolean
     *
     * @return player 1 chose
     */
    public boolean getPlayer2Chose() {
        return this.model.getPlayer2Chose();
    }

    /**
     * Setter for the player 2 chose boolean
     *
     * @param bool
     */
    public void setPlayer2Chose(boolean bool) {
        this.model.setPlayer2Chose(bool);
    }

    public void refresh() {
        this.model.refresh();

    }

    public boolean checkCooperation() {
        return model.checkCooperation();
    }
}
