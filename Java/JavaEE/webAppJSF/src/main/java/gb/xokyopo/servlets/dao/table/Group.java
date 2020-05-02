package gb.xokyopo.servlets.dao.table;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "my_groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany
    @JoinTable(name = "my_users_my_groups",
            joinColumns = { @JoinColumn(name = "grouplist_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private List<User> userList;

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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
