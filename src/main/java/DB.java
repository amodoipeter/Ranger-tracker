import org.sql2o.Sql2o;

public class DB {
//    postgresql-concentric-33639
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wild", "moringa", "Ap@33358371");
//    postgres://jieoxouxjsarmw:aca439265342852420efb13d28330b3bbc5a36eddfb469c0ddf063be93d1c22b@ec2-3-233-236-188.compute-1.amazonaws.com:5432/d7om722o88p9i

    static String connectionString = "jdbc:postgresql://ec2-54-83-33-14.compute-1.amazonaws.com:5432/d7om722o88p9i"; //!
    static Sql2o sql2o = new Sql2o(connectionString, "jieoxouxjsarmw", "aca439265342852420efb13d28330b3bbc5a36eddfb469c0ddf063be93d1c22b"); //!

}


