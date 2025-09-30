package ru.demo.secondlessonapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getTitle() {
        if (getRoleId() == 1)
            this.title = RoleName.PARTICIPANT.toString();
        if (getRoleId() == 2)
            this.title = RoleName.MANAGER.toString();
        if (getRoleId() == 3)
            this.title = RoleName.ADMIN.toString();
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
