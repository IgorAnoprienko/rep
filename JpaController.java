package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.IModel;

public class JpaController implements IController {
	public EntityManagerFactory emf = Persistence.createEntityManagerFactory("Practice");

	public List getObjectList(Class clazz) {
		EntityManager em = emf.createEntityManager();
		// ������� ��'� ����������� ������ ��� �������� �����
		String queryName = clazz.getSimpleName() + "." + "findAll";
		// �������� ������ ������ ������� �������� �����
		List list = em.createNamedQuery(queryName).getResultList();
		em.close();
		return list;
	}

	@Override
	public void add(Object obj) {
		if (exist((IModel) obj))
			return;
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void edit(int id, Object obj, String className) {
		if (exist((IModel) obj))
			return;
		EntityManager em = emf.createEntityManager();
		try {
			Class clazz = Class.forName(className);
			IModel objU = (IModel) em.find(clazz, id);
			objU.updateWith(obj);
			em.getTransaction().begin();
			em.merge(objU);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int id, String className) {
		EntityManager em = emf.createEntityManager();
		try {
			Class clazz = Class.forName("model." + className);
			Object delObj = em.find(clazz, id);
			em.getTransaction().begin();
			em.remove(delObj);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public TableModel getModel(String className) {
		try {
			Class clazz = Class.forName("model." + className);
			// �������� ��������� �������
			IModel obj = (IModel) clazz.newInstance();
			String[] header = obj.getTableHeaders();
			// �������� ������ ��'����
			List list = getObjectList(clazz);
			if (list == null || list.size() == 0)
				return new DefaultTableModel(null, header);
			// ��������� ����� ��������� ������
			Object[][] array = new Object[list.size()][header.length];
			// ���������� �����
			int i = 0;
			for (Object s : list)
				array[i++] = ((IModel) s).getTableRowData();
			// ��������� ������
			return new DefaultTableModel(array, header);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean exist(IModel obj) {
		Class clazz = obj.getClass();
		// �������� ������ ������ ������� �������� �����
		List list = getObjectList(clazz);
		if (list != null && list.size() != 0)
			for (Object current : list)
				if (current.equals(obj))
					return true;
		return false;
	}

	public Object findByID(Class cls, int id) {
		EntityManager em = emf.createEntityManager();
		Object res = em.find(cls, id);
		em.close();
		return res;
	}

}
