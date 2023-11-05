package com.gabriel.music.redesocial.repository;

import com.gabriel.music.redesocial.domain.user.ImageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageUserRepository extends JpaRepository<ImageUser, Long> {
}
