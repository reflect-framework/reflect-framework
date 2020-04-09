package nth.reflect.util.english.plural;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EnglishPluralTest {

	@Test
	public void testFromSingularNoun_givenFez_mustReturnFezzes() {
		assertThat(EnglishPlural.fromSingularNoun("fez")).isEqualTo("fezzes");
	}

	@Test
	public void testFromSingularNoun_givenGas_mustReturnGasses() {
		assertThat(EnglishPlural.fromSingularNoun("gas")).isEqualTo("gasses");
	}

	@Test
	public void testFromSingularNoun_givenTruss_mustReturnTrusses() {
		assertThat(EnglishPlural.fromSingularNoun("truss")).isEqualTo("trusses");
	}

	@Test
	public void testFromSingularNoun_givenBus_mustReturnBuses() {
		assertThat(EnglishPlural.fromSingularNoun("bus")).isEqualTo("buses");
	}

	@Test
	public void testFromSingularNoun_givenMarsh_mustReturnMarshes() {
		assertThat(EnglishPlural.fromSingularNoun("marsh")).isEqualTo("marshes");
	}

	@Test
	public void testFromSingularNoun_givenLunch_mustReturnLunches() {
		assertThat(EnglishPlural.fromSingularNoun("lunch")).isEqualTo("lunches");
	}

	@Test
	public void testFromSingularNoun_givenTax_mustReturnTaxes() {
		assertThat(EnglishPlural.fromSingularNoun("tax")).isEqualTo("taxes");
	}

	@Test
	public void testFromSingularNoun_givenBlitz_mustReturnBlitzes() {
		assertThat(EnglishPlural.fromSingularNoun("blitz")).isEqualTo("blitzes");
	}

	@Test
	public void testFromSingularNoun_givenRoof_mustReturnRoofs() {
		assertThat(EnglishPlural.fromSingularNoun("roof")).isEqualTo("roofs");
	}

	@Test
	public void testFromSingularNoun_givenBelief_mustReturnBeliefs() {
		assertThat(EnglishPlural.fromSingularNoun("belief")).isEqualTo("beliefs");
	}

	@Test
	public void testFromSingularNoun_givenChef_mustReturnChefs() {
		assertThat(EnglishPlural.fromSingularNoun("chef")).isEqualTo("chefs");
	}

	@Test
	public void testFromSingularNoun_givenChief_mustReturnChiefs() {
		assertThat(EnglishPlural.fromSingularNoun("chief")).isEqualTo("chiefs");
	}

	@Test
	public void testFromSingularNoun_givenWolf_mustReturnWolves() {
		assertThat(EnglishPlural.fromSingularNoun("wolf")).isEqualTo("wolves");
	}

	@Test
	public void testFromSingularNoun_givenWife_mustReturnWives() {
		assertThat(EnglishPlural.fromSingularNoun("wife")).isEqualTo("wives");
	}

	@Test
	public void testFromSingularNoun_givenCity_mustReturnCities() {
		assertThat(EnglishPlural.fromSingularNoun("city")).isEqualTo("cities");
	}

	@Test
	public void testFromSingularNoun_givenPuppy_mustReturnPuppies() {
		assertThat(EnglishPlural.fromSingularNoun("puppy")).isEqualTo("puppies");
	}

	@Test
	public void testFromSingularNoun_givenPhoto_mustReturnPhotos() {
		assertThat(EnglishPlural.fromSingularNoun("photo")).isEqualTo("photos");
	}

	@Test
	public void testFromSingularNoun_givenPiano_mustReturnPianos() {
		assertThat(EnglishPlural.fromSingularNoun("piano")).isEqualTo("pianos");
	}

	@Test
	public void testFromSingularNoun_givenHalo_mustReturnHalos() {
		assertThat(EnglishPlural.fromSingularNoun("halo")).isEqualTo("halos");
	}

	@Test
	public void testFromSingularNoun_givenPotato_mustReturnPotatoes() {
		assertThat(EnglishPlural.fromSingularNoun("potato")).isEqualTo("potatoes");
	}

	@Test
	public void testFromSingularNoun_givenTomato_mustReturnTomatoes() {
		assertThat(EnglishPlural.fromSingularNoun("tomato")).isEqualTo("tomatoes");
	}

	@Test
	public void testFromSingularNoun_givenAnalysis_mustReturnAnalyses() {
		assertThat(EnglishPlural.fromSingularNoun("analysis")).isEqualTo("analyses");
	}

	@Test
	public void testFromSingularNoun_givenEllipsis_mustReturnEllipses() {
		assertThat(EnglishPlural.fromSingularNoun("ellipsis")).isEqualTo("ellipses");
	}

	@Test
	public void testFromSingularNoun_givenPhenomenon_mustReturnPhenomena() {
		assertThat(EnglishPlural.fromSingularNoun("phenomenon")).isEqualTo("phenomena");
	}

	@Test
	public void testFromSingularNoun_givenCriterion_mustReturnCriteria() {
		assertThat(EnglishPlural.fromSingularNoun("criterion")).isEqualTo("criteria");
	}

	@Test
	public void testFromSingularNoun_givenSheep_mustReturnSheep() {
		assertThat(EnglishPlural.fromSingularNoun("sheep")).isEqualTo("sheep");
	}

	@Test
	public void testFromSingularNoun_givenSeries_mustReturnSeries() {
		assertThat(EnglishPlural.fromSingularNoun("series")).isEqualTo("series");
	}

	@Test
	public void testFromSingularNoun_givenSpecies_mustReturnSpecies() {
		assertThat(EnglishPlural.fromSingularNoun("species")).isEqualTo("species");
	}

	@Test
	public void testFromSingularNoun_givenDeer_mustReturnDeer() {
		assertThat(EnglishPlural.fromSingularNoun("deer")).isEqualTo("deer");
	}

	@Test
	public void testFromSingularNoun_givenFish_mustReturnFish() {
		assertThat(EnglishPlural.fromSingularNoun("fish")).isEqualTo("fish");
	}

	@Test
	public void testFromSingularNoun_givenChild_mustReturnChildren() {
		assertThat(EnglishPlural.fromSingularNoun("child")).isEqualTo("children");
	}

	@Test
	public void testFromSingularNoun_givenGoose_mustReturnGeese() {
		assertThat(EnglishPlural.fromSingularNoun("goose")).isEqualTo("geese");
	}

	@Test
	public void testFromSingularNoun_givenMan_mustReturnMen() {
		assertThat(EnglishPlural.fromSingularNoun("man")).isEqualTo("men");
	}

	@Test
	public void testFromSingularNoun_givenWoman_mustReturnWomen() {
		assertThat(EnglishPlural.fromSingularNoun("woman")).isEqualTo("women");
	}

	@Test
	public void testFromSingularNoun_givenTooth_mustReturnTeeth() {
		assertThat(EnglishPlural.fromSingularNoun("tooth")).isEqualTo("teeth");
	}

	@Test
	public void testFromSingularNoun_givenFoot_mustReturnFeet() {
		assertThat(EnglishPlural.fromSingularNoun("foot")).isEqualTo("feet");
	}

	@Test
	public void testFromSingularNoun_givenMouse_mustReturnMice() {
		assertThat(EnglishPlural.fromSingularNoun("mouse")).isEqualTo("mice");
	}

	@Test
	public void testFromSingularNoun_givenPerson_mustReturnPeople() {
		assertThat(EnglishPlural.fromSingularNoun("person")).isEqualTo("people");
	}

	@Test
	public void testFromSingularNoun_givenCat_mustReturnCats() {
		assertThat(EnglishPlural.fromSingularNoun("cat")).isEqualTo("cats");
	}

	@Test
	public void testFromSingularNoun_givenHouse_mustReturnHouses() {
		assertThat(EnglishPlural.fromSingularNoun("house")).isEqualTo("houses");
	}

	@Test
	public void testFromSingularNoun_givenRay_mustReturnRays() {
		assertThat(EnglishPlural.fromSingularNoun("ray")).isEqualTo("rays");
	}

	@Test
	public void testFromSingularNoun_givenBoy_mustReturnBoys() {
		assertThat(EnglishPlural.fromSingularNoun("boy")).isEqualTo("boys");
	}

}
