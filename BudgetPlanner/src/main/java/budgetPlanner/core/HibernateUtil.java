package budgetPlanner.core;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import budgetPlanner.model.Expenses;
import budgetPlanner.model.IncomePay;
import budgetPlanner.model.Users;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory = buildSessionFactory();

	@SuppressWarnings("deprecation")
	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
	   
	public Integer addUserToDb(Users users) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;
		try {
			tx = session.beginTransaction();
			employeeID = (Integer) session.save(users);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return employeeID;
	}
	
	public String checkUsernameAndEmail(String username, String email) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String SQL_Query="FROM Users";
			Query query=session.createQuery(SQL_Query);
			List<?> users =query.list();
			Iterator<?> itr=users.iterator();
			while(itr.hasNext())
		    {
				Users user=(Users)itr.next();
				if (user.getUserName().equals(username)) {
					return "Username is in use!";
				}
				if (user.getEmail().equals(email)) {
					return "Email is in use!";
				}
		    }			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return null;
	}
	
	public Users checkUsernameAndPassword(String username, String password) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String SQL_Query="FROM Users";
			Query query=session.createQuery(SQL_Query);
			List<?> users =query.list();
			Iterator<?> itr=users.iterator();
			while(itr.hasNext()) {
				Users user=(Users)itr.next();
				if ((user.getUserName().equals(username)) && (user.getPassword().equals(password))) {
					return user;
				}
		    }			
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return null;
	}

	public IncomePay getIncomePay(Integer userId) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		IncomePay pay = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			pay = (IncomePay) session.get(IncomePay.class, userId);			
			tx.commit(); 
		} catch (HibernateException e) {
			if (tx != null) {
			tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return pay;
	}
	
	public void addIncomePay(IncomePay incomePay) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(incomePay);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
	}

	public void updateIncomePay(IncomePay incomePay) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(incomePay);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}		
	}

	public Expenses getExpenses(Integer userId) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Expenses myExpenses = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			myExpenses = (Expenses) session.get(Expenses.class, userId);			
			tx.commit(); 
		} catch (HibernateException e) {
			if (tx != null) {
			tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return myExpenses;
	}

	public void addExpenses(Expenses expenses) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(expenses);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}		
	}

	public void updateExpenses(Expenses expenses) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(expenses);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}			
	}
	
	public void updateUser(Users user) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}			
	}

	public String getUserName(Integer userId) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Users user = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			user = (Users) session.get(Users.class, userId);			
			tx.commit(); 
		} catch (HibernateException e) {
			if (tx != null) {
			tx.rollback();
			}
			throw new RuntimeException(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return user.getUserName();
	}
	
	public List<?> getUserList() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Transaction tx = null;
		List<?> users = null;
		try {
			tx = session.beginTransaction();
			String SQL_Query="FROM Users";
			Query query=session.createQuery(SQL_Query);
			users =query.list();					
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return users;
	}
	
	public boolean isAdmin(Integer userId) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Users user = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			user = (Users) session.get(Users.class, userId);			
			tx.commit(); 
		} catch (HibernateException e) {
			if (tx != null) {
			tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		if (user.getType().equals("admin")) {
			return true;
		}
		return false;
	}

	public void delteUser(Integer userId) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		IncomePay incomePay = null;
		Expenses expenses = null;
		Users user = null;
		try {
			incomePay = new IncomePay(userId);
			expenses = new Expenses(userId);
			user = getUser(userId);
		} catch (Exception e1) {
			throw new Exception(e1);
		}
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(incomePay);
			session.delete(expenses);
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
			tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		
	}

	private Users getUser(Integer userId) throws Exception {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		Users user = null;
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			user = (Users) session.get(Users.class, userId);			
			tx.commit(); 
		} catch (HibernateException e) {
			if (tx != null) {
			tx.rollback();
			}
			throw new Exception(e);
		} finally {
			if(session!=null) {
				session.close();
			}
		}
		return user;
	}
}
