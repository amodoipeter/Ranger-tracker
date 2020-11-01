import org.sql2o.Connection;

import java.sql.Timestamp;
import java.util.List;

public class Sightings {
    private final String rangerName;
    private int id;
    private String animal;
    private final String location;

    private final Timestamp date;
    private final Timestamp month;


    public Sightings(String location, String rangerName, String animal, Timestamp date, Timestamp month){

        this.rangerName = rangerName;
        this.date = date;
        this.month = month;
        this.animal = animal;
        this.animal=animal;
        this.location=location;

    }
    public String getRangerName(){
        return rangerName;
    }
    public int getId(){
        return id;
    }
    public String getLocation() { return location;}
    public Timestamp getDate() { return date; }
    public String getAnimal() { return animal; }
    public Timestamp getMonth() { return month; }


    //save method save()

    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO sightings (rangerName,location,animal) VALUES (:rangerName, :location,:animal)";

            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("location",location)
                    .addParameter("animal",animal)
                    .executeUpdate()
                    .getKey();
        }
    }

    //find()
    public static Sightings find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings where id=:id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Sightings.class);
        }
    }
    //all()
    public static List<Sightings> all() {
        String sql = "SELECT * FROM sightings";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Sightings.class);
        }
    }
    @Override
    public boolean equals(Object otherSighting){
        if (!(otherSighting instanceof Sightings)) {
            return false;
        } else {
            Sightings newSighting = (Sightings) otherSighting;
            return this.getId() == newSighting.id;
        }
    }

    public void month() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "UPDATE sightings SET month = now() WHERE id = :id";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}