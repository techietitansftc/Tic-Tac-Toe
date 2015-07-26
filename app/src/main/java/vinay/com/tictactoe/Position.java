package vinay.com.tictactoe;

/**
 * Created by vinayjagan on 7/26/15.
 */
public class Position {

    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }


}
