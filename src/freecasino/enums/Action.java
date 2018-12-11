/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freecasino.enums;

/**
 * List of actions a player or dealer may take. They were designed with
 * blackjack in mind, but are useful in other games, which is why this enum is
 * public.
 * 
 * It is not recommended to write any code that relies on the actions being in
 * any particular order. It may have additional actions added to it and be
 * reordered at any time based on need and convenience.
 * 
 * @author StrangerCoug <strangercoug@hotmail.com>
 */
public enum Action {
    HIT, STAND, DOUBLE, SPLIT, SURRENDER
}
