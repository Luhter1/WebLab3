package org.itmo.manageBean;

import java.sql.*;
import java.util.LinkedList;

import org.itmo.model.Point;

/**
 * Database manager for handling point storage and retrieval operations.
 * Manages PostgreSQL database connections and point table operations.
 */
public class DBManager {
    /**
     * Flag to track if the points table has been initialized
     */
    private static boolean isTableInit = false;

    /**
     * Database connection URL for PostgreSQL
     */
    private static final String URL_NAME = "jdbc:postgresql://postgres:5432/mydb";

    /**
     * Establishes and returns a database connection.
     *
     * @return Connection object to the PostgreSQL database
     * @throws SQLException if connection cannot be established
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_NAME, "postgres", "postgres");
    }

    /**
     * Initializes the points table if it doesn't exist.
     * Creates table with columns: id, x, y, r, isHit.
     * This method is idempotent - safe to call multiple times.
     */
    public static void InitPointTable() {
        if (isTableInit) return;

        try {
            Connection conn = getConnection();
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS points (\r\n" +
                    "    id bigint,\r\n" +
                    "    x double precision not null,\r\n" +
                    "    y double precision not null,\r\n" +
                    "    r double precision not null,\r\n" +
                    "    isHit boolean not null\r\n" +
                    ");");

            statement.close();
            conn.close();
            isTableInit = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Inserts a new point record into the database.
     * Automatically initializes the table if needed.
     *
     * @param id    unique identifier for the session/user
     * @param x     X coordinate of the point
     * @param y     Y coordinate of the point
     * @param r     radius value used for area checking
     * @param IsHit whether the point is within the target area
     */
    public static void insertPointIntoTable(long id, double x,
                                            double y, double r, boolean IsHit) {

        InitPointTable();
        try {
            Connection conn = getConnection();
            PreparedStatement pStatement = conn.prepareStatement(
                    "INSERT INTO points VALUES(?, ?, ?, ?, ?);"
            );

            pStatement.setLong(1, id);
            pStatement.setDouble(2, x);
            pStatement.setDouble(3, y);
            pStatement.setDouble(4, r);
            pStatement.setBoolean(5, IsHit);
            pStatement.executeUpdate();

            pStatement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Retrieves all points associated with a specific session ID.
     *
     * @param id the session identifier to filter points by
     * @return LinkedList of Point objects belonging to the specified session
     */
    public static LinkedList<Point> getPoints(long id) {
        LinkedList<Point> points = new LinkedList<>();

        try {
            Connection conn = getConnection();
            PreparedStatement pStatement = conn.prepareStatement(
                    "SELECT x, y, r, isHit FROM points WHERE id=?;"
            );

            pStatement.setLong(1, id);
            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                Point point = new Point(
                        resultSet.getDouble("x"),
                        resultSet.getDouble("y"),
                        resultSet.getDouble("r")
                );
                point.SetIsHit(
                        resultSet.getBoolean("isHit")
                );

                points.add(point);
            }

            pStatement.close();
            resultSet.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

        return points;
    }
}
