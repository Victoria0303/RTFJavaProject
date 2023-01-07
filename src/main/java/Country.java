public class Country {

    private static Integer countriesCount = 0;

    private final Integer id;
    private final String name;
    private final String region;
    private final Happiness happiness;
    private final Population population;
    private final double standardError;
    private final double economy;
    private final double dystopiaResidual;

    public Country(String name, String region, int happinessRank, double happinessScore,
        double standardError, double economy, double family, double health, double freedom,
        double trust, double generosity, double dystopiaResidual) {
        this.id = countriesCount;
        countriesCount++;
        this.name = name;
        this.region = region;
        this.happiness = new Happiness(this, happinessRank, happinessScore);
        this.standardError = standardError;
        this.economy = economy;
        this.population = new Population(this, family, health, freedom, trust, generosity);
        this.dystopiaResidual = dystopiaResidual;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public Happiness getHappiness() {
        return happiness;
    }

    public double getStandardError() {
        return standardError;
    }

    public double getEconomy() {
        return economy;
    }

    public double getDystopiaResidual() {
        return dystopiaResidual;
    }

    public Population getPopulation() {
        return population;
    }

    public Integer getId() {
        return id;
    }
}
