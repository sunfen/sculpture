package cn.sf.sculpture.project.domain.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.sf.sculpture.common.domain.entity.AbstractSecureObject;
import cn.sf.sculpture.user.domain.entity.User;

/**
 * 负责人
 * @author a123
 *
 */
@Table(name="principal")
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler", "projects" })
public class Principal extends AbstractSecureObject{

    @Column(length = 64)
    private String name;
    
    @Column(length = 11)
    private String phone;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;
    
    
    
    @OneToMany(mappedBy = "principal", cascade = CascadeType.REMOVE)
    private List<Project> projects;
    
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
	
}
