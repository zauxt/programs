// Name: Benjamin Bush

import javax.swing.JFrame;
import java.awt.*;

class FractalCanvas extends Canvas {
    public void paint(Graphics g) {
        // White Background -> Black Drawing Color -> Recursive Function Draws Initial Fractal
        setBackground(Color.WHITE);
        g.setColor(Color.BLACK);
        drawFractal(g, new int[]{400, 50, 750}, new int[]{50, 700, 700}, 4);
    }

    private void drawFractal(Graphics g, int[] x, int[] y, int limit) {
        // Base case: stop recursion when triangle's sides become smaller than pixel limit
        if (Math.abs(x[0] - x[1]) <= limit) return;
        
        // Fill triangle with current points
        g.fillPolygon(x, y, 3);

        // Calculating midpoints of each side of triangle
        int[] mid_x = {(x[0] + x[1]) / 2, (x[1] + x[2]) / 2, (x[0] + x[2]) / 2};
        int[] mid_y = {(y[0] + y[1]) / 2, (y[1] + y[2]) / 2, (y[0] + y[2]) / 2};

        // Effectively "erases" the inner triangle before reseting the color
        g.setColor(Color.WHITE);
        g.fillPolygon(mid_x, mid_y, 3);
        g.setColor(Color.BLACK);

        /* 
        
        Recursively draw three smaller triangles using new points calculated until base case reached with pixel limit

        Everything is kind of related diagonally reminds me a bit of matricies and vectors

        [pt_1] [__] [__]        [XXX] [mid_1] [_]       [XXX] [XXX] [mid_3]      [XXX] [XXX] [XXX]            [pt_1] [mid_1] [mid_3] 
        [__] [pt_2] [__]   -->  [mid_1] [XXX] [_]  -->  [XXX] [XXX] [_____] -->  [XXX] [XXX] [mid_2]    =     [mid_1] [pt_2] [mid_2]
        [__] [__] [pt_3]        [___] [___] [XXX]       [mid_3] [___] [XXX]      [XXX] [mid_2] [XXX]          [mid_3] [mid_2] [pt_3]
        
        */

        drawFractal(g, new int[]{x[0], mid_x[0], mid_x[2]}, new int[]{y[0], mid_y[0], mid_y[2]}, limit);
        drawFractal(g, new int[]{mid_x[0], x[1], mid_x[1]}, new int[]{mid_y[0], y[1], mid_y[1]}, limit);
        drawFractal(g, new int[]{mid_x[2], mid_x[1], x[2]}, new int[]{mid_y[2], mid_y[1], y[2]}, limit);
    }
}

public class Triangle {
    public static void main(String[] args) {
        // New JFrame Window -> Window Size -> Add Custom FractalCanvas to Frame -> Make It Visible
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.add(new FractalCanvas());
        frame.setVisible(true);
    }
}
