import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

public class UserMapper {
    public static DBObject toDbObject(User user) {
        DBObject document = new BasicDBObject();
        if (user.getId() != null) {
            document.put("_id", user.getId());
        }
        if (user.getName() != null) {
            document.put("name", user.getName());
        }
        if (user.getAge() != null) {
            document.put("age", user.getAge());
        }
        return document;
    }

    public static User toUser(DBObject dbObject) {
        User user = new User();

        if (dbObject.containsField("_id") && dbObject.get("_id") instanceof ObjectId) {
            ObjectId id = (ObjectId) dbObject.get("_id");
            user.setId(id);
        }

        if (dbObject.containsField("name") && dbObject.get("name") instanceof String) {
            String name = (String) dbObject.get("name");
            user.setName(name);
        }

        if (dbObject.containsField("age") && dbObject.get("age") instanceof Integer) {
            Integer age = (Integer) dbObject.get("age");
            user.setAge(age);
        }

        return user;
    }
}
