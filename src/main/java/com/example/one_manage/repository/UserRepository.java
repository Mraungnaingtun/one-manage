package com.example.one_manage.repository;

import com.example.one_manage.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByName(String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE USER_INFO  SET is_shown_noti = ?1 WHERE name = ?2", nativeQuery = true)
    void updateShowedNotiByUsername(boolean showedNoti, String username);
}
