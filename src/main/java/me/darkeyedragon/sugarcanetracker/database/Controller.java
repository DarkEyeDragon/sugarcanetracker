package me.darkeyedragon.sugarcanetracker.database;

import me.darkeyedragon.sugarcanetracker.SugarUser;
import me.darkeyedragon.sugarcanetracker.Sugarcanetracker;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Controller {

    private final Sugarcanetracker plugin;
    private final String url;

    public Controller(Sugarcanetracker plugin) {
        this.plugin = plugin;
        url = "jdbc:sqlite:" + Paths.get(plugin.getDataFolder().getPath(), "playerdata.db");
    }

    private Connection connect() throws SQLException {
        // SQLite connection string
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void createTable() throws SQLException {
        plugin.getDataFolder().mkdir();
        String query = "CREATE TABLE IF NOT EXISTS player_data (uuid text(36) PRIMARY KEY, sugarcane_broken integer)";
        executeQuery(query);
    }

    private void executeQuery(String query) throws SQLException {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        stmt.execute(query);
    }

    public void addPlayer(UUID uuid) throws SQLException {
        String query = "INSERT OR IGNORE INTO player_data(`uuid`, `sugarcane_broken`) VALUES (?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, uuid.toString());
            pstmt.setInt(2, 0);
            pstmt.executeUpdate();
        }
    }

    public void updateSugarcanecount(UUID uuid, int amount) throws SQLException {
        String query = "UPDATE player_data SET sugarcane_broken=? where `uuid`=?";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, amount);
            stmt.setString(2, uuid.toString());

            stmt.execute();
        }
    }


    public int getSugarcaneCount(UUID uuid) throws SQLException {
        String query = "SELECT sugarcane_broken FROM player_data where `uuid`=?";
        try (Connection conn = this.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, uuid.toString());
            ResultSet resultSet = stmt.executeQuery();

            return resultSet.getInt("sugarcane_broken");
        }
    }

    public List<SugarUser> getTop() throws SQLException {
        String query = "SELECT `uuid`,sugarcane_broken FROM player_data ORDER BY sugarcane_broken DESC LIMIT 10";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            List<SugarUser> sugarUsers = new ArrayList<>(10);
            while (resultSet.next()) {
                sugarUsers.add(new SugarUser(UUID.fromString(resultSet.getString("uuid")), resultSet.getInt("sugarcane_broken")));
            }
            return sugarUsers;
        }
    }

    public void closeConnection() throws SQLException {
        connect().close();
    }
}
