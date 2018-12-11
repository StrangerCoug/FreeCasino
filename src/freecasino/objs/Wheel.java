/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.objs;

/**
 *
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public class Wheel {
    private Object[] stops;
    private int position;
    
    public Wheel(Object[] stops) {
        this.stops = stops;
        position = 0;
    }
    
    public Object getWheelResult() {
        return stops[position];
    }
    
    public void spinWheel() {
        position = (int) (Math.random() * stops.length);
    }
}
