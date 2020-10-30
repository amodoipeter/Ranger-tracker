import org.sql2o.*;
import java.util.List;

public class Ranger {
    private String rangerName;
    private int rangerBadge;
    private int id;

    public Ranger(String rangerName, int rangerBadge) {
        this.rangerName = rangerName;
        this.rangerBadge = rangerBadge;
    }

    public int getId() {
        return id;
    }

    public String getRangerName() {
        return rangerName;
    }

    public int getRangerBadge() {
        return rangerBadge;
    }

    @Override
    public boolean equals(Object otherRanger) {
        if (!(otherRanger instanceof Ranger)) {
            return false;
        } else {
            Ranger newRanger = (Ranger) otherRanger;
            return this.getRangerName().equals(newRanger.getRangerName()) &&
                    this.getRangerBadge() == newRanger.getRangerBadge();
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO rangers (rangerName, rangerBadge) VALUES (:rangerName, :rangerBadge)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("rangerName", this.rangerName)
                    .addParameter("rangerBadge", this.rangerBadge)
                    .executeUpdate()
                    .getKey();
        }
    }

    //Return all db entries
    public static List<Ranger> all() {
        String sql = "SELECT * FROM rangers";
        try (Connection con = DB.sql2o.open()) {

            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Ranger.class);

        }
    }

    //find ranger by id find()
    public static Ranger find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM rangers where id=:id";
            Ranger ranger = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Ranger.class);
            return ranger;
        }
    }

    //delete()
    public void delete() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM rangers WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

    //sightings method

    public List<Sightings> getSighting() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings;";
            return con.createQuery(sql)
                    .executeAndFetch(Sightings.class);
        }

    }
}