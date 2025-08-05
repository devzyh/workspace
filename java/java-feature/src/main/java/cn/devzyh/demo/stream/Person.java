package cn.devzyh.demo.stream;

import java.util.List;

public class Person {

    int id;
    String name;
    List<String> hobbies;

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

    public List<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    public Person() {
    }

    public Person(int id, String name, List<String> hobbies) {
        this.id = id;
        this.name = name;
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hobbies=" + hobbies +
                '}';
    }
}
