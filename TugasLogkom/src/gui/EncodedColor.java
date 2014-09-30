/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Color;

/**
 *
 * @author V Anugrah
 */
public class EncodedColor {
    int bit0;
    int bit1;
    
    public EncodedColor(int bit0, int bit1){
        this.bit0 = bit0;
        this.bit1 = bit1;
    }
    public Color getColor(){
        // 00 untuk merah
        if (bit0 < 0 && bit1 < 0){
            return Color.RED;
        }
        // 01 untuk kuning
        else if (bit0 < 0 && bit1 > 0){
            return Color.YELLOW;
        }
        // 10 untuk hijau
        else if (bit0 > 0 && bit1 > 0){
            return Color.GREEN;
        }
        // 11 untuk biru
        else{
            return Color.BLUE;
        }
    }
}
