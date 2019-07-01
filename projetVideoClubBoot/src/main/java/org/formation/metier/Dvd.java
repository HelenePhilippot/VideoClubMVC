package org.formation.metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.formation.metier.view.JsonViews;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@DiscriminatorValue("dvd")
public class Dvd extends Article {
	@JsonView(JsonViews.Common.class)
	private Boolean bonus;

	public Dvd() {

	}

	public Boolean getBonus() {
		return bonus;
	}

	public void setBonus(Boolean bonus) {
		this.bonus = bonus;
	}

}
