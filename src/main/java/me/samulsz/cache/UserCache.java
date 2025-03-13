package me.samulsz.cache;

import me.samulsz.connections.MysqlConnection;
import java.util.HashMap;
import java.util.Map;

public class UserCache {
    private final MysqlConnection mysqlConnection;
    private final Map<String, Double> cache = new HashMap<>();

    public UserCache(MysqlConnection mysqlConnection) {
        this.mysqlConnection = mysqlConnection;
        loadCache();
    }

    private void loadCache() {
        cache.putAll(mysqlConnection.getMap());
    }

    public double getCoins(String playerName) {
        if (cache.containsKey(playerName)) {
            return cache.get(playerName);
        }
        double coins = mysqlConnection.getCoinsFromPlayer(playerName);
        cache.put(playerName, coins);
        return coins;
    }

    public void addCoins(String playerName, double coins) {
        double currentCoins = getCoins(playerName);
        double newCoins = currentCoins + coins;
        mysqlConnection.setCoins(playerName, newCoins);
        cache.put(playerName, newCoins);
    }

    public void removeCoins(String playerName, double coins) {
        double currentCoins = getCoins(playerName);
        double newCoins = currentCoins - coins;
        mysqlConnection.setCoins(playerName, newCoins);
        cache.put(playerName, newCoins);
    }

    public boolean createPlayer(String playerName) {
        if (!cache.containsKey(playerName)) {
            boolean created = mysqlConnection.createPlayer(playerName);
            if (created) {
                cache.put(playerName, 0.0);
            }
            return created;
        }
        return false;
    }

    public boolean exists(String playerName) {
        return cache.containsKey(playerName) || mysqlConnection.exists(playerName);
    }
}