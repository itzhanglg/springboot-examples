package com.xtkj.test;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xtkj.Application;
import com.xtkj.dao.UserRepository;
import com.xtkj.dao.UserRepositoryByName;
import com.xtkj.dao.UserRepositoryCrudRepository;
import com.xtkj.dao.UserRepositoryPagingAndSorting;
import com.xtkj.dao.UserRepositoryQueryAnnotation;
import com.xtkj.dao.UserRepositorySpecification;
import com.xtkj.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRepositoryByName userRepositoryByName;
	@Autowired
	private UserRepositoryQueryAnnotation userRepositoryQueryAnnotation;
	@Autowired
	private UserRepositoryCrudRepository userRepositoryCrudRepository;
	@Autowired
	private UserRepositoryPagingAndSorting userRepositoryPagingAndSorting;
	@Autowired
	private UserRepositorySpecification userRepositorySpecification;
	
	//6.JpaSpecificationExecutor--测试
	@Test	//单条件测试
	public void testJpaSpecificationExecutor1() {
		//Specification:用于封装查询条件
		Specification<User> spec = new Specification<User>() {
			//Predicate:封装了单个的查询条件
			//Root<User> root:查询对象的属性的封装
			//CriteriaQuery<?> query:封装了我们要执行查询中的各个部分信息,select  from  order by
			//CriteriaBuilder criteriaBuilder:查询条件的构造器,定义不同的查询条件
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//where name = "周星驰"
				//equal(arg0,arg1): arg0:查询的条件属性  arg1:条件的值
				Predicate pre = criteriaBuilder.equal(root.get("name"), "周星驰");
				return pre;
			}
		};
		List<User> userList = this.userRepositorySpecification.findAll(spec);
		for (User user : userList) {
			System.out.println(userList);
		}
	}
	@Test	//多条件测试
	public void testJpaSpecificationExecutor2() {
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//一个Predicate封装一个查询条件,两个条件用数组或集合
				Predicate[] pre = new Predicate[] {
					criteriaBuilder.equal(root.get("name"), "周星驰"),
					criteriaBuilder.equal(root.get("age"), 36)
				};
				//and(pre):pre是可变数组,通过and方法将多个条件连接起来
				return criteriaBuilder.and(pre);
			}
		};
		List<User> userList = this.userRepositorySpecification.findAll(spec);
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test	//多条件测试第二种写法
	public void testJpaSpecificationExecutor3() {
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				//一个Predicate封装一个查询条件,两个条件用数组或集合
				//and(pre):pre是可变数组,通过and方法将多个条件连接起来
//				return criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), "周星驰"),criteriaBuilder.equal(root.get("age"), 36));
				
				//where (name = "周星驰" and age = 36) or id = 1	 --and的优先级比or高,不用括号也可以
				return criteriaBuilder.or(criteriaBuilder.and(criteriaBuilder.equal(root.get("name"), "周星驰"),criteriaBuilder.equal(root.get("age"), 36)),
							criteriaBuilder.equal(root.get("id"), "1"));
			}
		};
		//sort封装排序规则
		Sort sort = new Sort(Direction.DESC, "id");
		List<User> userList = this.userRepositorySpecification.findAll(spec,sort);
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	//5.PagingAndSortingRepository--测试
	@Test	//排序测试
	public void testPagingAndSortingRepositorySort() {
		//Sort对象封装了排序规则
		Sort sort = new Sort(Direction.DESC, "id");
		List<User> userList = (List<User>)this.userRepositoryPagingAndSorting.findAll(sort);
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test	//分页测试
	public void testPagingAndSortingRepositoryPaging() {
		//Pageable封装了分页的参数，当前页，每页显示的条数。当前页是从零开始。
		//PageRequest.of(page,size): page当前页，size每页显示的条数
		Pageable pageable = PageRequest.of(0, 2);
		Page<User> page = this.userRepositoryPagingAndSorting.findAll(pageable);
		System.out.println("总条数："+page.getTotalElements());
		System.out.println("总页数："+page.getTotalPages());
		for (User user : page.getContent()) {
			System.out.println(user);
		}
	}
	@Test	//排序+分页排序
	public void testPagingAndSortingRepositorySortAndPaging() {
		//PageRequest.of(int page, int size, Direction direction, String... properties)
		//page:当前页，size:每页条数,direction:排序方向,properties:以哪个实体属性进行排序
		Pageable pageable = PageRequest.of(0, 2, Direction.DESC, "id");
		Page<User> page = this.userRepositoryPagingAndSorting.findAll(pageable);
		System.out.println("总条数："+page.getTotalElements());
		System.out.println("总页数："+page.getTotalPages());
		for (User user : page.getContent()) {
			System.out.println(user);
		}
	}
	
	//4.CrudRepository--测试
	@Test	//添加数据
	public void testCrudRepositorySave() {
		User user = new User();
		user.setAddress("中国杭州");
		user.setAge(16);
		user.setName("姜文");
		this.userRepositoryCrudRepository.save(user);
	}
	@Test	//修改数据
	public void testCrudRepositoryUpdate() {
		User user = new User();
		user.setId(4);
		user.setAddress("中国广州");
		user.setAge(28);
		user.setName("姜文");
		this.userRepositoryCrudRepository.save(user);
	}
	@Test	//根据id查询
	public void testCrudRepositoryFindById() {
		Optional<User> user = this.userRepositoryCrudRepository.findById(4);
		System.out.println(user);
	}
	@Test	//查询所有
	public void testCrudRepositoryFindAll() {
		List<User> userList = (List<User>)this.userRepositoryCrudRepository.findAll();
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test	//根据id删除数据
	public void testCrudRepositoryDeleteById() {
		this.userRepositoryCrudRepository.deleteById(4);
	}
	
	//3.Repository--@Query注解测试
	@Test	//hql语句查询
	public void testFindByNameUserHQL() {
		List<User> userList = this.userRepositoryQueryAnnotation.findByNameUserHQL("zlg");
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test	//sql语句查询
	public void testFindByNameUserSQL() {
		List<User> userList = this.userRepositoryQueryAnnotation.findByNameUserSQL("成龙");
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test	//修改数据
	@Transactional		//@Test与@Transactional一起使用时，事务是自动回滚的
	@Rollback(false)	//取消自动回滚
	public void testUpdateUserNameById() {
		this.userRepositoryQueryAnnotation.updateUserNameById("葛优", 1);;
	}
	
	//2.Repository--方法名称命名测试
	@Test	//单条件查询
	public void testFindByName() {
		List<User> userList = this.userRepositoryByName.findByName("zlg");
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test	//多条件查询
	public void testFindByNameAndAge() {
		List<User> userList = this.userRepositoryByName.findByNameAndAge("zlg", 23);
		for (User user : userList) {
			System.out.println(user);
		}
	}
	@Test	//模糊查询
	public void testFindByNameLike() {
		List<User> userList = this.userRepositoryByName.findByNameLike("z%");
		for (User user : userList) {
			System.out.println(user);
		}
	}
	
	//1.JpaRepository--添加数据
	@Test
	public void testSave() {
		User user = new User();
		user.setName("成龙");
		user.setAge(42);
		user.setAddress("中国深圳");
		this.userRepository.save(user);
	}
	
}
