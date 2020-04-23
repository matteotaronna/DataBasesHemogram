package hemogram.db.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import hemogram.db.interfaces.UserManager;
import hemogram.db.pojos.users.Role;
import hemogram.db.pojos.users.User;

public class JPAUserManager implements UserManager {

private EntityManager em;
	
	@Override
	public void connect() 
	{
		em = Persistence.createEntityManagerFactory("HemogramDB-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public void disconnect() 
	{
		em.close();
	}

	@Override
	public void createUser(User user) 
	{
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}

	@Override
	public void createRole(Role role) 
	{
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		boolean existRole = false;
		for (Role role2 : roles) 
		{
			if(role.getRole().equalsIgnoreCase(role2.getRole()))
			{
				existRole = true;
				break;
			}
		}
		if(existRole == false)
		{
			em.getTransaction().begin();
			em.persist(role);
			em.getTransaction().commit();
		}
	}

	@Override
	public Role getRole(int id) 
	{
		Query q = em.createNativeQuery("SELECT * FROM roles WHERE id = ?", Role.class);
		q.setParameter(1, id);
		Role role = (Role) q.getSingleResult();
		return role;
	}

	@Override
	public List<Role> getRoles() 
	{
		Query q = em.createNativeQuery("SELECT * FROM roles", Role.class);
		List<Role> roles = (List<Role>) q.getResultList();
		return roles;
	}
	
	@Override
	public Role getRoleByName (String roleName)
	{
		Role role = null;
		try
		{
			Query q = em.createNativeQuery("SELECT * FROM roles WHERE role = ?", Role.class);
			q.setParameter(1, roleName);
			role = (Role) q.getSingleResult();
			
		} catch (NoResultException nre) {
			// This is what happens when no result is retrieved
			return null;
		}
		return null;
	}

	@Override
	public User checkPassword(String username, String password) 
	{
		User user = null;
		try {
			// Create a MessageDigest
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hash = md.digest();
			// Create the query
			Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND password = ?", User.class);
			q.setParameter(1, username);
			q.setParameter(2, hash);
			user = (User) q.getSingleResult();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoResultException nre) {
			// This is what happens when no result is retrieved
			return null;
		}
		return user;
	}

}
