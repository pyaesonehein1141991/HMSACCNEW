package org.hms.accounting.menu.service.interfaces;

import java.util.List;

import org.hms.accounting.menu.MainMenu;
import org.hms.accounting.menu.MenuItem;
import org.hms.accounting.menu.SubMenu;
import org.hms.java.component.SystemException;

public interface IMenuService {
	public void addNewMainMenu(MainMenu mainMenu) throws SystemException;

	public void updateMainMenu(MainMenu mainMenu) throws SystemException;

	public void deleteMainMenu(MainMenu mainMenu) throws SystemException;

	public void addNewSubMenu(SubMenu subMenu) throws SystemException;

	public void updateSubMenu(SubMenu subMenu) throws SystemException;

	public void deleteSubMenu(SubMenu subMenu) throws SystemException;

	public void addNewMenuItem(MenuItem menuItem) throws SystemException;

	public void updateMenuItem(MenuItem menuItem) throws SystemException;

	public void deleteMenuItem(MenuItem menuItem) throws SystemException;

	public MainMenu findMainMenuById(String id) throws SystemException;

	public List<MainMenu> findAllMainMenu() throws SystemException;
}
