package Entity;
import java.util.*;

public class User {


    private final String id;
    private final String name;
    private final String email;

    private User(Builder builder){
        this.id=UUID.randomUUID().toString();
        if(builder.name==null || builder.email==null){
            throw new IllegalArgumentException("User name or email cannot be null");
        }
        this.name= builder.name;
        this.email= builder.email;
    }

    public static class Builder{
        private String name;
        private String email;

        public Builder setName(String name){
            this.name=name;
            return this;
        }

        public Builder setEmail(String email){
            this.email=email;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
