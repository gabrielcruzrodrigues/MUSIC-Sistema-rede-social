package com.gabriel.music.redesocial.domain.user;

import com.gabriel.music.redesocial.domain.enums.AvaliabityEnum;
import com.gabriel.music.redesocial.domain.enums.GenreEnum;
import com.gabriel.music.redesocial.domain.enums.InstrumentsEnum;
import com.gabriel.music.redesocial.domain.material.Material;
import com.gabriel.music.redesocial.domain.post.Comment;
import com.gabriel.music.redesocial.domain.post.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true, nullable = false)
    @NotNull
    @NotBlank(message = "o campo username não pode estar nulo.")
    private String username;

    @Column(unique = true, nullable = false)
    @Email
    @NotNull(message = "o campo email não pode estar nulo.")
    private String email;

    @NotBlank
    @NotNull(message = "O campo password não pode estar nulo.")
    @Size(min = 8, max = 60)
    private String password;

    private String cep;
    private String about;
    private String whatsapp;
    private LocalDate age;
    private LocalDate entryDate;
    private Integer shows;

    @Enumerated(value = EnumType.STRING)
    private List<GenreEnum> genre;

    @Enumerated(EnumType.STRING)
    private List<InstrumentsEnum> instruments;

    @Enumerated(EnumType.STRING)
    private List<AvaliabityEnum> availability;

    @OneToMany(mappedBy = "user")
    private List<SocialMedia> socialMedia;

    @OneToMany(mappedBy = "creator")
    private List<Post> posts;

    @OneToOne(mappedBy = "userImageProfile")
    private ImageUser imageProfile;

    @OneToOne(mappedBy = "userBackground")
    private ImageUser imageBackground;

    @OneToOne(mappedBy = "user")
    private PhoneNumber phoneNumber;

    @OneToMany(mappedBy = "idUserPhotos")
    private List<ImageUser> photos;

    @OneToMany(mappedBy = "user")
    private List<VideoUser> videos;

    @OneToMany(mappedBy = "user")
    private List<Friend> friends;

    @OneToMany(mappedBy = "purchasedMaterialsUser_id")
    private List<Material> purchasedMaterials;

    @OneToMany(mappedBy = "createdMaterialsUser_id")
    private List<Material> createdMaterials;

    @OneToMany(mappedBy = "savesUser_id")
    private List<Material> saves;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @Override
    public String toString() {
        Long var10000 = this.getId();
        return "User(id=" + var10000 + ", name=" + this.getName() + ", username=" + this.getUsername() + ", email=" + this.getEmail()
                + ", password=" + this.getPassword() + ", cep=" + this.getCep() + ", goals=" + this.getAbout() + ", whatsapp=" + this.getWhatsapp()
                + ", age=" + this.getAge() + ", entryDate=" + this.getEntryDate() + ", shows=" + this.getShows() + ", genre=" + this.getGenre()
                + ", instruments=" + this.getInstruments() + ", availability=" + this.getAvailability() + ", socialMedia=" + this.getSocialMedia()
                + ", posts=" + this.getPosts() + ", imageProfile=" + this.getImageProfile().getImageReference() + ", imageBackground=" + this.getImageBackground()
                + ", phoneNumber=" + this.getPhoneNumber() + ", photos=" + this.getPhotos() + ", videos=" + this.getVideos() + ", friends=" + this.getFriends()
                + ", purchasedMaterials=" + this.getPurchasedMaterials() + ", createdMaterials=" + this.getCreatedMaterials() + ", saves=" + this.getSaves() + ", comments=" + this.getComments() + ")";
    }
}
