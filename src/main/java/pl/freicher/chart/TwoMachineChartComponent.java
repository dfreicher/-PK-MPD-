package pl.freicher.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JComponent;
import javax.swing.Scrollable;

/**
 * @author Freicher
 * @version 1.0
 */
public class TwoMachineChartComponent extends JComponent implements Scrollable {
    
    private int SQUARE_WIDTH = 25;
    private List<Item> first = new ArrayList<Item>();
    private List<Item> second = new ArrayList<Item>();

    public TwoMachineChartComponent(List<Item> first, List<Item> second) {
        this.first = first;
        this.second = second;
    }
    
    public int getMaxX() {
        int max = 0;
        for(Item i : second) {
           
            if (i.getEnd() > max)
                max = i.getEnd();
        }
        
        return max;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;
        
        g2.drawString("Maszyna 1" , 10, 100);
        g2.drawString("Maszyna 2" , 10, 150);

        for (int i = 100, l=0; i<=(100+(SQUARE_WIDTH*getMaxX())); i+=SQUARE_WIDTH, l++) {
            
            g2.drawLine(i, 75, i, 175);
            g2.drawString(l+"", i, 65);
        }

        Random r = new Random();
        
        for(int i=0; i<first.size(); i++) {
            g2.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            Item f = first.get(i);
            Item f2 = second.get(i);
            
            if(f.getEnd() - f.getStart() > 0)
                g2.fillRect(100+(f.getStart()* SQUARE_WIDTH), 85, (f.getEnd()-f.getStart())*SQUARE_WIDTH, 25);
            
            
            if(f2.getEnd() - f2.getStart() > 0)
                g2.fillRect(100+(f2.getStart()* SQUARE_WIDTH), 135, (f2.getEnd()-f2.getStart())*SQUARE_WIDTH, 25);
             
            g2.setColor(Color.white);
             
             if(f.getEnd() - f.getStart() > 0)
                g2.drawString(f.getTitle(), 100+(f.getStart()* SQUARE_WIDTH)+5, 100);
             if(f2.getEnd() - f2.getStart() > 0)
                g2.drawString(f2.getTitle(), 100+(f2.getStart()* SQUARE_WIDTH)+5, 150);
        }
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
       return new Dimension(640, 480);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
       return 0;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }
}
