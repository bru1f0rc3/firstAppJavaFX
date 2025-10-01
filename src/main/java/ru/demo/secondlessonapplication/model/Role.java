package ru.demo.secondlessonapplication.model;

import jakarta.persistence.*;
import org.hibernate.Session;
import ru.demo.secondlessonapplication.util.HibernateSessionFactoryUtil;

@Entity
@Table(name = "roles", schema = "public")
public class Role {
    @Id
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "title")
    private String title;

    public Long getRoleId() {
        return roleId;
    }

    private String loadTitleRoleName(){
        String title = "";
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            Query query = session.createQuery("from Role where roleId = :roleId");
            query.setParameter("roleId", getRoleId());
            Role role = (Role) query.getSingleResult();
            title = role.getTitle();
        }
        catch (Exception e){
            System.out.println("Исключение " + e);
        }
        return title;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getTitle() {
        if (this.title == null) {
            this.title = loadTitleRoleName();
        }
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}