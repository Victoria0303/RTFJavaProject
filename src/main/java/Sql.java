import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.sqlite.SQLiteConfig;

public class Sql {

    private Connection connection;
    private Statement statement;

    public Sql() throws SQLException {
        SQLiteConfig config = new SQLiteConfig();
        config.enforceForeignKeys(true);
        connection = DriverManager.getConnection("jdbc:sqlite:countries.db", config.toProperties());
        statement = connection.createStatement();
        addCountryTable();
    }


    private void addCountryTable() throws SQLException {
        addHappinessTable();
        addPopulationTable();

        var sql = """
            CREATE TABLE IF NOT EXISTS country(
                id integer primary key,
                name text,
                region text,
                happiness_id integer,
                population_id integer,
                standard_error real,
                economy real,
                dystopia_residual real,
                FOREIGN KEY (happiness_id) REFERENCES happiness(id),
                FOREIGN KEY (population_id) REFERENCES population(id)
            );
            """;

        statement.execute(sql);
    }

    private void addHappinessTable() throws SQLException {
        var sql = """
            CREATE TABLE IF NOT EXISTS happiness(
                id integer primary key,
                rank integer,
                score real
            );
            """;

        statement.execute(sql);
    }

    private void addPopulationTable() throws SQLException {
        var sql = """
            CREATE TABLE IF NOT EXISTS population(
                id integer primary key,
                family real,
                health real,
                freedom real,
                generosity real,
                trust real
            );
            """;

        statement.execute(sql);
    }

    public void addCountry(Country country) throws SQLException {
        addHappiness(country.getHappiness());
        addPopulation(country.getPopulation());

        var query = """
            INSERT or IGNORE INTO country(
                id, name, region, happiness_id, population_id,
                standard_error, economy, dystopia_residual
            ) VALUES (?,?,?,?,?,?,?,?)
            """;

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, country.getId());
        preparedStatement.setString(2, country.getName());
        preparedStatement.setString(3, country.getRegion());
        preparedStatement.setInt(4, country.getId());
        preparedStatement.setInt(5, country.getId());
        preparedStatement.setDouble(6, country.getStandardError());
        preparedStatement.setDouble(7, country.getEconomy());
        preparedStatement.setDouble(8, country.getDystopiaResidual());
        preparedStatement.executeUpdate();
    }

    private void addHappiness(Happiness happiness) throws SQLException {
        var query = """
            INSERT or IGNORE INTO happiness(
                id, rank, score
            ) VALUES (?,?,?)
            """;

        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, happiness.country().getId());
        preparedStatement.setInt(2, happiness.rank());
        preparedStatement.setDouble(3, happiness.score());
        preparedStatement.executeUpdate();
    }

    private void addPopulation(Population population) throws SQLException {
        var query = """
            INSERT or IGNORE INTO population(
                id, family, health, freedom, trust, generosity
            ) VALUES (?,?,?,?,?,?)
            """;

        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, population.country().getId());
        preparedStatement.setDouble(2, population.family());
        preparedStatement.setDouble(3, population.health());
        preparedStatement.setDouble(4, population.freedom());
        preparedStatement.setDouble(5, population.trust());
        preparedStatement.setDouble(6, population.generosity());
        preparedStatement.executeUpdate();
    }

    public void selectAll() throws SQLException {
        var query = """
                SELECT c.name, c.region, h.rank, h.score,
                p.family, p.health, p.freedom, p.trust, p.generosity
                FROM country c 
                INNER JOIN population p ON p.id = c.id 
                INNER JOIN happiness h ON h.id = c.id 
            """;

        var rs = statement.executeQuery(query);
        var count = rs.getMetaData().getColumnCount() + 1;
        while (rs.next()) {
            for (var i = 1; i < count; i++) {
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println();
        }
    }

    public String getCountryWithHigherEconomy() throws SQLException {
        var query = """
            SELECT name, economy FROM country
            WHERE region = 'Latin America and Caribbean' or region = 'Eastern Asia'
            ORDER BY economy DESC
            """;

        return statement.executeQuery(query).getString(1);
    }

    public String getCountryWithHigherEconomy1() throws SQLException {
        List<String> countryByAvg = new ArrayList<>();
        var query = """
            SELECT c.name, h.score + p.family + p.health + p.freedom + p.trust + p.generosity FROM country c
            INNER JOIN population p ON p.id = c.id and (c.region = 'North America' or c.region = 'Western Europe')
            INNER JOIN happiness h ON h.id = c.id and (c.region = 'North America' or c.region = 'Western Europe')
            ORDER BY h.score + p.family + p.health + p.freedom + p.trust + p.generosity
            """;

        var s = statement.executeQuery(query);
        var i = 0;
        while (s.next()) {
            countryByAvg.add(s.getString(1));
            i++;
        }
        return countryByAvg.get(i / 2);
    }

    public Map<String, Double> getCountriesEconomy() throws SQLException {
        Map<String, Double> economyByCountry = new HashMap<>();
        var query = """
            SELECT name, economy FROM country
            ORDER BY economy DESC
            """;

        var rs = statement.executeQuery(query);
        while (rs.next()) {
            economyByCountry.put(rs.getString(1), rs.getDouble(2));
        }

        return economyByCountry;
    }
}