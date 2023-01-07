import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.lang3.CharUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.data.category.DefaultCategoryDataset;

public class Main {

    private static Sql sql;

    public static void main(String[] args) throws IOException, SQLException {
        var p = CSVParser.parse();
        sql = new Sql();
        for (var p1 : p) {
            sql.addCountry(p1);
        }
        //s.selectAll();
        ex1();
        ex2();
        ex3();
        //Выведите в консоль страну с самым высоким показателем экономики среди "Latin America and Caribbean" и "Eastern Asia"
        //Найдите страну с "самыми средними показателями" среди "Western Europe" и "North America"
    }

    private static void ex1() throws SQLException, IOException {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        var countryByEconomy = sql.getCountriesEconomy();
        for (var key : countryByEconomy.keySet()) {
            ds.addValue(countryByEconomy.get(key), key, "");
        }

        var c = ChartFactory.createBarChart("Title", "F", "S", ds);
        ChartUtils.saveChartAsPNG(new File("chart.jpg"), c, 1920, 1080);
    }

    private static void ex2() throws SQLException {
        System.out.println(
            "Cтрана с самым высоким показателем экономики среди \"Latin America and Caribbean\" и \"Eastern Asia\" "
                + sql.getCountryWithHigherEconomy());
    }

    private static void ex3() throws IOException, SQLException {
        System.out.println("Страна с \"самыми средними показателями\" среди \"Western Europe\" и \"North America\" "
            + sql.getCountryWithHigherEconomy1());
    }
}