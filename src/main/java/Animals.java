import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Animals {
    private  String name;
    private String health;
    private String age;
    private  int id;

    //Constants


    public static final String  THREAT_TYPE ="Non-Endangered";
    //constructor

    public Animals(String name, String health, String age){
        this.name = name;
        this.health=health;
        this.age=age;
    }

    //getters

    public String getName(){ return name; }

    public String getHealth(){ return health; }
    public String getAge(){ return age; }

    public int getId(){ return  id;}
//    public static String getThreatType(){ return  THREAT_TYPE  ;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animals animal = (Animals) o;
        return id == animal.id &&
                Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public void save() {
        try(Connection con = DB.sql2o.open()) {

            String sql = "INSERT INTO animals (name,health,age) VALUES (:name,:health,:age )";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("age", this.age)
//                    .addParameter("threattype", THREAT_TYPE)
                    .addParameter("health", this.health)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Animals> all() {

        String sql = "SELECT * FROM animals";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Animals.class);
        }
    }


    public static Animals find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals where id=:id";
            Animals animals = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animals.class);
            return animals;
        }
    }

}
