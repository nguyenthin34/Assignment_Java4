package com.poly.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private boolean actives;

	private String descriptions;

	private String poster;

	private String title;

	private int views;


	//bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy="video")
	private List<Favorite> favorites;

	//bi-directional many-to-one association to Shared
	@OneToMany(mappedBy="video")
	private List<Shared> shareds;

	public Video() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getActives() {
		return this.actives;
	}


	public void setActives(boolean actives) {
		this.actives = actives;
	}


	public String getDescriptions() {
		return this.descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}


	public String getPoster() {
		return this.poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getViews() {
		return this.views;
	}

	public void setViews(int views) {
		this.views = views;
	}


	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Favorite addFavorite(Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setVideo(this);

		return favorite;
	}

	public Favorite removeFavorite(Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setVideo(null);

		return favorite;
	}

	public List<Shared> getShareds() {
		return this.shareds;
	}

	public void setShareds(List<Shared> shareds) {
		this.shareds = shareds;
	}

	public Shared addShared(Shared shared) {
		getShareds().add(shared);
		shared.setVideo(this);

		return shared;
	}

	public Shared removeShared(Shared shared) {
		getShareds().remove(shared);
		shared.setVideo(null);

		return shared;
	}

}