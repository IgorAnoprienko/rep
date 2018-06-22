package controller;

import javax.swing.table.TableModel;

public interface IController {

	public void add(Object obj);
	public void edit(int id, Object obj,String className);
	public void delete(int id, String className);
	TableModel getModel(String className);
	
}