package com.poly.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private boolean admins;
	private String email;
	private String fullname;
	private String passwords;

	//bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy="user")
	private List<Favorite> favorites;

	//bi-directional many-to-one association to Shared
	@OneToMany(mappedBy="user")
	private List<Shared> shareds;

	public User() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean getAdmins() {
		return this.admins;
	}

	public void setAdmins(boolean admins) {
		this.admins = admins;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPasswords() {
		return this.passwords;
	}

	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public Favorite addFavorite(Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setUser(this);

		return favorite;
	}

	public Favorite removeFavorite(Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setUser(null);

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
		shared.setUser(this);

		return shared;
	}

	public Shared removeShared(Shared shared) {
		getShareds().remove(shared);
		shared.setUser(null);

		return shared;
	}

}