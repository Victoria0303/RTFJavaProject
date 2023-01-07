import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
    }

    private static void ex1() throws SQLException, IOException {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        var countryByEconomy = sql.getCountriesEconomy();
        for (var key : countryByEconomy.keySet()) {
            ds.addValue(countryByEconomy.get(key), key, "");
        }

        var c = ChartFactory.createBarChart("График по показателю экономики", "Страны", "Экономика", ds);
        ChartUtils.saveChartAsPNG(new File("chart.jpg"), c, 1920, 1080);
        System.out.println("1. Диаграмма была успешно создана!");
    }

    private static void ex2() throws SQLException {
        System.out.println(
            "2. Cтрана с самым высоким показателем экономики среди \"Latin America and Caribbean\" и \"Eastern Asia\" - "
                + sql.getCountryWithHigherEconomy());
    }


    private static void ex3() throws IOException, SQLException {
        System.out.println("3. Страна с \"самыми средними показателями\" среди \"Western Europe\" и \"North America\" - "
            + sql.getCountryWithAvgAvgEconomy());
    }
}


