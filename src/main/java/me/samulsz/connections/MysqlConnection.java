package me.samulsz.connections;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MysqlConnection {
    private Map<String, Double> map;
    private static Connection mysql;

    public MysqlConnection(Plugin plugin) {
        setupConnection(plugin.getConfig());
    }

    private void setupConnection(FileConfiguration config) {
        map = new HashMap<>();
        String host = config.getString("db.address");
        String database = config.getString("db.database");
        String username = config.getString("db.name");
        String password = config.getString("db.pass");

        String url = "jdbc:mysql://" + host + "/" + database + "?autoReconnect=true";
        try {
            mysql = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = mysql.prepareStatement("CREATE TABLE IF NOT EXISTS `flow-economy` (`playerName` VARCHAR(16), `coins` FLOAT(53))");
            statement.executeUpdate();
            statement.close();
            load();
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§c[Floweconomy] error on connect to mysql database!");
        }
    }
    public void load() {
        try {
            PreparedStatement statement = mysql.prepareStatement("SELECT * FROM `flow-economy`;");
            ResultSet query = statement.executeQuery();
            while (query.next()) {
                if (!map.containsKey(query.getString("playerName"))) {
                    map.put(query.getString("playerName"), query.getDouble("coins"));
                }
            }
            query.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createPlayer(String playerName) {
        if (!map.containsKey(playerName)) {
            map.put(playerName, getCoinsFromPlayer(playerName));
            return true;
        }
        return false;
    }

    public boolean exists(String playerName) {
        try {
            PreparedStatement statement = mysql.prepareStatement("SELECT * FROM `flow-economy` WHERE `playerName` = ?;");
            statement.setString(1, playerName);
            ResultSet query = statement.executeQuery();
            boolean exists = query.next();
            query.close();
            statement.close();

            return exists;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void save(String playerName){
        if (exists(playerName)){
            try {
                PreparedStatement statement = mysql.prepareStatement("UPDATE `flow-economy` SET `coins` = ? WHERE `playerName` = ?;");
                statement.setDouble(1, getCoinsFromPlayer(playerName));
                statement.setString(2, playerName);
                statement.executeUpdate();
                statement.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }else {
            try {
                PreparedStatement statement = mysql.prepareStatement("INSERT INTO `flow-economy` (`playerName`, `coins`) VALUES (?, ?);");
                statement.setString(1, playerName);
                statement.setDouble(2, getCoinsFromPlayer(playerName));
                statement.executeUpdate();
                statement.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public double getDouble(String playerName) {
        try {
            PreparedStatement statement = mysql.prepareStatement("SELECT `coins` FROM `flow-economy` WHERE `playerName` = ?;");
            statement.setString(1, playerName);
            ResultSet query = statement.executeQuery();
            double coins = 0.0;
            if (query.next()) {
                coins = query.getDouble("coins");
            }
            query.close();
            statement.close();

            return coins;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public void removeCoins(String playerName, double coins) {
        setCoins(playerName, (getCoinsFromPlayer(playerName) - coins));
    }
    public void addCoins(String playerName, double coins) {
        setCoins(playerName, (getCoinsFromPlayer(playerName) + coins));
    }

    public void setCoins(String playerName, double coins) {
        if (map.containsKey(playerName)) {
            map.put(playerName, coins);
        } else {
            if (exists(playerName)) {
                try {
                    PreparedStatement statement = mysql.prepareStatement("UPDATE `flow-economy` SET `coins` = ? WHERE `playerName` = ?;");
                    statement.setDouble(1, coins);
                    statement.setString(2, playerName);
                    statement.executeUpdate();
                    statement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    PreparedStatement statement = mysql.prepareStatement("INSERT INTO `flow-economy` (`playerName`, `coins`) VALUES (?, ?);");
                    statement.setString(1, playerName);
                    statement.setDouble(2, coins);
                    statement.executeUpdate();
                    statement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public double getCoinsFromPlayer(String playerName) {
        if (map.containsKey(playerName)) {
            return map.get(playerName);
        }
        return (exists(playerName) ? getDouble(playerName) : 0);
    }
    
    public Map<String, Double> getMap() {
        return map;
    }
}
