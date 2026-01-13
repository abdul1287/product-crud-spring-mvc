package product_crud.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import product_crud.model.Product;

@Repository
public class ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void save(Product prod) {
		sessionFactory.getCurrentSession().saveOrUpdate(prod);
	}

	@Transactional(readOnly = true)
	public List<Product> getAll() {
		return sessionFactory.getCurrentSession().createQuery("from Product", Product.class).getResultList();
	}

	@Transactional(readOnly = true)
	public Product getById(int id) {
		return sessionFactory.getCurrentSession().get(Product.class, id);
	}

	@Transactional
	public void delete(int id) {
		Product prod = getById(id);
		if (prod != null) {
			sessionFactory.getCurrentSession().delete(prod);
		}
	}
}
