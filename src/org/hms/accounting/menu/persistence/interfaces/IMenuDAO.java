package org.hms.accounting.menu.persistence.interfaces;

import java.util.List;

import org.hms.accounting.menu.MainMenu;
import org.hms.accounting.menu.MainMenuValue;
import org.hms.accounting.menu.MenuItem;
import org.hms.accounting.menu.MenuItemValue;
import org.hms.accounting.menu.SubMenu;
import org.hms.accounting.menu.SubMenuValue;
import org.hms.java.component.persistence.exception.DAOException;

public interface IMenuDAO {
	public void insert(MainMenu MainMenu) throws DAOException;

	public void update(MainMenu MainMenu) throws DAOException;

	public void delete(MainMenu MainMenu) throws DAOException;

	public List<MainMenuValue> findMainMenuValueByMainMenuId(String mainMenuId) throws DAOException;

	public void delete(MainMenuValue mainMenuValue) throws DAOException;

	public void insert(SubMenu SubMenu) throws DAOException;

	public void update(SubMenu SubMenu) throws DAOException;

	public void delete(SubMenu SubMenu) throws DAOException;

	public List<SubMenuValue> findSubMenuValueBySubMenuId(String subMenuId) throws DAOException;

	public void delete(SubMenuValue subMenuValue) throws DAOException;

	public void insert(MenuItem MenuItem) throws DAOException;

	public void update(MenuItem MenuItem) throws DAOException;

	public void delete(MenuItem MenuItem) throws DAOException;

	public List<MenuItemValue> findMenuItemValueByMenuItemId(String menuItemValueId) throws DAOException;

	public void delete(MenuItemValue menuItemValue) throws DAOException;

	public MainMenu findById(String id) throws DAOException;

	public List<MainMenu> findAll() throws DAOException;

}
