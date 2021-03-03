import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Mongo {
    private static DB database;

    public static DB getDatabase() {
        if (database == null) {

            MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
            database = mongoClient.getDB("geek");
        }
        return database;
    }

    public static DBCollection getUsersCollection() {
        return getDatabase().getCollection("users");
    }
}
