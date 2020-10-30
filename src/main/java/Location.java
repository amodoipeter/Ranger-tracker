import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Location {
    private final String name;
    private int id;

    //constructor
    public Location(String name) {
        this.name = name;
    }
    //getters
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    //save()
    public void save() {
        try(Connection con = DB.sql2o.open())  {
            String sql = "INSERT INTO locations (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }

    //Overrides


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    //find()
    public static Location find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM locations where id=:id";
            Location location= con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Location.class);
            return location;
        }
    }


    //List<>
    public static List<Location> all() {
        String sql = "SELECT * FROM locations";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Location.class);
        }
    }
}