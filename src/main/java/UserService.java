import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static boolean create(User user) {
        if (user.getId() != null) {
            System.err.println("При создании пользователь не может иметь id");
            return false;
        }
        WriteResult result = Mongo.getUsersCollection().insert(UserMapper.toDbObject(user));
        return result.wasAcknowledged();
    }

    public static List<User> getAll() {
        DBCursor usersCursor = Mongo.getUsersCollection().find();
        List<User> users = new ArrayList<>();
        while (usersCursor.hasNext()) {
            users.add(UserMapper.toUser(usersCursor.next()));
        }
        return users;

    }

    public static User getById(String hexId) {
        DBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(hexId));
        DBObject dbUSer = Mongo.getUsersCollection().findOne(query);
        return UserMapper.toUser(dbUSer);
    }

    public static boolean update(User user) {
        if (user.getId() == null) {
            System.err.println("При обновлении пользователь должен иметь id");
            return false;
        }
        DBObject query = new BasicDBObject();
        query.put("_id", user.getId());
        DBObject modify = Mongo.getUsersCollection().findAndModify(query, UserMapper.toDbObject(user));
        return modify != null;
    }

    public static boolean delete(User user) {
        if (user.getId() == null) {
            System.err.println("При удалении пользователь должен иметь id");
            return false;
        }
        DBObject query = new BasicDBObject();
        query.put("_id", user.getId());
        WriteResult result = Mongo.getUsersCollection().remove(query);
        return result.wasAcknowledged();
    }
}
