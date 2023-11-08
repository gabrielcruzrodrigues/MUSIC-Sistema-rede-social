package com.gabriel.music.redesocial.domain.user.DTO;

import com.gabriel.music.redesocial.domain.Friend;
import com.gabriel.music.redesocial.domain.PhoneNumber;
import com.gabriel.music.redesocial.domain.user.SocialMedia;
import com.gabriel.music.redesocial.domain.enums.AvaliabityEnum;
import com.gabriel.music.redesocial.domain.enums.GenreEnum;
import com.gabriel.music.redesocial.domain.enums.InstrumentsEnum;
import com.gabriel.music.redesocial.domain.material.Material;
import com.gabriel.music.redesocial.domain.post.Comment;
import com.gabriel.music.redesocial.domain.post.Post;
import com.gabriel.music.redesocial.domain.user.ImageUser;
import com.gabriel.music.redesocial.domain.user.VideoUser;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record UserResponseRegisterToSearchForABand(
        Long id,
        String name,
        String username,
        String email,
        String cep,
        String goals,
        String whatsapp,
        Date age,
        LocalDate EntryDate,
        Integer shows,
        List<GenreEnum> genre,
        List<InstrumentsEnum> instruments,
        List<AvaliabityEnum> availability,
        List<SocialMedia> socialMedia,
        List<Post> posts,
        ImageUser imageProfile,
        ImageUser imageBackground,
        PhoneNumber phoneNumber,
        List<ImageUser> photos,
        List<VideoUser> videos,
        List<Friend> friends,
        List<Material> purchasedMaterials,
        List<Material> createdMaterials,
        List<Material> saves,
        List<Comment> comments
) {
}
