package com.gabriel.music.redesocial.controller;

import com.gabriel.music.redesocial.domain.user.DTO.SocialMediaUpdate;
import com.gabriel.music.redesocial.domain.user.SocialMedia;
import com.gabriel.music.redesocial.domain.user.DTO.SocialMediaRegistration;
import com.gabriel.music.redesocial.service.SocialMediaService;
import com.gabriel.music.redesocial.service.exceptions.SocialMediaNotFoundException;
import com.gabriel.music.redesocial.service.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("social-media")
public class SocialMediaController {

    @Autowired
    private SocialMediaService socialMediaService;

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping("/create")
    public ResponseEntity<SocialMedia> save(@RequestBody @Valid SocialMediaRegistration socialMediaRegistration) throws UserNotFoundException {
        SocialMedia socialMedia = socialMediaService.save(socialMediaRegistration);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{username}")
                .buildAndExpand(socialMedia.getUsernameSocialMedia())
                .toUri();
        return ResponseEntity.created(uri).body(socialMedia);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<SocialMedia>> findAll() {
        return ResponseEntity.ok().body(socialMediaService.findAll());
    }

    @PutMapping("/update/{usernameSocial}")
    public ResponseEntity<SocialMedia> update(@RequestBody SocialMediaUpdate socialMediaUpdate, @PathVariable String usernameSocial) throws SocialMediaNotFoundException {
        return ResponseEntity.ok().body(socialMediaService.update(socialMediaUpdate, usernameSocial));
    }
}
