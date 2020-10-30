import org.sql2o.Connection;
import java.util.List;
import java.util.Objects;

public class Endangered extends Animals {

    private final String health;
    private final String name;
    private final String age;
    private int id;

    //constants
    private static final String THREAT_TYPE = "Endangered";

//getters

    public String getAge() {
        return age;
    }
    public int getId() {
        return id;
    }
    public static String getThreatType() {
        return THREAT_TYPE;
    }
    public String getHealth() {
        return health;
    }

//constructors

    public Endangered(String name, String health, String age) {

        super(name, health, age);
        this.name=name;
        this.health = health;
        this.age = age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endangered that = (Endangered) o;
        return id == that.id &&
                Objects.equals(health, that.health) &&
                Objects.equals(age, that.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(health, age, id);
    }

    //database
    //save()
    public void save() {
        try(Connection con = DB.sql2o.open())  {
            String sql = "INSERT INTO animals (name,threattype,age, health) VALUES (:name ,:threattype,:age, :health)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", super.getName())
                    .addParameter("threattype",THREAT_TYPE)
                    .addParameter("age",this.age)
                    .addParameter("health",this.health)
                    .executeUpdate()
                    .getKey();
        }
    }

    //list<>
    public static List<Endangered> allEndangered() {
        String sql = "SELECT * FROM animals where threattype='Endangereds'";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Endangered.class);
        }
    }

//find()

    public static Endangered find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Endangered endangered = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Endangered.class);
            return endangered;
        }
    }


    @Override
    public String getName() {
        return name;
    }
}