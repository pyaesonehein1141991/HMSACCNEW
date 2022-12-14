package org.hms.accounting.menu;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import org.hms.accounting.common.BasicEntity;
import org.hms.accounting.common.TableName;
import org.hms.java.component.idgen.service.IDInterceptor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = TableName.MAINMENU)
@TableGenerator(name = "MAINMENU_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", pkColumnValue = "MAINMENU_GEN", allocationSize = 10)
@NamedQueries(value = {
		@NamedQuery(name = "MainMenu.findAll", query = "SELECT m FROM MainMenu m ORDER BY m.priority ASC"),
		@NamedQuery(name = "MainMenu.findById", query = "SELECT m FROM MainMenu m WHERE m.id = :id") })
@EntityListeners(IDInterceptor.class)
@Data
@NoArgsConstructor
public class MainMenu implements Serializable {

	private static final long serialVersionUID = 8712245339976255300L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MAINMENU_GEN")
	private String id;
	private String name;
	private String action;
	private int priority;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "MAINMENUID", referencedColumnName = "ID")
	private List<SubMenu> subMenuList;

	@Embedded
	private BasicEntity basicEntity;

	@Version
	private int version;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + priority;
		result = prime * result + ((basicEntity == null) ? 0 : basicEntity.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MainMenu other = (MainMenu) obj;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priority != other.priority)
			return false;
		if (basicEntity == null) {
			if (other.basicEntity != null)
				return false;
		} else if (!basicEntity.equals(other.basicEntity))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

}
