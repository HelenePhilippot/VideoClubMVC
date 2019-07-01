package org.formation.metier;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.formation.metier.view.JsonViews;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("bluray")
public class BluRay extends Article {
	@Column(name = "trois_d")
	@JsonView(JsonViews.Common.class)
	private Boolean troisD;

	public BluRay() {

	}

	public Boolean getTroisD() {
		return troisD;
	}

	public void setTroisD(Boolean troisD) {
		this.troisD = troisD;
	}

}
