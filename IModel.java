package model;

public interface IModel {
	public String[] getTableHeaders();
	public Object[] getTableRowData();
	void updateWith(Object mask);
	public int getId();
}
