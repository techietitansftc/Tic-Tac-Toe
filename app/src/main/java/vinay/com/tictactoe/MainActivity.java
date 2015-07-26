package vinay.com.tictactoe;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    String player = "X";
    boolean won = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<ButtonCoord> buttons = new ArrayList<>();
        final ArrayList<ButtonCoord> buttonsClickXP = new ArrayList<>();
        final ArrayList<ButtonCoord> buttonsClickOP = new ArrayList<>();
        final TextView tv = (TextView) findViewById(R.id.textView);
        buttons.add(new ButtonCoord(new Position(1, 1), (Button) findViewById(R.id.button)));
        buttons.add(new ButtonCoord(new Position(2, 1), (Button) findViewById(R.id.button2)));
        buttons.add(new ButtonCoord(new Position(3, 1), (Button) findViewById(R.id.button3)));
        buttons.add(new ButtonCoord(new Position(1, 2), (Button) findViewById(R.id.button4)));
        buttons.add(new ButtonCoord(new Position(2, 2), (Button) findViewById(R.id.button5)));
        buttons.add(new ButtonCoord(new Position(3, 2), (Button) findViewById(R.id.button6)));
        buttons.add(new ButtonCoord(new Position(1, 3), (Button) findViewById(R.id.button7)));
        buttons.add(new ButtonCoord(new Position(2, 3), (Button) findViewById(R.id.button8)));
        buttons.add(new ButtonCoord(new Position(3, 3), (Button) findViewById(R.id.button9)));
        for (int i = 0; i < buttons.size(); i++) {
            final ButtonCoord buttonCoord = buttons.get(i);
            buttonCoord.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Activity", buttonCoord.position.x + ", " + buttonCoord.position.y);
                    buttonCoord.button.setText(player);
                    buttonCoord.button.setEnabled(false);
                    if (player == "X") {
                        buttonsClickXP.add(buttonCoord);
                        if (isStraightY(buttonsClickXP, buttonCoord) || isStraightX(buttonsClickXP, buttonCoord) || isDiagonal(buttonsClickXP, buttonCoord)) {
                            tv.setText("X Won!");
                            won = true;
                        }
                    }
                    if (player == "0") {
                        buttonsClickOP.add(buttonCoord);
                        if (isStraightY(buttonsClickOP, buttonCoord) || isStraightX(buttonsClickOP, buttonCoord) || isDiagonal(buttonsClickOP, buttonCoord)) {
                            tv.setText("0 Won!");
                            won = true;

                        }
                    }

                    if (won) {
                        for (int i = 0; i < buttons.size(); i++) {
                            buttons.get(i).button.setEnabled(false);
                        }
                    }

                    if (player.equals("X"))
                        player = "0";
                    else
                        player = "X";
                    if (!won)
                        tv.setText(player + " Turn");
                    if (buttonsClickOP.size() + buttonsClickXP.size() == 9)
                        tv.setText("Tie!");
                }
            });
        }
        Button b = (Button) findViewById(R.id.resetGame);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player = "X";
                buttonsClickXP.clear();
                buttonsClickOP.clear();
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).button.setText("");
                    buttons.get(i).button.setEnabled(true);
                }
                tv.setText("X Turn!");
                won = false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean isStraightY(ArrayList<ButtonCoord> buttonCoords, ButtonCoord bc) {
        int count = 0;
        for (int i = 0; i < buttonCoords.size(); i++) {
            if (buttonCoords.get(i).position.x == bc.position.x) {
                count++;
            }
        }
        if (count == 3)
            return true;
        else
            return false;
    }

    private boolean isStraightX(ArrayList<ButtonCoord> buttonCoords, ButtonCoord bc) {
        int count = 0;
        for (int i = 0; i < buttonCoords.size(); i++) {
            if (buttonCoords.get(i).position.y == bc.position.y) {
                count++;
            }
        }
        if (count == 3)
            return true;
        else
            return false;
    }

    private boolean isDiagonal(ArrayList<ButtonCoord> buttonCoords, ButtonCoord bc) {
        int count = 0;
        for (int i = 0; i < buttonCoords.size(); i++) {
            ButtonCoord other = buttonCoords.get(i);
            boolean same = other.position == bc.position;
            boolean middle = ((other.position.x == bc.position.x + 1 && other.position.y == bc.position.y + 1)
                    && (other.position.x + 1 == bc.position.x && other.position.y == bc.position.y + 1))
                    || ((other.position.x + 1 == bc.position.x && other.position.y + 1 == bc.position.y)
                    && (other.position.x == bc.position.x + 1 && other.position.y + 1 == bc.position.y));
            boolean topRight = (other.position.x + 1 == bc.position.x && other.position.y + 1 == bc.position.y)
                    || (other.position.x + 2 == bc.position.x && other.position.y + 2 == bc.position.y);
            boolean topLeft = (other.position.x - 1 == bc.position.x && other.position.y - 1 == bc.position.y)
                    || (other.position.x - 2 == bc.position.x && other.position.y - 2 == bc.position.y);
            boolean bottomLeft = (other.position.x == bc.position.x + 1 && other.position.y == bc.position.y + 1)
                    || (other.position.x == bc.position.x + 2 && other.position.y == bc.position.y + 2);
            boolean bottomRight = (other.position.x + 1 == bc.position.x && other.position.y == bc.position.y + 1)
                    || (other.position.x + 2 == bc.position.x && other.position.y == bc.position.y + 2);
            if (same || middle || topRight || topLeft || bottomLeft || bottomRight) count++;
        }
        if (count == 3)
            return true;
        else
            return false;
    }

}
