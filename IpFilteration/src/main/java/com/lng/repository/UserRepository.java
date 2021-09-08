package com.lng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lng.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>
{

	@Query("select u.isAdmin from User u where LOWER(userId)=LOWER(:id)")
	public boolean IsAdmin(@Param("id") String id);
	public User findUserByUserId(String userId);
	
}
