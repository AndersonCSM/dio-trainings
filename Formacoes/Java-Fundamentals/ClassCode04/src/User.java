import static java.util.Objects.hash;
import static java.util.Objects.isNull;

public class User {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString(){
        return String.format("id: %s, name: %s", id, name);
    }

    @Override
    public boolean equals(final Object o){
        if (o == this) return true;
        if ((isNull(o)) || !(o instanceof User user)) return false;
        return this.id == user.getId() && java.util.Objects.equals(this.name, user.getName());

    }

    @Override
    public int hashCode(){
        return hash(this.id, this.name);
    }

}
