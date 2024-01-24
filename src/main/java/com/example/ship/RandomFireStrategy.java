package com.example.ship;

public class RandomFireStrategy implements FireStrategy {

    @Override
    public String getCoordinates(Integer size, Player player) {

        int sizehalf = size / 2;
        String resp = "";
        if (Player.PLAYER_A.equals(player)) {
            int x = (int) Math.random() * sizehalf;
            int y = (int) Math.random() * sizehalf;
            resp = x + "," + y;
        } else if (Player.PLAYER_B.equals(player)) {
            int x = (int) (sizehalf + (Math.random() * sizehalf));
            int y = (int) (sizehalf + (Math.random() * sizehalf));
            resp = x + "," + y;
        }
        return resp;
    }
}
