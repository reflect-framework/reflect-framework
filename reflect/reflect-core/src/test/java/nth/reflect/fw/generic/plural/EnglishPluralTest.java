package nth.reflect.fw.generic.plural;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EnglishPluralTest {

//	public static void main(String[] args) {
//		List<String> list = new ArrayList();
//		list.add("fez_fezzes");
//		list.add("gas_gasses");
//		list.add("truss_trusses");
//		list.add("bus_buses");
//		list.add("marsh_marshes");
//		list.add("lunch_lunches");
//		list.add("tax_taxes");
//		list.add("blitz_blitzes");
//		list.add("roof_roofs");
//		list.add("belief_beliefs");
//		list.add("chef_chefs");
//		list.add("chief_chiefs");
//		list.add("wolf_wolves");
//		list.add("wife_wives");
//		list.add("city_cities");
//		list.add("puppy_puppies");
//		list.add("photo_photos");
//		list.add("piano_pianos");
//		list.add("halo_halos");
//		list.add("potato_potatoes");
//		list.add("tomato_tomatoes");
//		list.add("cactus_cacti");
//		list.add("focus_foci");
//		list.add("analysis_analyses");
//		list.add("ellipsis_ellipses");
//		list.add("phenomenon_phenomena");
//		list.add("criterion_criteria");
//		list.add("sheep_sheep");
//		list.add("series_series");
//		list.add("species_species");
//		list.add("deer_deer");
//		list.add("fish_fish");
//		list.add("child_children");
//		list.add("goose_geese");
//		list.add("man_men");
//		list.add("woman_women");
//		list.add("tooth_teeth");
//		list.add("foot_feet");
//		list.add("mouse_mice");
//		list.add("person_people");
//		for (String item : list) {
//			String[] items = item.split("_");
//			String singular = items[0];
//			String plural = items[1];
//			System.out.println("	@Test");
//			System.out
//					.println("	public void testFromSingularNoun_given" + StringUtil.convertToCamelCase(singular, true)
//							+ "_mustReturn" + StringUtil.convertToCamelCase(plural, true) + "() {");
//			System.out.println("		assertThat(EnglishPlural.fromSingularNoun(\"" + singular + "\")).isEqualTo(\""
//					+ plural + "\");");
//			System.out.println("	}");
//		}
//	}

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
